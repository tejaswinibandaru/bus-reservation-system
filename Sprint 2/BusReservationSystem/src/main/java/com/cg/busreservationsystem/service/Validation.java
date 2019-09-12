package com.cg.busreservationsystem.service;

import java.time.LocalDate;

import com.cg.busreservationsystem.exception.BusException;
import com.cg.busreservationsystem.exception.DateException;
import com.cg.busreservationsystem.exception.PassengerException;

public class Validation {
	
	public void validateDate(LocalDate date) throws DateException {
		if(!date.isAfter(LocalDate.now())){
			throw new DateException("Date should be after today's date");
		}
	}
	
	public void validatePassengersCount(int count) {
		if(count>5) {
			throw new PassengerException("Passenger count should not be more than 5 per ticket");
		}
	}
	
	public void validateGender(Character gender) {
		if(!gender.equals('F')||!gender.equals('M')) {
			throw new PassengerException("Gender should be M or F");
		}
	}
	
	public void validateTravel(String busSource, String busDestination) {
		if(busSource.equals(busDestination)) {
			throw new BusException("Source and Destination cant be same");
		}
	}

}
