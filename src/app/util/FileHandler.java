package app.util;

import java.io.File;
import java.util.ArrayList;

public class FileHandler {

	public static final String ASSET_DIRECTORY = "\\src\\app\\assets";
	public static final String RECEIPT_DIRECTORY = "\\src\\receipts";
	public static final String DATABASE_DIRECTORY = "\\src\\database";

	public static String getAssetsPath() {
		return System.getProperty("user.dir") + ASSET_DIRECTORY;
	}
	
	public static String getReceiptPath() {
		String receiptPath = System.getProperty("user.dir") + RECEIPT_DIRECTORY;
		
		File receiptDirectory = new File(receiptPath);
		if (!receiptDirectory.exists()) 
			receiptDirectory.mkdir();
			
		return receiptPath;
	}
	
	public static String getDatabasePath() {
		String productPath = System.getProperty("user.dir") + DATABASE_DIRECTORY;
		
		File productDirectory = new File(productPath);
		if (!productDirectory.exists()) 
			productDirectory.mkdir();
			
		return productPath;
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
