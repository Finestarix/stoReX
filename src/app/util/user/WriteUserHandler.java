package app.util.user;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

import com.google.gson.Gson;

import app.controller.UserController;
import app.model.User;
import app.util.FileHandler;
import app.util.MessageHandler;

public class WriteUserHandler {

	private static final String FILE = FileHandler.getDatabasePath();

	public static void generateUserReport(String fileName) {

		fileName = FILE + "//" + fileName;

		try {
			File file = new File(fileName);
			file.createNewFile();
		} catch (IOException e1) {
			MessageHandler.error("Error Generating File !");
			System.exit(0);
		}

		ArrayList<User> users = UserController.getAllUsers();
		Gson gson = new Gson();
		
		String userJSON = gson.toJson(users);
		writeFile(fileName, userJSON);
	}
	
	private static void writeFile(String fileName, String userJSON) {
        OutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(new File(fileName));
            outputStream.write(userJSON.getBytes(), 0, userJSON.length());
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
