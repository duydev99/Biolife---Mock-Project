package com.jsfw.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jsfw.dto.PasswordDTO;
import com.jsfw.models.FogotPasswordCode;
import com.jsfw.services.FogotPasswordCodeService;
import com.jsfw.services.PasswordService;
import com.jsfw.services.UserService;

@Controller
public class FogotPasswordController {
	@Autowired
	UserService userService;

	@Autowired
	PasswordService passwordService;

	@Autowired
	FogotPasswordCodeService fogotPasswordCodeService;

	@Autowired
	HttpServletRequest request;

	@GetMapping("/user/fogot_password/view")
	public String getFogotPassword(Model model) {
		try {
			model.addAttribute("body", IndexController.getBody("body_fogot_password"));
			model.addAttribute("path", request.getContextPath());
			return "index";
		} catch (Exception e) {
			System.out.println(e);
			return "redirect:/error-404";
		}
	}

	@PostMapping("/user/fogot_password/send_mail")
	public String endTokenForEmail(@RequestParam("email") String email, RedirectAttributes redirectAttributes) {
		try {
			boolean check = userService.checkEmail(email);

			if (email.equals("")) {
				redirectAttributes.addFlashAttribute("message", "Bạn chưa nhập email!");
			} else if (check) {
				passwordService.sendRequestFogotPassword(email);
				redirectAttributes.addFlashAttribute("message", "Vui lòng kiểm tra mail của bạn!");
			} else {
				redirectAttributes.addFlashAttribute("message", "Email không tồn tại");
			}
			return "redirect:/user/fogot_password/view";
		} catch (Exception e) {
			System.out.println(e);
			return "redirect:/error-404";
		}
	}

//
	@GetMapping("/user/fogot_password/set_password/view")
	public String getChangePasswordToken(Model model, @RequestParam("token") String token) {
		// xac nhan token co ton tai khong
		try {
			FogotPasswordCode fogotPasswordCode = fogotPasswordCodeService.getFogotPasswordCodeByCode(token);
			if (fogotPasswordCode != null) {
				// kiem tra token con song khong
				if (fogotPasswordCodeService.checkCodeExpired(fogotPasswordCode)) {
					model.addAttribute("token", token);
					model.addAttribute("body", IndexController.getBody("body_set_password"));
					model.addAttribute("path", request.getContextPath());
					return "index";
				}
			}
			return "redirect:/error-404";
		} catch (Exception e) {
			System.out.println(e);
			return "redirect:/error-404";
		}
	}

	@PostMapping("/user/fogot_password/set_password/submit")
	public String postChangePasswordToken(RedirectAttributes redirectAttributes, @RequestParam("token") String token,
			@ModelAttribute PasswordDTO passwordDTO) {
		try {
			if (passwordDTO.getConfirmPassword().equals(passwordDTO.getNewPassword())) {
				// Đặt lại mk
				passwordService.setPasswordNew(token, passwordDTO.getNewPassword());
				redirectAttributes.addFlashAttribute("message", "Đặt lại mật khẩu thành công");
			} else {
				redirectAttributes.addFlashAttribute("message", "Mật khẩu xác nhận không chính xác");
				redirectAttributes.addFlashAttribute("token", token);
			}
			return "redirect:/security/login/form";
		} catch (Exception e) {
			System.out.println(e);
			return "redirect:/error-404";
		}
	}

}
