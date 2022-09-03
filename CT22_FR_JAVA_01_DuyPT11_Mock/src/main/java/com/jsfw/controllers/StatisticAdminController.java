package com.jsfw.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.jsfw.services.OrderDetailService;
import com.jsfw.services.ProductService;

@Controller
public class StatisticAdminController {
	@Autowired
	OrderDetailService orderDetailService;

	@Autowired
	HttpServletRequest request;

	@Autowired
	ProductService service;

	@PreAuthorize("hasRole('1')")
	@GetMapping("/admin/index")
	public String getViewCategoryAdmin() {
		return "admin/statistic/view";
	}

	@PreAuthorize("hasRole('1')")
	@GetMapping("/admin/statistic/turnover-by-category")
	public String turnoverByCategory(Model model) {
		try {
			model.addAttribute("body", IndexController.getBody("turnover-by-category"));
			model.addAttribute("path", request.getContextPath());
			model.addAttribute("currentPage", "");
			model.addAttribute("url", "Trang chủ / Thống kê theo loại");

			List<Object[]> data = orderDetailService.turnoverByCategory();
			model.addAttribute("data", data);
			return "master/index";
		} catch (Exception e) {
			System.out.println(e);
			return "redirect:/error-404";
		}
	}

	@PreAuthorize("hasRole('1')")
	@GetMapping("/admin/statistic/turnover-by-product")
	public String turnoverByProduct(Model model) {
		try {
			model.addAttribute("body", IndexController.getBody("turnover-by-product"));
			model.addAttribute("path", request.getContextPath());
			model.addAttribute("currentPage", "");
			model.addAttribute("url", "Trang chủ / Thống kê theo sản phẩm");

			List<Object[]> data = orderDetailService.turnoverByProduct();
			model.addAttribute("data", data);
			return "master/index";
		} catch (Exception e) {
			System.out.println(e);
			return "redirect:/error-404";
		}
	}

	@GetMapping("/admin/statistic/turnover-by-user")
	public String turnoverByUser(Model model) {
		try {
			model.addAttribute("body", IndexController.getBody("turnover-by-user"));
			model.addAttribute("path", request.getContextPath());
			model.addAttribute("currentPage", "");
			model.addAttribute("url", "Trang chủ / Thống kê theo khách hàng");

			List<Object[]> data = orderDetailService.turnoverByUser();
			model.addAttribute("data", data);
			return "master/index";
		} catch (Exception e) {
			System.out.println(e);
			return "redirect:/error-404";
		}
	}

}
