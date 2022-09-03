package com.jsfw.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class EncodeUntil {
	private static BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
	
	public static String encoderString(String string) {
		return	bCryptPasswordEncoder.encode(string);
	}
	
	public static boolean stringEqualsStringEncode(String string, String stringEncode) {
		return	bCryptPasswordEncoder.matches(string,stringEncode);
	}
}
