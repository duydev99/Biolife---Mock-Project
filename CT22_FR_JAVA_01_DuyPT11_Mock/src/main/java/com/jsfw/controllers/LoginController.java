package com.jsfw.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jsfw.models.Tbl_User;
import com.jsfw.services.UserService;
import com.jsfw.utils.EncodeUntil;

@Controller
public class LoginController {
	@Autowired
	UserService service;

	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String getClientLogin(Model model, HttpServletRequest request) {
		try {
			Tbl_User sessionUser = (Tbl_User) request.getSession().getAttribute("userSession");
			if (sessionUser != null) {
				String str = "<a href='" + request.getContextPath()
						+ "/users-detail' class='login-link'><i class='biolife-icon icon-login'></i>"
						+ sessionUser.getUsername() + "</a>";
				model.addAttribute("changeHeader", str);
			} else {
				String str = "<a href='" + request.getContextPath()
						+ "/login' class='login-link'><i class='biolife-icon icon-login'></i>Login/Register</a>";
				model.addAttribute("changeHeader", str);
			}
			model.addAttribute("userLogin", new Tbl_User());
			model.addAttribute("body", IndexController.getBody("body_login"));
			model.addAttribute("path", request.getContextPath());
			return "index";
		} catch (Exception e) {
			System.out.println(e);
			return "redirect:/error-404";
		}
	}

	@RequestMapping(value = "login-save", method = RequestMethod.POST)
	public String postClientLogin(@ModelAttribute("userLogin") Tbl_User user, Model model, HttpServletRequest request) {
		try {
			if (service.findByUserName(user.getUsername()) == null) {
				Tbl_User sessionUser = (Tbl_User) request.getSession().getAttribute("userSession");
				if (sessionUser != null) {
					String str = "<a href='" + request.getContextPath()
							+ "/users-detail' class='login-link'><i class='biolife-icon icon-login'></i>"
							+ sessionUser.getUsername() + "</a>";
					model.addAttribute("changeHeader", str);
				} else {
					String str = "<a href='" + request.getContextPath()
							+ "/login' class='login-link'><i class='biolife-icon icon-login'></i>Login/Register</a>";
					model.addAttribute("changeHeader", str);
				}
				model.addAttribute("messUsername", "Tài khoảng không tồn tại");
				model.addAttribute("body", IndexController.getBody("body_login"));
				model.addAttribute("path", request.getContextPath());
				return "index";
			} else {
				Tbl_User sessionUser = (Tbl_User) request.getSession().getAttribute("userSession");
				if (sessionUser != null) {
					String str = "<a href='" + request.getContextPath()
							+ "/users-detail' class='login-link'><i class='biolife-icon icon-login'></i>"
							+ sessionUser.getUsername() + "</a>";
					model.addAttribute("changeHeader", str);
				} else {
					String str = "<a href='" + request.getContextPath()
							+ "/login' class='login-link'><i class='biolife-icon icon-login'></i>Login/Register</a>";
					model.addAttribute("changeHeader", str);
				}
				if (service.findByUsernameAndPassword(user.getUsername(),
						EncodeUntil.encoderString(user.getPassword())) == null) {
					// , ConvertPasswordToMD5.ConvertToMD5(user.getPassword())) == null) {
					model.addAttribute("messPass", "Mật khẩu chưa chính xác");
					model.addAttribute("body", IndexController.getBody("body_login"));
					model.addAttribute("path", request.getContextPath());
					return "index";
				} else {
					request.getSession().setAttribute("userSession", service.findByUserName(user.getUsername()));
					return "redirect:/index";
				}
			}
		} catch (Exception e) {
			System.out.println(e);
			return "redirect:/error-404";
		}

	}

}
