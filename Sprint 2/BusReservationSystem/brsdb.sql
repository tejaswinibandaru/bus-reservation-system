create database brsdb;

use brsdb;

CREATE TABLE user(
user_id BIGINT PRIMARY KEY AUTO_INCREMENT  NOT NULL,
user_name VARCHAR(30) NOT NULL,
password VARCHAR(30) NOT NULL,
user_type VARCHAR(30) NOT NULL,
email VARCHAR(30)  NOT NULL,
phone_number BIGINT  NOT NULL,
delete_flag INT  NOT NULL);


CREATE TABLE bus(
bus_id BIGINT PRIMARY KEY AUTO_INCREMENT NOT NULL,
bus_name VARCHAR(30) NOT NULL,
bus_type VARCHAR(30) NOT NULL,
bus_class VARCHAR(30) NOT NULL,
no_of_seats INT NOT NULL,
source VARCHAR(30)  NOT NULL,
destination VARCHAR(30)  NOT NULL,
cost decimal  NOT NULL, 
delete_flag INT  NOT NULL);

CREATE TABLE bus_day(
bus_day_id BIGINT PRIMARY KEY AUTO_INCREMENT NOT NULL,
bus_id BIGINT NOT NULL,
day VARCHAR(30)  NOT NULL,
delete_flag INT  NOT NULL,
FOREIGN KEY (bus_id) REFERENCES bus(bus_id) );

CREATE TABLE booking(
booking_id BIGINT PRIMARY KEY AUTO_INCREMENT,
user_id BIGINT NOT NULL,
transaction_id BIGINT NOT NULL,
bus_id BIGINT NOT NULL,
date_of_journey TIMESTAMP NOT NULL,
mode_of_payment VARCHAR(30) NOT NULL,
total_cost DECIMAL  NOT NULL ,
booking_status VARCHAR(30) NOT NULL,
delete_flag INT  NOT NULL,
FOREIGN KEY (user_id) REFERENCES user(user_id),
FOREIGN KEY (transaction_id) REFERENCES bus_transaction(transaction_id),
FOREIGN KEY (bus_id) REFERENCES bus(bus_id)
);


CREATE table passenger(
passenger_id BIGINT PRIMARY KEY auto_increment  NOT NULL,
booking_id BIGINT NOT NULL,
passenger_name VARCHAR(30)  NOT NULL,
passenger_age INT  NOT NULL,
passenger_gender CHAR NOT NULL,
delete_flag INT NOT NULL,
FOREIGN KEY (booking_id) REFERENCES booking(booking_id));

CREATE TABLE bus_transaction(
transaction_id BIGINT PRIMARY KEY AUTO_INCREMENT  NOT NULL,
date TIMESTAMP NOT NULL,
bus_id BIGINT  NOT NULL,
available_seats INT NOT NULL,
transaction_status VARCHAR(20) NOT NULL,
delete_flag INT NOT NULL,
FOREIGN KEY (bus_id) REFERENCES bus(bus_id));

#saveBooking
INSERT INTO booking(user_id,transaction_id,bus_id,date_of_journey,mode_of_payment,total_cost) VALUES(?,?,?,?,?,?);

#if required use this,after booking
UPDATE booking SET delete_flag=1 WHERE booking_id=?;

#removeBooking
#DELETE FROM booking WHERE booking_id=?;

#list all bookings
SELECT * FROM booking WHERE delete_flag=0 AND booking_id=?;

#list bookings by booking id
SELECT b.booking_id,b.date_of_journey,p.passenger_name,p.passenger_age,p.passenger_gender,b.mode_of_payment,b.total_cost,b.status FROM
booking b JOIN passenger p ON b.booking_id=p.booking_id WHERE b.booking_id=?;


#savePassenger
INSERT INTO passenger 
____________________
		String sql ="INSERT INTO booking(booking_id,user_id,transaction_id,bus_id,date_of_journey,mode_of_payment,total_cost,status,delete_flag) VALUES(?,?,?,?,?,?,?,?,?)";		
	String sql ="DELETE FROM booking WHERE booking_id=?";
String sql ="SELECT * FROM booking";
		String sql= "SELECT * FROM booking WHERE booking_id=?";
		String sql="INSERT INTO passenger (passenger_id,booking_id,passenger_name,passenger_age,passenger_gender,delete_flag) VALUES(?,?,?,?,?,?) ";
		String sql="SELECT * FROM passenger";
		String sql="SELECT * FROM passenger WHERE passenger_name=?";

		String sql="INSERT INTO bus(bus_id,bus_name,bus_type,bus_class,no_of_seats,source,destination,cost,delete_flag) VALUES(?,?,?,?,?,?,?,?,?)";
		String sql="DELETE FROM bus WHERE bus_id=?";
		String sql="SELECT * FROM bus";
		String sql="SELECT * FROM bus WHERE bus_id=?";
		String sql="SELECT * FROM transaction WHERE (transaction.bus_id= bus.bus_id";
		String sql="SELECT * FROM transaction WHERE date=?";
