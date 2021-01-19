package dev.simon.exceptions;

public class AccountNotFoundException extends Exception{

	public AccountNotFoundException(){
		super("An account with that number could not be found. Please try again.");
	}

}
