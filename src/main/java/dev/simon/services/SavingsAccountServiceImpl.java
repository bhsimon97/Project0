package dev.simon.services;


import java.util.List;

import dev.simon.daos.SavingsAccountDAO;
import dev.simon.daos.SavingsAccountDAOdbImpl;
import dev.simon.entities.Account;
import dev.simon.entities.SavingsAccount;
import dev.simon.exceptions.AccountNotFoundException;

public class SavingsAccountServiceImpl implements SavingsAccountService{
	
	private static SavingsAccountDAO sdao = new SavingsAccountDAOdbImpl();

	@Override
	public SavingsAccount createSavingsAccount(int ownerId) {
		SavingsAccount s = sdao.createSavingsAccount(ownerId);
		return s;
		
	}

	@Override
	public SavingsAccount getSavingsAccount(int accountNumber) throws AccountNotFoundException{
		try {
		for(SavingsAccount s : sdao.getAllSavingsAccounts()) {
			if(s.getAccountNumber() == accountNumber) {
				return s;
			}
		}throw new AccountNotFoundException();
		}catch(AccountNotFoundException e) {
			e.getMessage();
		}
		return null;
	}

	@Override
	public List<SavingsAccount> getUsersSavingsAccounts(int ownerId) {
		List<SavingsAccount> savingsAccount = sdao.getUsersSavingsAccounts(ownerId);
		return savingsAccount;
	}
	
	public Account updateSavingsAccount(Account savingsAccount) {
		sdao.updateSavingsAccount(savingsAccount);
		return savingsAccount;
	}
	
	@Override
	public SavingsAccount deleteSavingsAccount(int accountNumber) {
		sdao.deleteSavingsAccount(accountNumber);
		return null;
	}

}
