package app.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import app.model.User;
import app.repository.parent.Repository;

public class UserRepository extends Repository<User> {

	public static final int ITEM_LOAD = 20;
	private static final String TABLE_NAME = "users";
	private static final String USER_STATUS_ACTIVE = "'Active'";
	private static final String USER_STATUS_PASSIVE = "'Passive'";

	public static ArrayList<User> getUserByEmailAndPassword(String email, String password) {
		String query = String.format("SELECT * FROM %s WHERE email=? AND password=?", TABLE_NAME);
		ResultSet result = UserRepository.executeQuery(query, email, password);
		return Repository.toObject(User.class, result);
	}

	public static ArrayList<User> getAllUsers() {
		String query = String.format("SELECT * FROM %s WHERE status LIKE %s ORDER BY created_at", TABLE_NAME,
				USER_STATUS_ACTIVE);
		ResultSet resultSet = Repository.executeQuery(query);
		return Repository.toObject(User.class, resultSet);
	}

	public static ArrayList<User> getUsersAlreadyLoaded(int currentLoadedItem) {
		String query = String.format("SELECT * FROM %s WHERE status LIKE %s ORDER BY created_at LIMIT %d", TABLE_NAME,
				USER_STATUS_ACTIVE, currentLoadedItem);
		ResultSet result = Repository.executeQuery(query);
		ArrayList<User> loadedUsers = Repository.toObject(User.class, result);
		return loadedUsers;
	}

	public static ArrayList<User> getUsersPerPage(int currentLoadedPage) {
		String query = String.format("SELECT * FROM %s WHERE status LIKE %s ORDER BY created_at LIMIT %d,%d",
				TABLE_NAME, USER_STATUS_ACTIVE, (currentLoadedPage - 1) * ITEM_LOAD, ITEM_LOAD);
		ResultSet result = Repository.executeQuery(query);
		ArrayList<User> loadedUsers = Repository.toObject(User.class, result);
		return loadedUsers;
	}

	public static int getTotalUser() {
		try {
			String query = String.format("SELECT COUNT(*) FROM %s", TABLE_NAME);
			ResultSet result = Repository.executeQuery(query);
			result.next();
			return result.getInt(1);
		} catch (SQLException e) {
		}

		return -1;
	}

	public static void insertUser(User user) {
		String query = String.format("INSERT INTO %s (id, name, email, password, role) VALUES(?, ?, ?, ?, ?)",
				TABLE_NAME);
		UserRepository.executeUpdate(query, user.getId(), user.getName(), user.getEmail(), user.getPassword(),
				user.getRole());
	}

	public static void bannedUser(User user) {
		String query = String.format("UPDATE %s SET status = %s WHERE id LIKE ?", TABLE_NAME, USER_STATUS_PASSIVE);
		ProductRepository.executeUpdate(query, user.getId());
	}

}
