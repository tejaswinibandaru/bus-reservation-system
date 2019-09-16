package com.cg.busreservationsystem.dto;

import java.math.BigInteger;

public class Passenger {
	private BigInteger passengerId;
	private BigInteger bookingId;
	private String passengerName;
	private Integer passengerAge;
	private Character passengerGender;
	//private Integer deleteFlag;
	
	public Passenger() {
		
	}

	public Passenger(BigInteger passengerId, String passengerName, Integer passengerAge, Character passengerGender) {
		super();
		this.passengerId = passengerId;
		this.passengerName = passengerName;
		this.passengerAge = passengerAge;
		this.passengerGender = passengerGender;
	}

	public BigInteger getPassengerId() {
		return passengerId;
	}

	public void setPassengerId(BigInteger passengerId) {
		this.passengerId = passengerId;
	}

	public String getPassengerName() {
		return passengerName;
	}

	public void setPassengerName(String passengerName) {
		this.passengerName = passengerName;
	}

	public Integer getPassengerAge() {
		return passengerAge;
	}

	public void setPassengerAge(Integer passengerAge) {
		this.passengerAge = passengerAge;
	}

	public Character getPassengerGender() {
		return passengerGender;
	}

	public void setPassengerGender(Character passengerGender) {
		this.passengerGender = passengerGender;
	}

	/*
	 * public Integer getDeleteFlag() { return deleteFlag; }
	 * 
	 * public void setDeleteFlag(Integer deleteFlag) { this.deleteFlag = deleteFlag;
	 * }
	 */

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		//result = prime * result + ((deleteFlag == null) ? 0 : deleteFlag.hashCode());
		result = prime * result + ((passengerAge == null) ? 0 : passengerAge.hashCode());
		result = prime * result + ((passengerGender == null) ? 0 : passengerGender.hashCode());
		result = prime * result + ((passengerId == null) ? 0 : passengerId.hashCode());
		result = prime * result + ((passengerName == null) ? 0 : passengerName.hashCode());
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
		Passenger other = (Passenger) obj;
		/*
		 * if (deleteFlag == null) { if (other.deleteFlag != null) return false; } else
		 * if (!deleteFlag.equals(other.deleteFlag)) return false;
		 */
		if (passengerAge == null) {
			if (other.passengerAge != null)
				return false;
		} else if (!passengerAge.equals(other.passengerAge))
			return false;
		if (passengerGender == null) {
			if (other.passengerGender != null)
				return false;
		} else if (!passengerGender.equals(other.passengerGender))
			return false;
		if (passengerId == null) {
			if (other.passengerId != null)
				return false;
		} else if (!passengerId.equals(other.passengerId))
			return false;
		if (passengerName == null) {
			if (other.passengerName != null)
				return false;
		} else if (!passengerName.equals(other.passengerName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Passenger [passengerId=" + passengerId + ", passengerName=" + passengerName + ", passengerAge="
				+ passengerAge + ", passengerGender=" + passengerGender + "]";
	}

	public BigInteger getBookingId() {
		return bookingId;
	}

	public void setBookingId(BigInteger bookingId) {
		this.bookingId = bookingId;
	}

	
}
