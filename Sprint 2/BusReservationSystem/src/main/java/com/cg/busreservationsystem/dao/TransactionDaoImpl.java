package com.cg.busreservationsystem.dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.cg.busreservationsystem.dto.Bus;
import com.cg.busreservationsystem.dto.Transaction;

public class TransactionDaoImpl implements TransactionDao{
	
	private List<Transaction> transactionList=new ArrayList<Transaction>();
	

	@Override
	public Transaction saveTransaction(Transaction transaction) {
		// TODO Auto-generated method stub
		for(Transaction t: transactionList) {
			if(t.getDate()==transaction.getDate() && t.getBus()==transaction.getBus()) {
				transactionList.set(transactionList.indexOf(t), transaction);
			}
		}
		if(!transactionList.contains(transaction)) {
			transactionList.add(transaction);
		}
		return transaction;
	}

	@Override
	public Integer removeTransaction(Bus bus) {
		// TODO Auto-generated method stub
		for(Transaction t:transactionList) {
			if(t.getBus()==bus) {
				transactionList.remove(bus);
				return 1;
			}
		}
		return 0;
	}

	@Override
	public List<Transaction> findAllTransactions() {
		// TODO Auto-generated method stub
		return transactionList;
	}

	@Override
	public List<Transaction> findTransactionsByDate(LocalDate date) {
		// TODO Auto-generated method stub
		List<Transaction> transactionsByDate=new ArrayList<Transaction>();
		for(Transaction t:transactionList) {
			if(date.equals(t.getDate())) {
				transactionsByDate.add(t);
			}
		}
		
		return transactionsByDate;
	}

}
