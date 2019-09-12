package com.cg.busreservationsystem.service;

import java.time.LocalDate;

import com.cg.busreservationsystem.exception.DateException;

public class Validation {
	
	public void validateDate(LocalDate date) throws DateException {
		if(!date.isAfter(LocalDate.now())){
			throw new DateException("Date should be after today's date");
		}
		
	}

}
