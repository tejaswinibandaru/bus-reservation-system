package com.cg.busreservationsystem.service;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.cg.busreservationsystem.dao.BusDao;
import com.cg.busreservationsystem.dao.BusDaoImpl;
import com.cg.busreservationsystem.dao.TransactionDaoImpl;
import com.cg.busreservationsystem.dto.Bus;
import com.cg.busreservationsystem.dto.BusTransaction;
import com.cg.busreservationsystem.exception.BusException;

public class AdminServiceImpl implements AdminService{
	
	public BusDao busDao = new BusDaoImpl();
	//BookingDao bookingDao = new BookingDaoImpl();
	public TransactionDaoImpl transactionDao = new TransactionDaoImpl();	//transDao in CSI

	@Override
	public Bus addBusDetails(Bus bus) {
		// TODO Auto-generated method stub
		return busDao.saveBus(bus);
	}

	@Override
	public Integer removeBusDetails(BigInteger busId) {
		// TODO Auto-generated method stub
		return busDao.removeBus(busId);
	}

	@Override
	public Bus searchBus(BigInteger busId) {
		// TODO Auto-generated method stub
		return busDao.findBusById(busId);
	}

	@Override
	public List<Bus> viewBuses() {
		// TODO Auto-generated method stub
		return busDao.findAllBuses();
	}

	@Override
	public List<BusTransaction> getTransactionsByDate(LocalDate date) {
		// TODO Auto-generated method stub
		return transactionDao.findTransactionsByDate(date);
	}
	
	public static void validateBusType(int busType) {
		if(busType < 0 || busType > 1) {
			 throw new BusException("Wrong bus type");
		}
	}
	
	public static int checkNumberInput() throws InputMismatchException{
		Scanner sc=new Scanner(System.in);
		try {
			return sc.nextInt();
		}catch (InputMismatchException e) {
			// TODO: handle exception
			throw new InputMismatchException("Wrong input type");
		}
		
	}

}
