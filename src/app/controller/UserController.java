package app.controller;

import java.util.ArrayList;
import java.util.UUID;

import app.model.User;
import app.repository.UserRepository;
import app.session.UserSession;
import util.HashingHandler;

public class UserController {

	public static User auth(String email, String password) {

		String hashedPassword = HashingHandler.SHA256(password);
		ArrayList<User> users = UserRepository.getUserByEmailAndPassword(email, hashedPassword);

		User user = null;
		
		if (users.size() >= 1) {
			user = users.get(0);
			UserSession.setUser(user);
		}
		
		return user;
	}
	
	public static void insertNewUser(String name, String email, String password) {
		String id = UUID.randomUUID().toString();
		String hashedPassword = HashingHandler.SHA256(password);
		User user = new User(id, name, email, hashedPassword);
		UserRepository.insertUser(user);
	}

}
