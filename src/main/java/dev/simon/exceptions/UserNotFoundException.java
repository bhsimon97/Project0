package dev.simon.exceptions;

public class UserNotFoundException extends Exception{

	public UserNotFoundException() {
		
		super("Account information with that username or password could not be found.\n"
				+ "Please try again or register an account!");
		
//		System.out.println("Account information with that username or password could not be found.");
//		System.out.println("Please try again or register an account!");
		
	}

		
	}


