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

import dev.simon.entities.Transaction;
import dev.simon.util.JDBCConnection;

public class TransactionDAOdbImpl implements TransactionDAO{
	
	public static Connection conn = JDBCConnection.getConnection();

	@Override
	public Transaction createTransaction(Transaction transaction) {
		try {
			
		String sql = "CALL add_transaction(?,?)";
		CallableStatement cs = conn.prepareCall(sql);
		cs.setDouble(1, transaction.getNetChange());
		cs.setInt(2, transaction.getAccountNumber());
		
		cs.executeQuery();
		return transaction;
	}catch(SQLException e) {
		e.printStackTrace();
	}
	return null;
	}

	@Override
	public Transaction getTransactionByTransactionId(int transactionId) {
		try {
			String sql = "SELECT * FROM transactions WHERE id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setDouble(1, transactionId);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				Transaction t = new Transaction();
				t.setAccountNumber(rs.getInt("ACCOUNT_ID"));
				t.setNetChange(rs.getDouble("NET_CHANGE"));
				t.setTransactionId(rs.getInt("ID"));
				
				return t;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Transaction> getTransactionsByAccountNumber(int accountNumber) {
		List<Transaction> transactions = new ArrayList<Transaction>();
		try {
			String sql = "SELECT * FROM transactions WHERE account_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setDouble(1, accountNumber);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Transaction t = new Transaction();
				t.setAccountNumber(rs.getInt("ACCOUNT_ID"));
				t.setNetChange(rs.getDouble("NET_CHANGE"));
				t.setTransactionId(rs.getInt("ID"));
				
				transactions.add(t);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return transactions;
	}

	@Override
	public Set<Transaction> getAllTransactions() {
		Set<Transaction> allTransactions = new HashSet<Transaction>();
		try {
			String sql = "SELECT * FROM transactions";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Transaction t = new Transaction();
				t.setAccountNumber(rs.getInt("ACCOUNT_ID"));
				t.setNetChange(rs.getDouble("NET_CHANGE"));
				t.setTransactionId(rs.getInt("ID"));
				
				allTransactions.add(t);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return allTransactions;
	}

	@Override
	public Transaction updateTransaction(Transaction transaction) {
		try {
			String sql = "UPDATE transactions SET net_change = ? WHERE id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setDouble(1, transaction.getNetChange());
			ps.setInt(2, transaction.getTransactionId());
			
			ps.executeQuery();
			return transaction;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean deleteTransaction(int transactionId) {
		try {
			String sql = "DELETE transactions WHERE id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1, transactionId);
			
			ps.executeQuery();
			
			return true;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

}
