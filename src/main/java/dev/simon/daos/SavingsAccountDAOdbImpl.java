package dev.simon.daos;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import dev.simon.entities.Account;
import dev.simon.entities.SavingsAccount;
import dev.simon.util.JDBCConnection;

public class SavingsAccountDAOdbImpl implements SavingsAccountDAO{
	
	public static Connection conn = JDBCConnection.getConnection();

	@Override
	public SavingsAccount createSavingsAccount(int ownerId) {
		try {
			SavingsAccount savingsAccount = new SavingsAccount();
			savingsAccount.setOwner(ownerId);
			
			String sql = "CALL add_account(?,?,?)";
			CallableStatement cs = conn.prepareCall(sql);
			cs.setString(1, "Savings");
			cs.setInt(2, 0);
			cs.setInt(3, ownerId);
			
			cs.execute();
			return savingsAccount;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public SavingsAccount getSavingsAccountByAccountNumber(int accountNumber) {
		try {
			String sql = "SELECT * FROM accounts WHERE id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, Integer.toString(accountNumber));
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				SavingsAccount s = new SavingsAccount();
				s.setAccountNumber(rs.getInt("ID"));
				s.setBalance(rs.getDouble("BALANCE"));
				s.setOwner(rs.getInt("USER_ID"));
				
				return s;
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<SavingsAccount> getUsersSavingsAccounts(int ownerId) {
		List<SavingsAccount> usersSavingsAccounts = new ArrayList<SavingsAccount>();
		
			try {
				String sql = "SELECT * FROM accounts WHERE owner_id = ? AND account_type = 'Savings'";
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setInt(1, ownerId);
				ResultSet rs = ps.executeQuery();
				
				while(rs.next()) {
					SavingsAccount s = new SavingsAccount();
				
					s.setAccountNumber(rs.getInt("ID"));
					s.setBalance(rs.getDouble("BALANCE"));
					s.setOwner(rs.getInt("OWNER_ID"));
				
					usersSavingsAccounts.add(s);
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}
		return usersSavingsAccounts;
	}

	@Override
	public Set<SavingsAccount> getAllSavingsAccounts() {
		Set<SavingsAccount> savingsAccounts = new HashSet<SavingsAccount>();
		
		try {
			String sql = "SELECT * FROM accounts WHERE account_type = 'Savings'";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				SavingsAccount s = new SavingsAccount();
				
				s.setAccountNumber(rs.getInt("ID"));
				s.setBalance(rs.getDouble("BALANCE"));
				s.setOwner(rs.getInt("OWNER_ID"));

				savingsAccounts.add(s);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	return savingsAccounts;
	}

	@Override
	public Account updateSavingsAccount(Account savingsAccount) {
		try {
			String sql = "UPDATE accounts SET balance = ? WHERE id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setDouble(1, savingsAccount.getBalance());
			ps.setInt(2, savingsAccount.getAccountNumber());
			
			ps.executeQuery();
			
			return savingsAccount;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean deleteSavingsAccount(int accountNumber) {
		try {
			String sql = "DELETE accounts WHERE id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1, accountNumber);
			
			ps.executeQuery();
			
			return true;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

}
