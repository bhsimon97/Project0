package dev.simon.daos;

import java.util.List;

import java.util.Set;

import dev.simon.entities.Account;
import dev.simon.entities.CheckingAccount;


public interface CheckingAccountDAO {
	
	// CREATE:
	
	CheckingAccount createCheckingAccount(int ownerId);
	
	// READ:
	
	CheckingAccount getCheckingAccountByAccountNumber(int accountNumber);
	Set<CheckingAccount> getAllCheckingAccounts();
	List<CheckingAccount> getUsersCheckingAccounts(int ownerId);
	
	// UPDATE:
	
	Account updateCheckingAccount(Account checkingAccount);
	
	// DESTROY:
	
	boolean deleteCheckingAccount(int accountNumber);

}
