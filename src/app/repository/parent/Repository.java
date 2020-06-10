package app.repository.parent;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.mysql.jdbc.Driver;

import app.annotation.FieldDatabase;
import app.util.MessageHandler;

public abstract class Repository<T> {

	private final static String DB_SERVER = "localhost";
	private final static int DB_PORT = 3306;
	private final static String DB_NAME = "javah2bp";
	private final static String DB_USERNAME = "root";
	private final static String DB_PASSWORD = "";
	private static String connectionString = String.format("jdbc:mysql://%s:%d/%s", DB_SERVER, DB_PORT, DB_NAME);
	private static String rootConnectionString = String
			.format("jdbc:mysql://%s:%d/mysql?zeroDateTimeBehavior=convertToNull", DB_SERVER, DB_PORT);

	private static Connection connection;

	private static Connection getConnection() {
		if (connection == null) {
			try {
				DriverManager.registerDriver(new Driver());
				connection = (Connection) DriverManager.getConnection(connectionString, DB_USERNAME, DB_PASSWORD);
			} catch (Exception exception1) {

				int confirm = MessageHandler
						.confirmation("Database not found ! Create database javah2bp and the tables ?");
				if (confirm == 0)
					createDatabase();
				else {
					MessageHandler.error("Exiting program !");
					System.exit(0);
				}
			}

			createTableProduct();
			createTableUser();
			createTableTransactionHeader();
			createTableTransactionDetail();
		}
		return connection;
	}

	private static void createDatabase() {
		try {
			connection = (Connection) DriverManager.getConnection(rootConnectionString, DB_USERNAME, DB_PASSWORD);
			String createDatabase = String.format("CREATE DATABASE %s", DB_NAME);
			executeCreateQuery(createDatabase);

			connection = (Connection) DriverManager.getConnection(connectionString, DB_USERNAME, DB_PASSWORD);
		} catch (SQLException exception2) {
			MessageHandler.error("Failed to execute query !");
			System.exit(0);
		}
	}

	private static void createTableProduct() {
		String createTableProduct = "CREATE TABLE IF NOT EXISTS `products` (\r\n" + "  `id` varchar(40) NOT NULL,\r\n"
				+ "  `name` varchar(255) NOT NULL,\r\n" + "  `price` int(11) NOT NULL,\r\n"
				+ "  `quantity` int(11) NOT NULL,\r\n"
				+ "  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),\r\n"
				+ "  `updated_at` datetime DEFAULT current_timestamp() ON UPDATE current_timestamp(),\r\n"
				+ "PRIMARY KEY (`id`)\r\n" + ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;";
		executeCreateQuery(createTableProduct);
	}

	private static void createTableUser() {
		String createTableUser = "CREATE TABLE IF NOT EXISTS `users` (\r\n" + "  `id` varchar(40) NOT NULL,\r\n"
				+ "  `name` varchar(100) NOT NULL,\r\n" + "  `email` varchar(100) NOT NULL,\r\n"
				+ "  `password` varchar(100) NOT NULL,\r\n" + "  `role` varchar(10) NOT NULL DEFAULT 'User',\r\n"
				+ "  `status` varchar(10) NOT NULL DEFAULT 'Active',\r\n"
				+ "  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),\r\n"
				+ "  `updated_at` datetime DEFAULT current_timestamp() ON UPDATE current_timestamp(),\r\n"
				+ "PRIMARY KEY (`id`)\r\n" + ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;";
		executeCreateQuery(createTableUser);
	}

	private static void createTableTransactionHeader() {
		String createTableTransactionHeader = "CREATE TABLE IF NOT EXISTS `transaction_header` (\r\n"
				+ "  `id` varchar(40) NOT NULL,\r\n" + "  `user_id` varchar(40) NOT NULL,\r\n"
				+ "  `date` timestamp NOT NULL DEFAULT current_timestamp(),\r\n"
				+ "  `report_name` varchar(40) NOT NULL,\r\n" + "PRIMARY KEY (`id`)\r\n"
				+ ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;";
		executeCreateQuery(createTableTransactionHeader);
	}

	private static void createTableTransactionDetail() {
		String createTableTransactionDetail = "CREATE TABLE IF NOT EXISTS `transaction_detail` (\r\n"
				+ "  `transaction_id` varchar(40) NOT NULL,\r\n" + "  `product_id` varchar(40) NOT NULL,\r\n"
				+ "  `quantity` int(11) NOT NULL,\r\n" + "PRIMARY KEY (`transaction_id`, `product_id`)\r\n"
				+ ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;\r\n" + "";
		executeCreateQuery(createTableTransactionDetail);
	}

	private static void executeCreateQuery(String query) {
		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate(query);
			statement.close();
		} catch (SQLException e) {
			MessageHandler.error("Failed to execute query !");
			System.exit(0);
		}
	}

	protected static ResultSet executeQuery(String query, String... params) {
		try {
			PreparedStatement preparedStatement = getConnection().prepareStatement(query);
			for (int i = 1; i <= params.length; i++)
				preparedStatement.setString(i, params[i - 1]);
			return preparedStatement.executeQuery();
		} catch (SQLException e) {
			MessageHandler.error("Failed to execute query !");
			System.exit(0);
		}
		return null;
	}

	protected static void executeUpdate(String query, String... params) {
		try {
			PreparedStatement statement = getConnection().prepareStatement(query);
			for (int i = 1; i <= params.length; i++)
				statement.setString(i, params[i - 1]);
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			MessageHandler.error("Failed to execute query !");
			System.exit(0);
		}
	}

	protected static <T> ArrayList<T> toObject(Class<T> type, ResultSet resultSet) {

		ArrayList<T> models = new ArrayList<>();

		try {
			while (resultSet.next()) {

				T classType = type.newInstance();
				Field[] fields = classType.getClass().getDeclaredFields();

				for (Field field : fields) {

					field.setAccessible(true);
					FieldDatabase fieldDatabase = field.getAnnotation(FieldDatabase.class);
					String fieldName = fieldDatabase.name();
					Object value = resultSet.getObject(fieldName);

					Class<?> fieldType = field.getType();

					if (isPrimitive(fieldType)) {
						Class<?> wrapper = convertToWrapper(fieldType);
						value = wrapper.cast(value);
					}

					field.set(classType, value);
				}

				models.add(classType);
			}
		} catch (SQLException | InstantiationException | IllegalAccessException e) {
			MessageHandler.error("Failed to execute query !");
			System.exit(0);
		}

		return models;
	}

	private static boolean isPrimitive(Class<?> type) {
		return (type == int.class || type == long.class || type == double.class || type == float.class
				|| type == boolean.class || type == byte.class || type == char.class || type == short.class);
	}

	private static Class<?> convertToWrapper(Class<?> type) {

		if (type == int.class)
			return Integer.class;

		else if (type == long.class)
			return Long.class;

		else if (type == double.class)
			return Double.class;

		else if (type == float.class)
			return Float.class;

		else if (type == boolean.class)
			return Boolean.class;

		else if (type == byte.class)
			return Byte.class;

		else if (type == char.class)
			return Character.class;

		else if (type == short.class)
			return Short.class;

		MessageHandler.error("Failed to execute query !");
		System.exit(0);
		return null;
	}
}
