package dev.simon.daos;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import java.util.Set;

import dev.simon.entities.User;

public class UserDAOlocalImpl implements UserDAO{
	
	public static Map<Integer,User> user_table = new HashMap<Integer,User>();
	private static int idMaker = 0;

	public User createUser(User user) {
		user.setUserId(++idMaker);
		user_table.put(idMaker, user);
		
		return user;
	}

	public User getUserById(int userId) {
		User u = user_table.get(userId);
		return u;
	}

	public Set<User> getAllUsers() {
		Set<User> users = new HashSet<User>(user_table.values());
		return users;
	}

	public User updateUser(User user) {
		user_table.put(user.getUserId(), user);
		return user;
	}

	public boolean deleteUser(int userId) {
		user_table.remove(userId);
		return true;
	}


}
