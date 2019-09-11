package com.cg.busreservationsystem.dao;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import com.cg.busreservationsystem.dto.Bus;

public class BusDaoImpl implements BusDao{
	
	private List<Bus> busList=new ArrayList<Bus>();
	

	@Override
	public Bus saveBus(Bus bus){
		// TODO Auto-generated method stub
		busList.add(bus);
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
