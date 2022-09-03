package com.jsfw.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jsfw.models.Tbl_User;
import com.jsfw.services.CategoryService;
import com.jsfw.services.ManufacturerService;
import com.jsfw.services.ProductService;

@Controller
@RequestMapping(value = "/master/")
public class MasterController {

	@Autowired
	ProductService productService;
	@Autowired
	CategoryService categoryService;
	@Autowired
	ManufacturerService manufacturerService;

	@RequestMapping(value = { "", "index", "home" }, method = RequestMethod.GET)
	public String getMaster(Model model, HttpServletRequest request) {
		try {
			model.addAttribute("body", IndexController.getBody("body_statistics"));
			model.addAttribute("path", request.getContextPath());
			model.addAttribute("currentPage", "Trang chủ");
			model.addAttribute("url", "Trang chủ");
			model.addAttribute("products", productService.findAll());
			model.addAttribute("categories", categoryService.findAll());
			model.addAttribute("manufacturers", manufacturerService.findAll());
			Tbl_User masterTbl_User = (Tbl_User) request.getSession().getAttribute("master");
			if (masterTbl_User == null || request.getSession().getAttribute("master") == "") {
				return "redirect:/security/login/form";
			} else {
				model.addAttribute("name", masterTbl_User.getName());
				return "master/index";
			}
		} catch (Exception e) {
			System.out.println(e);
			return "redirect:/error-404";
		}
	}

	@RequestMapping(value = "logout", method = RequestMethod.GET)
	public String getLogOut(HttpServletRequest request) {
		try {
			request.getSession().setAttribute("master", null);
			return "redirect:/master";
		} catch (Exception e) {
			System.out.println(e);
			return "redirect:/error-404";
		}
	}

}
