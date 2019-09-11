package com.cg.busreservationsystem.service;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;

import com.cg.busreservationsystem.dto.Booking;
import com.cg.busreservationsystem.dto.Bus;
import com.cg.busreservationsystem.dto.Passenger;
import com.cg.busreservationsystem.dto.Transaction;

public interface UserService {
	
	public Bus addBusDetails(Bus bus);
	public Integer removeBusDetails(BigInteger busId);
	public Bus searchBus(BigInteger busId);
	public List<Bus> viewBuses();
	public List<Transaction> getTransactionsByDate(LocalDate date);
	
	public List<Bus> getRunningBuses(LocalDate dateOfJourney, String src, String dest);

	public boolean checkBusTransaction(LocalDate dateOfJourney, Bus bus, Integer noOfPassengers);

	public Booking createBooking(List<Passenger> passengerList, LocalDate dateOfJourney, Bus bus, String modeOfPayment);

	public List<Booking> viewTicketsByDate(LocalDate date);
	public Integer cancelTicket(Booking b);
	public List<Booking> viewTicketList();
	//public Passenger addPassenger(Passenger passenger);
	public List<Passenger> viewPassengers();

}
