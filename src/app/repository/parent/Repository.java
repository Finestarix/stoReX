package app.repository.parent;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Driver;

import app.annotation.FieldDatabase;
import util.MessageHandler;

public abstract class Repository<T> {

	private final static String DB_SERVER = "localhost";
	private final static int DB_PORT = 3306;
	private final static String DB_NAME = "javah2bp";
	private final static String DB_USERNAME = "root";
	private final static String DB_PASSWORD = "";
	private static String connectionString = String.format("jdbc:mysql://%s:%d/%s", DB_SERVER, DB_PORT, DB_NAME);

	private static Connection connection;

	private static Connection getConnection() {
		if (connection == null) {
			try {
				DriverManager.registerDriver(new Driver());
				connection = (Connection) DriverManager.getConnection(connectionString, DB_USERNAME, DB_PASSWORD);
			} catch (Exception e) {
				MessageHandler.error("Failed to connect database !");
				System.exit(0);
			}
		}
		return connection;
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
