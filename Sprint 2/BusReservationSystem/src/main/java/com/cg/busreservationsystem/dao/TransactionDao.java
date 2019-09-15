package com.cg.busreservationsystem.dao;

import java.time.LocalDate;
import java.util.List;

import com.cg.busreservationsystem.dto.Bus;
import com.cg.busreservationsystem.dto.BusTransaction;

public interface TransactionDao {
	
	public BusTransaction saveTransaction(BusTransaction busTransaction);
	public Integer removeTransaction(Bus bus);
	public List<BusTransaction> findAllTransactions();
	public List<BusTransaction> findTransactionsByDate(LocalDate date);

}
