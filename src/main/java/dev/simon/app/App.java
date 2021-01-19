package dev.simon.app;

import java.util.Scanner;

import dev.simon.entities.User;
import dev.simon.exceptions.AccountNotFoundException;
import dev.simon.exceptions.UserNotFoundException;
import dev.simon.services.UserService;
import dev.simon.services.UserServiceImpl;
import dev.simon.entities.Account;
import dev.simon.services.CheckingAccountService;
import dev.simon.services.CheckingAccountServiceImpl;

import dev.simon.services.SavingsAccountService;
import dev.simon.services.SavingsAccountServiceImpl;

import dev.simon.services.TransactionService;
import dev.simon.services.TransactionServiceImpl;


public class App {

	private static Scanner scan = new Scanner(System.in);
	private static UserService userv = new UserServiceImpl();
	private static CheckingAccountService cserv = new CheckingAccountServiceImpl();
	private static SavingsAccountService sserv = new SavingsAccountServiceImpl();
	private static TransactionService tserv = new TransactionServiceImpl();

	private static User loggedInUser = null;

	public static void main(String[] args) throws UserNotFoundException, AccountNotFoundException {

		System.out.println("Welcome to the Simon Bank app!");

		User superUser = new User();

		boolean loggedIn = false;

		/*-------------------------------------------------------------------------------------------------------------*/
		// Login or Create New Account

		while (loggedIn == false) {
			System.out.println("Press 1 to login.");
			System.out.println("Press 2 to create a new account.");
			int choice = scan.nextInt();

			switch (choice) {
			case 1: {
				System.out.println(" ");
				System.out.println("Please enter your username."); 																// Login to normal account
				String username = scan.next();
				System.out.println(" ");
				System.out.println("Please enter your password.");
				String password = scan.next();
				System.out.println(" ");

				if (username.equalsIgnoreCase("admin") && password.equalsIgnoreCase("admin")) { 								// Login to SuperUser account																								/
					loggedInUser = superUser;
					loggedIn = true;
				}else {

				User u = userv.login(username, password);
				
				if(u != null) {
					loggedInUser = u;
					System.out.println("Hello, " + loggedInUser.getFirstName() + " " + loggedInUser.getLastName() + "!");
					loggedIn = true;
				}
				}
			}
				break;

			case 2: { 																											// Create new user account
				System.out.println("Please create a username.");
				String username = scan.next();
				System.out.println(" ");
				System.out.println("Please create a password.");
				String password = scan.next();
				System.out.println(" ");
				System.out.println("Please enter your first name.");
				String firstName = scan.next();
				System.out.println(" ");
				System.out.println("Please enter your last name.");
				String lastName = scan.next();
				System.out.println(" ");
				

				User u = userv.createUser(username, password, firstName, lastName);
				System.out.println("Created user: " + u.getUsername());
				System.out.println("Logging you in to your new account!");
				System.out.println(" ");
				loggedInUser = userv.login(username, password);
				loggedIn = true;
				System.out.println("Welcome, " + u.getFirstName() + " " + u.getLastName() + "!");
			}
				break;
			}

			/*-------------------------------------------------------------------------------------------------------------*/
			// SuperUser Functionality

			while (loggedInUser == superUser) {

				System.out.println("Welcome, Administrator.");
				System.out.println("Press 1 to view all user accounts.");
				System.out.println("Press 2 to create a new user account."); 													// Main Menu
				System.out.println("Press 3 to update a current user account.");
				System.out.println("Press 4 to delete a current user account.");
				System.out.println("Press 0 to log out.");

				int superChoice = scan.nextInt();
				System.out.println(" ");

				switch (superChoice) {
				case 1: { 																										// View all users
					System.out.println(userv.getAllUsers());
					System.out.println(" ");
				}
					break;

				case 2: {
					System.out.println("Please enter a username."); 															// Create new user
					String username = scan.next();
					System.out.println(" ");
					System.out.println("Please enter a password.");
					String password = scan.next();
					System.out.println(" ");
					System.out.println("Please enter the user's first name.");
					String firstName = scan.next();
					System.out.println(" ");
					System.out.println("Please enter the user's last name.");
					String lastName = scan.next();
					System.out.println(" ");

					User u = userv.createUser(username, password, firstName, lastName);
					System.out.println("Created user: " + u.getUsername());
					System.out.println(" ");
				}
					break;

				case 3: { 																										// Update existing user
					System.out.println("Please enter the user's ID number.");
					int userId = scan.nextInt();
					System.out.println(" ");
					System.out.println("Please enter the user's updated username.");
					String newUsername = scan.next();
					System.out.println(" ");
					System.out.println("Please enter the user's updated first name.");
					String newFirstName = scan.next();
					System.out.println(" ");
					System.out.println("Please enter the user's updated last name.");
					String newLastName = scan.next();
					System.out.println(" ");
					
					userv.updateUser(userId, newUsername, newFirstName, newLastName);
					System.out.println("User #" + userId + " updated!");
					System.out.println(" ");
				}
					break;
					
				case 4: {
					System.out.println("Please enter the ID number belonging to the user account you would like to delete.");
					int userId = scan.nextInt();
					System.out.println(" ");
					
					System.out.println("To confirm: you would like to delete the following user?");
					System.out.println(userv.getUser(userId));
					System.out.println("Press Y/N (NOTE: This cannot be undone!)");
					String yn = scan.next();
					System.out.println(" ");
					
					if (yn.equalsIgnoreCase("y")) {
						userv.deleteUser(userId);
						System.out.println("User deleted.");
						System.out.println(" ");
					}
					
					if(yn.equalsIgnoreCase("N")) {
						System.out.println("Returning to the menu.");
						System.out.println(" ");
					}
				}
					break;

				case 0: {
					loggedInUser = null; 																						// Log out
					loggedIn = false;
				}
					break;
				}
			}

			/*-------------------------------------------------------------------------------------------------------------*/
			// User Functionality

			while (loggedIn) {

				System.out.println("Press 1 to view, make transactions using, or delete checking accounts.");
				System.out.println("Press 2 to view, make transactions using, or delete savings accounts.");
				System.out.println("Press 3 to create a new checking or savings account.");
				System.out.println("Press 0 to log out.");

				int choiceWhileLoggedIn = scan.nextInt();
				System.out.println(" ");
				switch (choiceWhileLoggedIn) { 																					/*------------------------*/
				case 1: {
					System.out.println("Your checking accounts: " + cserv.getUsersCheckingAccounts(loggedInUser.getUserId())); 			// Pulls all of user's checking accounts

					System.out.println("Please type an account number to make a transaction regarding said account.");
					int accountNumber = scan.nextInt();
					System.out.println(" ");
					
					Account currentAccount = cserv.getCheckingAccount(accountNumber);
					
					if (currentAccount == null){
						break;
					}
					
					double currentBalance = currentAccount.getBalance();
					
					System.out.println("This account's current balance is $" + currentBalance + ".");
					
					accountMenu(accountNumber);	// Simply prints options of depositing, withdrawing, viewing transaction history, or deleting the account

					int depositWithdrawlChoice = scan.nextInt();

					switch (depositWithdrawlChoice) {
					case 1: {																												/*------------------------*/
						System.out.println("Please type the amount you would like to deposit."); 											// Deposit to account
						double depositAmount = scan.nextDouble();
							if(depositAmount < 0) {
								System.out.println("You cannot deposit a negative amount of money! Please try again.");
								System.out.println(" ");
								break;
							}
						System.out.println(" ");
						tserv.deposit(depositAmount, accountNumber);
						currentBalance += depositAmount;
						currentAccount.setBalance(currentBalance);
						cserv.updateCheckingAccount(currentAccount);
						System.out.println(
								"Successfully deposited $" + depositAmount + " to account number " + accountNumber + "!");

						System.out.println("Current balance: $" + currentAccount.getBalance());
					}
						break;

					case 2: {																												/*------------------------*/
						System.out.println("Please type the amount you would like to withdraw."); 											// Withdraw from account
						System.out.println("Current balance: $" + currentAccount.getBalance());
						double withdrawAmount = scan.nextDouble();
							if(withdrawAmount < 0) {
								System.out.println("You cannot withdraw a negative amount of money! Please try again.");
								System.out.println(" ");
								break;
							}
						System.out.println(" ");
						tserv.withdraw(withdrawAmount, accountNumber);
						currentAccount.setBalance(currentAccount.getBalance() - withdrawAmount);
						cserv.updateCheckingAccount(currentAccount);
						System.out.println(
								"Successfully withdrew $" + withdrawAmount + " from account number " + accountNumber + "!");
						System.out.println("Current balance: $" + currentAccount.getBalance());
					}
						break;

					case 3: {																												/*------------------------*/
						System.out.println("Previous transactions for account number " + accountNumber + ":");								// Get transaction history for account
						System.out.println(tserv.getTransactionsByAccountNumber(accountNumber));
					}
						break;

					case 4: {																												/*------------------------*/
						if (currentAccount.getBalance() == 0) {																				// Delete account if balance is 0
							cserv.deleteAccount(accountNumber);
							System.out.println("Account number " + accountNumber + " deleted.");
						} else {
							System.out.println("Please check your balance before attempting to delete an account!");
							System.out.println("You must either pay your debt or empty the account.");
							System.out.println("Your current balance is: $" + currentAccount.getBalance());
						}
					}
						break;
					}

				}
					break;

				case 2: { 																										/*------------------------*/
					System.out.println("Your savings accounts: " + sserv.getUsersSavingsAccounts(loggedInUser.getUserId()));	// Pulls all of user's savings accounts
					System.out.println("Please type an account number to make a transaction regarding said account.");
					int accountNumber = scan.nextInt();
					System.out.println(" ");
					Account currentAccount = sserv.getSavingsAccount(accountNumber);

					if(currentAccount != null) {
					accountMenu(accountNumber);

					int depositWithdrawlChoice = scan.nextInt();
					switch (depositWithdrawlChoice) {
					case 1: {																												/*------------------------*/
						System.out.println("Please type the amount you would like to deposit."); 											// Deposit to account
						int depositAmount = scan.nextInt();
						System.out.println(" ");
						tserv.deposit(depositAmount, accountNumber);
						currentAccount.setBalance(currentAccount.getBalance() + depositAmount);
						sserv.updateSavingsAccount(currentAccount);
						System.out.println(
								"Successfully deposited $" + depositAmount + " to account number " + accountNumber + "!");

						System.out.println("Current balance: $" + currentAccount.getBalance());
					}
						break;

					case 2: {																												/*------------------------*/
						System.out.println("Please type the amount you would like to withdraw."); 											// Withdraw from account
						System.out.println("Current balance: $" + currentAccount.getBalance());
						int withdrawAmount = scan.nextInt();
						System.out.println(" ");
						tserv.withdraw(withdrawAmount, accountNumber);
						currentAccount.setBalance(currentAccount.getBalance() - withdrawAmount);
						sserv.updateSavingsAccount(currentAccount);
						System.out.println(
								"Successfully withdrew $" + withdrawAmount + " from account number " + accountNumber + "!");
						System.out.println("Current balance: $" + currentAccount.getBalance());
					}
						break;

					case 3: {																												/*------------------------*/
						System.out.println("Previous transactions for account number " + accountNumber + ":");								// Get transaction history for account
						System.out.println(tserv.getTransactionsByAccountNumber(accountNumber));
					}
						break;

					case 4: {																												/*------------------------*/
						if (currentAccount.getBalance() == 0) {																				// Delete account if balance is 0
							sserv.deleteSavingsAccount(accountNumber);
							System.out.println("Account number " + accountNumber + " deleted.");
						} else {
							System.out.println(" ");
							System.out.println("Please check your balance before attempting to delete an account!");
							System.out.println("You must either pay your debt or empty the account.");
							System.out.println(" ");
							System.out.println("Your current balance is: $" + currentAccount.getBalance());
							System.out.println(" ");
						}
					}
						break;
					}
					System.out.println(" ");
					}else {
						System.out.println("An account with that number could not be found.");
						System.out.println("Please try again!");
						System.out.println(" ");
					}

				}
					break;

				case 3: { 																										/*------------------------*/
					System.out.println("Press 1 to create a checking account.");
					System.out.println("Press 2 to create a savings account."); 												// Create new checking/savings account
					System.out.println("Press 0 to return to the main menu.");
					int accountChoice = scan.nextInt();
					System.out.println(" ");

					if (accountChoice == 1) {
						int ownerId = loggedInUser.getUserId();
						cserv.createCheckingAccount(ownerId); // Create checking
						System.out.println("New checking account created!");
					}

					if (accountChoice == 2) {
						int ownerId = loggedInUser.getUserId();
						sserv.createSavingsAccount(ownerId); // Create savings
						System.out.println("New savings account created!");
					}
				}
					break;

				case 0: {
					loggedInUser = null;																						// log out
					loggedIn = false;
				}
					break;
				}
			}
		}
	}

	public static void accountMenu(int accountNumber) {
		System.out.println("Press 1 to deposit to this account.");
		System.out.println("Press 2 to withdraw from this account.");
		System.out.println("Press 3 to see all previous transactions regarding account number " + accountNumber + ".");
		System.out.println("Press 4 to delete this account.");
		System.out.println("Press any other number to return to the main menu.");
	}


}
