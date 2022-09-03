package com.jsfw.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.jsfw.models.Tbl_User;
import com.jsfw.services.CategoryService;
import com.jsfw.services.ManufacturerService;
import com.jsfw.services.OrderService;
import com.jsfw.services.ProductService;
import com.jsfw.utils.StatisticUtils;

@Controller
@RequestMapping(value = "/statistics/")
public class StatisticsController {

	@Autowired
	OrderService orderService;
	@Autowired
	ProductService productService;
	@Autowired
	CategoryService categoryService;
	@Autowired
	ManufacturerService manufacturerService;

	@RequestMapping(value = { "", "index", "list" }, method = RequestMethod.GET)
	public String getIndex(Model model, HttpServletRequest request) {
		try {
			model.addAttribute("body", IndexController.getBody("body_statistics"));
			model.addAttribute("path", request.getContextPath());
			model.addAttribute("currentPage", "Quản lý thống kê");
			model.addAttribute("url", "Trang chủ / Thống kê");
			model.addAttribute("list", null);
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

	@RequestMapping(value = "total", method = RequestMethod.GET)
	public String getTotal(@RequestParam("key") String key, @RequestParam("value1") int value1,
			@RequestParam("value2") int value2, Model model, HttpServletRequest request) {
		try {
			model.addAttribute("body", IndexController.getBody("body_statistics"));
			model.addAttribute("path", request.getContextPath());
			model.addAttribute("currentPage", "Quản lý thống kê");
			model.addAttribute("url", "Trang chủ / Thống kê / Theo tổng doanh thu");
			model.addAttribute("products", productService.findAll());
			model.addAttribute("categories", categoryService.findAll());
			model.addAttribute("manufacturers", manufacturerService.findAll());
			switch (key) {
			case "year":
				if (value1 > 0) {
					model.addAttribute("key", "month");
					model.addAttribute("value2", value1);
				} else {
					model.addAttribute("key", key);
					model.addAttribute("value2", -1);
				}
				break;
			case "quarter":
				if (value1 > 0) {
					model.addAttribute("key", "month");
					model.addAttribute("value2", -1);
				} else {
					model.addAttribute("key", key);
					model.addAttribute("value2", value1);
				}

				break;
			case "month":
				if (value1 > 0)
					model.addAttribute("key", -1);
				else
					model.addAttribute("key", key);
				model.addAttribute("value2", value1);
				break;
			default:
				break;
			}
			model.addAttribute("list",
					StatisticUtils.getStatisticsTotal(model, request, orderService, key, value1, value2));

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

	@RequestMapping(value = "products", method = RequestMethod.GET)
	public String getProduct(@RequestParam("id") int id, @RequestParam("key") String key,
			@RequestParam("value1") int value1, @RequestParam("value2") int value2, Model model,
			HttpServletRequest request) {
		try {
			model.addAttribute("body", IndexController.getBody("body_statistics_product"));
			model.addAttribute("path", request.getContextPath());
			model.addAttribute("currentPage",
					"Quản lý thống kê / Sản phẩm " + productService.findById(id).get().getName());
			model.addAttribute("url", "Trang chủ / Thống kê / Theo sản phẩm");
			model.addAttribute("productId", id);
			model.addAttribute("products", productService.findAll());
			model.addAttribute("categories", categoryService.findAll());
			model.addAttribute("manufacturers", manufacturerService.findAll());
			switch (key) {
			case "year":
				if (value1 > 0) {
					model.addAttribute("key", "month");
					model.addAttribute("value2", value1);
				} else {
					model.addAttribute("key", key);
					model.addAttribute("value2", -1);
				}
				break;
			case "quarter":
				if (value1 > 0) {
					model.addAttribute("key", "month");
					model.addAttribute("value2", -1);
				} else {
					model.addAttribute("key", key);
					model.addAttribute("value2", value1);
				}

				break;
			case "month":
				if (value1 > 0)
					model.addAttribute("key", -1);
				else
					model.addAttribute("key", key);
				model.addAttribute("value2", value1);
				break;
			default:
				break;
			}
			model.addAttribute("list", StatisticUtils.getStatisticsProduct(model, request, orderService, key, value1,
					productService.findById(id).get(), value2));
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

	@RequestMapping(value = "categories", method = RequestMethod.GET)
	public String getCategory(@RequestParam("id") int id, @RequestParam("key") String key,
			@RequestParam("value1") int value1, @RequestParam("value2") int value2, Model model,
			HttpServletRequest request) {
		try {
			model.addAttribute("body", IndexController.getBody("body_statistics_category"));
			model.addAttribute("path", request.getContextPath());
			model.addAttribute("currentPage",
					"Quản lý thống kê / Loại sản phẩm " + categoryService.findById(id).get().getName());
			model.addAttribute("url", "Trang chủ / Thống kê / Theo loại sản phẩm");
			model.addAttribute("categoryId", id);
			model.addAttribute("products", productService.findAll());
			model.addAttribute("categories", categoryService.findAll());
			model.addAttribute("manufacturers", manufacturerService.findAll());
			switch (key) {
			case "year":
				if (value1 > 0) {
					model.addAttribute("key", "month");
					model.addAttribute("value2", value1);
				} else {
					model.addAttribute("key", key);
					model.addAttribute("value2", -1);
				}
				break;
			case "quarter":
				if (value1 > 0) {
					model.addAttribute("key", "month");
					model.addAttribute("value2", -1);
				} else {
					model.addAttribute("key", key);
					model.addAttribute("value2", value1);
				}

				break;
			case "month":
				if (value1 > 0)
					model.addAttribute("key", -1);
				else
					model.addAttribute("key", key);
				model.addAttribute("value2", value1);
				break;
			default:
				break;
			}
			model.addAttribute("list", StatisticUtils.getStatisticsCategory(model, request, orderService, key, value1,
					categoryService.findById(id).get(), value2));
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

	@RequestMapping(value = "manufacturers", method = RequestMethod.GET)
	public String getManufacturers(@RequestParam("id") int id, @RequestParam("key") String key,
			@RequestParam("value1") int value1, @RequestParam("value2") int value2, Model model,
			HttpServletRequest request) {
		try {
			model.addAttribute("body", IndexController.getBody("body_statistics_manufacturer"));
			model.addAttribute("path", request.getContextPath());
			model.addAttribute("currentPage",
					"Quản lý thống kê / Nhà cung cấp " + manufacturerService.findById(id).get().getName());
			model.addAttribute("url", "Trang chủ / Thống kê / Theo nhà cung cấp");
			model.addAttribute("manufacturerId", id);
			model.addAttribute("products", productService.findAll());
			model.addAttribute("categories", categoryService.findAll());
			model.addAttribute("manufacturers", manufacturerService.findAll());
			switch (key) {
			case "year":
				if (value1 > 0) {
					model.addAttribute("key", "month");
					model.addAttribute("value2", value1);
				} else {
					model.addAttribute("key", key);
					model.addAttribute("value2", -1);
				}
				break;
			case "quarter":
				if (value1 > 0) {
					model.addAttribute("key", "month");
					model.addAttribute("value2", -1);
				} else {
					model.addAttribute("key", key);
					model.addAttribute("value2", value1);
				}

				break;
			case "month":
				if (value1 > 0)
					model.addAttribute("key", -1);
				else
					model.addAttribute("key", key);
				model.addAttribute("value2", value1);
				break;
			default:
				break;
			}
			model.addAttribute("list", StatisticUtils.getStatisticsManufacturer(model, request, orderService, key,
					value1, manufacturerService.findById(id).get(), value2));
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
