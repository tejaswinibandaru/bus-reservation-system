package com.cg.busreservationsystem.ui;

import java.math.BigInteger;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

import com.cg.busreservationsystem.dao.*;
import com.cg.busreservationsystem.dto.*;
import com.cg.busreservationsystem.exception.*;
import com.cg.busreservationsystem.service.*;;

public class MyApplication {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		//int ch= sc.nextInt();
		
//		BookingDao bd = new BookingDaoImpl();
//		Booking b1 = new Booking(42344,LocalDate.now(),);
		
		BusDao busDao = new BusDaoImpl();
		Set<DayOfWeek> days = new TreeSet<DayOfWeek>();
		days.add(DayOfWeek.of(1));
		days.add(DayOfWeek.of(3));
		Bus b1 = new Bus((BigInteger.valueOf(2342)),"Rampyari", "sleeper", "ac", 33, days,"Mum","Pune",345.5);
		
		System.out.println(busDao.saveBus(b1));
	}

}
