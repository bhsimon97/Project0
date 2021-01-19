package dev.simon.services;

import java.util.List;

import dev.simon.entities.Account;
import dev.simon.entities.SavingsAccount;
import dev.simon.exceptions.AccountNotFoundException;

public interface SavingsAccountService {
	
	SavingsAccount createSavingsAccount(int ownerId);
	SavingsAccount getSavingsAccount(int accountNumber) throws AccountNotFoundException;
	List<SavingsAccount> getUsersSavingsAccounts(int ownerId);
	
	public Account updateSavingsAccount(Account currentAccount);
	
	SavingsAccount deleteSavingsAccount(int accountNumber);

}
