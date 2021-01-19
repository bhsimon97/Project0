package dev.simon.daos;

import java.util.Set;

import dev.simon.entities.User;

public interface UserDAO {
	
	//CREATE:
	
	User createUser(User user);
	
	//READ:
	
	User getUserById(int userId);
	
	Set<User> getAllUsers();
	
	//UPDATE:
	
	User updateUser(User user);
	
	//DESTROY:
	
	boolean deleteUser(int userId);

}
