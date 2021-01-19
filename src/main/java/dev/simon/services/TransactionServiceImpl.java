package dev.simon.services;

import dev.simon.entities.Transaction;

import java.util.List;
import java.util.Set;

import dev.simon.daos.TransactionDAO;
import dev.simon.daos.TransactionDAOdbImpl;

public class TransactionServiceImpl implements TransactionService{
	
	private static TransactionDAO tdao = new TransactionDAOdbImpl();

	@Override
	public Transaction deposit(double amount, int accountNumber) {
		Transaction transaction = new Transaction(amount, accountNumber);
		Transaction t = tdao.createTransaction(transaction);
		return t;
	}

	@Override
	public Transaction withdraw(double amount, int accountNumber) {
		Transaction transaction = new Transaction((0 - amount), accountNumber);
		Transaction t = tdao.createTransaction(transaction);
		return t;
	}
	
	@Override
	public Set<Transaction> getAllTransactions(){
		Set<Transaction> allTransactions = tdao.getAllTransactions();
		return allTransactions;
	}
	
	@Override
	public List<Transaction> getTransactionsByAccountNumber(int accountNumber){
		List<Transaction> accountTransactions = tdao.getTransactionsByAccountNumber(accountNumber);
		return accountTransactions;
	}

}
