package com.cg.busreservationsystem.ui;

import java.math.BigInteger;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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

		adm = new AdminServiceImpl();
		cust = new CustomerServiceImpl();

		showType();
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
		Scanner scr = new Scanner(System.in);
		int choice=0;
		while(choice<6) {
			System.out.println("Press 1 for Adding Bus Details");
			System.out.println("Press 2 for Removing Bus Details");
			System.out.println("Press 3 for Modifying Bus Details");
			System.out.println("Press 4 for Viewing Transaction Details");
			System.out.println("Press 5 for Editing Personal Details");
			System.out.println("Enter your choice:");
			choice= scr.nextInt();
			switch(choice)
			{
			case 1:
				//fetch details here
				System.out.println("Enter the bus name");
				String busName = scr.next();
				int bt=0;
				while(true) {
					
				System.out.println("Enter the bus type, 0 for sleeper, 1 for semi-sleeper");
				bt= scr.nextInt();
				
				try {
					AdminServiceImpl.validateBusType(bt);
					break;
				}catch (RuntimeException e) {
					// TODO: handle exception
					System.out.println(e.getMessage());
					continue;
				}
				}
				
				System.out.println("Enter the bus class, 0 for AC, 1 for non-AC");
				int bc= scr.nextInt();
				
				int bs;
				while(true) {
					System.out.println("Enter the no of bus seats");
					try {
						bs = AdminServiceImpl.checkNumberInput();
						break;
					}catch (Exception e) {
						// TODO: handle exception
						System.out.println("Exception :"+e.getMessage());
						continue;
					}
				}
				
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
				System.out.println(adm.addBusDetails(bus));
				
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
		Scanner scr=new Scanner(System.in);
		int choice=0;
		while(choice<6) {
			System.out.println("Press 1 to Booking a Ticket");
			System.out.println("Press 2 for Viewing a Booking");
			System.out.println("Press 3 for Viewing Bookings List");
			System.out.println("Press 4 for Cancelling a Ticket");
			System.out.println("Press 5 for Editing Personal Details");
			
			System.out.println("Enter your choice: ");
			choice=scr.nextInt();
			
			switch (choice) {
			case 1:
				System.out.println("Enter your date of journey(DD-MM-YYYY):" );
				String dateStr=scr.next();
				DateTimeFormatter formatter=DateTimeFormatter.ofPattern("DD-MM-YYYY");
				LocalDate date=LocalDate.parse(dateStr,formatter);
				System.out.println("Enter your source: ");
				String source=scr.next();
				System.out.println("Enter your destination: ");
				String destination=scr.next();
				List<Bus> busList=cust.getRunningBuses(date, source, destination);
				System.out.println("Running buses on your day of journey: ");
				int i=0;
				for(Bus b:busList) {
					System.out.println(i+1+" "+b.getBusId()+" "+b.getBusName()+" "+b.getBusType()+" "+b.getBusClass()+" "+b.getCost());
				}
				System.out.println("Enter the bus Id of the bus you will be travelling: ");
				BigInteger busId=scr.nextBigInteger();
				for(Bus b:busList) {
					if(busId==b.getBusId()) {
						System.out.println("Enter the number of passengers: ");
						int passengersCount=scr.nextInt();
						boolean bookingStatus=cust.checkBusTransaction(date, b, passengersCount);
						if(bookingStatus) {
							List<Passenger> passengersList=new ArrayList<Passenger>();
							for(int j=0;j<passengersCount;j++) {
								System.out.println("Enter Passenger "+j+1+" details: ");
								Passenger p=new Passenger();
								System.out.println("Name: ");
								String passengerName=scr.next();
								p.setPassengerName(passengerName);
								int passengerAge=scr.nextInt();
								p.setPassengerAge(passengerAge);
								System.out.println("Gender(M/F)");
								char passengerGender=scr.next().charAt(0);
								p.setPassengerGender(passengerGender);
								passengersList.add(p);
							}
							System.out.println("Enter the mode of payment(UPI/DC/CC/NB): ");
							String paymentMode=scr.next();
							
							Booking booking=cust.createBooking(passengersList, date, b, paymentMode);
							System.out.println("Booking details: ");
							System.out.println(booking.getBookingId()+" "+booking.getDateOfJourney()+" "+booking.getModeOfPayment());
							System.out.println("List of passengers");
							for(Passenger p:booking.getPassengers()) {
								System.out.println(p.getPassengerName()+" "+p.getPassengerAge()+" "+p.getPassengerGender());
							}
						}
					}
					else {
						System.out.println("Bus is full. Can't proceed with booking");
						continue;
					}
				}
				
				break;
			case 2:
				break;
			case 3:
				System.out.println("List of your bookings: ");
				for(Booking b:cust.viewTicketList()) {
					System.out.println(b.getBookingId()+" "+b.getDateOfJourney()+" "+b.getModeOfPayment()+" "+b.getPassengers());
				}
				break;
			case 4:
				System.out.println("Enter the booking id you want to cancel the booking for: ");
				BigInteger bookingId=scr.nextBigInteger();
				for(Booking b:cust.viewTicketList()) {
					if(bookingId==b.getBookingId()) {
						int cancelStatus=cust.cancelTicket(b);
						if(cancelStatus==1) {
							System.out.println("Booking cancelled");
						}
						else {
							System.out.println("Error in cancelling the booking");
						}
					}
				}
				break;
			case 5:
				System.out.println("You cannot edit your personal details. System is under maintenance");
				break;
			default:
				break;
			}
			
		}

	}

}
