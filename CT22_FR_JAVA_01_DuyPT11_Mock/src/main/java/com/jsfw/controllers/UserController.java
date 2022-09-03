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

import com.jsfw.models.Tbl_Order;
import com.jsfw.models.Tbl_Order_Detail;
import com.jsfw.models.Tbl_Product;
import com.jsfw.models.Tbl_User;
import com.jsfw.services.CategoryService;
import com.jsfw.services.ManufacturerService;
import com.jsfw.services.OrderDetailService;
import com.jsfw.services.OrderService;
import com.jsfw.services.ProductService;
import com.jsfw.services.UserService;
import com.jsfw.utils.EncodeUntil;
import com.jsfw.utils.PageUtils;

@Controller
public class UserController {

	@Autowired
	CategoryService categoryService;
	@Autowired
	ManufacturerService manufacturerService;
	@Autowired
	OrderService orderService;
	@Autowired
	OrderDetailService orderDetailService;
	@Autowired
	UserService userService;
	@Autowired
	ProductService productService;

	@RequestMapping(value = "users/index", method = RequestMethod.GET)
	public String getIndex(@RequestParam("page") int page, Model model, HttpServletRequest request) {
		try {
			model.addAttribute("body", IndexController.getBody("body_user"));
			model.addAttribute("path", request.getContextPath());
			model.addAttribute("currentPage", "Quản lý người dùng");
			model.addAttribute("url", "Trang chủ / Người dùng");
			List<Tbl_User> users = userService.findAll();
			int numObjInPage = 10;
			int totalPages = PageUtils.getTotalPage(users.size(), numObjInPage);
			String pathString = request.getContextPath() + "/users/index?";
			String pageString = PageUtils.getPages(page, totalPages, pathString);
			model.addAttribute("page", pageString);
			model.addAttribute("list", PageUtils.getListUsersInPage(page, numObjInPage, users));
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

	@RequestMapping(value = "users/add", method = RequestMethod.GET)
	public String getUserAdd(Model model, HttpServletRequest request) {
		try {
			model.addAttribute("body", IndexController.getBody("body_user_add"));
			model.addAttribute("path", request.getContextPath());
			model.addAttribute("currentPage", "Thêm người dùng");
			model.addAttribute("url", "Trang chủ / Người dùng / Thêm mới");
			model.addAttribute("userAdd", new Tbl_User());
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

	@RequestMapping(value = "users/edit", method = RequestMethod.GET)
	public String getUserEdit(@RequestParam("id") int id, Model model, HttpServletRequest request) {
		try {
			model.addAttribute("body", IndexController.getBody("body_user_edit"));
			model.addAttribute("path", request.getContextPath());
			model.addAttribute("currentPage", "Cập nhật người dùng");
			model.addAttribute("url", "Trang chủ / Người dùng / Cập nhật");
			model.addAttribute("userEdit", userService.findById(id).get());
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

	@RequestMapping(value = "users/change-pass", method = RequestMethod.GET)
	public String getUserChangePass(@RequestParam("id") int id, Model model, HttpServletRequest request) {
		try {
			model.addAttribute("body", IndexController.getBody("body_user_change_pass"));
			model.addAttribute("path", request.getContextPath());
			model.addAttribute("currentPage", "Đổi mật khẩu");
			model.addAttribute("url", "Trang chủ / Người dùng / Đổi mật khẩu");
			model.addAttribute("id", id);
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

	@RequestMapping(value = "users/postChange", method = RequestMethod.POST)
	public String postUserChangePass(@RequestParam("id") int id, @RequestParam("pass") String pass, Model model,
			HttpServletRequest request) {
		try {
			Tbl_User user = userService.findById(id).get();
			user.setPassword(EncodeUntil.encoderString(pass));
			userService.edit(user);
		} catch (Exception e) {
			System.out.println(e);
			return "redirect:/error-404";
		}
		return "redirect:/users/index?page=1";
	}

	@RequestMapping(value = "user/postAdd", method = RequestMethod.POST)
	public String postUserAdd(@ModelAttribute("userAdd") Tbl_User user, Model model, HttpServletRequest request) {
		try {
			if (userService.findByUserName(user.getUsername()) == null) {

				if (userService.findByEmail(user.getEmail()) != null) {
					model.addAttribute("body", IndexController.getBody("body_user_add"));
					model.addAttribute("path", request.getContextPath());
					model.addAttribute("currentPage", "Thêm người dùng");
					model.addAttribute("url", "Trang chủ / Người dùng / Thêm mới");
					model.addAttribute("messUsername", null);
					model.addAttribute("messEmail", "Email đã tồn tại");
					model.addAttribute("messPhone", null);
					model.addAttribute("userAdd", new Tbl_User());
					Tbl_User masterTbl_User = (Tbl_User) request.getSession().getAttribute("master");
					if (masterTbl_User == null || request.getSession().getAttribute("master") == "") {
						return "redirect:/security/login/form";
					} else {
						model.addAttribute("name", masterTbl_User.getName());
						return "master/index";
					}
				} else if (userService.findByPhone(user.getPhone()) != null) {
					model.addAttribute("body", IndexController.getBody("body_user_add"));
					model.addAttribute("path", request.getContextPath());
					model.addAttribute("currentPage", "Thêm người dùng");
					model.addAttribute("url", "Trang chủ / Người dùng / Thêm mới");
					model.addAttribute("messUsername", null);
					model.addAttribute("messEmail", null);
					model.addAttribute("messPhone", "Số điện thoại đã tồn tại");
					model.addAttribute("userAdd", new Tbl_User());
					Tbl_User masterTbl_User = (Tbl_User) request.getSession().getAttribute("master");
					if (masterTbl_User == null || request.getSession().getAttribute("master") == "") {
						return "redirect:/security/login/form";
					} else {
						model.addAttribute("name", masterTbl_User.getName());
						return "master/index";
					}
				} else {
					Tbl_User newUser = new Tbl_User();
					newUser.setName(user.getName());
					newUser.setUsername(user.getUsername());
					newUser.setPassword(EncodeUntil.encoderString(user.getPassword()));
					newUser.setPhone(user.getPhone());
					newUser.setAddress(user.getAddress());
					newUser.setEmail(user.getEmail());
					newUser.setStatus(0);
					newUser.setType(user.getType());
					userService.create(newUser);
					return "redirect:/users/index?page=1";
				}
			} else {
				model.addAttribute("body", IndexController.getBody("body_user_add"));
				model.addAttribute("path", request.getContextPath());
				model.addAttribute("currentPage", "Thêm người dùng");
				model.addAttribute("url", "Trang chủ / Người dùng / Thêm mới");
				model.addAttribute("messUsername", "Tài khoản đã tồn tại");
				model.addAttribute("messEmail", null);
				model.addAttribute("messPhone", null);
				model.addAttribute("userAdd", new Tbl_User());
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

	@RequestMapping(value = "user/postEdit", method = RequestMethod.POST)
	public String postUserEdit(@ModelAttribute("userEdit") Tbl_User user, Model model, HttpServletRequest request) {
		try {
			if (userService.findByUserName(user.getUsername()) == null
					|| userService.findById(user.getId()).get().getUsername().equals(user.getUsername())) {

				if (userService.findByEmail(user.getEmail()) != null
						&& !userService.findById(user.getId()).get().getEmail().equals(user.getEmail())) {
					model.addAttribute("body", IndexController.getBody("body_user_edit"));
					model.addAttribute("path", request.getContextPath());
					model.addAttribute("currentPage", "Cập nhật người dùng");
					model.addAttribute("url", "Trang chủ / Người dùng / Cập nhật");
					model.addAttribute("messUsername", null);
					model.addAttribute("messEmail", "Email đã tồn tại");
					model.addAttribute("messPhone", null);
					model.addAttribute("userAdd", new Tbl_User());
					Tbl_User masterTbl_User = (Tbl_User) request.getSession().getAttribute("master");
					if (masterTbl_User == null || request.getSession().getAttribute("master") == "") {
						return "redirect:/security/login/form";
					} else {
						model.addAttribute("name", masterTbl_User.getName());
						return "master/index";
					}
				} else if (userService.findByPhone(user.getPhone()) != null
						&& !userService.findById(user.getId()).get().getPhone().equals(user.getPhone())) {
					model.addAttribute("body", IndexController.getBody("body_user_edit"));
					model.addAttribute("path", request.getContextPath());
					model.addAttribute("currentPage", "Cập nhật người dùng");
					model.addAttribute("url", "Trang chủ / Người dùng / Cập nhật");
					model.addAttribute("messUsername", null);
					model.addAttribute("messEmail", null);
					model.addAttribute("messPhone", "Số điện thoại đã tồn tại");
					model.addAttribute("userAdd", new Tbl_User());
					Tbl_User masterTbl_User = (Tbl_User) request.getSession().getAttribute("master");
					if (masterTbl_User == null || request.getSession().getAttribute("master") == "") {
						return "redirect:/security/login/form";
					} else {
						model.addAttribute("name", masterTbl_User.getName());
						return "master/index";
					}
				} else {
					Tbl_User newUser = new Tbl_User();
					newUser.setId(user.getId());
					newUser.setName(user.getName());
					newUser.setUsername(user.getUsername());
					newUser.setPhone(user.getPhone());
					newUser.setAddress(user.getAddress());
					newUser.setEmail(user.getEmail());
					newUser.setType(user.getType());
					userService.edit(newUser);
					return "redirect:/users/index?page=1";
				}
			} else {
				model.addAttribute("body", IndexController.getBody("body_user_edit"));
				model.addAttribute("path", request.getContextPath());
				model.addAttribute("currentPage", "Cập nhật người dùng");
				model.addAttribute("url", "Trang chủ / Người dùng / Cập nhật");
				model.addAttribute("messUsername", "Tài khoản đã tồn tại");
				model.addAttribute("messEmail", null);
				model.addAttribute("messPhone", null);
				model.addAttribute("userAdd", new Tbl_User());
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

	@RequestMapping(value = "client-logout", method = RequestMethod.GET)
	public String getLogOut(HttpServletRequest request) {
		try {
			request.getSession().setAttribute("userSession", null);
			return "redirect:/index";
		} catch (Exception e) {
			System.out.println(e);
			return "redirect:/error-404";
		}
	}

	@RequestMapping(value = "users-detail", method = RequestMethod.GET)
	public String logOut(HttpServletRequest request, Model model) {
		// Xử lý header user login
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

			// Set model
			List<Tbl_Order> orders = orderService.findByUser(sessionUser);
			model.addAttribute("user", sessionUser);
			model.addAttribute("orders", orders);
			model.addAttribute("url", "Trang Chủ / Thông Tin Người Dùng");
			model.addAttribute("categories", categoryService.findAll());
			model.addAttribute("manufacturers", manufacturerService.findAll());
			model.addAttribute("body", IndexController.getBody("body_user_detail"));
			model.addAttribute("path", request.getContextPath());
			return "index";
		} catch (Exception e) {
			System.out.println(e);
			return "redirect:/error-404";
		}
	}

	@RequestMapping(value = "user-change-status-order", method = RequestMethod.GET)
	public String changeStatusOrder(@RequestParam("orderId") int orderId, HttpServletRequest request, Model model) {
		// Xử lý header user login
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

			Tbl_Order order = orderService.findById(orderId);
			if (order.getStatus() == 0) {
				order.setStatus(3);
				orderService.create(order);
				List<Tbl_Order_Detail> orderDetails = orderDetailService.findByOrder(order);
				for (Tbl_Order_Detail od : orderDetails) {
					Tbl_Product product = od.getTblProduct();
					product.setAmount(product.getAmount() + od.getAmount());
					productService.edit(product);
				}
			}
			// Set model
			List<Tbl_Order> orders = orderService.findByUser(sessionUser);
			model.addAttribute("user", sessionUser);
			model.addAttribute("orders", orders);
			model.addAttribute("url", "Trang Chủ / Thông Tin Người Dùng");
			model.addAttribute("categories", categoryService.findAll());
			model.addAttribute("manufacturers", manufacturerService.findAll());
			model.addAttribute("body", IndexController.getBody("body_user_detail"));
			model.addAttribute("path", request.getContextPath());
			return "index";
		} catch (Exception e) {
			System.out.println(e);
			return "redirect:/error-404";
		}
	}

	@RequestMapping(value = "user-order-detail", method = RequestMethod.GET)
	public String getUserOrderDetail(@RequestParam("orderId") int orderId, HttpServletRequest request, Model model) {
		// Xử lý header user login
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

			Tbl_Order order = orderService.findById(orderId);

			// Set model
			List<Tbl_Order_Detail> orderDetails = orderDetailService.findByOrder(order);
			model.addAttribute("user", sessionUser);
			model.addAttribute("orders", orderDetails);
			model.addAttribute("url", "Trang Chủ / Thông Tin Người Dùng / Chi Tiết Đơn Hàng");
			model.addAttribute("categories", categoryService.findAll());
			model.addAttribute("manufacturers", manufacturerService.findAll());
			model.addAttribute("body", IndexController.getBody("body_user_order_detail"));
			model.addAttribute("path", request.getContextPath());
			return "index";
		} catch (Exception e) {
			System.out.println(e);
			return "redirect:/error-404";
		}
	}

	@RequestMapping(value = "user-update-info", method = RequestMethod.GET)
	public String getEditUserClient(HttpServletRequest request, Model model) {
		// Xử lý header user login
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

			// Set model
			model.addAttribute("user", sessionUser);
			model.addAttribute("url", "Trang Chủ / Thông Tin Người Dùng / Cập Nhật / " + sessionUser.getUsername());
			model.addAttribute("categories", categoryService.findAll());
			model.addAttribute("manufacturers", manufacturerService.findAll());
			model.addAttribute("body", IndexController.getBody("body_user_edit"));
			model.addAttribute("path", request.getContextPath());
			return "index";
		} catch (Exception e) {
			System.out.println(e);
			return "redirect:/error-404";
		}
	}

	@RequestMapping(value = "save-edit-user-client", method = RequestMethod.POST)
	public String postUpdateUserClient(@ModelAttribute("user") Tbl_User user, HttpServletRequest request, Model model) {
		try {
			userService.edit(user);
			Tbl_User userUpdated = userService.findById(user.getId()).get();
			request.getSession().setAttribute("userSession", userUpdated);
			return "redirect:/users-detail";
		} catch (Exception e) {
			System.out.println(e);
			return "redirect:/error-404";
		}
	}

	@RequestMapping(value = "user-change-password", method = RequestMethod.GET)
	public String getChangePasswordClient(HttpServletRequest request, Model model) {
		// Xử lý header user login
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

			// Set model
			model.addAttribute("mess", null);
			model.addAttribute("url", "Trang Chủ / Thông Tin Người Dùng / Đổi Mật Khẩu / " + sessionUser.getUsername());
			model.addAttribute("categories", categoryService.findAll());
			model.addAttribute("manufacturers", manufacturerService.findAll());
			model.addAttribute("body", IndexController.getBody("body_change_password"));
			model.addAttribute("path", request.getContextPath());
			return "index";
		} catch (Exception e) {
			System.out.println(e);
			return "redirect:/error-404";
		}
	}

	@RequestMapping(value = "save-change-password-client", method = RequestMethod.POST)
	public String postChangePasswordClient(HttpServletRequest request, Model model) {
		// Xử lý header user login
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

			String oldPassString = request.getParameter("passOld");
			String newPassString = request.getParameter("passNew");

			if (EncodeUntil.stringEqualsStringEncode(oldPassString, sessionUser.getPassword())) {
				sessionUser.setPassword(EncodeUntil.encoderString(newPassString));
				userService.edit(sessionUser);
				Tbl_User userUpdated = userService.findById(sessionUser.getId()).get();
				request.getSession().setAttribute("userSession", userUpdated);
				return "redirect:/users-detail";
			} else {
				model.addAttribute("mess", "Mật khẩu cũ không chính xác");
				model.addAttribute("url",
						"Trang Chủ / Thông Tin Người Dùng / Đổi Mật Khẩu / " + sessionUser.getUsername());
				model.addAttribute("categories", categoryService.findAll());
				model.addAttribute("manufacturers", manufacturerService.findAll());
				model.addAttribute("body", IndexController.getBody("body_change_password"));
				model.addAttribute("path", request.getContextPath());
				return "index";
			}
		} catch (Exception e) {
			System.out.println(e);
			return "redirect:/error-404";
		}
	}

}
