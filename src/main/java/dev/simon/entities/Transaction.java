package dev.simon.entities;

public class Transaction {
	
	private int transactionId;
	private double netChange;
	private int accountNumber;
	
	
	public Transaction() {
		super();
	}


	public Transaction(double netChange, int accountNumber) {
		super();
		this.netChange = netChange;
		this.accountNumber = accountNumber;
	}


	public int getTransactionId() {
		return transactionId;
	}
	
	public void setTransactionId(int transactionId){
		this.transactionId = transactionId;
	}
	
	public double getNetChange() {
		return netChange;
	}


	public void setNetChange(double netChange) {
		this.netChange = netChange;
	}


	public int getAccountNumber() {
		return accountNumber;
	}


	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}


	@Override
	public String toString() {
		return "Transaction #" + transactionId + ": Net Change = " + netChange + " (Account #" + accountNumber + ")";
	}
	
	
	

}
