package com.cg.busreservationsystem.service;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;

import com.cg.busreservationsystem.dao.BookingDao;
import com.cg.busreservationsystem.dao.BookingDaoImpl;
import com.cg.busreservationsystem.dao.BusDao;
import com.cg.busreservationsystem.dao.BusDaoImpl;
import com.cg.busreservationsystem.dao.TransactionDao;
import com.cg.busreservationsystem.dao.TransactionDaoImpl;
import com.cg.busreservationsystem.dto.Bus;
import com.cg.busreservationsystem.dto.Transaction;

public class AdminServiceImpl implements AdminService{
	
	BusDao busDao = new BusDaoImpl();
	BookingDao bookingDao = new BookingDaoImpl();
	TransactionDao transactionDao = new TransactionDaoImpl();

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
	public List<Transaction> getTransactionsByDate(LocalDate date) {
		// TODO Auto-generated method stub
		return transactionDao.findTransactionsByDate(date);
	}

}
