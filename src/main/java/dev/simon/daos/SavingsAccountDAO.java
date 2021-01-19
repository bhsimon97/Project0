package dev.simon.daos;

import java.util.List;
import java.util.Set;

import dev.simon.entities.Account;
import dev.simon.entities.SavingsAccount;

public interface SavingsAccountDAO {
	
	//CREATE:
	
	SavingsAccount createSavingsAccount(int ownerId);
	
	//READ:
	
	SavingsAccount getSavingsAccountByAccountNumber(int accountNumber);
	List<SavingsAccount> getUsersSavingsAccounts(int ownerId);
	
	Set<SavingsAccount> getAllSavingsAccounts();
	
	//UPDATE:
	
	Account updateSavingsAccount(Account savingsAccount);
	
	//DESTROY:
	
	boolean deleteSavingsAccount(int accountNumber);

}
