package com.cg.busreservationsystem.dao;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import com.cg.busreservationsystem.dto.Booking;
import com.cg.busreservationsystem.dto.Passenger;

public class BookingDaoImpl implements BookingDao{
	
	List<Booking> bookingsList=new ArrayList<Booking>();
	List<Passenger> passengersList=new ArrayList<Passenger>();

	@Override
	public Booking saveBooking(Booking booking) {
		// TODO Auto-generated method stub
		for(Booking book:bookingsList) {
			if(book.getBookingId()==booking.getBookingId()) {
				bookingsList.set(bookingsList.indexOf(book), booking);
			}
		}
		return booking;
	}

	@Override
	public Integer removeBooking(BigInteger bookingId) {
		// TODO Auto-generated method stub
		for(Booking book:bookingsList) {
			if(bookingId==book.getBookingId()) {
				bookingsList.remove(book);
				return 1;
			}
		}
		return 0;
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
			if(bookingId==book.getBookingId()) {
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
	

}
