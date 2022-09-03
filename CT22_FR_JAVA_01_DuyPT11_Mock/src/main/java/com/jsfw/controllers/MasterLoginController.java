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
@RequestMapping(value = "/master_login/")
public class MasterLoginController {

	@Autowired
	UserService service;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String getMasterLogin(Model model, HttpServletRequest request) {
		try {
			model.addAttribute("masterLogin", new Tbl_User());
			model.addAttribute("path", request.getContextPath());
			return "master/login";
		} catch (Exception e) {
			System.out.println(e);
			return "redirect:/error-404";
		}
	}

	@RequestMapping(value = "save", method = RequestMethod.POST)
	public String postMasterLogin(@ModelAttribute("masterLogin") Tbl_User user, Model model,
			HttpServletRequest request) {
		try {
			request.getSession().setAttribute("master", null);
			model.addAttribute("masterLogin", new Tbl_User());
			model.addAttribute("path", request.getContextPath());
			if (service.findByUserName(user.getUsername()) == null) {
				model.addAttribute("messUsername", "Tài khoản không tồn tại");
				return "master/login";
			} else {
				Tbl_User rsUser = service.findByUsernameAndPassword(user.getUsername(),
						EncodeUntil.encoderString(user.getPassword()));
				if (rsUser == null) {
					model.addAttribute("messPass", "Mật khẩu không đúng");
					return "master/login";
				} else {
					if (service.findByUserName(user.getUsername()).getType() != 1) {
						model.addAttribute("messUsername", "Tài khoản không có quyền admin");
						return "master/login";
					} else {
						request.getSession().setAttribute("master", rsUser);
						return "redirect:/master";
					}

				}
			}
		} catch (Exception e) {
			System.out.println(e);
			return "redirect:/error-404";
		}

	}
}
