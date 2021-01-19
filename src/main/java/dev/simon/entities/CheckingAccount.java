package dev.simon.entities;

public class CheckingAccount extends Account{
	
	private int accountNumber;
	private double balance;
	private int ownerId;
	
	public CheckingAccount() {
		super();
	}
	
	public CheckingAccount(double balance) {
		super();
		this.balance = balance;
	}
	
	public CheckingAccount(int ownerId, double balance) {
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
		return "Checking Account #" + this.accountNumber + ": Balance = " + this.balance;
	}
	
	
	
	

}
