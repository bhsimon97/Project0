package dev.simon.services;

import dev.simon.entities.User;
import dev.simon.exceptions.UserNotFoundException;

import java.util.Set;

import dev.simon.daos.UserDAO;
import dev.simon.daos.UserDAOdbImpl;

public class UserServiceImpl implements UserService{
	
	private static UserDAO udao = new UserDAOdbImpl();

	@Override
	public User createUser(String username, String password, String firstName, String lastName) {
		User u = new User(username, password, firstName, lastName);
		udao.createUser(u);
		return u;
	}

	@Override
	public User login(String username, String password) throws UserNotFoundException {
		try {
			for(User u : udao.getAllUsers()) {
				if(u.getUsername().equals(username) && u.getPassword().equals(password)) {
					return u;
				}
			}throw new UserNotFoundException();
			
		}catch(UserNotFoundException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	@Override
	public User getUser(int userId) {
		return udao.getUserById(userId);
	}
	
	@Override
	public Set<User> getAllUsers() {
		return udao.getAllUsers();
	}
	
	@Override 
	public User updateUser(int userId, String newUsername, String newFirstName, String newLastName) {
		User u = udao.getUserById(userId);
		u.setUsername(newUsername);
		u.setFirstName(newFirstName);
		u.setLastName(newLastName);
		udao.updateUser(u);
		return u;
	}
	
	@Override
	public boolean deleteUser(int userId) {
		udao.deleteUser(userId);
		return true;
	}

}
