package com.cg.busreservationsystem.service;

import java.math.BigInteger;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import com.cg.busreservationsystem.dao.BookingDao;
import com.cg.busreservationsystem.dao.BookingDaoImpl;
import com.cg.busreservationsystem.dao.BusDao;
import com.cg.busreservationsystem.dao.BusDaoImpl;
import com.cg.busreservationsystem.dao.TransactionDaoImpl;
import com.cg.busreservationsystem.dto.Booking;
import com.cg.busreservationsystem.dto.Bus;
import com.cg.busreservationsystem.dto.Passenger;
import com.cg.busreservationsystem.dto.Transaction;
import com.cg.busreservationsystem.exception.BusException;

public class UserServiceImpl implements UserService {

	public BusDao busDao = new BusDaoImpl();
	public BookingDao bookingDao = new BookingDaoImpl();
	public TransactionDaoImpl transactionDao = new TransactionDaoImpl();

	@Override
	public Bus addBusDetails(Bus bus) {
		// TODO Auto-generated method stub
		return busDao.saveBus(bus);
	}

	@Override
	public Integer removeBusDetails(BigInteger busId) {
		// TODO Auto-generated method stub
		return busDao.removeBus(busId);
	}

	@Override
	public Bus searchBus(BigInteger busId) {
		// TODO Auto-generated method stub
		return busDao.findBusById(busId);
	}

	@Override
	public List<Bus> viewBuses() {
		// TODO Auto-generated method stub
		return busDao.findAllBuses();
	}

	@Override
	public List<Transaction> getTransactionsByDate(LocalDate date) {
		// TODO Auto-generated method stub
		return transactionDao.findTransactionsByDate(date);
	}

	public static void validateBusType(int busType) {
		if(busType < 0 || busType > 1) {
			throw new BusException("Wrong bus type");
		}
	}

	public static int checkNumberInput() throws InputMismatchException{
		Scanner sc=new Scanner(System.in);
		try {
			return sc.nextInt();
		}catch (InputMismatchException e) {
			// TODO: handle exception
			throw new InputMismatchException("Wrong input type");
		}

	}


	public List<Bus> getRunningBuses(LocalDate dateOfJourney, String src, String dest) {	//change method [parameters
		// TODO Auto-generated method stub
		/*Steps :
			1. Input Date, Src, Destination
			2. get dayOfJourney
			3. check in busList
			4. return buses
			5. select buses
			6. find transaction
			7. input number of passengers
			8. check seatsAvailable
			8. if yes, then proceed to add passengers, add to list<passenger>
			9. create booking object by adding remaining details.
			10. add to list<booking:>
		 */
		//adminServ = (AdminServiceImpl) adm;
		List<Bus> busList = new ArrayList<Bus>();
		Set<DayOfWeek> days;
		DayOfWeek dayOfWeek = dateOfJourney.getDayOfWeek();
		System.out.println(dayOfWeek);
		System.out.println(viewBuses());
		for (Bus bus : viewBuses()){
			days = bus.getDayOfJourney();
			if(days.contains(dayOfWeek)) {
				if((bus.getSource().equalsIgnoreCase(src)) && bus.getDestination().equalsIgnoreCase(dest))
					busList.add(bus);
			}

		}
		return busList;
	}



	@Override
	public boolean checkBusTransaction(LocalDate dateOfJourney, Bus bus, Integer noOfPassengers) {
		// TODO Auto-generated method stub
		Transaction trans;
		List<Transaction> listTransaction = transactionDao.getTransactionList();
		if(listTransaction.isEmpty()) {
			System.out.println("transaction list is empty");
			trans = new Transaction();
			trans.setBus(bus);
			trans.setDate(dateOfJourney);
			transactionDao.getTransactionList().add(trans);
			//System.out.println(trans.getBus());

			System.out.println(trans.getAvailableSeats()+" is num of available seats");
			if(trans.getAvailableSeats()>=noOfPassengers)
				return true;
			else
				return false;
		}
		else {
			System.out.println("transaction list is not empty");
			for (Transaction transaction : listTransaction) {
				for (Booking booking : transaction.getBookings()) {
					System.out.println("booking id is found to be"+booking.getBookingId());
					if(booking.getBus().equals(bus))
						if(booking.getDateOfJourney().equals(dateOfJourney))
							if(transaction.getAvailableSeats()>=noOfPassengers)
								return true;
				}
			}

		}
		return false;
	}


	public Booking createBooking(List<Passenger> passengerList, LocalDate dateOfJourney, Bus bus, String modeOfPayment) {

		Booking b = new Booking(dateOfJourney, bus, passengerList, modeOfPayment);
		List<Transaction> listTransactions = (transactionDao).getTransactionList();
		for (Transaction transaction : listTransactions) {
			if(transaction.getDate().equals(dateOfJourney))
			{
				if(transaction.getBus().equals(bus))
				{
					
					
					System.out.println(listTransactions);
					
					
					System.out.println(listTransactions.indexOf(transaction));
					int index=(transactionDao.getTransactionList().indexOf(transaction));
					Transaction t = transactionDao.getTransactionList().get(index);
					 
					ArrayList<Booking> currentBooking =t.getBookings();
					boolean booked= currentBooking.add(b);
					System.out.println(currentBooking);
					System.out.println(bookingDao.saveBooking(b));
					System.out.println(booked);
				}
			}
		}
		return b;

	}


	@Override
	public List<Booking> viewTicketsByDate(LocalDate date) {
		// TODO Auto-generated method stub
		List<Booking> listBooking = new ArrayList<Booking>();
		for (Booking booking : bookingDao.findAllBookings()) {
			if(booking.getDateOfJourney().equals(date))
				listBooking.add(booking);

		}
		return listBooking;
	}

	@Override
	public Integer cancelTicket(Booking b) {
		// TODO Auto-generated method stub
		boolean cancel =bookingDao.findAllBookings().remove(b);
		if(cancel) return 1;
		else return 0;
	}

	@Override
	public List<Booking> viewTicketList() {
		// TODO Auto-generated method stub
		return bookingDao.findAllBookings();
	}

	/*	@Override
	public Passenger addPassenger(Passenger passenger) {
		// TODO Auto-generated method stub

		return null;
	}*/

	@Override
	public List<Passenger> viewPassengers() {
		// TODO Auto-generated method stub
		return bookingDao.findAllPassengers();
	}


}
