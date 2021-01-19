package dev.simon.daos;

import java.util.List;
import java.util.Set;

import dev.simon.entities.Transaction;

public interface TransactionDAO {
	
	// CREATE:
	
	Transaction createTransaction(Transaction transaction);
	
	// READ:
	Transaction getTransactionByTransactionId(int transactionId);
	List<Transaction> getTransactionsByAccountNumber(int accountNumber);
	Set<Transaction> getAllTransactions();
	
	// UPDATE:
	
	Transaction updateTransaction(Transaction transaction);
	
	// DELETE:
	
	boolean deleteTransaction(int transactionId);
	

}
