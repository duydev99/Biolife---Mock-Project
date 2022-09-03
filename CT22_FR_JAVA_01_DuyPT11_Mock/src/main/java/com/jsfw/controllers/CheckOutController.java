package com.jsfw.controllers;

import java.util.Date;
import java.util.List;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jsfw.models.Cart;
import com.jsfw.models.Tbl_Order;
import com.jsfw.models.Tbl_Order_Detail;
import com.jsfw.models.Tbl_Product;
import com.jsfw.models.Tbl_User;
import com.jsfw.services.CategoryService;
import com.jsfw.services.MailService;
import com.jsfw.services.ManufacturerService;
import com.jsfw.services.OrderDetailService;
import com.jsfw.services.OrderService;
import com.jsfw.services.PaymentService;
import com.jsfw.services.ProductService;
import com.jsfw.utils.MailUtils;

@Controller
public class CheckOutController {

	@Autowired
	PaymentService paymentService;
	@Autowired
	CategoryService categoryService;
	@Autowired
	ManufacturerService manufacturerService;
	@Autowired
	OrderService orderService;
	@Autowired
	OrderDetailService orderDetailService;
	@Autowired
	ProductService productService;
	@Autowired
	MailService mailService;

	// @PreAuthorize("hasRole('0')")
	@RequestMapping(value = "check-out", method = RequestMethod.GET)
	public String getCheckOut(Model model, HttpServletRequest request) {
		try {// Xử lý user login header
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

			Tbl_User userSessionTbl_User = (Tbl_User) request.getSession().getAttribute("userSession");
			if (userSessionTbl_User == null) {
				return "redirect:/security/login/form";
			} else {
				List<Cart> carts = (List<Cart>) request.getSession().getAttribute("sessionCart");
				if (carts.isEmpty()) {
					return "redirect:/shop?filter=all&orderby=default&key=-1&page=1";
				} else {
					// Xử lý lấy ra tổng giá trị của giỏ hàng
					int total = 0;
					for (Cart c : carts) {
						total += c.getProduct().getPrice() * c.getAmount();
					}

					model.addAttribute("total", total);
					model.addAttribute("numberProducts", carts.size());
					model.addAttribute("list", carts);
					model.addAttribute("categories", categoryService.findAll());
					model.addAttribute("manufacturers", manufacturerService.findAll());
					model.addAttribute("payments", paymentService.findAll());
					model.addAttribute("url", "Trang Chủ / Thanh Toán");
					model.addAttribute("body", IndexController.getBody("body_checkout"));
					model.addAttribute("path", request.getContextPath());
					return "index";
				}
			}
		} catch (Exception e) {
			System.out.println(e);
			return "redirect:/error-404";
		}
	}

	@RequestMapping(value = "save-check-out", method = RequestMethod.POST)
	public String saveCheckOut(HttpServletRequest request, Model model) throws MessagingException {
		try {
			String phoneString = request.getParameter("phone");
			String addressString = request.getParameter("address");
			String paymentId = request.getParameter("payment");
			Tbl_User user = (Tbl_User) request.getSession().getAttribute("userSession");
			List<Cart> carts = (List<Cart>) request.getSession().getAttribute("sessionCart");

			int total = 0;
			String mailString = "";

			for (Cart c : carts) {
				total += c.getAmount() * c.getProduct().getPrice();
			}

			Tbl_Order order = new Tbl_Order();
			order.setTblUser(user);
			order.setPhone(phoneString);
			order.setAddress(addressString);
			order.setTblPayment(paymentService.findById(Integer.valueOf(paymentId)).get());
			order.setTotal(total);
			order.setCreateTime(new Date());
			order.setUpdateTime(new Date());
			order.setStatus(0);
			orderService.create(order);

			for (Cart cart : carts) {
				Tbl_Order_Detail detail = new Tbl_Order_Detail();
				Tbl_Product product = productService.findById(cart.getId()).get();
				detail.setTblProduct(product);
				detail.setAmount(cart.getAmount());
				detail.setPrice(cart.getProduct().getPrice());
				detail.setCreateTime(new Date());
				detail.setUpdateTime(new Date());
				detail.setTblOrder(order);
				orderDetailService.create(detail);
				product.setAmount(product.getAmount() - cart.getAmount());
				productService.edit(product);
			}

			mailService.sendEmail(user.getEmail(), "Hóa đơn đặt hàng", MailUtils.getMail(carts, order));

			request.getSession().setAttribute("sessionCart", null);
			return "redirect:/index";
		} catch (Exception e) {
			System.out.println(e);
			return "redirect:/error-404";
		}
	}
}
