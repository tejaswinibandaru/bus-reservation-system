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
import com.cg.busreservationsystem.dto.Passenger;
import com.cg.busreservationsystem.dto.Transaction;
import com.cg.busreservationsystem.exception.BookingException;

//import com.cg.jdbc.ems.util.DBUtil;
import com.cg.busreservationsystem.exception.*;
import com.cg.busreservationsystem.dto.*;;

public class UserDaoImpl implements UserDao {
	// db code starts here
	//prep -work 1- Connection
		private static Connection connection;
		private PreparedStatement ps;
		private Statement st;
		private ResultSet rs;
		private static Logger myLogger;
	     static{   	
	   	  Properties props = System.getProperties();
	   	  String userDir= props.getProperty("user.dir")+"/src/main/resources/";
	   	  System.out.println("Current working directory is " +userDir);
	   	  PropertyConfigurator.configure(userDir+"log4j.properties");
	 		myLogger=Logger.getLogger("DBUtil.class");
	 		}
		static {
			try {
				connection= DBUtil.getConnection();
				myLogger.info("connection Obtained!!");
			} catch (BookingException e) {  //MyException e
				myLogger.error("Connection Not Obtained at EmployeeDao : "+e);
				//System.out.print("Connection Not Obtained at EmployeeDao");
			}
		}
	
	private List<Transaction> transactionList=new ArrayList<Transaction>();
	private List<Bus> busList=new ArrayList<Bus>();

	List<Booking> bookingsList=new ArrayList<Booking>();
	List<Passenger> passengersList=new ArrayList<Passenger>();

	@Override
	public Booking saveBooking(Booking booking) {
		// TODO Auto-generated method stub
		//added to AL
		String sql ="insert into booking(booking_id,total_cost) values(?,?)";		
		try {
		//step1 : obtain ps 
			ps= connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
		//step 2: set the ps placeholder values
			ps.setBigInteger(1, booking.getBookingId());
			ps.setDouble(2, booking.getTotalCost());			
		//step 3: execute Query (for DML we have executeUpdate method )
			int noOfRec = ps.executeUpdate();
		//getting the auto-generated value
			BigInteger generatedId = BigInteger.valueOf(0L);
			rs = ps.getGeneratedKeys();
			if (rs.next()) {
				generatedId = BigInteger.valueOf(rs.getLong(1));
				myLogger.info("Auto generated Id " + generatedId);
			}
		//setting the auto-generated Id to current booking obj
			booking.setBookingId(generatedId);
		} catch (SQLException e) {
			myLogger.error(" Error at saveBooking Dao method : "+e);
			throw new BookingException(" Error at saveBooking Dao method : "+e);
		}finally {
			if(ps!=null) {
				try {
					ps.close();
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
		String sql ="delete from booking where emp_id=?";
		int noOfRec=0;
		try {
			ps=connection.prepareStatement(sql);
			ps.setLong(1, bookingId.longValue());
			
			noOfRec=ps.executeUpdate();
		}catch (SQLException e) {
			System.out.println(" Error at remove booking Dao method : "+e);
		}finally {
			if(ps!=null) {
				try {
					ps.close();
				} catch (SQLException e) {
					System.out.println(" Error at remove booking Dao method : "+e);
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
		String sql ="select * from my_emp";
		List<Booking> bookingList = new ArrayList<Booking>();	
		try {
			ps= connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			//for select queries we have executeQuery method which returns ResultSet
			rs= ps.executeQuery();
			while (rs.next()) {
				//create booking obj
				Booking booking = new Booking();
				//get the value from rs and set to booking obj
				booking.setBookingId(BigInteger.valueOf(rs.getLong(1)));
				booking.setBus(rs.get);
				booking.setTotalCost(rs.getDouble(4));				
				//add the booking obj to bookingList
				bookingList.add(booking);
				
			}
		} catch (SQLException e) {
			System.out.println(" Error at findAllBookings Dao method : "+e);
		}finally {
			if(ps!=null) {
				try {
					ps.close();
				} catch (SQLException e) {
					System.out.println(" Error at findAllBookings Dao method : "+e);
				}
			}
		}
		return bookingList;
	}

	

	@Override
	public Booking findBookingById(BigInteger bookingId) {
		// TODO Auto-generated method stub
		for(Booking book:bookingsList) {
			if(book.getBookingId().equals(bookingId)) {
				return book;
			}
		}
		return null;
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

	public List<Transaction> getTransactionList() {
		return transactionList;
	}

	public void setTransactionList(List<Transaction> transactionList) {
		this.transactionList = transactionList;
	}

	@Override
	public Transaction saveTransaction(Transaction transaction) {
		// TODO Auto-generated method stub
		transactionList.add(transaction);
		return transaction;
	}

	@Override
	public Integer removeTransaction(Bus bus) {
		// TODO Auto-generated method stub
		for(Transaction t:transactionList) {
			if(bus.equals(t.getBus())) {
				transactionList.remove(t);
				return 1;
			}
		}
		return 0;
	}

	
	/*@Override
	 * public List<Transaction> findAllTransactions() { // TODO Auto-generated
	 * method stub return transactionList; }
	 */

	@Override
	public List<Transaction> findTransactionsByDate(LocalDate date) {
		// TODO Auto-generated method stub
		List<Transaction> transactionsByDate=new ArrayList<Transaction>();
		for(Transaction t:transactionList) {
			if(date.equals(t.getDate())) {
				transactionsByDate.add(t);
			}
		}
		
		return transactionsByDate; 
	} 

}
