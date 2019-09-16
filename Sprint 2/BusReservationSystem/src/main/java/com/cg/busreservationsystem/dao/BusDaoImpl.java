package com.cg.busreservationsystem.dao;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.cg.busreservationsystem.dto.Bus;
import com.cg.busreservationsystem.exception.BookingException;
import com.cg.busreservationsystem.util.DBUtil;

public class BusDaoImpl implements BusDao{
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

	@Override
	public Bus saveBus(Bus bus){
		String bus_sql="insert into bus(bus_name,bus_type,bus_class,no_of_seats,source,destination,cost";
		String day_sql="insert into bus_day(bus_id,day) values (?,?)";
		try {
			preparedStatement=connection.prepareStatement(bus_sql);
			preparedStatement.setString(1, bus.getBusName());
			preparedStatement.setString(2, bus.getBusType());
			preparedStatement.setString(3, bus.getBusClass());
			preparedStatement.setInt(4, bus.getNoOfSeats());
			preparedStatement.setString(5,bus.getSource());
			preparedStatement.setString(6, bus.getDestination());
			preparedStatement.setDouble(7, bus.getCost());
			
			preparedStatement=connection.prepareStatement(day_sql);
			
		}catch (Exception e) {
			// TODO: handle exception
		}finally {
		}
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

}
