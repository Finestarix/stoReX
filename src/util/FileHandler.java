package util;

import java.io.File;

public class FileHandler {

	public static final String ASSET_DIRECTORY = "\\src\\app\\asset";
	
	public static String getAssetsPath() {
		return System.getProperty("user.dir") + ASSET_DIRECTORY;
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

}
