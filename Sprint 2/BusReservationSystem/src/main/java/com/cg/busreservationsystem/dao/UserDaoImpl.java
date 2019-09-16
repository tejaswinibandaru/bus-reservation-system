package com.cg.busreservationsystem.dao;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
	//prep -work 1- Connection
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

	private List<BusTransaction> transactionList=new ArrayList<BusTransaction>();
	private List<Bus> busList=new ArrayList<Bus>();

	List<Booking> bookingsList=new ArrayList<Booking>();
	List<Passenger> passengersList=new ArrayList<Passenger>();

	@Override
	public Booking saveBooking(Booking booking) {
		// TODO Auto-generated method stub
		//added to AL
		String sql ="INSERT INTO booking(user_id,transaction_id,bus_id,date_of_journey,mode_of_payment,total_cost) values(?,?,?,?,?,?);";		
		try {
			//step1 : obtain ps 
			preparedStatement= connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			//step 2: set the ps placeholder values

			preparedStatement.setLong(2, booking.getTransactionId().longValue());								//getTrsansactionId
			preparedStatement.setLong(3, booking.getBus().getBusId().longValue());
			//preparedStatement.setTimestamp(4, booking.getDateOfJourney().);				//to timestamp
			preparedStatement.setString(5, booking.getModeOfPayment());
			preparedStatement.setDouble(6, booking.getTotalCost());
			//step 3: execute Query (for DML we have executeUpdate method )
			int noOfRec = preparedStatement.executeUpdate();
			//getting the auto-generated value
			BigInteger generatedId = BigInteger.valueOf(0L);
			resultSet = preparedStatement.getGeneratedKeys();
			if (resultSet.next()) {
				generatedId = BigInteger.valueOf(resultSet.getLong("booking_id"));
				myLogger.info("Auto generated Id " + generatedId);
			}
			//setting the auto-generated Id to current booking obj
			booking.setBookingId(generatedId);
		} catch (SQLException e) {
			myLogger.error(" Error at saveBooking Dao method : "+e);
			throw new BookingException(" Error at saveBooking Dao method : "+e);
		}finally {
			if(preparedStatement!=null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					System.out.println(" Error at saveBooking Dao method : "+e);
				}
			}
		}

		bookingsList.add(booking);
		return booking;
	}

	@Override
	public Integer removeBooking(BigInteger bookingId) {
		// TODO Auto-generated method stub
		String sql ="UPDATE booking SET delete_flag=1 WHERE booking_id=?;";				
		int noOfRec=0;
		try {
			preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setLong(1, bookingId.longValue());

			noOfRec=preparedStatement.executeUpdate();
		}catch (SQLException e) {
			System.out.println(" Error at remove booking Dao method : "+e);
			myLogger.error(" Error at saveBooking Dao method : "+e);
		}finally {
			if(preparedStatement!=null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					System.out.println(" Error at remove booking Dao method : "+e);
					myLogger.error(" Error at saveBooking Dao method : "+e);
				}
			}
		}
		return noOfRec;
	}
	/*Booking b=this.findBookingById(bookingId);
		if(b==null) {
			return 0;
		}
		bookingsList.remove(b);
		return 1;
	}*/

	@Override
	public List<Booking> findAllBookings() {
		// TODO Auto-generated method stub
		String sql ="select * from booking where delete_flag=0";
		String sql2 = "select * from passenger where booking_id=? AND delete_flag=0";
		List<Booking> bookingList = new ArrayList<Booking>();	
		try {
			preparedStatement= connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			//for select queries we have executeQuery method which returns ResultSet
			resultSet= preparedStatement.executeQuery();
			while (resultSet.next()) {
				//create booking object
				Booking booking = new Booking();
				//get the value from rs and set to booking obj
				booking.setBookingId(BigInteger.valueOf(resultSet.getLong("booking_id")));
				//
				PreparedStatement preparedStatement2 =connection.prepareStatement(sql2);
				preparedStatement2.setLong(1, booking.getBookingId().longValue());
				ResultSet resultSet2 =preparedStatement2.executeQuery();
				while(resultSet2.next())
				{
					Passenger passenger= new Passenger();
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
				//booking.setDateOfJourney(resultSet.getTimestamp("date_of_journey").toLocalDate());		//converting from timestamp to localdate
				BigInteger busId = BigInteger.valueOf(resultSet.getLong("bus_id"));
				booking.setBus(findBusById(busId));
				//findPassengerListById();						find passengerListById
				//booking.setPassengers(resultSet);
				booking.setPassengers(passengersList);
				booking.setTotalCost(resultSet.getDouble("total_cost"));
				booking.setModeOfPayment(resultSet.getString("mode_of_payment"));
				booking.setBookingStatus(resultSet.getString("booking_status"));
				//add the booking obj to bookingList
				bookingList.add(booking);

			}
		} catch (SQLException e) {
			System.out.println(" Error at findAllBookings Dao method : "+e);
			myLogger.error(" Error at saveBooking Dao method : "+e);
		}finally {
			if(preparedStatement!=null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					System.out.println(" Error at findAllBookings Dao method : "+e);
					myLogger.error(" Error at saveBooking Dao method : "+e);
				}
			}
		}
		return bookingList;
	}



	@Override
	public Booking findBookingById(BigInteger bookingId) {

		String sql ="SELECT * FROM booking WHERE delete_flag=0 AND booking_id=?";
		String sql2 = "select * from passenger where booking_id=? AND delete_flag=0";
		Booking booking=  new Booking();
		// TODO Auto-generated method stub
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setLong(1, bookingId.longValue());
			

			resultSet= preparedStatement.executeQuery();
			while(resultSet.next())
			{
				booking.setBookingId(bookingId);
				//booking.setDateOfJourney(resultSet.getTimestamp("date_of_journey"));		//timestamp to localdate

				PreparedStatement preparedStatement2 =connection.prepareStatement(sql2);
				preparedStatement2.setLong(1, booking.getBookingId().longValue());
				ResultSet resultSet2 =preparedStatement2.executeQuery();
				while(resultSet2.next())
				{
					Passenger passenger= new Passenger();
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
				//booking.setDateOfJourney(resultSet.getTimestamp("date_of_journey").toLocalDate());		//converting from timestamp to localdate
				BigInteger busId = BigInteger.valueOf(resultSet.getLong("bus_id"));
				booking.setBus(findBusById(busId));
				//findPassengerListById();						find passengerListById
				//booking.setPassengers(resultSet);
				booking.setPassengers(passengersList);
				booking.setTotalCost(resultSet.getDouble("total_cost"));
				booking.setModeOfPayment(resultSet.getString("mode_of_payment"));
				booking.setBookingStatus(resultSet.getString("booking_status"));
			}
		}catch (SQLException e) {
			System.out.println(" Error at findAllBookings Dao method : "+e);
			myLogger.error(" Error at saveBooking Dao method : "+e);
		}finally {
			if(preparedStatement!=null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					System.out.println(" Error at findAllBookings Dao method : "+e);
					myLogger.error(" Error at saveBooking Dao method : "+e);
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

	@Override
	public Passenger savePassenger(Passenger passenger) {
		// TODO Auto-generated method stub
		passengersList.add(passenger);
		return passenger;
	}

	@Override
	public List<Passenger> findAllPassengers() {
		// TODO Auto-generated method stub
		return passengersList;
	}

	@Override
	public Passenger findPassengerByName(String pname) {
		// TODO Auto-generated method stub
		for(Passenger p:passengersList) {
			if(pname.equalsIgnoreCase(p.getPassengerName())) {
				return p;
			}
		}
		return null;
	}

	@Override
	public Bus saveBus(Bus bus){
		// TODO Auto-generated method stub
		busList.add(bus);
		return bus;
	}

	@Override
	public Integer removeBus(BigInteger busId) {
		// TODO Auto-generated method stub
		Bus b=this.findBusById(busId);
		if(b==null) {
			return 0;
		}
		busList.remove(b);
		return 1;
	}

	@Override
	public List<Bus> findAllBuses() {
		// TODO Auto-generated method stub
		return busList;
	}

	@Override
	public Bus findBusById(BigInteger busId) {
		// TODO Auto-generated method stub

		for(Bus b:busList) {
			if(busId.equals(b.getBusId())) {
				return b;
			}
		}
		return null;
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
		transactionList.add(busTransaction);
		return busTransaction;
	}

	@Override
	public Integer removeTransaction(Bus bus) {
		// TODO Auto-generated method stub
		for(BusTransaction t:transactionList) {
			if(bus.equals(t.getBus())) {
				transactionList.remove(t);
				return 1;
			}
		}
		return 0;
	}


	/*@Override
	 * public List<BusTransaction> findAllTransactions() { // TODO Auto-generated
	 * method stub return transactionList; }
	 */

	@Override
	public List<BusTransaction> findTransactionsByDate(LocalDate date) {
		// TODO Auto-generated method stub
		List<BusTransaction> transactionsByDate=new ArrayList<BusTransaction>();
		for(BusTransaction t:transactionList) {
			if(date.equals(t.getDate())) {
				transactionsByDate.add(t);
			}
		}

		return transactionsByDate; 
	} 

}
