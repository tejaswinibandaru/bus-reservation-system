package com.cg.busreservationsystem.dto;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BusTransaction {
	
	private BigInteger transactionId;
	private LocalDate date;
	private Integer availableSeats;
	private ArrayList<Booking> bookings;
	private String ticketStatus;
	private Bus bus;
	//private Integer deleteFlag;
	
	public BusTransaction() {
		
	}

	public BusTransaction(BigInteger transactionId, LocalDate date, Integer availableSeats, ArrayList<Booking> bookings,
			String ticketStatus, Bus bus) {
		super();
		this.transactionId = transactionId;
		this.date = date;
		this.availableSeats = availableSeats;
		this.bookings = bookings;
		this.ticketStatus = ticketStatus;
		this.bus = bus;
		//this.deleteFlag = deleteFlag;
	}

	public BigInteger getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(BigInteger transactionId) {
		this.transactionId = transactionId;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Integer getAvailableSeats() {
		return availableSeats;
	}

	public void setAvailableSeats(Integer availableSeats) {
		this.availableSeats = availableSeats;
	}

	public List<Booking> getBookings() {
		return bookings;
	}

	public void setBookings(List<Booking> list) {
		this.bookings = list;
	}

	public String getTicketStatus() {
		return ticketStatus;
	}

	public void setTicketStatus(String ticketStatus) {
		this.ticketStatus = ticketStatus;
	}

	public Bus getBus() {
		return bus;
	}

	public void setBus(Bus bus) {
		this.bus = bus;
	}

	/*public Integer getDeleteFlag() {
		return deleteFlag;
	}

	public void setDelete_flag(Integer delete_flag) {
		this.deleteFlag = delete_flag;
	}*/

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((availableSeats == null) ? 0 : availableSeats.hashCode());
		result = prime * result + ((bookings == null) ? 0 : bookings.hashCode());
		result = prime * result + ((bus == null) ? 0 : bus.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		//result = prime * result + ((deleteFlag == null) ? 0 : deleteFlag.hashCode());
		result = prime * result + ((ticketStatus == null) ? 0 : ticketStatus.hashCode());
		result = prime * result + ((transactionId == null) ? 0 : transactionId.hashCode());
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
		BusTransaction other = (BusTransaction) obj;
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
		/*if (deleteFlag == null) {
			if (other.deleteFlag != null)
				return false;
		} else if (!deleteFlag.equals(other.deleteFlag))
			return false;*/
		if (ticketStatus == null) {
			if (other.ticketStatus != null)
				return false;
		} else if (!ticketStatus.equals(other.ticketStatus))
			return false;
		if (transactionId == null) {
			if (other.transactionId != null)
				return false;
		} else if (!transactionId.equals(other.transactionId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "BusTransaction [transactionId=" + transactionId + ", date=" + date + ", availableSeats="
				+ availableSeats + ", bookings=" + bookings + ", ticketStatus=" + ticketStatus + ", bus=" + bus
				+ "]";
	}
	
	
	
}
