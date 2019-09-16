package com.cg.busreservationsystem.dto;

import java.math.BigInteger;
import java.time.DayOfWeek;
import java.util.List;
import java.util.Set;

public class Bus {
	private BigInteger busId;
	private String busName;

	public enum busTypeList {
		SLEEPER, SEMI_SLEEPER;
	}

	public enum busClassList {
		AC, NON_AC
	};

	private busTypeList busType;
	private busClassList busClass;
	private Integer noOfSeats;
	private List<DayOfWeek> dayOfJourney;
	private String source;
	private String destination;
	private Double cost;

	public Bus() {
		busId = BigInteger.valueOf(0);
		busName = null;
		busClass = null;
		noOfSeats = 0;
		dayOfJourney = null;
		source = "";
		destination = "";
		cost = 0.0;
	}

	public Bus(BigInteger busId, String busName, String busType, String busClass, Integer noOfSeats,
			List<DayOfWeek> dayOfJourney, String source, String destination, Double cost) {
		super();

		this.busId = busId;
		this.busName = busName;
		this.busType = busTypeList.valueOf(busType.toUpperCase());
		this.busClass = busClassList.valueOf(busClass.toUpperCase());
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

	public void setBusType(String busType) {
		this.busType = busTypeList.valueOf(busType.toUpperCase());
	}

	public void setBusClass(String busClass) {
		this.busClass = busClassList.valueOf(busClass.toUpperCase());
	}

	public busTypeList getBusType() {
		return this.busType;
	}

	public busClassList getBusClass() {
		return this.busClass;
	}

	public Integer getNoOfSeats() {
		return noOfSeats;
	}

	public void setNoOfSeats(Integer noOfSeats) {
		this.noOfSeats = noOfSeats;
	}

	public List<DayOfWeek> getDayOfJourney() {
		return dayOfJourney;
	}

	public void setDayOfJourney(List<DayOfWeek> dayOfJourney) {
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
		try {
			if (this.hashCode() == (obj.hashCode()))
				return true;
			/*
			 * if (this == obj) return true; if (obj == null) return false; if (getClass()
			 * != obj.getClass()) return false; Bus other = (Bus) obj; if (busClass == null)
			 * { if (other.busClass != null) return false; } else if
			 * (!busClass.equals(other.busClass)) return false; if (busId == null) { if
			 * (other.busId != null) return false; } else if (!busId.equals(other.busId))
			 * return false; if (busName == null) { if (other.busName != null) return false;
			 * } else if (!busName.equals(other.busName)) return false; if (busType == null)
			 * { if (other.busType != null) return false; } else if
			 * (!busType.equals(other.busType)) return false; if (cost == null) { if
			 * (other.cost != null) return false; } else if (!cost.equals(other.cost))
			 * return false; if (dayOfJourney == null) { if (other.dayOfJourney != null)
			 * return false; } else if (!dayOfJourney.equals(other.dayOfJourney)) return
			 * false; <<<<<<< HEAD /* if (deleteFlag == null) { if (other.deleteFlag !=
			 * null) return false; } else if (!deleteFlag.equals(other.deleteFlag)) return
			 * false;
			 */
			/*
			 * if (destination == null) { ======= if (destination == null) { >>>>>>> branch
			 * 'master' of https://github.com/tejaswinibandaru/bus-reservation-system.git if
			 * (other.destination != null) return false; } else if
			 * (!destination.equals(other.destination)) return false; if (noOfSeats == null)
			 * { if (other.noOfSeats != null) return false; } else if
			 * (!noOfSeats.equals(other.noOfSeats)) return false; if (source == null) { if
			 * (other.source != null) return false; } else if (!source.equals(other.source))
			 * return false;
			 */

			return false;
		} catch (Exception e) {
			System.out.println("Exception:" + e);
		}
		return false;
	}

	@Override
	public String toString() {
		return "Bus [busId=" + busId + ", busName=" + busName + ", busType=" + busType + ", busClass=" + busClass
				+ ", noOfSeats=" + noOfSeats + ", dayOfJourney=" + dayOfJourney + ", source=" + source
				+ ", destination=" + destination + ", cost=" + cost + "]";
	}

}
