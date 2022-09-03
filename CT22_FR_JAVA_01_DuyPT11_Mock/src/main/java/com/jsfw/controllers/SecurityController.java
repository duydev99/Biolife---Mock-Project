package com.jsfw.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jsfw.models.Tbl_User;
import com.jsfw.services.UserService;

@Controller
public class SecurityController {
	@Autowired
	HttpServletRequest request;
	@Autowired
	UserService UserService;

	@RequestMapping("/security/login/form")
	public String loginForm(Model model) {
		try {// Tbl_User u = UserService.findByUserName("trung123123");
			// System.err.println(u.getUsername());
			// System.err.println(u.getPassword());
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
			model.addAttribute("message", "Vui lòng đăng nhập!");
			model.addAttribute("userLogin", new Tbl_User());
			model.addAttribute("body", IndexController.getBody("body_login"));
			model.addAttribute("path", request.getContextPath());
			return "index";
		} catch (Exception e) {
			System.out.println(e);
			return "redirect:/error-404";
		}
	}

	@RequestMapping("/security/login/success")
	public String loginSuccess(Model model, Authentication authentication, HttpServletRequest req) {
		try {
			Tbl_User sessionUser = UserService.findByUserName(req.getRemoteUser());

			if (req.isUserInRole("1")) {
				req.getSession().setAttribute("master", sessionUser);
				return "redirect:/master";
			} else {
				req.getSession().setAttribute("userSession", sessionUser);
				return "redirect:/index";
			}
		} catch (Exception e) {
			System.out.println(e);
			return "redirect:/error-404";
		}

	}

	@RequestMapping("/security/login/error")
	public String loginError(Model model) {
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
			model.addAttribute("message", "Tài khoản hoặc mật khẩu không chính xác!");
			model.addAttribute("userLogin", new Tbl_User());
			model.addAttribute("body", IndexController.getBody("body_login"));
			model.addAttribute("path", request.getContextPath());
			return "index";
		} catch (Exception e) {
			System.out.println(e);
			return "redirect:/error-404";
		}
	}

	@RequestMapping("/security/unauthoried")
	public String unauthoried(Model model) {
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
			model.addAttribute("message", "Không có quyền truy xuất!");
			model.addAttribute("body", IndexController.getBody("body_login"));
			model.addAttribute("userLogin", new Tbl_User());
			model.addAttribute("path", request.getContextPath());
			return "index";
		} catch (Exception e) {
			System.out.println(e);
			return "redirect:/error-404";
		}
	}

	@RequestMapping("/security/logoff/success")
	public String logoffSuccess(Model model) {
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
			request.getSession().setAttribute("userSession", null);
			request.getSession().setAttribute("master", null);
			model.addAttribute("message", "Đăng xuất thành công!");
			model.addAttribute("body", IndexController.getBody("body_login"));
			model.addAttribute("userLogin", new Tbl_User());
			model.addAttribute("path", request.getContextPath());
			return "index";
		} catch (Exception e) {
			System.out.println(e);
			return "redirect:/error-404";
		}
	}
}
