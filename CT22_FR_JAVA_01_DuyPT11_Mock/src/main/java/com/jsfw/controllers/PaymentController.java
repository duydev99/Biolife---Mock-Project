package com.jsfw.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.jsfw.models.Tbl_Payment;
import com.jsfw.models.Tbl_User;
import com.jsfw.services.PaymentService;
import com.jsfw.utils.PageUtils;

@Controller
@RequestMapping(value = "/payments/")
public class PaymentController {
	@Autowired
	PaymentService service;

	@RequestMapping(value = { "", "index", "list" }, method = RequestMethod.GET)
	public String getPayment(@RequestParam("page") int page, Model model, HttpServletRequest request) {
		try {
			model.addAttribute("body", IndexController.getBody("body_payment"));
			model.addAttribute("path", request.getContextPath());
			model.addAttribute("currentPage", "Quản lý hình thức thanh toán");
			model.addAttribute("url", "Trang chủ / Hình thức thanh toán");
			List<Tbl_Payment> payments = service.findAll();
			int numObjInPage = 10;
			int totalPages = PageUtils.getTotalPage(payments.size(), numObjInPage);
			String pathString = request.getContextPath() + "/payments?";
			String pageString = PageUtils.getPages(page, totalPages, pathString);
			model.addAttribute("page", pageString);
			model.addAttribute("list", PageUtils.getListPaymentsInPage(page, numObjInPage, payments));
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

	@RequestMapping(value = "add", method = RequestMethod.GET)
	public String getPaymentAdd(Model model, HttpServletRequest request) {
		try {
			model.addAttribute("body", IndexController.getBody("body_payment_add"));
			model.addAttribute("path", request.getContextPath());
			model.addAttribute("currentPage", "Thêm mới hình thức thanh toán");
			model.addAttribute("url", "Trang chủ / Hình thức thanh toán / Thêm mới");
			model.addAttribute("paymentAdd", new Tbl_Payment());
			model.addAttribute("mess", null);
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

	@RequestMapping(value = "postAdd", method = RequestMethod.POST)
	public String postPaymentAdd(@ModelAttribute("PaymentAdd") Tbl_Payment payment, Model model,
			HttpServletRequest request) {
		try {
			model.addAttribute("mess", null);
			if (service.findByName(payment.getMethod()) == null) {
				service.create(payment);
				return "redirect:/payments?page=1";
			} else {
				model.addAttribute("body", IndexController.getBody("body_payment_add"));
				model.addAttribute("path", request.getContextPath());
				model.addAttribute("currentPage", "Thêm mới hình thức thanh toán");
				model.addAttribute("url", "Trang chủ / Hình thức thanh toán / Thêm mới");
				model.addAttribute("paymentAdd", new Tbl_Payment());
				model.addAttribute("mess", "Hình thức này đã tồn tại");
				Tbl_User masterTbl_User = (Tbl_User) request.getSession().getAttribute("master");
				if (masterTbl_User == null || request.getSession().getAttribute("master") == "") {
					return "redirect:/security/login/form";
				} else {
					model.addAttribute("name", masterTbl_User.getName());
					return "master/index";
				}
			}
		} catch (Exception e) {
			System.out.println(e);
			return "redirect:/error-404";
		}
	}

	@RequestMapping(value = "edit", method = RequestMethod.GET)
	public String getPaymentEdit(@RequestParam("id") int id, Model model, HttpServletRequest request) {
		try {
			model.addAttribute("body", IndexController.getBody("body_payment_update"));
			model.addAttribute("path", request.getContextPath());
			model.addAttribute("currentPage", "Cập nhật hình thức thanh toán");
			model.addAttribute("url", "Trang chủ / Hình thức thanh toán / Cập nhật");
			model.addAttribute("paymentEdit", service.findById(id));
			model.addAttribute("mess", null);
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

	@RequestMapping(value = "postEdit", method = RequestMethod.POST)
	public String postPaymentEdit(@ModelAttribute("paymentEdit") Tbl_Payment payment, Model model,
			HttpServletRequest request) {
		try {
			model.addAttribute("mess", null);
			if (service.findByName(payment.getMethod()) == null) {
				service.edit(payment);
				return "redirect:/payments?page=1";
			} else {
				if (service.findByName(payment.getMethod()).getId() == payment.getId()) {
					service.edit(payment);
					return "redirect:/payments?page=1";
				} else {
					model.addAttribute("body", IndexController.getBody("body_payment_update"));
					model.addAttribute("path", request.getContextPath());
					model.addAttribute("currentPage", "Cập nhật hình thức thanh toán");
					model.addAttribute("url", "Trang chủ / Hình thức thanh toán / Cập nhật");
					model.addAttribute("paymentEdit", service.findById(payment.getId()));
					model.addAttribute("mess", "Hình thức này đã tồn tại");
					Tbl_User masterTbl_User = (Tbl_User) request.getSession().getAttribute("master");
					if (masterTbl_User == null || request.getSession().getAttribute("master") == "") {
						return "redirect:/security/login/form";
					} else {
						model.addAttribute("name", masterTbl_User.getName());
						return "master/index";
					}
				}
			}
		} catch (Exception e) {
			System.out.println(e);
			return "redirect:/error-404";
		}
	}

	@RequestMapping(value = "delete", method = RequestMethod.GET)
	public String getPaymentDelete(@RequestParam("id") int id) {
		try {
			service.remove(id);
			return "redirect:/payments?page=1";
		} catch (Exception e) {
			System.out.println(e);
			return "redirect:/error-404";
		}
	}
}
