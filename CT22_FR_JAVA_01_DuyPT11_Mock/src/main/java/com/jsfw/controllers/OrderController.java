package com.jsfw.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.jsfw.models.Tbl_Order;
import com.jsfw.models.Tbl_User;
import com.jsfw.services.OrderService;
import com.jsfw.utils.PageUtils;

@Controller
public class OrderController {

	@Autowired
	OrderService orderService;

	@RequestMapping(value = { "orders" }, method = RequestMethod.GET)
	public String getOrder(@RequestParam("page") int page, Model model, HttpServletRequest request) {
		try {
			model.addAttribute("body", IndexController.getBody("body_orders"));
			model.addAttribute("path", request.getContextPath());
			model.addAttribute("currentPage", "Quản lý hóa đơn");
			model.addAttribute("url", "Trang chủ / Hóa đơn");
			List<Tbl_Order> orders = orderService.findAll();
			int numObjInPage = 10;
			int totalPages = PageUtils.getTotalPage(orders.size(), numObjInPage);
			String pathString = request.getContextPath() + "/orders?";
			String pageString = PageUtils.getPages(page, totalPages, pathString);
			model.addAttribute("page", pageString);
			model.addAttribute("list", PageUtils.getListOrdersInPage(page, numObjInPage, orders));
			Tbl_User masterTbl_User = (Tbl_User) request.getSession().getAttribute("master");
			if (masterTbl_User == null || request.getSession().getAttribute("master") == "") {
				return "redirect:/security/login/form";
			} else {
				model.addAttribute("name", masterTbl_User.getName());
				return "master/index";
			}
		} catch (Exception e) {
			System.out.println(e);
			return "redirect:/orders";
		}
	}

	@RequestMapping(value = { "order-change-status" }, method = RequestMethod.GET)
	public String changeStatus(@RequestParam("orderId") int id) {
		try {
			Tbl_Order order = orderService.findById(id);
			order.setStatus(1);
			orderService.edit(order);
			return "redirect:/orders?page=1";
		} catch (Exception e) {
			System.out.println(e);
			return "redirect:/error-404";
		}
	}

	@RequestMapping(value = "order-detail-server", method = RequestMethod.GET)
	public String getDetail(@RequestParam("orderId") int id, Model model, HttpServletRequest request) {
		try {
			model.addAttribute("body", IndexController.getBody("body_order_detail"));
			model.addAttribute("path", request.getContextPath());
			model.addAttribute("currentPage", "Chi tiết hóa đơn");
			model.addAttribute("url", "Trang chủ / Hóa đơn / Chi tiết");
			model.addAttribute("list", orderService.findById(id).getTblOrderDetails());
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
}
