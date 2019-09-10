package com.cg.busreservationsystem.service;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;

import com.cg.busreservationsystem.dto.Bus;
import com.cg.busreservationsystem.dto.Transaction;

public interface AdminService {
	
	public Bus addBusDetails(Bus bus);
	public Integer removeBusDetails(BigInteger busId);
	public Bus searchBus(BigInteger busId);
	public List<Bus> viewBuses();
	public List<Transaction> getTransactionsByDate(LocalDate date);

}
