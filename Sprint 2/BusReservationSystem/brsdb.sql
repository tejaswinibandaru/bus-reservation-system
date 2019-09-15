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