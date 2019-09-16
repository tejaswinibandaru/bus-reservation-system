/**
 * 
 */
package com.cg.busreservationsystem.test;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.cg.busreservationsystem.dao.UserDao;
import com.cg.busreservationsystem.dao.UserDaoImpl;
import com.cg.busreservationsystem.dto.Bus;
import com.cg.busreservationsystem.exception.BookingException;
import com.cg.busreservationsystem.util.DBUtil;

/**
 * @author admin
 *
 */
public class TestBusBooking {

	/**
	 * @param args
	 */
	private static Connection connection;
	private PreparedStatement preparedStatement;
	private ResultSet resultSet;
	private static Logger myLogger;
	static{   	
		Properties props = System.getProperties();
		String userDir= props.getProperty("user.dir")+"/src/main/resources/";
		System.out.println("Current working directory is " +userDir);
		PropertyConfigurator.configure(userDir+"log4j.properties");
		myLogger=Logger.getLogger("DBUtil.class");
		try {
			connection= DBUtil.getConnection();
			myLogger.info("connection Obtained!!");
		} catch (BookingException e) {  //MyException e
			myLogger.error("Connection Not Obtained at EmployeeDao : "+e);
			//System.out.print("Connection Not Obtained at EmployeeDao");
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		UserDao userdao= new UserDaoImpl();
		/*
		 * List<DayOfWeek> days=new ArrayList<DayOfWeek>(); days.add(DayOfWeek.of(1));
		 * days.add(DayOfWeek.of(2)); Collections.sort(days);
		 * System.out.println(userdao.saveBusDay(days, BigInteger.valueOf(1)));
		 */
		
		for(Bus bus:userdao.findAllBuses()) {
			System.out.println(bus);
		}
		System.out.println(userdao.findDayOfWeekByBus(BigInteger.valueOf(1)));
		
		Bus b=new Bus();
		b.setBusName("kaveri");
		b.setBusType("sleeper");
		b.setBusClass("ac");
		b.setNoOfSeats(30);
		b.setSource("mumbai");
		b.setDestination("pune");
		b.setCost(500.0);
		System.out.println(b);
		
	}

}
