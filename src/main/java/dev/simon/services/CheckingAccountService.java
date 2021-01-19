package dev.simon.services;

import java.util.List;

import dev.simon.entities.Account;
import dev.simon.entities.CheckingAccount;
import dev.simon.exceptions.AccountNotFoundException;

public interface CheckingAccountService {
	
	CheckingAccount createCheckingAccount(int ownerId);
	CheckingAccount getCheckingAccount(int accountNumber) throws AccountNotFoundException;
	List<CheckingAccount> getUsersCheckingAccounts(int ownerId);
	Account updateCheckingAccount(Account checkingAccount);
	
	CheckingAccount deleteAccount(int accountNumber);
	

}
