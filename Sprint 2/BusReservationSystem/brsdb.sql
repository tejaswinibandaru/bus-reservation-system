create database brsdb;

use brsdb;

CREATE table user(
user_id bigint PRIMARY KEY auto_increment  NOT NULL,
user_name varchar(30) NOT NULL,
password varchar(30) NOT NULL,
user_type varchar(30) NOT NULL,
email varchar(30)  NOT NULL,
phone_number bigint  NOT NULL,
delete_flag int  NOT NULL);

CREATE table bus(
bus_id bigint PRIMARY KEY AUTO_INCREMENT NOT NULL,
bus_name varchar(30) NOT NULL,
bus_type varchar(30) NOT NULL,
bus_class varchar(30) NOT NULL,
no_of_seats int NOT NULL,
source varchar(30)  NOT NULL,
destination varchar(30)  NOT NULL,
cost decimal  NOT NULL 
delete_flag int  NOT NULL);

CREATE table bus_day(
bus_day_id bigint PRIMARY KEY AUTO_INCREMENT NOT NULL,
bus_id bigint FOREIGN KEY REFERENCES bus(bus_id) NOT NULL,
day varchar(30)  NOT NULL,
delete_flag int  NOT NULL);

CREATE table booking(
booking_id bigint PRIMARY KEY auto_increment  NOT NULL,
user_id bigint FOREIGN KEY NOT NULL,
transaction_id bigint  FOREIGN KEY NOT NULL,
bus_id bigint  FOREIGN KEY NOT NULL,
date_of_journey TIMESTAMP NOT NULL,
mode_of_payment varchar(30)  NOT NULL,
total_cost decimal  NOT NULL ,
status varchar  NOT NULL,
delete_flag int  NOT NULL);

CREATE table passenger(
passenger_id bigint PRIMARY KEY auto_increment  NOT NULL,
booking_id bigint FOREIGN KEY auto_increment  NOT NULL,
passenger_name varchar(30)  NOT NULL,
passenger_age int  NOT NULL,
passenger_gender char  NOT NULL,
delete_flag int  NOT NULL);

CREATE table transaction(
transaction_id bigint PRIMARY KEY auto_increment  NOT NULL,
date TIMESTAMP NOT NULL,
bus_id bigint FOREIGN KEY AUTO_INCREMENT NOT NULL,
available_seats int NOT NULL,
status varchar NOT NULL,
delete_flag int  NOT NULL);

#saveBooking
INSERT INTO booking(user_id,transaction_id,bus_id,date_of_journey,mode_of_payment,total_cost) values(?,?,?,?,?,?);

#if required use this,after booking
UPDATE booking SET status=? AND delete_flag=? WHERE booking_id=?;

#removeBooking
DELETE FROM booking WHERE booking_id=?;

#list all bookings
SELECT * FROM booking;

#list bookings by booking id
SELECT b.booking_id,b.date_of_journey,p.passenger_name,p.passenger_age,p.passenger_gender,b.mode_of_payment,b.total_cost,b.status FROM
booking b JOIN passenger p ON b.booking_id=p.booking_id WHERE b.booking_id=?;


#savePassenger
INSERT INTO passenger 
____________________
		String sql ="insert into booking(booking_id,user_id,transaction_id,bus_id,date_of_journey,mode_of_payment,total_cost,status,delete_flag) values(?,?,?,?,?,?,?,?,?)";		
	String sql ="delete from booking where booking_id=?";
String sql ="select * from booking";
		String sql= "select * from booking where booking_id=?";
		String sql="insert into passenger (passenger_id,booking_id,passenger_name,passenger_age,passenger_gender,delete_flag) values(?,?,?,?,?,?) ";
		String sql="select * from passenger";
		String sql="select * from passenger where passenger_name=?";

		String sql="insert into bus(bus_id,bus_name,bus_type,bus_class,no_of_seats,source,destination,cost,delete_flag) values(?,?,?,?,?,?,?,?,?)";
		String sql="delete from bus where bus_id=?";
		String sql="select * from bus";
		String sql="select * from bus where bus_id=?";
		String sql="select * from transaction where (transaction.bus_id= bus.bus_id";
		String sql="select * from transaction where date=?";
