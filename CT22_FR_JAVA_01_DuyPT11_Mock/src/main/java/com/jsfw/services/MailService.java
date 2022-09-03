package com.jsfw.services;

import javax.mail.MessagingException;

public interface MailService {
	void sendEmail(String toMail,String subject, String content) throws MessagingException;
}
