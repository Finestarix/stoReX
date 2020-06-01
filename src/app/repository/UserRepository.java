package app.repository;

import java.sql.ResultSet;
import java.util.ArrayList;

import app.model.User;
import app.repository.parent.Repository;

public class UserRepository extends Repository<User> {

	private static final String TABLE_NAME = "users";

	public static ArrayList<User> getUserByEmailAndPassword(String email, String password) {
		String query = String.format("SELECT * FROM %s WHERE email=? AND password=?", TABLE_NAME);
		ResultSet result = UserRepository.executeQuery(query, email, password);
		return Repository.toObject(User.class, result);
	}

	public static void insertUser(User user) {
		String query = String.format("INSERT INTO %s (id, name, email, password, role) VALUES(?, ?, ?, ?, ?)", TABLE_NAME);
		UserRepository.executeUpdate(query, user.getId(), user.getName(), user.getEmail(), user.getPassword(), user.getRole());
	}

}
