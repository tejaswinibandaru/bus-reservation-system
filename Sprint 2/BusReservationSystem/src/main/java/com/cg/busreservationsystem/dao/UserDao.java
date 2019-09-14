package com.cg.busreservationsystem.dao;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;

import com.cg.busreservationsystem.dto.Booking;
import com.cg.busreservationsystem.dto.Bus;
import com.cg.busreservationsystem.dto.Passenger;
import com.cg.busreservationsystem.dto.Transaction;

public interface UserDao {
	
	public Booking saveBooking(Booking booking);
	public Integer removeBooking(BigInteger bookingId);
	public List<Booking> findAllBookings();
	public Booking findBookingById(BigInteger bookingId);
	public Passenger savePassenger(Passenger passenger);
	public List<Passenger> findAllPassengers();
	public Passenger findPassengerByName(String pname);
	
	public Bus saveBus(Bus bus);
	public Integer removeBus(BigInteger busId);
	public List<Bus> findAllBuses();
	public Bus findBusById(BigInteger busId);
	
	public Transaction saveTransaction(Transaction transaction);
	public Integer removeTransaction(Bus bus);
	public List<Transaction> findAllTransactions();
	public List<Transaction> findTransactionsByDate(LocalDate date);

}
