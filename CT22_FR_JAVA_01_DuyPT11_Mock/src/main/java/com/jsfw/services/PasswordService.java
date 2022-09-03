package com.jsfw.services;

public interface PasswordService {
	
//	boolean checkPassword(String passwordCheck);
//	
//	void changePassword(String newPassword);
	
	void sendRequestFogotPassword(String email);
	
	void setPasswordNew(String token, String newPassword);
}
