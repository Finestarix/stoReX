package app.util;

import java.io.File;
import java.util.ArrayList;

public class FileHandler {

	public static final String ASSET_DIRECTORY = "\\src\\assets";
	public static final String RECEIPT_DIRECTORY = "\\src\\receipts";

	public static String getAssetsPath() {
		return System.getProperty("user.dir") + ASSET_DIRECTORY;
	}
	
	public static String getReceiptPath() {
		return System.getProperty("user.dir") + RECEIPT_DIRECTORY;
	}

	public static String getAssetsPath(String fileName) {

		String path = getAssetsPath() + "\\" + fileName;
		try {
			File file = new File(path);

			if (!file.exists()) {
				MessageHandler.error("Error Load Asset !");
				System.exit(0);
			}

		} catch (Exception ex) {
			MessageHandler.error("Error Load Asset !");
			System.exit(0);
		}

		return path;
	}

	public static ArrayList<String> getDirectory(File folder) {
		ArrayList<String> fileNames = new ArrayList<String>();
		for (final File file : folder.listFiles()) 
			if (file.isFile()) 
					fileNames.add(file.getAbsolutePath());
		return fileNames;
	}

}
