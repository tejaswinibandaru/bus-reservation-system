package com.cg.busreservationsystem.dao;

import java.math.BigInteger;
import java.util.List;

import com.cg.busreservationsystem.dto.Bus;

public interface BusDao {
	
	public Bus saveBus(Bus bus);
	public Integer removeBus(BigInteger busId);
	public List<Bus> findAllBuses();
	public Bus findBusById(BigInteger busId);

}
