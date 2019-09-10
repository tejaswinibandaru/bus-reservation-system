package com.cg.busreservationsystem.dao;

import java.time.LocalDate;
import java.util.List;

import com.cg.busreservationsystem.dto.Bus;
import com.cg.busreservationsystem.dto.Transaction;

public interface TransactionDao {
	
	public Transaction saveTransaction(Transaction transaction);
	public Integer removeTransaction(Bus bus);
	public List<Transaction> findAllTransactions();
	public List<Transaction> findTransactionsByDate(LocalDate date);

}
