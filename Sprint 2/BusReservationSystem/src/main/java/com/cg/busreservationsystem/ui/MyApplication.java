package com.cg.busreservationsystem.ui;

import java.math.BigInteger;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

import com.cg.busreservationsystem.dto.Booking;
import com.cg.busreservationsystem.dto.Bus;
import com.cg.busreservationsystem.dto.Passenger;
import com.cg.busreservationsystem.dto.Transaction;
import com.cg.busreservationsystem.service.UserService;
import com.cg.busreservationsystem.service.UserServiceImpl;
import com.cg.busreservationsystem.service.Validation;;

public class MyApplication {

	static UserService userService;
	static int counter = 0;
	static Validation validation;

	public static void main(String[] args) {

		userService = new UserServiceImpl();

		showType();
	}

	static void showType() {
		validation= new Validation();
		Scanner scanner = new Scanner(System.in);
		int runLoop = 1;
		String input;
		while (runLoop != 0) {
			int choice=0;
			while(true) {
			
			System.out.println("Select 1 for Admin");
			System.out.println("Select 2 for Customer");
			input = scanner.next();
			try { 
				choice=validation.validateChoice(input);
			
			if (choice == 1) {
				adminMenu();
			} else if (choice == 2) {
				customerMenu();
			}
			
				break;
			} catch (RuntimeException e) {
				// TODO: handle exception
				System.out.println("Exception occured:" +e.getMessage());
				continue;
			}
			}
			System.out.println("Press 1 to continue, 0 to stop");
			runLoop = scanner.nextInt();

		} scanner.close();
	}

	static void adminMenu() {
		validation=new Validation();
		Scanner scanner = new Scanner(System.in);
		
		int runLoop = 1;
		String input;
		while (runLoop != 0) {
			int choice = 0;
			while(true) {
			System.out.println("Press 1 for Adding Bus Details");
			System.out.println("Press 2 for Removing Bus Details");
			System.out.println("Press 3 for Modifying Bus Details");
			System.out.println("Press 4 for Viewing Transaction Details");
			System.out.println("Press 5 for Editing Personal Details");
			System.out.println("Enter your choice:");
			input = scanner.next(); //INputMismatchExcp
			try { 
			choice=validation.validateChoice1(input);
			break;
			} catch (RuntimeException e) {
						// TODO: handle exception
						System.out.println("Exception occured:" +e.getMessage());
						continue;
					}
			}
		
	
			switch (choice) {
			case 1:
				// fetch details here
				System.out.println("Enter the bus name");
				String busName = scanner.next();
				int busType = 0;
				while (true) {

					System.out.println("Enter the bus type, 0 for sleeper, 1 for semi-sleeper");
					busType = scanner.nextInt();

					try {
						UserServiceImpl.validateBusType(busType);
						break;
					} catch (RuntimeException e) {
						// TODO: handle exception
						System.out.println("Exception occured:" +e.getMessage());
						continue;
					}
				}

				int busClass=0;
					while (true) {

					System.out.println("Enter the bus class, 0 for AC, 1 for non-AC");
					busClass = scanner.nextInt();
					try {
						validation.validateBusClass(busClass);
						break;
					} catch (Exception e) {
						// TODO: handle exception
						System.out.println("Exception occured:" +e.getMessage());
						continue;
					}
				}

				int busSeats;
				while (true) {
					System.out.println("Enter the no of bus seats");
					try {
						busSeats = UserServiceImpl.checkNumberInput();
						break;
					} catch (Exception e) {
						// TODO: handle exception
						System.out.println("Exception :" + e.getMessage());
						continue;
					}
				}
				int noOfDays=0; ;
				while(true) {
				System.out.println("Enter the no of days of the week on which day the bus will run");
				input = scanner.next();
				try { 
					noOfDays=validation.validateChoice2(input);
				     break;      
				}catch (RuntimeException e) {
					// TODO: handle exception
					System.out.println("Exception occured:" +e.getMessage());
					continue;
				}
				}
				Set<DayOfWeek> days = new TreeSet<DayOfWeek>();
				for (int i = 0; i < noOfDays; i++) {
					int day=0;
					while(true) {
					System.out.println("Enter the day number starting from 1(Monday) to 7(Sunday): ");
					input = scanner.next();
					//int day = scanner.nextInt();
					try { 
						day=validation.validateChoice2(input);
					     break;      
					}catch (RuntimeException e) {
						// TODO: handle exception
						System.out.println("Exception occured:" +e.getMessage());
						continue;
					}
					}
					days.add(DayOfWeek.of(day));
				}


				String source ;
				String destination;
				while(true) {
					System.out.println("Enter the bus source: ");
					source = scanner.next();

					System.out.println("Enter the bus destination");

					destination = scanner.next();

					try {
						validation.validateTravel(source,destination);
						break;
					} catch (Exception e) {
						// TODO: handle exception
						System.out.println("Exception occured:" +e.getMessage());
						continue;
					}
				}


				double costPerSeat;
				while(true) {

					System.out.println("Enter the bus cost per seat");

					try {
						costPerSeat = Validation.validateCost();
						break;
					} catch (Exception e) {
						// TODO: handle exception
						System.out.println("Exception occured:" +e.getMessage());
						continue;
					}
				}
				Bus bus = new Bus(BigInteger.valueOf(++counter), busName, busType, busClass, busSeats, days, source,
						destination, costPerSeat);

				bus = userService.addBusDetails(bus);
				System.out.println("Bus added: ");
				System.out.println("Bus Id: " + bus.getBusId());
				System.out.println("Bus name: " + bus.getBusName());
				System.out.println("Bus Type: " + bus.getBusType());
				System.out.println("Bus Class: " + bus.getBusClass());
				System.out.println("Number of Seats: " + bus.getNoOfSeats());
				System.out.println("Days Of Journey: " + bus.getDayOfJourney());
				System.out.println("Source: " + bus.getSource());
				System.out.println("Destination: " + bus.getDestination());
				System.out.println("Cost per seat: " + bus.getCost());
				// System.out.println(userService.addBusDetails(bus));

				// System.out.println(userServ.viewBuses());

				break;

			case 2:
				System.out.println("Enter the bus id to remove");
				BigInteger busId = scanner.nextBigInteger();
				int removeStatus = userService.removeBusDetails(busId);
				if (removeStatus == 1) {
					System.out.println("Bus removed");
				} else {
					System.out.println("Id not found");
				}
				break;
			case 3:
				System.out.println("Enter the bus id to update details: ");
				busId = scanner.nextBigInteger();
				for (Bus busObj : userService.viewBuses()) {
					if (busId.equals(busObj.getBusId())) {
						System.out.println("Update the cost per seat of the bus: ");
						double cost = scanner.nextDouble();
						busObj.setCost(cost);
					}
				}
				break;
			case 4:
				System.out.println("Enter the date(DD-MM-YYYY): ");
				String dateStr = scanner.next();
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
				LocalDate date = LocalDate.parse(dateStr, formatter);
				System.out.println("List of transactions");
				for (Transaction transaction : userService.getTransactionsByDate(date)) {
					System.out.println(transaction.getDate() + " " + transaction.getBus() + transaction.getBookings());
				}
				break;
			case 5:
				System.out.println("You cannot edit your personal details. System is under maintenance");
				break;
				default:
					System.out.println("Wrong choice : Enter a valid Integer input");
					break;
			}
			System.out.println("Press 1 to continue, 0 to exit");
			runLoop = scanner.nextInt();
		} scanner.close();
		
	}

	static void customerMenu() {
		validation=new Validation();
		Scanner scanner=new Scanner(System.in);
		int runLoop=1;
		String input;
		while(runLoop!=0) {
			int choice=0;
			while(true) {
			System.out.println("Press 1 to Booking a Ticket");
			System.out.println("Press 2 for Viewing a Booking");
			System.out.println("Press 3 for Viewing Bookings List");
			System.out.println("Press 4 for Cancelling a Ticket");
			System.out.println("Press 5 for Editing Personal Details");
			System.out.println("Enter your choice: ");
			input = scanner.next(); //INputMismatchExcp
			try { 
			choice=validation.validateChoice1(input);
			break;
			} catch (RuntimeException e) {
						// TODO: handle exception
						System.out.println("Exception occured:" +e.getMessage());
						continue;
					}
			}
			
			switch (choice) {
			case 1:
				LocalDate date;
				while(true) {
					System.out.println("Enter your date of journey(DD-MM-YYYY):" );
					String dateStr=scanner.next();
					DateTimeFormatter formatter=DateTimeFormatter.ofPattern("dd-MM-yyyy");
					date=LocalDate.parse(dateStr,formatter);
					try {
						validation.validateDate(date);
						break;
					}catch (Exception e) {
						System.out.println("Exception occurred: "+e.getMessage());
						continue;

					}
				}

				System.out.println("Enter your source: ");
				String source=scanner.next();
				System.out.println("Enter your destination: ");
				String destination=scanner.next();


				List<Bus> busList=userService.getRunningBuses(date, source, destination);
				System.out.println("Running buses on your day of journey: ");
				int i=0;
				for(Bus b:busList) {
					System.out.println((i+1)+" "+b.getBusId()+" "+b.getBusName()+" "+b.getBusType()+" "+b.getBusClass()+" "+b.getCost());
				}
				System.out.println("Enter the bus Id of the bus you will be travelling: ");

				BigInteger busId=scanner.nextBigInteger();

				for(Bus busObj:busList) {
					if(busId.equals(busObj.getBusId())) {
						int passengersCount;
						while(true) {
							System.out.println("Enter the number of passengers: ");
							passengersCount=scanner.nextInt();
							try {
								validation.validatePassengersCount(passengersCount);
								break;
							}catch (Exception e) {
								// TODO: handle exception
								System.out.println("Exception occurred: "+e.getMessage());
								continue;
							}
						}
						//						System.out.println("Enter the number of passengers: ");
						//						passengersCount=scanner.nextInt();
						boolean bookingStatus=userService.checkBusTransaction(date, busObj, passengersCount);
						if(bookingStatus) {
							List<Passenger> passengersList=new ArrayList<Passenger>();
							for(int j=0;j<passengersCount;j++) {
								System.out.println("Enter Passenger "+j+1+" details: ");
								Passenger passenger=new Passenger();
								System.out.println("Name: ");
								String passengerName=scanner.next();
								passenger.setPassengerName(passengerName);
								int passengerAge=scanner.nextInt();
								passenger.setPassengerAge(passengerAge);
								System.out.println("Gender(M/F)");
								char passengerGender=scanner.next().charAt(0);
								passenger.setPassengerGender(passengerGender);
								passengersList.add(passenger);
							}
							System.out.println("Enter the mode of payment(UPI/DC/CC/NB): ");
							String paymentMode=scanner.next();

							Booking booking=userService.createBooking(passengersList, date, busObj, paymentMode);
							System.out.println("Booking details: ");
							System.out.println(booking.getBookingId()+" "+booking.getDateOfJourney()+" "+booking.getModeOfPayment());
							System.out.println("List of passengers");
							for(Passenger p:booking.getPassengers()) {
								System.out.println(p.getPassengerName()+" "+p.getPassengerAge()+" "+p.getPassengerGender());
							}
						}
						else {
							System.out.println("Bus is full. Can't proceed with booking");
							continue;
						}
					}	
				}
				break;
			case 2:
				break;
			case 3:
				System.out.println("List of your bookings: ");
				for(Booking booking:userService.viewTicketList()) {
					System.out.println(booking.getBookingId()+" "+booking.getDateOfJourney()+" "+booking.getModeOfPayment()+" "+booking.getPassengers());
				}
				break;
			case 4:
				System.out.println("Enter the booking id you want to cancel the booking for: ");
				BigInteger bookingId=scanner.nextBigInteger();
				for(Booking booking:userService.viewTicketList()) {
					if(booking.getBookingId().equals(bookingId)) {
						int cancelStatus=userService.cancelTicket(booking);
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
				System.out.println("Wrong choice : Enter a valid Integer input");
				break;
			}
			System.out.println("Press 1 to continue, 0 to exit");
			runLoop=scanner.nextInt();

		} scanner.close();
		
	}
}

