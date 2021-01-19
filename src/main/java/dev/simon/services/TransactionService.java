package dev.simon.services;

import java.util.List;
import java.util.Set;

import dev.simon.entities.Transaction;

public interface TransactionService {
	
	Transaction deposit(double amount, int accountNumber);
	Transaction withdraw(double amount, int accountNumber);
	Set<Transaction> getAllTransactions();
	List<Transaction> getTransactionsByAccountNumber(int accountNumber);

}
