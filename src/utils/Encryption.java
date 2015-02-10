package utils;

import java.io.UnsupportedEncodingException;
import java.security.*;

//Classe per poder encriptar dades
public class Encryption {
	public static String MD5(String string) {
		byte[] bytesOfMessage;
		try {
			bytesOfMessage = string.getBytes("UTF-8");
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte byteData[] = md.digest(bytesOfMessage);
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < byteData.length; i++) {
				sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16)
						.substring(1));
			}
			return sb.toString();
		} catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
			e.printStackTrace();
			return "";
		}
	}
}
