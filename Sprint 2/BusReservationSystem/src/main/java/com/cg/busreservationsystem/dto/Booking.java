package com.cg.busreservationsystem.dto;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Booking {
	
	private BigInteger bookingId;
	private BigInteger userId;
	private BigInteger transactionId;
	private LocalDate dateOfJourney;
	private Bus bus;
	private List<Passenger> passengers;
	private String modeOfPayment;
	private Double totalCost;
	private String bookingStatus;
	//private Integer deleteFlag;				//object shouldnt contain flag unnecessarily. it is just for DB
	
	public String getBookingStatus() {
		return bookingStatus;
	}

	public void setBookingStatus(String bookingStatus) {
		this.bookingStatus = bookingStatus;
	}

	/*
	 * public Integer getDeleteFlag() { return deleteFlag; }
	 * 
	 * public void setDeleteFlag(Integer deleteFlag) { this.deleteFlag = deleteFlag;
	 * }
	 */


	static BigInteger idCounter= BigInteger.valueOf(1000L);
	
	public Booking() {
		
	}

	public Booking(LocalDate dateOfJourney, Bus bus, List<Passenger> passengerList,String modeOfPayment) {
		super();
		idCounter.add(BigInteger.valueOf(1L));
		
		this.bookingId = idCounter;
		this.dateOfJourney = dateOfJourney;
		this.bus = bus;
		this.passengers = passengerList;
		this.modeOfPayment = modeOfPayment;
		this.totalCost = costCalc();
	}

	public BigInteger getBookingId() {
		return bookingId;
	}

	public void setBookingId(BigInteger bookingId) {
		this.bookingId = bookingId;
	}

	public BigInteger getUserId() {
		return userId;
	}

	public void setUserId(BigInteger userId) {
		this.userId = userId;
	}

	public BigInteger getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(BigInteger transactionId) {
		this.transactionId = transactionId;
	}

	public LocalDate getDateOfJourney() {
		return dateOfJourney;
	}

	public void setDateOfJourney(LocalDate dateOfJourney) {
		this.dateOfJourney = dateOfJourney;
	}

	public Bus getBus() {
		return bus;
	}

	public void setBus(Bus bus) {
		this.bus = bus;
	}

	public List<Passenger> getPassengers() {
		return passengers;
	}

	public void setPassengers(List<Passenger> passengers) {
		this.passengers = passengers;
	}

	public String getModeOfPayment() {
		return modeOfPayment;
	}

	public void setModeOfPayment(String modeOfPayment) {
		this.modeOfPayment = modeOfPayment;
	}

	public Double getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(Double totalCost) {
		this.totalCost = totalCost;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bookingId == null) ? 0 : bookingId.hashCode());
		result = prime * result + ((bookingStatus == null) ? 0 : bookingStatus.hashCode());
		result = prime * result + ((bus == null) ? 0 : bus.hashCode());
		result = prime * result + ((dateOfJourney == null) ? 0 : dateOfJourney.hashCode());
		//result = prime * result + ((deleteFlag == null) ? 0 : deleteFlag.hashCode());
		result = prime * result + ((modeOfPayment == null) ? 0 : modeOfPayment.hashCode());
		result = prime * result + ((passengers == null) ? 0 : passengers.hashCode());
		result = prime * result + ((totalCost == null) ? 0 : totalCost.hashCode());
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
		Booking other = (Booking) obj;
		if (bookingId == null) {
			if (other.bookingId != null)
				return false;
		} else if (!bookingId.equals(other.bookingId))
			return false;
		if (bookingStatus == null) {
			if (other.bookingStatus != null)
				return false;
		} else if (!bookingStatus.equals(other.bookingStatus))
			return false;
		if (bus == null) {
			if (other.bus != null)
				return false;
		} else if (!bus.equals(other.bus))
			return false;
		if (dateOfJourney == null) {
			if (other.dateOfJourney != null)
				return false;
		} else if (!dateOfJourney.equals(other.dateOfJourney))
			return false;
		/*
		 * if (deleteFlag == null) { if (other.deleteFlag != null) return false; } else
		 * if (!deleteFlag.equals(other.deleteFlag)) return false;
		 */
		if (modeOfPayment == null) {
			if (other.modeOfPayment != null)
				return false;
		} else if (!modeOfPayment.equals(other.modeOfPayment))
			return false;
		if (passengers == null) {
			if (other.passengers != null)
				return false;
		} else if (!passengers.equals(other.passengers))
			return false;
		if (totalCost == null) {
			if (other.totalCost != null)
				return false;
		} else if (!totalCost.equals(other.totalCost))
			return false;
		return true;
	}

	double costCalc() {
		double cost = (bus.getCost())*(passengers.size());
		return cost;
	}

	@Override
	public String toString() {
		return "Booking [bookingId=" + bookingId + ", dateOfJourney=" + dateOfJourney + ", bus=" + bus + ", passengers="
				+ passengers + ", modeOfPayment=" + modeOfPayment + ", totalCost=" + totalCost + ", bookingStatus="
				+ bookingStatus + "]";
	}
	
	
	
}
