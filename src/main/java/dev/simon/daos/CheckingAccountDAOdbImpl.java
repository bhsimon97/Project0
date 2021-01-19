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
import dev.simon.entities.CheckingAccount;
import dev.simon.util.JDBCConnection;

public class CheckingAccountDAOdbImpl implements CheckingAccountDAO {
	
	public static Connection conn = JDBCConnection.getConnection();

	@Override
	public CheckingAccount createCheckingAccount(int ownerId) {
		try {
			CheckingAccount checkingAccount = new CheckingAccount();
			checkingAccount.setOwner(ownerId);
			
			String sql = "CALL add_account(?,?,?)";
			CallableStatement cs = conn.prepareCall(sql);
			cs.setString(1, "Checking");
			cs.setInt(2, 0);
			cs.setInt(3, ownerId);
			
			cs.execute();
			return checkingAccount;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public CheckingAccount getCheckingAccountByAccountNumber(int accountNumber) {
		try {
			String sql = "SELECT * FROM accounts WHERE id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, Integer.toString(accountNumber));
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				CheckingAccount c = new CheckingAccount();
				c.setAccountNumber(rs.getInt("ID"));
				c.setBalance(rs.getDouble("BALANCE"));
				c.setOwner(rs.getInt("OWNER_ID"));
				
				return c;
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Set<CheckingAccount> getAllCheckingAccounts() {
		Set<CheckingAccount> checkingAccounts = new HashSet<CheckingAccount>();
		
			try {
				String sql = "SELECT * FROM accounts WHERE account_type = 'Checking'";
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				
				while(rs.next()) {
					CheckingAccount c = new CheckingAccount();
					
					c.setAccountNumber(rs.getInt("ID"));
					c.setBalance(rs.getDouble("BALANCE"));
					c.setOwner(rs.getInt("OWNER_ID"));

					checkingAccounts.add(c);
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}
		return checkingAccounts;
	}

	@Override
	public List<CheckingAccount> getUsersCheckingAccounts(int ownerId) {
		List<CheckingAccount> usersCheckingAccounts = new ArrayList<CheckingAccount>();
		
		try {
			String sql = "SELECT * FROM accounts WHERE owner_id = ? AND account_type = 'Checking'";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, ownerId);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				CheckingAccount c = new CheckingAccount();
				
				c.setAccountNumber(rs.getInt("ID"));
				c.setBalance(rs.getDouble("BALANCE"));
				c.setOwner(rs.getInt("OWNER_ID"));
				
				usersCheckingAccounts.add(c);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return usersCheckingAccounts;
	}

	@Override
	public Account updateCheckingAccount(Account checkingAccount) {
		try {
			String sql = "UPDATE accounts SET balance = ? WHERE id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setDouble(1, checkingAccount.getBalance());
			ps.setInt(2, checkingAccount.getAccountNumber());
			
			ps.executeQuery();
			
			return checkingAccount;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean deleteCheckingAccount(int accountNumber) {
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
