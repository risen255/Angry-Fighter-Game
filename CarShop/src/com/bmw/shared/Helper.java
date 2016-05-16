package com.bmw.shared;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Helper {
	
	public static String md5(String str) {
		MessageDigest md5Diggest = null;
		
		try {
			md5Diggest = MessageDigest.getInstance("MD5");
			md5Diggest.update(StandardCharsets.UTF_8.encode(str));
		} catch(NoSuchAlgorithmException e) {
			System.out.println(e.toString());
		}
		return String.format("%032x", new BigInteger(1, md5Diggest.digest()));
	}
	
	public static boolean isNumeric(String str)
	{
		return str.matches("-?\\d+(\\.\\d+)?");  //match a number with optional '-' and decimal.
	}
}
