package dev.simon.entities;

public class Account {
	
	private int accountNumber;
	private double balance;
	private int ownerId;
	
	public Account(){
		super();	
	}
	
	public int getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = this.balance + balance;
	}
	
	public int getOwner() {
		return ownerId;
	}
	
	public void setOwner(int ownerId) {
		this.ownerId = ownerId;
	}

}
