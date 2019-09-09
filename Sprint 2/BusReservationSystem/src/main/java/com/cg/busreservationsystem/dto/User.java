
package com.cg.busreservationsystem.dto;

import java.math.BigInteger;
import java.util.Set;



public class User {
	private BigInteger userId;
	private String userName;
	private String password;
	private Character userType;
	private String email;
	private BigInteger phoneNumber;
	private Set<Booking> bookingsList;
	private Set<Bus> busList;
	private Set<Transaction> transactionsList;
	
	public User() {
		
	}

	public User(BigInteger userId, String userName, String password, Character userType, String email,
			BigInteger phoneNumber, Set<Booking> bookingsList, Set<Bus> busList, Set<Transaction> transactionsList) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.password = password;
		this.userType = userType;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.bookingsList = bookingsList;
		this.busList = busList;
		this.transactionsList = transactionsList;
	}

	public BigInteger getUserId() {
		return userId;
	}

	public void setUserId(BigInteger userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Character getUserType() {
		return userType;
	}

	public void setUserType(Character userType) {
		this.userType = userType;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public BigInteger getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(BigInteger phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Set<Booking> getBookingsList() {
		return bookingsList;
	}

	public void setBookingsList(Set<Booking> bookingsList) {
		this.bookingsList = bookingsList;
	}

	public Set<Bus> getBusList() {
		return busList;
	}

	public void setBusList(Set<Bus> busList) {
		this.busList = busList;
	}

	public Set<Transaction> getTransactionsList() {
		return transactionsList;
	}

	public void setTransactionsList(Set<Transaction> transactionsList) {
		this.transactionsList = transactionsList;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bookingsList == null) ? 0 : bookingsList.hashCode());
		result = prime * result + ((busList == null) ? 0 : busList.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((phoneNumber == null) ? 0 : phoneNumber.hashCode());
		result = prime * result + ((transactionsList == null) ? 0 : transactionsList.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
		result = prime * result + ((userType == null) ? 0 : userType.hashCode());
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
		User other = (User) obj;
		if (bookingsList == null) {
			if (other.bookingsList != null)
				return false;
		} else if (!bookingsList.equals(other.bookingsList))
			return false;
		if (busList == null) {
			if (other.busList != null)
				return false;
		} else if (!busList.equals(other.busList))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (phoneNumber == null) {
			if (other.phoneNumber != null)
				return false;
		} else if (!phoneNumber.equals(other.phoneNumber))
			return false;
		if (transactionsList == null) {
			if (other.transactionsList != null)
				return false;
		} else if (!transactionsList.equals(other.transactionsList))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		if (userType == null) {
			if (other.userType != null)
				return false;
		} else if (!userType.equals(other.userType))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", password=" + password + ", userType=" + userType
				+ ", email=" + email + ", phoneNumber=" + phoneNumber + ", bookingsList=" + bookingsList + ", busList="
				+ busList + ", transactionsList=" + transactionsList + "]";
	}
	
	
}
