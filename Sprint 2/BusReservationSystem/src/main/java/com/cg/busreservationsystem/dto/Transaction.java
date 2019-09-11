package com.cg.busreservationsystem.dto;

import java.time.LocalDate;
import java.util.ArrayList;

public class Transaction {
	
	private LocalDate date;
	private Integer availableSeats;
	private ArrayList<Booking> bookings;
	private Bus bus;
	
	public Transaction() {
		
	}

	public Transaction(LocalDate date, Integer availableSeats, ArrayList<Booking> bookings, Bus bus) {
		super();
		this.date = date;
		this.availableSeats = availableSeats;
		this.bookings = bookings;
		this.bus = bus;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Integer getAvailableSeats() {
		availableSeats=bus.getNoOfSeats();
		int occupied=0;
		for (Booking booking : bookings) {
			occupied+=booking.getPassengers().size();
			
		}
		availableSeats=availableSeats-occupied;
		return availableSeats;
	}

	public void setAvailableSeats(Integer availableSeats) {
		this.availableSeats = availableSeats;
	}

	public ArrayList<Booking> getBookings() {
		return bookings;
	}

	public void setBookings(ArrayList<Booking> bookings) {
		this.bookings = bookings;
	}

	public Bus getBus() {
		return bus;
	}

	public void setBus(Bus bus) {
		this.bus = bus;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((availableSeats == null) ? 0 : availableSeats.hashCode());
		result = prime * result + ((bookings == null) ? 0 : bookings.hashCode());
		result = prime * result + ((bus == null) ? 0 : bus.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Transaction other = (Transaction) obj;
		if (availableSeats == null) {
			if (other.availableSeats != null)
				return false;
		} else if (!availableSeats.equals(other.availableSeats))
			return false;
		if (bookings == null) {
			if (other.bookings != null)
				return false;
		} else if (!bookings.equals(other.bookings))
			return false;
		if (bus == null) {
			if (other.bus != null)
				return false;
		} else if (!bus.equals(other.bus))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Transaction [date=" + date + ", availableSeats=" + availableSeats + ", bookings=" + bookings + ", bus="
				+ bus + "]";
	}
	
	

}
