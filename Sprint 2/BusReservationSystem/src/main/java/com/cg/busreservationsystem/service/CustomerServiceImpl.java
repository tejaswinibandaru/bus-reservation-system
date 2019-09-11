package com.cg.busreservationsystem.service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.cg.busreservationsystem.dao.BookingDao;
import com.cg.busreservationsystem.dao.BookingDaoImpl;
import com.cg.busreservationsystem.dao.BusDao;
import com.cg.busreservationsystem.dao.BusDaoImpl;
import com.cg.busreservationsystem.dao.TransactionDao;
import com.cg.busreservationsystem.dao.TransactionDaoImpl;
import com.cg.busreservationsystem.dto.Booking;
import com.cg.busreservationsystem.dto.Bus;
import com.cg.busreservationsystem.dto.Passenger;
import com.cg.busreservationsystem.dto.Transaction;

public class CustomerServiceImpl implements CustomerService{

	//BusDao busDao = new BusDaoImpl();
	BookingDao bookingDao = new BookingDaoImpl();
	//TransactionDao transactionDao = new TransactionDaoImpl();
	AdminServiceImpl adminServ;// = new AdminServiceImpl();
	@Override
	public List<Bus> getRunningBuses(AdminServiceImpl adm, LocalDate dateOfJourney, String src, String dest) {	//change method [parameters
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
		adminServ = adm;
		List<Bus> busList = new ArrayList<Bus>();
		Set<DayOfWeek> days;
		DayOfWeek d = dateOfJourney.getDayOfWeek();
		System.out.println(d);
		System.out.println(((adminServ.busDao).findAllBuses()));
		for (Bus bus : ((adminServ.busDao).findAllBuses())) {
			days = bus.getDayOfJourney();
			if(days.contains(d)) {
				if((bus.getSource().equalsIgnoreCase(src)) && bus.getDestination().equalsIgnoreCase(dest))
					busList.add(bus);
			}
			
		}
		return busList;
	}

	

	@Override
	public boolean checkBusTransaction(LocalDate dateOfJourney, Bus bus, Integer noOfPassengers) {
		// TODO Auto-generated method stub
		List<Transaction> listTransaction = (adminServ.transactionDao).getTransactionList();
		for (Transaction transaction : listTransaction) {
			for (Booking booking : transaction.getBookings()) {
				if(booking.getBus().equals(bus))
					if(booking.getDateOfJourney().equals(dateOfJourney))
						if(transaction.getAvailableSeats()>=noOfPassengers)
							return true;
			}
		}
		return false;
	}

	
	public Booking createBooking(List<Passenger> passengerList, LocalDate dateOfJourney, Bus bus, String modeOfPayment) {
		
		Booking b = new Booking(dateOfJourney, bus, passengerList, modeOfPayment);
		List<Transaction> listTransactions = (adminServ.transactionDao).getTransactionList();
		for (Transaction transaction : listTransactions) {
			if(transaction.getDate().equals(dateOfJourney))
			{
				transaction.getBookings().add(b);
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
