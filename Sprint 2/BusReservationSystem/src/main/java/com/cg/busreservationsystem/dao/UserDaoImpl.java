package com.cg.busreservationsystem.dao;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.cg.busreservationsystem.dto.Booking;
import com.cg.busreservationsystem.dto.Bus;
import com.cg.busreservationsystem.dto.Passenger;
import com.cg.busreservationsystem.dto.Transaction;

public class UserDaoImpl implements UserDao {
	
	private List<Transaction> transactionList=new ArrayList<Transaction>();
	private List<Bus> busList=new ArrayList<Bus>();

	List<Booking> bookingsList=new ArrayList<Booking>();
	List<Passenger> passengersList=new ArrayList<Passenger>();

	@Override
	public Booking saveBooking(Booking booking) {
		// TODO Auto-generated method stub
		bookingsList.add(booking);
		return booking;
	}

	@Override
	public Integer removeBooking(BigInteger bookingId) {
		// TODO Auto-generated method stub
		Booking b=this.findBookingById(bookingId);
		if(b==null) {
			return 0;
		}
		bookingsList.remove(b);
		return 1;
	}

	@Override
	public List<Booking> findAllBookings() {
		// TODO Auto-generated method stub
		return bookingsList;
	}

	@Override
	public Booking findBookingById(BigInteger bookingId) {
		// TODO Auto-generated method stub
		for(Booking book:bookingsList) {
			if(book.getBookingId().equals(bookingId)) {
				return book;
			}
		}
		return null;
	}

	@Override
	public Passenger savePassenger(Passenger passenger) {
		// TODO Auto-generated method stub
		passengersList.add(passenger);
		return passenger;
	}

	@Override
	public List<Passenger> findAllPassengers() {
		// TODO Auto-generated method stub
		return passengersList;
	}

	@Override
	public Passenger findPassengerByName(String pname) {
		// TODO Auto-generated method stub
		for(Passenger p:passengersList) {
			if(pname.equalsIgnoreCase(p.getPassengerName())) {
				return p;
			}
		}
		return null;
	}

	@Override
	public Bus saveBus(Bus bus){
		// TODO Auto-generated method stub
		busList.add(bus);
		return bus;
	}

	@Override
	public Integer removeBus(BigInteger busId) {
		// TODO Auto-generated method stub
		Bus b=this.findBusById(busId);
		if(b==null) {
			return 0;
		}
		busList.remove(b);
		return 1;
	}

	@Override
	public List<Bus> findAllBuses() {
		// TODO Auto-generated method stub
		return busList;
	}

	@Override
	public Bus findBusById(BigInteger busId) {
		// TODO Auto-generated method stub
		
		for(Bus b:busList) {
			if(busId.equals(b.getBusId())) {
				return b;
			}
		}
		return null;
	}

	public List<Transaction> getTransactionList() {
		return transactionList;
	}

	public void setTransactionList(List<Transaction> transactionList) {
		this.transactionList = transactionList;
	}

	@Override
	public Transaction saveTransaction(Transaction transaction) {
		// TODO Auto-generated method stub
		transactionList.add(transaction);
		return transaction;
	}

	@Override
	public Integer removeTransaction(Bus bus) {
		// TODO Auto-generated method stub
		for(Transaction t:transactionList) {
			if(bus.equals(t.getBus())) {
				transactionList.remove(t);
				return 1;
			}
		}
		return 0;
	}

	
	/*@Override
	 * public List<Transaction> findAllTransactions() { // TODO Auto-generated
	 * method stub return transactionList; }
	 */

	@Override
	public List<Transaction> findTransactionsByDate(LocalDate date) {
		// TODO Auto-generated method stub
		List<Transaction> transactionsByDate=new ArrayList<Transaction>();
		for(Transaction t:transactionList) {
			if(date.equals(t.getDate())) {
				transactionsByDate.add(t);
			}
		}
		
		return transactionsByDate;
	}

}
