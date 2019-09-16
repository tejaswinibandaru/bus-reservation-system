package com.cg.busreservationsystem.util;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.cg.busreservationsystem.exception.BusException;
public class DBUtil {
	
	private static Logger myLogger;
    private static Connection connection;
    static{
  	
  	  Properties props = System.getProperties();
  	  String userDir= props.getProperty("user.dir")+"\\src\\main\\resources\\";

  	  System.out.println("Current working directory is " +userDir);
  	  PropertyConfigurator.configure(userDir+"log4j.properties");
		myLogger=Logger.getLogger("DBUtil.class");
		}
    public static Connection getConnection() throws BusException {
  	  
  	  String driver,url,username, password;
  	  try {
  		//creating properties and load the properties
  			Properties prop=DBUtil.loadProp();
  		
  		  //get properties from file
  		  //driver = prop.getProperty("driver");
  		  url = prop.getProperty("url");
  		  username = prop.getProperty("user");
  		  password = prop.getProperty("password");
  		  
  		  //loading and registering the driver
  		 // Class.forName(driver);
  		  
  		  //getConnection
  		  connection=DriverManager.getConnection(url, username, password);
  		  if(connection!=null)
  			  myLogger.info("connection Obtained! : "+connection);				
  			//  System.out.println("connection Obtained!");
  		  else
  			  throw new BusException("sorry!!! Something went wrong"
    			  		+ " with the connection");
  	  }catch(Exception e) {
  		  throw new BusException(e.getMessage());
  	  }
  	   return connection;  
    }//end of method
   //method for loading property file 
    private static Properties loadProp() throws BusException {
  	  Properties props = System.getProperties();
  	  String userDir= props.getProperty("user.dir")+"\\src\\main\\resources\\";

  	  //String userDir= props.getProperty("user.dir")+"JdbcAssignment/src/main/resources/";

  	  Properties myProp=new Properties();
		try (FileInputStream fis=new FileInputStream(userDir+"jdbc.properties"))	{  			
			myProp.load(fis);
			myLogger.info("Property File loaded : ");	
		} 
		catch (Exception e){
			myLogger.error("Property File Not loaded");	
			throw new BusException(e.getMessage());
		}
		return myProp;
	}
//method for closing the connection
	public static void closeConnection() throws BusException {
  	  try {
  		  if(connection !=null) {
  			  connection.close();
  			  myLogger.error("Closing Connection");
  		  }
  		  else
  			  myLogger.error("Connection already closed");
  	  } catch (Exception e) {
  		  myLogger.error("Connection already closed");	
  		  throw new BusException(e.getMessage());
  	  }
    }

}
