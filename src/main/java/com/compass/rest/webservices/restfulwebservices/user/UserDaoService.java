package com.compass.rest.webservices.restfulwebservices.user;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class UserDaoService {
	
	private static List<User> users = new ArrayList<>();
	private static int userId = 0;
	
	static {
		users.add(new User (++userId, "João", LocalDate.now().minusYears(30)));
		users.add(new User (++userId,"Maria", LocalDate.now().minusYears(40)));
		users.add(new User (++userId,"José", LocalDate.now().minusYears(20)));
	} 
	
	public List<User> findAll(){
		return users;
	}
	
	/*
	 
		 public User findById(int id) {
			return users.get(id - 1);
		}
	 
	 */
	public User findById(int id) {
		return users.stream().filter(x -> x.getId().equals(id)).findFirst().get();
	}
	
	public User addUser(User newUser) {
		newUser.setId(++userId);
		users.add(newUser);
		return newUser;
	}
	
}
