package com.jsfw.services.impl;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.jsfw.services.MailService;

@Service
public class MailServiceImpl implements MailService{
	@Autowired
    private JavaMailSender javaMailSender;
	
	@Override
	public void sendEmail(String toMail, String subject, String content) throws MessagingException {
		//SimpleMailMessage msg = new SimpleMailMessage();
		
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, "UTF-8");
		
		mimeMessageHelper.setTo(toMail);

		mimeMessageHelper.setSubject(subject);
		mimeMessageHelper.setText(content,true);

        javaMailSender.send(mimeMessage);
	}

}
