package com.cg.busreservationsystem.dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.cg.busreservationsystem.dto.Transaction;

public class TransactionDaoImpl implements TransactionDao{
	
	private List<Transaction> transactionList=new ArrayList<Transaction>();
	

	@Override
	public Transaction saveTransaction(Transaction transaction) {
		// TODO Auto-generated method stub
		for(Transaction t: transactionList) {
			
		}
		return null;
	}

	@Override
	public Integer removeTransaction(Integer transId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Transaction> findAllTransactions() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Transaction> findTransactionsByDate(LocalDate date) {
		// TODO Auto-generated method stub
		return null;
	}

}
