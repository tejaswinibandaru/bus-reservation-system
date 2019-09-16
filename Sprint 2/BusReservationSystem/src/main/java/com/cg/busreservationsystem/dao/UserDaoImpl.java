package com.cg.busreservationsystem.dao;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.cg.busreservationsystem.dto.Booking;
import com.cg.busreservationsystem.dto.Bus;
import com.cg.busreservationsystem.dto.BusTransaction;
import com.cg.busreservationsystem.dto.Passenger;
import com.cg.busreservationsystem.exception.BookingException;
import com.cg.busreservationsystem.util.DBUtil;;

public class UserDaoImpl implements UserDao {
	// db code starts here
	// prep -work 1- Connection
	private static Connection connection;
	private PreparedStatement preparedStatement;
	private ResultSet resultSet;
	private static Logger myLogger;
	static {
		Properties props = System.getProperties();
		String userDir = props.getProperty("user.dir") + "/src/main/resources/";
		System.out.println("Current working directory is " + userDir);
		PropertyConfigurator.configure(userDir + "log4j.properties");
		myLogger = Logger.getLogger("UserDaoImpl.class");
		try {
			connection = DBUtil.getConnection();
			myLogger.info("connection Obtained!!");
		} catch (BookingException e) { // MyException e
			myLogger.error("Connection Not Obtained at EmployeeDao : " + e);
			// System.out.print("Connection Not Obtained at EmployeeDao");
		}
	}

	private List<BusTransaction> transactionList = new ArrayList<BusTransaction>();
	private List<Bus> busList = new ArrayList<Bus>();

	List<Booking> bookingsList = new ArrayList<Booking>();
	List<Passenger> passengersList = new ArrayList<Passenger>();

	@Override
	public Booking saveBooking(Booking booking) {
		// TODO Auto-generated method stub
		// added to AL
		String sql = "INSERT INTO booking(user_id,transaction_id,bus_id,date_of_journey,mode_of_payment,total_cost,booking_status,delete_flag) values(?,?,?,?,?,?,?,0);";
		try {
			// step1 : obtain ps
			preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			// step 2: set the ps placeholder values
			preparedStatement.setLong(1, booking.getUserId().longValue());
			preparedStatement.setLong(2, booking.getTransactionId().longValue()); // getTrsansactionId
			preparedStatement.setLong(3, booking.getBus().getBusId().longValue());
			preparedStatement.setTimestamp(4, Timestamp.valueOf(booking.getDateOfJourney().atStartOfDay())); // to
			// timestamp
			preparedStatement.setString(5, booking.getModeOfPayment());
			preparedStatement.setDouble(6, booking.getTotalCost());
			preparedStatement.setString(7, booking.getBookingStatus());
			// step 3: execute Query (for DML we have executeUpdate method )
			int noOfRec = preparedStatement.executeUpdate();
			// getting the auto-generated value
			BigInteger generatedId = BigInteger.valueOf(0L);
			resultSet = preparedStatement.getGeneratedKeys();
			if (resultSet.next()) {
				generatedId = BigInteger.valueOf(resultSet.getLong("booking_id"));
				myLogger.info("Auto generated Id " + generatedId);
			}
			// setting the auto-generated Id to current booking obj
			booking.setBookingId(generatedId);
		} catch (SQLException e) {

			myLogger.error(" Error at saveBooking Dao method : " + e);
			throw new BookingException(" Error at saveBooking Dao method : " + e);
		} finally {
			if (preparedStatement != null) {

				try {
					preparedStatement.close();
				} catch (SQLException e) {
					System.out.println(" Error at saveBooking Dao method : " + e);
					myLogger.error(" Error at saveBooking Dao method : " + e);
				}
			}
		}

		bookingsList.add(booking);
		return booking;
	}

	@Override
	public Integer removeBooking(BigInteger bookingId) {
		// TODO Auto-generated method stub
		String sql = "UPDATE booking SET delete_flag=1 WHERE booking_id=?;";
		int noOfRec = 0;
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setLong(1, bookingId.longValue());

			noOfRec = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println(" Error at remove booking Dao method : " + e);
			myLogger.error(" Error at removeBooking Dao method : " + e);
		} finally {
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					System.out.println(" Error at remove booking Dao method : " + e);
					myLogger.error(" Error at removeBooking Dao method : " + e);
				}
			}
		}
		return noOfRec;
	}
	/*
	 * Booking b=this.findBookingById(bookingId); if(b==null) { return 0; }
	 * bookingsList.remove(b); return 1; }
	 */

	@Override
	public List<Booking> findAllBookings() {
		// TODO Auto-generated method stub
		String sql = "select * from booking where delete_flag=0";
		String sql2 = "select * from passenger where booking_id=? AND delete_flag=0";
		List<Booking> bookingList = new ArrayList<Booking>();
		PreparedStatement preparedStatement2 = null;
		try {
			preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			// for select queries we have executeQuery method which returns ResultSet
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				// create booking object
				Booking booking = new Booking();
				// get the value from rs and set to booking obj
				booking.setBookingId(BigInteger.valueOf(resultSet.getLong("booking_id")));
				//
				preparedStatement2 = connection.prepareStatement(sql2);
				preparedStatement2.setLong(1, booking.getBookingId().longValue());
				ResultSet resultSet2 = preparedStatement2.executeQuery();
				while (resultSet2.next()) {
					Passenger passenger = new Passenger();
					passenger.setBookingId(BigInteger.valueOf(resultSet2.getLong("booking_id")));
					passenger.setPassengerName(resultSet2.getString("passenger_name"));
					passenger.setPassengerId(BigInteger.valueOf(resultSet2.getLong("passenger_id")));
					passenger.setPassengerAge(resultSet2.getInt("passenger_age"));
					passenger.setPassengerGender(resultSet2.getString("passenger_gender").charAt(0));
					passengersList.add(passenger);
				}
				//
				booking.setUserId(BigInteger.valueOf(resultSet.getLong("user_id")));
				booking.setTransactionId(BigInteger.valueOf(resultSet.getLong("transaction_id")));
				booking.setDateOfJourney(resultSet.getTimestamp("date_of_journey").toLocalDateTime().toLocalDate()); // converting
				// from
				// timestamp
				// to
				// localdate
				BigInteger busId = BigInteger.valueOf(resultSet.getLong("bus_id"));
				booking.setBus(findBusById(busId));
				booking.setPassengers(passengersList);
				booking.setTotalCost(resultSet.getDouble("total_cost"));
				booking.setModeOfPayment(resultSet.getString("mode_of_payment"));
				booking.setBookingStatus(resultSet.getString("booking_status"));
				// add the booking obj to bookingList
				bookingList.add(booking);

			}
		} catch (SQLException e) {
			System.out.println(" Error at findAllBookings Dao method : " + e);
			myLogger.error(" Error at findAllBookings Dao method : " + e);
		} finally {
			if (preparedStatement2 != null) {
				try {
					preparedStatement2.close();
				} catch (SQLException e) {

					System.out.println(" Error at findAllBookings Dao method : " + e);
					myLogger.error(" Error at findAllBookings Dao method : " + e);

					System.out.println(" Error at findAllBookings Dao method : " + e);
					myLogger.error(" Error at findAllBookings Dao method : " + e);

				}
			}
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					System.out.println(" Error at findAllBookings Dao method : " + e);
					myLogger.error(" Error at findAllBookings  Dao method : " + e);
				}
			}
		}
		return bookingList;
	}

	@Override
	public Booking findBookingById(BigInteger bookingId) {

		String sql = "SELECT * FROM booking WHERE delete_flag=0 AND booking_id=?";
		String sql2 = "select * from passenger where booking_id=? AND delete_flag=0";
		Booking booking = new Booking();
		PreparedStatement preparedStatement2 = null;
		// TODO Auto-generated method stub
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setLong(1, bookingId.longValue());

			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				booking.setBookingId(bookingId);

				preparedStatement2 = connection.prepareStatement(sql2);
				preparedStatement2.setLong(1, booking.getBookingId().longValue());
				ResultSet resultSet2 = preparedStatement2.executeQuery();
				while (resultSet2.next()) {
					Passenger passenger = new Passenger();
					passenger.setBookingId(BigInteger.valueOf(resultSet2.getLong("booking_id")));
					passenger.setPassengerName(resultSet2.getString("passenger_name"));
					passenger.setPassengerId(BigInteger.valueOf(resultSet2.getLong("passenger_id")));
					passenger.setPassengerAge(resultSet2.getInt("passenger_age"));
					passenger.setPassengerGender(resultSet2.getString("passenger_gender").charAt(0));
					passengersList.add(passenger);
				}
				//
				booking.setUserId(BigInteger.valueOf(resultSet.getLong("user_id")));
				booking.setTransactionId(BigInteger.valueOf(resultSet.getLong("transaction_id")));
				booking.setDateOfJourney(resultSet.getTimestamp("date_of_journey").toLocalDateTime().toLocalDate()); // converting
				// from
				// timestamp
				// to
				// localdate
				BigInteger busId = BigInteger.valueOf(resultSet.getLong("bus_id"));
				booking.setBus(findBusById(busId));
				// findPassengerListById(); find passengerListById
				// booking.setPassengers(resultSet);
				booking.setPassengers(passengersList);
				booking.setTotalCost(resultSet.getDouble("total_cost"));
				booking.setModeOfPayment(resultSet.getString("mode_of_payment"));
				booking.setBookingStatus(resultSet.getString("booking_status"));
			}

		} catch (SQLException e) {
			System.out.println(" Error at findBookingById Dao method : " + e);

			myLogger.error(" Error at findBookingById Dao method : " + e);

		} finally {
			if (preparedStatement2 != null) {
				try {
					preparedStatement2.close();
				} catch (SQLException e) {

					System.out.println(" Error at findBookingById Dao method : " + e);
					myLogger.error(" Error at findBookingById Dao method : " + e);

					System.out.println(" Error at findBookingById Dao method : " + e);
					myLogger.error(" Error at findBookingById Dao method : " + e);

				}
			}
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {

					System.out.println(" Error at findBookingById Dao method : " + e);
					myLogger.error(" Error at findBookingById Dao method : " + e);

					System.out.println(" Error at findBookingById Dao method : " + e);
					myLogger.error(" Error at findBookingById Dao method : " + e);

				}
			}
		}
		/*
		 * for(Booking booking2:bookingsList) {
		 * if(booking2.getBookingId().equals(bookingId)) { return booking2; } } return
		 * null;
		 */
		return booking;
	}

	public List<Booking> findBookingByTransactionId(BigInteger transactionId) {
		List<Booking> bookings = new ArrayList<Booking>();
		String sql = "SELECT * FROM booking WHERE transactionId=? AND delete_flag=0";
		Booking booking = new Booking();
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setLong(1, transactionId.longValue());

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				booking.setBookingId(BigInteger.valueOf(resultSet.getLong(1)));
				booking.setDateOfJourney(resultSet.getTimestamp("date_of_journey").toLocalDateTime().toLocalDate());
				booking.setUserId(BigInteger.valueOf(resultSet.getLong("user_id")));
				booking.setBookingStatus(resultSet.getString("booking_status"));
				booking.setModeOfPayment(resultSet.getString("mode_of_payment"));
				booking.setTotalCost(resultSet.getDouble("total_cost"));
				BigInteger bookingId = BigInteger.valueOf(resultSet.getLong("bus_id"));
				Bus bus = findBusById(bookingId);
				booking.setBus(bus);
				List<Passenger> passengers = findPassengersByBookingId(bookingId);
				booking.setPassengers(passengers);
				booking.setTransactionId(BigInteger.valueOf(resultSet.getLong("transaction_id")));
			}
		} catch (SQLException e) {
			System.out.println(" Error at findBookingById Dao method : " + e);
			myLogger.error(" Error at findBookingById Dao method : " + e);
		} finally {
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					System.out.println(" Error at findBookingById Dao method : " + e);
					myLogger.error(" Error at findBookingById Dao method : " + e);
				}
			}
		}
		return bookings;
	}

	@Override
	public Passenger savePassenger(Passenger passenger) {
		// TODO Auto-generated method stub
		// passengersList.add(passenger);

		String sql = "INSERT INTO passenger(booking_id, passenger_name, passenger_age, passenger_gender,delete_flag) VALUES (?,?,?,?,0)";
		int noOfRecs = 0;
		try {
			preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			preparedStatement.setLong(1, passenger.getBookingId().longValue());
			preparedStatement.setString(2, passenger.getPassengerName());
			preparedStatement.setInt(3, passenger.getPassengerAge());
			preparedStatement.setString(4, passenger.getPassengerGender().toString());

			noOfRecs = preparedStatement.executeUpdate();
			resultSet = preparedStatement.getGeneratedKeys();
			while (resultSet.next()) {
				passenger.setPassengerId(BigInteger.valueOf(resultSet.getLong("passenger_id")));
			}

		} catch (SQLException e) {
			System.out.println(" Error at savePassenger Dao method : " + e);
			myLogger.error(" Error at savePassenger Dao method : " + e);
		} finally {
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					System.out.println(" Error at savePassenger Dao method : " + e);
					myLogger.error(" Error at savePassenger Dao method : " + e);
				}
			}
		}

		return passenger;
	}

	@Override
	public List<Passenger> findPassengersByBookingId(BigInteger bookingId) {
		// TODO Auto-generated method stub
		// return passengersList;
		String sql = "select * from passenger where booking_id=? AND delete_flag=0";
		try {

			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setLong(1, bookingId.longValue());

			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Passenger passenger = new Passenger();
				passenger.setBookingId(BigInteger.valueOf(resultSet.getLong("booking_id")));
				passenger.setPassengerName(resultSet.getString("passenger_name"));
				passenger.setPassengerId(BigInteger.valueOf(resultSet.getLong("passenger_id")));
				passenger.setPassengerAge(resultSet.getInt("passenger_age"));
				passenger.setPassengerGender(resultSet.getString("passenger_gender").charAt(0));
				passengersList.add(passenger);
			}
		} catch (SQLException e) {
			System.out.println(" Error at findPassengerByBookingId Dao method : " + e);
			myLogger.error(" Error at findPassengerByBookingId Dao method : " + e);
		} finally {
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					System.out.println(" Error at findPassengerByBookingId Dao method : " + e);
					myLogger.error(" Error at findPassengerByBookingId Dao method : " + e);
				}
			}
		}
		return passengersList;

	}

	@Override
	public Passenger findPassengerByName(String pname) {
		// TODO Auto-generated method stub
		/*
		 * for(Passenger p:passengersList) {
		 * if(pname.equalsIgnoreCase(p.getPassengerName())) { return p; } } return null;
		 */
		String sql = "select * from passenger where passenger_name=? AND delete_flag=0";
		Passenger passenger = new Passenger();
		try {

			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, pname);

			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {

				passenger.setBookingId(BigInteger.valueOf(resultSet.getLong("booking_id")));
				passenger.setPassengerName(resultSet.getString("passenger_name"));
				passenger.setPassengerId(BigInteger.valueOf(resultSet.getLong("passenger_id")));
				passenger.setPassengerAge(resultSet.getInt("passenger_age"));
				passenger.setPassengerGender(resultSet.getString("passenger_gender").charAt(0));
			}
		} catch (SQLException e) {
			System.out.println(" Error at findPassengerByName Dao method : " + e);
			myLogger.error(" Error at findPassengerByName Dao method : " + e);
		} finally {
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					System.out.println(" Error at findPassengerByName Dao method : " + e);
					myLogger.error(" Error at findPassengerByName Dao method : " + e);
				}
			}
		}
		return passenger;

	}

	@Override
	public Bus saveBus(Bus bus) {
		// TODO Auto-generated method stub
		// busList.add(bus);
		String sql = "INSERT INTO bus(bus_name, bus_type, bus_class, no_of_seats,source, destination,cost,delete_flag) VALUES (?,?,?,?,?,?,?,0)";
		int noOfRecs = 0;
		try {
			preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			preparedStatement.setString(1, bus.getBusName());
			preparedStatement.setString(2, bus.getBusType().name());
			preparedStatement.setString(3, bus.getBusClass().name());
			preparedStatement.setInt(4, bus.getNoOfSeats());
			preparedStatement.setString(5, bus.getSource());
			preparedStatement.setString(6, bus.getDestination());
			preparedStatement.setDouble(7, bus.getCost());


			noOfRecs = preparedStatement.executeUpdate();
			myLogger.info(noOfRecs + " rows inserted");

			BigInteger generatedId = BigInteger.valueOf(0L);
			resultSet = preparedStatement.getGeneratedKeys();

			if (resultSet.next()) {
				bus.setBusId(BigInteger.valueOf(resultSet.getLong(1)));
				myLogger.info("Auto generated id: " + generatedId);
			}
			int returnedVal =saveBusDay(bus.getDayOfJourney(),bus.getBusId());
			myLogger.info(returnedVal+" days added to bus Id "+bus.getBusId());

		} catch (SQLException e) {
			System.out.println(" Error at saveBus Dao method : " + e);
			myLogger.error(" Error at saveBus Dao method : " + e);
		} finally {
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					System.out.println(" Error at saveBus Dao method : " + e);
					myLogger.error(" Error at saveBus Dao method : " + e);
				}
			}
		}

		// return passenger;
		return bus;
	}

	@Override
	public int saveBusDay(List<DayOfWeek> dayOfWeek, BigInteger busId) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO bus_day(bus_id,day,delete_flag) VALUES (?,?,0)";
		int noOfRecs = 0;
		try {
			preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			for (int i = 0; i < dayOfWeek.size(); i++) {
				preparedStatement.setLong(1, busId.longValue());
				preparedStatement.setString(2, dayOfWeek.get(i).toString());
				noOfRecs = preparedStatement.executeUpdate();

			}

		} catch (SQLException e) {
			System.out.println(" Error at saveBusDay Dao method : " + e);
			myLogger.error(" Error at saveBusDay Dao method : " + e);
		} finally {
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					System.out.println(" Error at saveBusDay Dao method : " + e);
					myLogger.error(" Error at saveBusDay Dao method : " + e);
				}
			}
		}

		return noOfRecs;
	}
	
	public int removeBusDayByBusId(BigInteger busId) {
		String sql = "UPDATE bus_day SET delete_flag=1 WHERE bus_id=?";
		int noOfRec=0;
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setLong(1, busId.longValue());
			
			noOfRec = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println(" Error at removeBusDayByBusId Dao method : " + e);
			myLogger.error(" Error at removeBusDayByBusId Dao method : " + e);
		} finally {
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					System.out.println(" Error at removeBusDayByBusId Dao method : " + e);
					myLogger.error(" Error at removeBusDayByBusId Dao method : " + e);
				}
			}
		}
		return noOfRec;
	}

	public List<DayOfWeek> findDayOfWeekByBus(BigInteger busId) {
		List<DayOfWeek> days = new ArrayList<DayOfWeek>();
		String sql = "SELECT * FROM bus_day WHERE bus_id=? AND delete_flag=0";

		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setLong(1, busId.longValue());

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				days.add(DayOfWeek.valueOf(resultSet.getString(3)));
			}
		} catch (SQLException e) {
			System.out.println(" Error at findBusDay Dao method : " + e);
			myLogger.error(" Error at findBusDay Dao method : " + e);
		} finally {
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					System.out.println(" Error at findBusDay Dao method : " + e);
					myLogger.error(" Error at findBusDay Dao method : " + e);
				}
			}
		}

		return days;
	}

	@Override
	public Integer removeBus(BigInteger busId) {
		// TODO Auto-generated method stub
		/*
		 * Bus b=this.findBusById(busId); if(b==null) { return 0; } busList.remove(b);
		 * return 1;
		 */
		String sql = "UPDATE bus SET delete_flag=1 WHERE bus_id=?;";

		int noOfRec = 0;
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setLong(1, busId.longValue());

			noOfRec = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println(" Error at removeBus Dao method : " + e);
			myLogger.error(" Error at removeBus Dao method : " + e);
		} finally {
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					System.out.println(" Error at removeBus Dao method : " + e);
					myLogger.error(" Error at removeBus Dao method : " + e);
				}
			}
		}
		return noOfRec;
	}

	@Override
	public List<Bus> findAllBuses() {
		// TODO Auto-generated method stub
		/*
		 * return busList;
		 */

		String sql = "select * from bus where delete_flag=0";
		// String sql2 = "select * from passenger where booking_id=? AND delete_flag=0";
		List<Bus> busList = new ArrayList<Bus>();
		Bus bus = new Bus();
		try {
			preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			// for select queries we have executeQuery method which returns ResultSet
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				// create booking object

				// get the value from rs and set to booking obj
				bus.setBusId(BigInteger.valueOf(resultSet.getLong(1)));

				bus.setBusName(resultSet.getString("bus_name"));
				bus.setCost(resultSet.getDouble("cost"));
				// bus.setBusClass(resultSet.getString("bus_class")); //ENUMERATION x2
				bus.setSource(resultSet.getString("source"));
				bus.setDestination(resultSet.getString("destination"));
				bus.setNoOfSeats(resultSet.getInt("no_of_seats"));

				List<DayOfWeek> days = new ArrayList<DayOfWeek>();
				days = findDayOfWeekByBus(bus.getBusId());
				bus.setDayOfJourney(days);

				busList.add(bus);

			}
		} catch (SQLException e) {
			System.out.println(" Error at findAllBuses Dao method : " + e);
			myLogger.error(" Error at findAllBuses Dao method : " + e);
		} finally {
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					System.out.println(" Error at findAllBuses Dao method : " + e);
					myLogger.error(" Error at findAllBuses  Dao method : " + e);
				}
			}
		}
		return busList;
	}

	@Override
	public Bus findBusById(BigInteger busId) {
		// TODO Auto-generated method stub

		/*
		 * for(Bus b:busList) { if(busId.equals(b.getBusId())) { return b; } } return
		 * null;
		 */
		String sql = "select * from bus where delete_flag=0 AND bus_id=?";
		// String sql2 = "select * from passenger where booking_id=? AND delete_flag=0";
		// List<Bus> busList = new ArrayList<Bus>();
		Bus bus = new Bus();
		try {
			preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setLong(1, busId.longValue());
			// for select queries we have executeQuery method which returns ResultSet
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				// create booking object

				// get the value from rs and set to booking obj
				bus.setBusId(BigInteger.valueOf(resultSet.getLong(1)));

				bus.setBusName(resultSet.getString("bus_name"));
				bus.setCost(resultSet.getDouble("cost"));
				// bus.setBusClass(resultSet.getString("bus_class")); //ENUMERATION x2
				bus.setSource(resultSet.getString("source"));
				bus.setDestination(resultSet.getString("destination"));
				bus.setNoOfSeats(resultSet.getInt("no_of_seats"));

				List<DayOfWeek> days = new ArrayList<DayOfWeek>();
				days = findDayOfWeekByBus(bus.getBusId());
				bus.setDayOfJourney(days);

				// busList.add(bus);

			}
		} catch (SQLException e) {
			System.out.println(" Error at findBusById Dao method : " + e);
			myLogger.error(" Error at findBusById Dao method : " + e);
		} finally {
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					System.out.println(" Error at findBusById Dao method : " + e);
					myLogger.error(" Error at findBusById  Dao method : " + e);
				}
			}
		}
		return bus;
	}

	public List<BusTransaction> getTransactionList() {

		return transactionList;

	}

	public void setTransactionList(List<BusTransaction> transactionList) {
		this.transactionList = transactionList;
	}

	@Override
	public BusTransaction saveTransaction(BusTransaction busTransaction) {
		// TODO Auto-generated method stub
		/*
		 * transactionList.add(busTransaction); return busTransaction;
		 */

		String sql = "INSERT INTO bus_transaction(date,bus_id,available_seats,transaction_status,delete_flag) VALUES (?,?,?,?,0)";
		int noOfRec = 0;
		try {
			preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setTimestamp(1, Timestamp.valueOf(busTransaction.getDate().atStartOfDay()));
			preparedStatement.setLong(2, busTransaction.getBus().getBusId().longValue());
			preparedStatement.setInt(3, busTransaction.getAvailableSeats());
			preparedStatement.setString(4, busTransaction.getTicketStatus());

			noOfRec = preparedStatement.executeUpdate();
			resultSet = preparedStatement.getGeneratedKeys();

			while (resultSet.next()) {
				busTransaction.setTransactionId(BigInteger.valueOf(resultSet.getLong(1)));
			}

		} catch (SQLException e) {
			System.out.println(" Error at saveTransaction Dao method : " + e);
			myLogger.error(" Error at saveTransaction Dao method : " + e);
		} finally {
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					System.out.println(" Error at saveTransaction Dao method : " + e);
					myLogger.error(" Error at saveTransaction  Dao method : " + e);
				}
			}
		}
		return busTransaction;

	}

	@Override
	public Integer removeTransaction(BigInteger transactionId) {
		// TODO Auto-generated method stub
		/*
		 * for (BusTransaction t : transactionList) { if (bus.equals(t.getBus())) {
		 * transactionList.remove(t); return 1; } } return 0;
		 */
		String sql = "UPDATE bus_transaction SET delete_flag=1 WHERE transaction_id=? ";
		int noOfRec = 0;
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setLong(1, transactionId.longValue());

			noOfRec = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println(" Error at removeTransaction Dao method : " + e);
			myLogger.error(" Error at removeTransaction Dao method : " + e);
		} finally {
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					System.out.println(" Error at removeTransaction Dao method : " + e);
					myLogger.error(" Error at removeTransaction  Dao method : " + e);
				}
			}
		}
		return noOfRec;

	}

	public List<BusTransaction> findAllTransactions() { // TODO Auto-generated method stub
		String sql = "SELECT * FROM bus_transaction WHERE delete_flag=0";
		int noOfRec = 0;
		BusTransaction busTransaction = new BusTransaction();
		try {
			preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				busTransaction.setTransactionId(BigInteger.valueOf(resultSet.getLong(1)));
				busTransaction.setDate(resultSet.getTimestamp("date").toLocalDateTime().toLocalDate());
				busTransaction.setAvailableSeats(resultSet.getInt("available_seats"));
				busTransaction.setBookings(findBookingByTransactionId(BigInteger.valueOf(resultSet.getLong(1))));
				busTransaction.setTicketStatus(resultSet.getString("transaction_status"));
				busTransaction.setBus(findBusById(BigInteger.valueOf(resultSet.getLong("bus_id"))));
				// busTransaction.setBookings(bookings);
				transactionList.add(busTransaction);
			}

		} catch (SQLException e) {
			System.out.println(" Error at findAllTransactions Dao method : " + e);
			myLogger.error(" Error at findAllTransactions Dao method : " + e);
		} finally {
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					System.out.println(" Error at findAllTransactions Dao method : " + e);
					myLogger.error(" Error at findAllTransactions  Dao method : " + e);
				}
			}
		}
		return transactionList;
	}

	@Override
	public List<BusTransaction> findTransactionsByDate(LocalDate date) {
		// TODO Auto-generated method stub
		List<BusTransaction> transactionsByDate = new ArrayList<BusTransaction>();
		String sql = "SELECT * FROM bus_transaction WHERE date=? AND delete_flag=0";
		/*
		 * for (BusTransaction t : transactionList) { if (date.equals(t.getDate())) {
		 * transactionsByDate.add(t); } }
		 */
		BusTransaction busTransaction = new BusTransaction();

		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setTimestamp(1, Timestamp.valueOf(date.atStartOfDay()));

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				busTransaction.setTransactionId(BigInteger.valueOf(resultSet.getLong(1)));
				busTransaction.setAvailableSeats(resultSet.getInt("available_seats"));
				busTransaction.setDate(resultSet.getTimestamp("date").toLocalDateTime().toLocalDate());
				busTransaction.setTicketStatus(resultSet.getString("status"));
				busTransaction.setBus(findBusById(BigInteger.valueOf(resultSet.getLong("bus_id"))));
				busTransaction.setBookings(
						findBookingByTransactionId(BigInteger.valueOf(resultSet.getLong("transaction_id"))));
				transactionsByDate.add(busTransaction);
			}

		} catch (SQLException e) {
			System.out.println(" Error at findTransactionsByDate Dao method : " + e);
			myLogger.error(" Error at findTransactionsByDate Dao method : " + e);
		} finally {
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					System.out.println(" Error at findTransactionsByDate Dao method : " + e);
					myLogger.error(" Error at findTransactionsByDate  Dao method : " + e);
				}
			}
		}

		return transactionsByDate;
	}

}
