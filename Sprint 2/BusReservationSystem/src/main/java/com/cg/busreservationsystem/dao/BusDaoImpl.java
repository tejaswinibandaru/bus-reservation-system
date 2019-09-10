package com.cg.busreservationsystem.dao;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import com.cg.busreservationsystem.dto.Bus;
import com.cg.busreservationsystem.exception.BusException;

public class BusDaoImpl implements BusDao{
	
	private List<Bus> busList=new ArrayList<Bus>();
	

	@Override
	public Bus saveBus(Bus bus){
		// TODO Auto-generated method stub
		for(Bus b:busList) {
			if(bus.getBusId()==b.getBusId()) {
				busList.set(busList.indexOf(b), bus);
			}
		}
		if(!busList.contains(bus)) {
			busList.add(bus);
			//throw new BusException("Bus is not working");
		}
		return bus;
	}

	@Override
	public Integer removeBus(BigInteger busId) {
		// TODO Auto-generated method stub
		for(Bus b:busList) {
			if(busId==b.getBusId()) {
				busList.remove(b);
				return 1;
			}
		}
		return 0;
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
			if(busId==b.getBusId()) {
				return b;
			}
		}
		return null;
	}

}
