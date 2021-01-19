package dev.simon.daos;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import dev.simon.entities.User;
import dev.simon.util.JDBCConnection;

public class UserDAOdbImpl implements UserDAO{
	
	public static Connection conn = JDBCConnection.getConnection();

	@Override
	public User createUser(User user) {

		try {
			String sql = "CALL add_user(?,?,?,?)";
			CallableStatement cs = conn.prepareCall(sql);
			cs.setString(1, user.getFirstName());
			cs.setString(2, user.getLastName());
			cs.setString(3, user.getUsername());
			cs.setString(4, user.getPassword());
			
			cs.execute();
			
			return user;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public User getUserById(int userId) {
		try {
			String sql = "SELECT * FROM users WHERE id = ?";
			PreparedStatement ps = conn.prepareCall(sql);
			ps.setString(1, Integer.toString(userId));
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				User u = new User();
				u.setUserId(rs.getInt("ID"));
				u.setFirstName(rs.getString("FIRST_NAME"));
				u.setLastName(rs.getString("LAST_NAME"));
				u.setUsername(rs.getString("USERNAME"));
				u.setPassword(rs.getString("PASSWORD"));
				
				return u;		
			}
		}catch(SQLException e) {
			e.getStackTrace();
		}
		return null;
	}

	@Override
	public Set<User> getAllUsers() {
		Set<User> users = new HashSet<User>();
		
		try {
			String sql = "SELECT * FROM users";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				User u = new User();
				u.setUserId(rs.getInt("ID"));
				u.setFirstName(rs.getString("FIRST_NAME"));
				u.setLastName(rs.getString("LAST_NAME"));
				u.setUsername(rs.getString("USERNAME"));
				u.setPassword(rs.getString("PASSWORD"));
				
				users.add(u);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return users;
	}

	@Override
	public User updateUser(User user) {
		try {
			String sql = "UPDATE users SET first_name = ?, last_name = ?, username = ?, password = ? WHERE id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, user.getFirstName());
			ps.setString(2, user.getLastName());
			ps.setString(3, user.getUsername());
			ps.setString(4, user.getPassword());
			ps.setInt(5, user.getUserId());
			
			ps.executeQuery();
			
			return user;
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean deleteUser(int userId) {
		
		try {
			String sql = "DELETE users WHERE id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, Integer.toString(userId));
			
			ps.executeQuery();
			return true;
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}

}
