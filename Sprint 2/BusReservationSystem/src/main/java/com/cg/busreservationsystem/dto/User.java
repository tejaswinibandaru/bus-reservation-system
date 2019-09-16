
package com.cg.busreservationsystem.dto;

import java.math.BigInteger;
import java.util.Set;



public class User {
	private BigInteger userId;
	private String username;
	private String pass;
	private Character userType;
	private String email;
	private BigInteger phoneNumber;
	private Set<Booking> bookingsList;
	private Set<Bus> busList;
	private Set<BusTransaction> transactionsList;
	//private Integer deleteFlag;
	
	public User() {
		 userId=BigInteger.valueOf(0);
	 username="";
		 pass="";
		 userType=null;
		 email="";
		 phoneNumber=BigInteger.valueOf(0);
		 bookingsList=null;
	    transactionsList=null;
	}

	public User(BigInteger userId, String username, String password, Character userType, String email,
			BigInteger phoneNumber, Set<Booking> bookingsList, Set<Bus> busList, Set<BusTransaction> transactionsList) {
		super();
		this.userId = userId;
		this.username = username;
		this.pass = password;
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String userName) {
		this.username = userName;
	}

	public String getPassword() {
		return pass;
	}

	public void setPassword(String password) {
		this.pass = password;
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

	public Set<BusTransaction> getTransactionsList() {
		return transactionsList;
	}

	public void setTransactionsList(Set<BusTransaction> transactionsList) {
		this.transactionsList = transactionsList;
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
		result = prime * result + ((bookingsList == null) ? 0 : bookingsList.hashCode());
		result = prime * result + ((busList == null) ? 0 : busList.hashCode());
		//result = prime * result + ((deleteFlag == null) ? 0 : deleteFlag.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((pass == null) ? 0 : pass.hashCode());
		result = prime * result + ((phoneNumber == null) ? 0 : phoneNumber.hashCode());
		result = prime * result + ((transactionsList == null) ? 0 : transactionsList.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		result = prime * result + ((userType == null) ? 0 : userType.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) throws NullPointerException {
		try { if (obj==null)
				return false;
		else {
	if (this.hashCode()==(obj.hashCode()))
			return true;
		}
		
		
		/*if (this == obj)
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
		/*
		 * if (deleteFlag == null) { if (other.deleteFlag != null) return false; } else
		 * if (!deleteFlag.equals(other.deleteFlag)) return false;
		 */
	/*	if (email == null) {
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
		if (userType == null) {
			if (other.userType != null)
				return false;
		} else if (!userType.equals(other.userType))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;*/
		
	} catch(RuntimeException e){System.out.println("Exception:" +e);
	}
		return true;
	}
	
	

	@Override
	public String toString() {
		return "User [userId=" + userId + ", username=" + username + ", password=" + pass + ", userType=" + userType
				+ ", email=" + email + ", phoneNumber=" + phoneNumber + ", bookingsList=" + bookingsList + ", busList="
				+ busList + ", transactionsList=" + transactionsList + "]";
	}

	
	
	
	
}
