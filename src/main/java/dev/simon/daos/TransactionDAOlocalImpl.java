package dev.simon.daos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import dev.simon.entities.Transaction;

public class TransactionDAOlocalImpl implements TransactionDAO{
	
	public static Map<Integer,Transaction> transaction_table = new HashMap<Integer,Transaction>();
	private static int transactionId = 0;

	public Transaction createTransaction(Transaction transaction) {
		transaction.setTransactionId(++transactionId);
		transaction_table.put(transactionId, transaction);
		return transaction;
	}

	public Transaction getTransactionByTransactionId(int transactionId) {
		Transaction t = transaction_table.get(transactionId);
		return t;
	}

	public List<Transaction> getTransactionsByAccountNumber(int accountNumber) {
		List<Transaction> allTransactions = new ArrayList<Transaction>(transaction_table.values());
		List<Transaction> accountTransactions = new ArrayList<Transaction>();
		for(Transaction transaction : allTransactions) {
			if(transaction.getAccountNumber() == accountNumber) {
				accountTransactions.add(transaction);
			}
		} return accountTransactions;
	
	}

	public Set<Transaction> getAllTransactions() {
		Set<Transaction> allTransactions = new HashSet<Transaction>(transaction_table.values());
		return allTransactions;
	}

	public Transaction updateTransaction(Transaction transaction) {
		transaction_table.put(transaction.getTransactionId(), transaction);
		return transaction;
	}

	public boolean deleteTransaction(int trnasactionId) {
		transaction_table.remove(transactionId);
		return true;
	}

}
