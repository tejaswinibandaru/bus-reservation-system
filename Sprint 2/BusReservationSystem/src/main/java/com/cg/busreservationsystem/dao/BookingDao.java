package com.cg.busreservationsystem.dao;

import java.util.List;

import com.cg.busreservationsystem.dto.Booking;
import com.cg.busreservationsystem.dto.Passenger;;

public interface BookingDao {
	
	public Booking saveBooking(Booking booking);
	public Integer removeBooking(Integer bookingId);
	public List<Booking> findAllBookings();
	public Booking findBookingById(Integer bookingId);
	public Passenger savePassenger(Passenger passenger);
	public List<Passenger> findAllPassengers();
	public Passenger findPassengerByName(String pname);

}
