package dev.simon.entities;

public class SavingsAccount extends Account{
	
	private int accountNumber;
	private double balance;
	private int ownerId;
	
	public SavingsAccount() {
		super();
	}

	public SavingsAccount(double balance) {
		super();
		this.balance = balance;
	}
	
	public SavingsAccount(int ownerId, double balance) {
		super();
		this.ownerId = ownerId;
		this.balance = balance;
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
		this.balance = balance;
	}
	
	public int getOwner() {
		return ownerId;
	}
	
	public void setOwner(int ownerId) {
		this.ownerId = ownerId;
	}

	@Override
	public String toString() {
		return "Savings Account #" + this.accountNumber + ": Balance = " + this.balance;
	}
	
	
	
	

}
