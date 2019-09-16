package com.cg.busreservationsystem.service;

import java.math.BigInteger;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import com.cg.busreservationsystem.dao.UserDao;
import com.cg.busreservationsystem.dao.UserDaoImpl;
import com.cg.busreservationsystem.dto.Booking;
import com.cg.busreservationsystem.dto.Bus;
import com.cg.busreservationsystem.dto.BusTransaction;
import com.cg.busreservationsystem.dto.Passenger;
import com.cg.busreservationsystem.exception.BusException;

public class UserServiceImpl implements UserService {
	
	public UserDao userDao = new UserDaoImpl();

	@Override
	public Bus addBusDetails(Bus bus) {
		// TODO Auto-generated method stub
		return userDao.saveBus(bus);
	}

	@Override
	public Integer removeBusDetails(BigInteger busId) {
		// TODO Auto-generated method stub
		return userDao.removeBus(busId);
	}

	@Override
	public Bus searchBus(BigInteger busId) {
		// TODO Auto-generated method stub
		return userDao.findBusById(busId);
	}

	@Override
	public List<Bus> viewBuses() {
		// TODO Auto-generated method stub
		return userDao.findAllBuses();
	}

	@Override
	public List<BusTransaction> getTransactionsByDate(LocalDate date) {
		// TODO Auto-generated method stub
		return userDao.findTransactionsByDate(date);
	}

	public static void validateBusType(int busType) {
		if(busType < 0 || busType > 1) {
			throw new BusException("Wrong bus type");
		}
	}
	
	public static void validateBusClass(int busClass) {
		if(busClass < 0 || busClass > 1) {
			throw new BusException("Wrong bus class");
		}
	}

	public static void validateTravel(String source, String destination) throws BusException{
		if(source.equals(destination)) {
			throw new BusException("source and destination cannot be same");
		}
	}
	public static int checkNumberInput() throws InputMismatchException{
		Scanner sc=new Scanner(System.in);
		try {
			return sc.nextInt();
		}catch (InputMismatchException e) {
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
		BusTransaction trans;
		List<BusTransaction> listTransaction = userDao.getTransactionList();
		if(listTransaction.isEmpty()) {
			System.out.println("transaction list is empty");
			trans = new BusTransaction();
			trans.setBus(bus);
			trans.setDate(dateOfJourney);
			userDao.getTransactionList().add(trans);
			//System.out.println(trans.getBus());

			System.out.println(trans.getAvailableSeats()+" is num of available seats");
			if(trans.getAvailableSeats()>=noOfPassengers)
				return true;
			else
				return false;
		}
		else {
			System.out.println("transaction list is not empty");
			for (BusTransaction busTransaction : listTransaction) {
				/*
				 * for (Booking booking : busTransaction.getBookings()) {
				 * System.out.println("booking id is found to be"+booking.getBookingId());
				 * if(booking.getBus().equals(bus))
				 * if(booking.getDateOfJourney().equals(dateOfJourney))
				 * if(busTransaction.getAvailableSeats()>=noOfPassengers) return true; }
				 */
				if(busTransaction.getDate().equals(dateOfJourney)) {
					if(busTransaction.getBus().equals(bus)) {
						if(busTransaction.getAvailableSeats()>=noOfPassengers) {
							return true;
						}
					}
				}
			}

		}
		return false;
	}


	public Booking createBooking(List<Passenger> passengerList, LocalDate dateOfJourney, Bus bus, String modeOfPayment) {

		Booking booking = new Booking(dateOfJourney, bus, passengerList, modeOfPayment);
		List<BusTransaction> listTransactions = userDao.getTransactionList();
		for (BusTransaction busTransaction : listTransactions) {
			if(busTransaction.getDate().equals(dateOfJourney))
			{
				if(busTransaction.getBus().equals(bus))
				{
					/*
					 * int index=(transactionDao.getTransactionList().indexOf(transaction)); boolean
					 * booked
					 * =(((transactionDao.getTransactionList()).get(index)).getBookings()).add(b);
					 * System.out.println(bookingDao.saveBooking(b)); System.out.println(booked);
					 */

					
					System.out.println(listTransactions);
					
					System.out.println(listTransactions.indexOf(busTransaction));
					int index=(userDao.getTransactionList().indexOf(busTransaction));
					BusTransaction currentTransaction = listTransactions.get(index);
					 
					List<Booking> currentBooking =currentTransaction.getBookings();
					if(currentBooking==null) {
						currentBooking=new ArrayList<Booking>();
						currentBooking.add(booking);
					}
					else {
						currentBooking.add(booking);
					}
					System.out.println(currentBooking);
					System.out.println(userDao.saveBooking(booking));
					//System.out.println(booked);

				}
			}
		}
		return booking;

	}


	@Override
	public List<Booking> viewTicketsByDate(LocalDate date) {
		// TODO Auto-generated method stub
		List<Booking> listBooking = new ArrayList<Booking>();
		for (Booking booking : userDao.findAllBookings()) {
			if(booking.getDateOfJourney().equals(date))
				listBooking.add(booking);

		}
		return listBooking;
	}

	@Override
	public Integer cancelTicket(Booking b) {
		// TODO Auto-generated method stub
		boolean cancel =userDao.findAllBookings().remove(b);
		if(cancel) return 1;
		else return 0;
	}

	@Override
	public List<Booking> viewTicketList() {
		// TODO Auto-generated method stub
		return userDao.findAllBookings();
	}

	/*	@Override
	public Passenger addPassenger(Passenger passenger) {
		// TODO Auto-generated method stub

		return null;
	}*/

	@Override
	public List<Passenger> viewPassengers() {
		// TODO Auto-generated method stub
		return userDao.findAllPassengers();
	}


}
