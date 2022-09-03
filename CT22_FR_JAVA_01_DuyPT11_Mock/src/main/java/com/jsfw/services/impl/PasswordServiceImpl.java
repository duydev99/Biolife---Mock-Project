package com.jsfw.services.impl;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jsfw.models.FogotPasswordCode;
import com.jsfw.models.Tbl_User;
import com.jsfw.repositories.UserRepository;
import com.jsfw.services.FogotPasswordCodeService;
import com.jsfw.services.MailService;
import com.jsfw.services.PasswordService;
import com.jsfw.utils.EncodeUntil;


@Service
public class PasswordServiceImpl implements PasswordService{
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	FogotPasswordCodeService fogotPasswordCodeService;
	
	@Autowired
	MailService mailService;
	
	private String contentMailFogotPassword(String token) {
		String content = "<p><a href='http://localhost:8080/user/fogot_password/set_password/view?token=" + token + "'>Đường dẫn đổi mật khẩu mới.</a></p>";
		String str="<p>Chào bạn, chúng tôi nhận được yêu cầu lấy lại mật khẩu từ bạn.</p>"
				+ "<p>Hãy nhấp vào link dưới đây để đổi mật khẩu:</p>";
		System.err.println(content);
		return str+content;
	}
	
	@Override
	public void sendRequestFogotPassword(String email) {
		final String subject = "Đặt Lại Mật Khẩu";
		FogotPasswordCode fogotPasswordCode = new FogotPasswordCode(email);
		fogotPasswordCodeService.createFogotPasswordCode(fogotPasswordCode);
		try {
			mailService.sendEmail(fogotPasswordCode.getEmail(), subject, this.contentMailFogotPassword(fogotPasswordCode.getCode()));
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void setPasswordNew(String code, String newPassword) {
		FogotPasswordCode confirmationToken = fogotPasswordCodeService.getFogotPasswordCodeByCode(code);
		Tbl_User user = userRepository.findByEmail(confirmationToken.getEmail());
		user.setPassword(EncodeUntil.encoderString(newPassword));
		userRepository.save(user);
		fogotPasswordCodeService.deleteFogotPasswordCode(confirmationToken);
	}

}
