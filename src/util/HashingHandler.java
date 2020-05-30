package util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashingHandler {

	private static final String ALGORITHM_NAME = "SHA-256";
	
	public static String SHA256(String plainText) {
		
		try {
			MessageDigest messageDigest = MessageDigest.getInstance(ALGORITHM_NAME);
			byte[] messageDigestByte = messageDigest.digest(plainText.getBytes());

			BigInteger bigInteger = new BigInteger(1, messageDigestByte);
			String hashText = bigInteger.toString(16);

			while (hashText.length() < 32) 
				hashText = "0" + hashText;

			return hashText;
		} catch (NoSuchAlgorithmException e) {
			MessageHandler.error("Error hashing password !");
			System.exit(0);
		}

		return null;
	}

}
