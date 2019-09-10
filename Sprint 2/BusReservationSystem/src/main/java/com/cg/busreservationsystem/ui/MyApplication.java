package com.cg.busreservationsystem.ui;

import java.math.BigInteger;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

import com.cg.busreservationsystem.dao.*;
import com.cg.busreservationsystem.dto.*;
import com.cg.busreservationsystem.exception.*;
import com.cg.busreservationsystem.service.*;;

public class MyApplication {

	static AdminService adm;
	static CustomerService cust;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Scanner sc = new Scanner(System.in);

		//int ch= sc.nextInt();

		adm = new AdminServiceImpl();
		cust = new CustomerServiceImpl();

		showType();

		Set<DayOfWeek> days = new TreeSet<DayOfWeek>();
		days.add(DayOfWeek.of(1));
		days.add(DayOfWeek.of(3));
		try {
			//Bus b1 = new Bus((BigInteger.valueOf(2342)),"Rampyari", "sleeper", "ac", 33, days,"Mum","Pune",345.5);
			//busDao.saveBus(b1);
			//Bus b2 = new Bus((BigInteger.valueOf(2343)),"Rampyari1", "semi-sleeper", "non-ac", 33, days,"Pune","Mum",340.0);
			//busDao.saveBus(b2);

			//System.out.println(busDao.findAllBuses());
			System.out.println("try");
		}
		catch(Exception e) {
			System.out.println("err");
			System.out.println(e.getMessage());
		}
	}

	static void showType() {
		System.out.println("Select 1 for Admin");
		System.out.println("Select 2 for Customer");
		Scanner scr = new Scanner(System.in);
		int choice = scr.nextInt();
		if(choice==1)
		{
			adminMenu();
		}
		else if(choice==2){
			custMenu();
		}
	}

	static void adminMenu() {
		System.out.println("Press 1 for Adding Bus Details");
		System.out.println("Press 2 for Removing Bus Details");
		System.out.println("Press 3 for Modifying Bus Details");
		System.out.println("Press 4 for Viewing Transaction Details");
		System.out.println("Press 5 for Editing Personal Details");
		Scanner scr = new Scanner(System.in);
		int choice;
		while((choice = scr.nextInt())<6) {
			switch(choice)
			{
			case 1:
				//fetch details here
				System.out.println("Enter the bus name");
				String busName = scr.next();
				
				System.out.println("Enter the bus type, 0 for sleeper, 1 for semi-sleeper");
				int bt= scr.nextInt();
				
				System.out.println("Enter the bus class, 0 for AC, 1 for non-AC");
				int bc= scr.nextInt();
				
				System.out.println("Enter the no of bus seats");
				int bs = scr.nextInt();
				
				System.out.println("Enter the no of days of the week on which day the bus will run");
				int noOfDays=scr.nextInt();
				Set<DayOfWeek> days=new TreeSet<DayOfWeek>();
				for(int i=0;i<noOfDays;i++) {
					System.out.println("Enter the day number starting from 1(Monday) to 7(Sunday): ");
					int day=scr.nextInt();
					days.add(DayOfWeek.of(day));
				}
				
				System.out.println("Enter the bus source");
				String source = scr.next();
				
				System.out.println("Enter the bus destination");
				String destination = scr.next();
				
				System.out.println("Enter the bus cost per seat");
				double costPerSeat = scr.nextDouble();

				
				Bus bus = new Bus(busName, bt, bc, bs, days, source, destination, costPerSeat);
				adm.addBusDetails(bus);
				break;
				
			case 2:
				System.out.println("Enter the bus id to remove");
				BigInteger busId = scr.nextBigInteger();
				adm.removeBusDetails(busId);
				break;
			}
		}
	}

	static void custMenu() {
		System.out.println("Press 1 to Booking a Ticket");
		System.out.println("Press 2 for Viewing a Booking");
		System.out.println("Press 3 for Viewing Bookings List");
		System.out.println("Press 4 for Cancelling a Ticket");
		System.out.println("Press 5 for Editing Personal Details");


	}

}
