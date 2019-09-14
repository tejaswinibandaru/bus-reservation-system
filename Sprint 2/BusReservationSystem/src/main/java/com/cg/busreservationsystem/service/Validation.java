package com.cg.busreservationsystem.service;

import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.cg.busreservationsystem.exception.BusException;
import com.cg.busreservationsystem.exception.DateException;
import com.cg.busreservationsystem.exception.PassengerException;
import com.cg.busreservationsystem.ui.MyApplication;

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

	public void validateBusClass(int busClass) {
		// TODO Auto-generated method stub
		if(busClass < 0 || busClass> 1) {
			throw new BusException("Wrong bus class");
		}
	}

	public static double validateCost() throws InputMismatchException{
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		try {
			return sc.nextDouble();
		}catch (InputMismatchException e) {
			// TODO: handle exception
			throw new InputMismatchException("Wrong cost type entered");
		}
	}
	public boolean isValidNumeric(String str) 
    { 
        str = str.trim(); // trims the white spaces. 
  
        if (str.length() == 0) 
            return false; 
  
        // if string is of length 1 and the only 
        // character is not a digit 
        if (str.length() == 1 && !Character.isDigit(str.charAt(0))) 
            return false; 
  
        // If the 1st char is not '+', '-', '.' or digit 
        if (str.charAt(0) != '+' && str.charAt(0) != '-'
            && !Character.isDigit(str.charAt(0)) 
            && str.charAt(0) != '.') 
            return false; 
  
        // To check if a '.' or 'e' is found in given 
        // string. We use this flag to make sure that 
        // either of them appear only once. 
        boolean flagDotOrE = false; 
  
        for (int i = 1; i < str.length(); i++) { 
            // If any of the char does not belong to 
            // {digit, +, -, ., e} 
            if (!Character.isDigit(str.charAt(i)) 
                && str.charAt(i) != 'e' && str.charAt(i) != '.'
                && str.charAt(i) != '+' && str.charAt(i) != '-') 
                return false; 
  
            if (str.charAt(i) == '.') { 
                // checks if the char 'e' has already 
                // occurred before '.' If yes, return 0. 
                if (flagDotOrE == true) 
                    return false; 
  
                // If '.' is the last character. 
                if (i + 1 >= str.length()) 
                    return false; 
  
                // if '.' is not followed by a digit. 
                if (!Character.isDigit(str.charAt(i + 1))) 
                    return false; 
            } 
  
            else if (str.charAt(i) == 'e') { 
                // set flagDotOrE = 1 when e is encountered. 
                flagDotOrE = true; 
  
                // if there is no digit before 'e'. 
                if (!Character.isDigit(str.charAt(i - 1))) 
                    return false; 
  
                // If 'e' is the last Character 
                if (i + 1 >= str.length()) 
                    return false; 
  
                // if e is not followed either by 
                // '+', '-' or a digit 
                if (!Character.isDigit(str.charAt(i + 1)) 
                    && str.charAt(i + 1) != '+'
                    && str.charAt(i + 1) != '-') 
                    return false; 
            } 
        } 
  
        /* If the string skips all above cases, then 
           it is numeric*/
        return true; 
    }
	public int validateChoice(String input) {
		// TODO Auto-generated method stub
		int choice = 0;
		if(isValidNumeric(input)) {
			choice = Integer.parseInt(input);
			if(choice < 1 ||choice> 2) {
				throw new BusException("Wrong choice entered");
			}
		}
		else
			throw new BusException("Integer choice to be entered");
		return choice;
	}
	
	public int validateChoice1(String input) {
		  if(!isValidNumeric(input)) 
			throw new BusException("Integer choice to be entered");
		  return Integer.parseInt(input);
		}
	public int validateChoice2(String input) {
		// TODO Auto-generated method stub
		int choice = 0;
		if(isValidNumeric(input)) {
			choice = Integer.parseInt(input);
			if(choice < 1 ||choice > 7) {
				throw new BusException("Week has only seven days");
			}
		}
		else
			throw new BusException("Integer choice to be entered");
		return choice;
	}
	

	}



