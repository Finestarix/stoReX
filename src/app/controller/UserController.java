package app.controller;

import java.util.ArrayList;
import java.util.UUID;

import app.model.User;
import app.repository.UserRepository;
import util.HashingHandler;

public class UserController {

	public static ArrayList<User> getAllUsers() {
		return UserRepository.getAllUsers();
	}
	
	public static ArrayList<User> getUsersAlreadyLoaded(int currentLoadedItem) {
		return UserRepository.getUsersAlreadyLoaded(currentLoadedItem);
	}

	public static ArrayList<User> getUsersPerPage(int currentPageLoaded) {
		return UserRepository.getUsersPerPage(currentPageLoaded);
	}

	public static int getTotalUser() {
		return UserRepository.getTotalUser();
	}
	
	public static User auth(String email, String password) {
		String hashedPassword = HashingHandler.SHA256(password);
		ArrayList<User> users = UserRepository.getUserByEmailAndPassword(email, hashedPassword);
		User user = null;
		if (users.size() >= 1) 
			user = users.get(0);
		return user;
	}
	
	public static void insertNewUser(String name, String email, String password) {
		String id = UUID.randomUUID().toString();
		String hashedPassword = HashingHandler.SHA256(password);
		User user = new User(id, name, email, hashedPassword);
		UserRepository.insertUser(user);
	}
	
	public static void bannedUser(User user) {
		UserRepository.bannedUser(user);
	}

}
