package dev.simon.services;

import java.util.List;

import dev.simon.daos.CheckingAccountDAO;
import dev.simon.daos.CheckingAccountDAOdbImpl;
import dev.simon.entities.Account;
import dev.simon.entities.CheckingAccount;
import dev.simon.exceptions.AccountNotFoundException;

public class CheckingAccountServiceImpl implements CheckingAccountService{
	
	private static CheckingAccountDAO cdao = new CheckingAccountDAOdbImpl();

	@Override
	public CheckingAccount createCheckingAccount(int ownerId) {
		CheckingAccount checking = cdao.createCheckingAccount(ownerId);
		return checking;
		
	}

	@Override
	public CheckingAccount getCheckingAccount(int accountNumber) throws AccountNotFoundException{
		try {
		CheckingAccount c = cdao.getCheckingAccountByAccountNumber(accountNumber);
		if(c != null) {
		return c; }
			throw new AccountNotFoundException();
		}catch(AccountNotFoundException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	@Override
	public List<CheckingAccount> getUsersCheckingAccounts(int ownerId){
		List<CheckingAccount> checkingAccounts = cdao.getUsersCheckingAccounts(ownerId);
		return checkingAccounts;
	}

	@Override
	public CheckingAccount deleteAccount(int accountNumber) {
		cdao.deleteCheckingAccount(accountNumber);
		return null;
	}

	@Override
	public Account updateCheckingAccount(Account checkingAccount) {
		cdao.updateCheckingAccount(checkingAccount);
		return checkingAccount;
	}

}
