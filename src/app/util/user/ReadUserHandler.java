package app.util.user;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import com.google.gson.GsonBuilder;

import app.controller.UserController;
import app.model.User;
import app.util.FileHandler;
import app.util.MessageHandler;

public class ReadUserHandler {

	private static final String FILE = FileHandler.getDatabasePath();

	public static void readProductHandler(String fileName) {

		fileName = FILE + "//" + fileName;

		try {			
			BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(fileName)));
			
			String temp;
			String userJSON = "";
			while ((temp = bufferedReader.readLine()) != null)
				userJSON += temp;
			
			bufferedReader.close();

			List<User> users = convertToUser(userJSON);
			
			insertToDatabase(users);
			
		} catch (IOException exception) {
			MessageHandler.error("Error Read User File !");
			System.exit(0);
		}
	}
	
	private static List<User> convertToUser(String userJSON) {
		List<User> users = Arrays.asList(new GsonBuilder().create().fromJson(userJSON, User[].class));
		return users;
	}
	
	private static void insertToDatabase(List<User> users) {
		for (User user : users) 
			UserController.insertNewUser(user);
	}

}
