package com.cg.busreservationsystem.dto;

import java.math.BigInteger;
import java.time.DayOfWeek;
import java.util.Set;

public class Bus {
	private BigInteger busId;
	private String busName;
	private static String[] busTypeList= {"sleeper","semi-sleeper"};
	private static String[] busClassList= {"AC","Non-AC"};
	private String busType;
	private String busClass;
	private Integer noOfSeats;
	private Set<DayOfWeek> dayOfJourney;
	private String source;
	private String destination;
	private Double cost;
	
	public Bus() {
		
	}
	
	
	public Bus(BigInteger busId, String busName, int busType, int busClass, Integer noOfSeats,
			Set<DayOfWeek> dayOfJourney, String source, String destination, Double cost) {
		super();
		this.busId = busId;
		this.busName = busName;
		this.busType = busTypeList[busType];
		this.busClass = busClassList[busClass];
		this.noOfSeats = noOfSeats;
		this.dayOfJourney = dayOfJourney;
		this.source = source;
		this.destination = destination;
		this.cost = cost;
	}


	public BigInteger getBusId() {
		return busId;
	}

	public void setBusId(BigInteger busId) {
		this.busId = busId;
	}

	public String getBusName() {
		return busName;
	}

	public void setBusName(String busName) {
		this.busName = busName;
	}


	public Integer getNoOfSeats() {
		return noOfSeats;
	}

	public void setNoOfSeats(Integer noOfSeats) {
		this.noOfSeats = noOfSeats;
	}

	public Set<DayOfWeek> getDayOfJourney() {
		return dayOfJourney;
	}

	public void setDayOfJourney(Set<DayOfWeek> dayOfJourney) {
		this.dayOfJourney = dayOfJourney;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public Double getCost() {
		return cost;
	}

	public void setCost(Double cost) {
		this.cost = cost;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((busClass == null) ? 0 : busClass.hashCode());
		result = prime * result + ((busId == null) ? 0 : busId.hashCode());
		result = prime * result + ((busName == null) ? 0 : busName.hashCode());
		result = prime * result + ((busType == null) ? 0 : busType.hashCode());
		result = prime * result + ((cost == null) ? 0 : cost.hashCode());
		result = prime * result + ((dayOfJourney == null) ? 0 : dayOfJourney.hashCode());
		result = prime * result + ((destination == null) ? 0 : destination.hashCode());
		result = prime * result + ((noOfSeats == null) ? 0 : noOfSeats.hashCode());
		result = prime * result + ((source == null) ? 0 : source.hashCode());
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
		Bus other = (Bus) obj;
		if (busClass == null) {
			if (other.busClass != null)
				return false;
		} else if (!busClass.equals(other.busClass))
			return false;
		if (busId == null) {
			if (other.busId != null)
				return false;
		} else if (!busId.equals(other.busId))
			return false;
		if (busName == null) {
			if (other.busName != null)
				return false;
		} else if (!busName.equals(other.busName))
			return false;
		if (busType == null) {
			if (other.busType != null)
				return false;
		} else if (!busType.equals(other.busType))
			return false;
		if (cost == null) {
			if (other.cost != null)
				return false;
		} else if (!cost.equals(other.cost))
			return false;
		if (dayOfJourney == null) {
			if (other.dayOfJourney != null)
				return false;
		} else if (!dayOfJourney.equals(other.dayOfJourney))
			return false;
		if (destination == null) {
			if (other.destination != null)
				return false;
		} else if (!destination.equals(other.destination))
			return false;
		if (noOfSeats == null) {
			if (other.noOfSeats != null)
				return false;
		} else if (!noOfSeats.equals(other.noOfSeats))
			return false;
		if (source == null) {
			if (other.source != null)
				return false;
		} else if (!source.equals(other.source))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Bus [busId=" + busId + ", busName=" + busName + ", busType=" + busType + ", busClass=" + busClass
				+ ", noOfSeats=" + noOfSeats + ", dayOfJourney=" + dayOfJourney + ", source=" + source
				+ ", destination=" + destination + ", cost=" + cost + "]";
	}
	
	
}
