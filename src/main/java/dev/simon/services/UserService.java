package dev.simon.services;

import java.util.Set;

import dev.simon.entities.User;
import dev.simon.exceptions.UserNotFoundException;

public interface UserService {
	
	User createUser(String username, String password, String firstName, String lastName);
	
	User login(String username, String password) throws UserNotFoundException;
	
	User getUser(int userId);
	
	Set<User> getAllUsers();
	
	User updateUser (int userId, String newUsername, String newFirstName, String newLastName);
	
	boolean deleteUser(int userId);
	
	}


