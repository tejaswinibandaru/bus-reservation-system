/**
 * 
 */
package com.cg.busreservationsystem.test;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.cg.busreservationsystem.dao.UserDao;
import com.cg.busreservationsystem.dao.UserDaoImpl;
import com.cg.busreservationsystem.dto.Booking;
import com.cg.busreservationsystem.dto.Bus;
import com.cg.busreservationsystem.dto.BusTransaction;
import com.cg.busreservationsystem.dto.Passenger;
import com.cg.busreservationsystem.dto.User;
import com.cg.busreservationsystem.exception.BookingException;
import com.cg.busreservationsystem.service.UserService;
import com.cg.busreservationsystem.service.UserServiceImpl;
import com.cg.busreservationsystem.service.Validation;
import com.cg.busreservationsystem.util.DBUtil;

/**
 * @author admin
 *
 */
public class TestBusBooking {
	public static void main(String[] args) {
		UserDao us=new UserDaoImpl(); 
		System.out.println(us.findAllBuses());
		System.out.println("--------------------------------");
		Bus bus=us.findBusById(BigInteger.valueOf(6));
		System.out.println(bus);
		
		
		BusTransaction bt=new BusTransaction();
		bt.setAvailableSeats(bus.getNoOfSeats());
		bt.setDate(LocalDate.now());
		bt.setBus(bus);
		System.out.println(us.saveTransaction(bt));
		System.out.println(bt);
		
		List<Passenger> passengers=new ArrayList<Passenger>();
		Passenger p=new Passenger();
		p.setPassengerName("abcd");
		p.setPassengerAge(20);
		p.setPassengerGender('F');
		p.setBookingId(BigInteger.valueOf(1));
		passengers.add(p);
		
		
		Booking b=new Booking();
		b.setBookingId(BigInteger.valueOf(1));
		b.setUserId(BigInteger.valueOf(1));
		b.setTransactionId(BigInteger.valueOf(1));
		b.setBus(bt.getBus());
		b.setDateOfJourney(bt.getDate());
		b.setPassengers(passengers);
		b.setModeOfPayment("UPI");
		b.setTotalCost(bus.getCost());
		b.setBookingStatus("active");
		
		System.out.println(us.saveBooking(b));
		
	}
}
