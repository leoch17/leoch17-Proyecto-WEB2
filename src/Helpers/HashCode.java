package Helpers;

import java.security.MessageDigest;
import java.lang.StringBuilder;
import java.security.NoSuchAlgorithmException;

public class HashCode {
	public String generateHash(String password) {
		String Hashpassword = " ";
		try {
			MessageDigest mdigest = MessageDigest.getInstance("SHA-256");
			mdigest.update(password.getBytes());
			byte[] hash = mdigest.digest();
			StringBuilder sbuilder = new StringBuilder();
			for(byte result: hash) {
				sbuilder.append(String.format("%02x",result));
			}
			Hashpassword = sbuilder.toString();
		}catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return Hashpassword;
				
	}

	

}
