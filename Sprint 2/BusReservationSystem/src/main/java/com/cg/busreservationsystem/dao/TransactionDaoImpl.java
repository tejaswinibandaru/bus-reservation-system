package com.cg.busreservationsystem.dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.cg.busreservationsystem.dto.Bus;
import com.cg.busreservationsystem.dto.BusTransaction;

public class TransactionDaoImpl implements TransactionDao{
	
	private List<BusTransaction> transactionList=new ArrayList<BusTransaction>();
	

	public List<BusTransaction> getTransactionList() {
		return transactionList;
	}

	public void setTransactionList(List<BusTransaction> transactionList) {
		this.transactionList = transactionList;
	}

	@Override
	public BusTransaction saveTransaction(BusTransaction busTransaction) {
		// TODO Auto-generated method stub
		transactionList.add(busTransaction);
		return busTransaction;
	}

	@Override
	public Integer removeTransaction(Bus bus) {
		// TODO Auto-generated method stub
		for(BusTransaction t:transactionList) {
			if(bus.equals(t.getBus())) {
				transactionList.remove(t);
				return 1;
			}
		}
		return 0;
	}

	@Override
	public List<BusTransaction> findAllTransactions() {
		// TODO Auto-generated method stub
		return transactionList;
	}

	@Override
	public List<BusTransaction> findTransactionsByDate(LocalDate date) {
		// TODO Auto-generated method stub
		List<BusTransaction> transactionsByDate=new ArrayList<BusTransaction>();
		for(BusTransaction t:transactionList) {
			if(date.equals(t.getDate())) {
				transactionsByDate.add(t);
			}
		}
		
		return transactionsByDate;
	}

}
