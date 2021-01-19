package dev.simon.daotests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;
import dev.simon.daos.UserDAO;
import dev.simon.daos.UserDAOlocalImpl;
import dev.simon.entities.User;

class UserDAOtests {
	
	private static UserDAO udao = new UserDAOlocalImpl();

	@Test
	public void createUser() {
		User u = new User("bhsimon","password","Benjamin","Simon");
		System.out.println(u);
		udao.createUser(u);
		System.out.println(u);
		
		User u2 = new User("apolicht","password","Annamarie","Policht");
		udao.createUser(u2);
		System.out.println(u2);
	}
	
	@Test
	public void getUserById() {
		User u = new User("bhsimon","password","Benjamin","Simon");
		udao.createUser(u);
		System.out.println(u);
		
		User u2 = new User("apolicht","password","Annamarie","Policht");
		udao.createUser(u2);
		System.out.println(u2);
		
		User test = udao.getUserById(1);
		
		System.out.println(test);
		
	}
	
	@Test
	public void getAllUsers() {
		User u = new User("bhsimon","password","Benjamin","Simon");
		udao.createUser(u);
		
		User u2 = new User("apolicht","password","Annamarie","Policht");
		udao.createUser(u2);
		
		Set<User> allUsers = new HashSet<User>();
		allUsers = udao.getAllUsers();
		System.out.println(allUsers);
	}
	
	@Test
	public void updateUser() {
		User u = new User("bhsimon","password","Benjamin","Simon");
		udao.createUser(u);
		System.out.println(u);
		
		u.setUsername("bensimon_says");
		u.setFirstName("Ben");
		System.out.println(u);
	}
	
	@Test
	public void deleteUser() {
		User u = new User("bhsimon","password","Benjamin","Simon");
		udao.createUser(u);
		
		User u2 = new User("apolicht","password","Annamarie","Policht");
		udao.createUser(u2);
		
		Set<User> allUsers = new HashSet<User>();
		allUsers = udao.getAllUsers();
		System.out.println(allUsers);
		
		udao.deleteUser(1);
		
		allUsers = udao.getAllUsers();
		System.out.println(allUsers);
		
	}
	
}
