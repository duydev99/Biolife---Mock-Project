package com.jsfw.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.jsfw.models.Tbl_Product;
import com.jsfw.models.Tbl_User;
import com.jsfw.services.CategoryService;
import com.jsfw.services.ManufacturerService;
import com.jsfw.services.ProductService;
import com.jsfw.utils.ShopUtils;

@Controller
public class ShopController {

	@Autowired
	ProductService productService;
	@Autowired
	CategoryService categoryService;
	@Autowired
	ManufacturerService manufacturerService;

	// Gid 3
	@RequestMapping(value = { "shop", "shop-gid-3" }, method = RequestMethod.GET)
	public String getShop(@RequestParam("page") int page, @RequestParam("filter") String filter,
			@RequestParam("orderby") String orderby, @RequestParam("key") int key, Model model,
			HttpServletRequest request) {
		try {// Xử lý để có danh sách sản phẩm cuối cùng
			List<Tbl_Product> products = productService.findAll();
			products = ShopUtils.getProductsFilter(filter, products, key);
			products = ShopUtils.getProductsOrderBy(orderby, products);

			// Xử lý user login header
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
			// Xử lý trang
			int productNumberInPage = 6;
			int totalProducts = products.size();
			String strPathPage = request.getContextPath() + "/shop?filter=" + filter + "&key=" + key + "&orderby="
					+ orderby + "&";
			int totalPages = ShopUtils.getTotalPage(totalProducts, productNumberInPage);
			String pages = ShopUtils.getPages(page, totalPages, strPathPage);

			// Set model
			String pathOrderbyString = request.getContextPath() + "/shop?filter=" + filter + "&key=" + key + "&page=1";
			String pathFilterString = request.getContextPath() + "/shop?orderby=" + orderby + "&page=1";
			String pathAllString = request.getContextPath() + "/shop?filter=all&key=-1&orderby=default&page=1";
			model.addAttribute("pages", pages);
			model.addAttribute("categories", categoryService.findAll());
			model.addAttribute("manufacturers", manufacturerService.findAll());
			model.addAttribute("list", ShopUtils.getListProductInPage(page, productNumberInPage, products));
			model.addAttribute("url", "Trang Chủ / Cửa Hàng");
			model.addAttribute("body", IndexController.getBody("body_shop_gid_3"));
			model.addAttribute("pathCurrentOrderBy", pathOrderbyString);
			model.addAttribute("pathCurrentFilter", pathFilterString);
			model.addAttribute("pathCurrentAll", pathAllString);
			model.addAttribute("path", request.getContextPath());
			return "index";
		} catch (Exception e) {
			System.out.println(e);
			return "redirect:/error-404";
		}
	}

	// Lọc sản phẩm theo loại sản phẩm Gid 3
	@RequestMapping(value = "shop-category", method = RequestMethod.GET)
	public String getCategory(@RequestParam("key1") int key1, @RequestParam("key2") int key2,
			@RequestParam("page") int page, @RequestParam("filter") String filter,
			@RequestParam("orderby") String orderby, Model model, HttpServletRequest request) {
		// Xử lý để có danh sách sản phẩm cuối cùng
		try {
			List<Tbl_Product> products = productService.findByCategory(categoryService.findById(key1).get());
			products = ShopUtils.getProductsFilter(filter, products, key2);
			products = ShopUtils.getProductsOrderBy(orderby, products);
			// Xử lý user login header
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
			// Xử lý trang
			int productNumberInPage = 6;
			int totalProducts = products.size();
			String strPathPage = request.getContextPath() + "/shop-category?filter=" + filter + "&key1=" + key1
					+ "&key2=" + key2 + "&orderby=" + orderby + "&";
			int totalPages = ShopUtils.getTotalPage(totalProducts, productNumberInPage);
			String pages = ShopUtils.getPages(page, totalPages, strPathPage);
			// Set model
			String pathOrderbyString = request.getContextPath() + "/shop-category?filter=" + filter + "&key1=" + key1
					+ "&key2=" + key2 + "&page=1";
			String pathFilterString = request.getContextPath() + "/shop-category?key1=" + key1 + "&orderby=" + orderby
					+ "&page=1";
			String pathAllString = request.getContextPath() + "/shop-category?filter=all&key1=" + key1
					+ "&key2=-1&orderby=default&page=1";
			model.addAttribute("pages", pages);
			model.addAttribute("categories", categoryService.findAll());
			model.addAttribute("manufacturers", manufacturerService.findAll());
			model.addAttribute("list", ShopUtils.getListProductInPage(page, productNumberInPage, products));
			model.addAttribute("url",
					"Trang Chủ / Cửa Hàng / Loại Sản Phẩm / " + categoryService.findById(key1).get().getName());
			model.addAttribute("body", IndexController.getBody("body_shop_gid_3"));
			model.addAttribute("pathCurrentOrderBy", pathOrderbyString);
			model.addAttribute("pathCurrentFilter", pathFilterString);
			model.addAttribute("pathCurrentAll", pathAllString);
			model.addAttribute("path", request.getContextPath());
			return "index";
		} catch (Exception e) {
			System.out.println(e);
			return "redirect:/error-404";
		}
	}

	// Lọc sản phẩm theo nhà cung cấp Gid 3
	@RequestMapping(value = "shop-manufacturer", method = RequestMethod.GET)
	public String getManuturer(@RequestParam("key1") int key1, @RequestParam("key2") int key2,
			@RequestParam("page") int page, @RequestParam("filter") String filter,
			@RequestParam("orderby") String orderby, Model model, HttpServletRequest request) {
		// Xử lý để có danh sách sản phẩm cuối cùng
		try {
			List<Tbl_Product> products = productService.findByManufacturer(manufacturerService.findById(key1).get());
			products = ShopUtils.getProductsFilter(filter, products, key2);
			products = ShopUtils.getProductsOrderBy(orderby, products);
			// Xử lý user login header
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
			// Xử lý trang
			int productNumberInPage = 6;
			int totalProducts = products.size();
			String strPathPage = request.getContextPath() + "/shop-manufacturer?filter=" + filter + "&key1=" + key1
					+ "&key2=" + key2 + "&orderby=" + orderby + "&";
			int totalPages = ShopUtils.getTotalPage(totalProducts, productNumberInPage);
			String pages = ShopUtils.getPages(page, totalPages, strPathPage);
			// Set model
			String pathOrderbyString = request.getContextPath() + "/shop-manufacturer?filter=" + filter + "&key1="
					+ key1 + "&key2=" + key2 + "&page=1";
			String pathFilterString = request.getContextPath() + "/shop-manufacturer?key1=" + key1 + "&orderby="
					+ orderby + "&page=1";
			String pathAllString = request.getContextPath() + "/shop-manufacturer?filter=all&key1=" + key1
					+ "&key2=-1&orderby=default&page=1";
			model.addAttribute("pages", pages);
			model.addAttribute("categories", categoryService.findAll());
			model.addAttribute("manufacturers", manufacturerService.findAll());
			model.addAttribute("list", ShopUtils.getListProductInPage(page, productNumberInPage, products));
			model.addAttribute("url",
					"Trang Chủ / Cửa Hàng / Nhà Cung Cấp / " + manufacturerService.findById(key1).get().getName());
			model.addAttribute("body", IndexController.getBody("body_shop_gid_3"));
			model.addAttribute("pathCurrentOrderBy", pathOrderbyString);
			model.addAttribute("pathCurrentFilter", pathFilterString);
			model.addAttribute("pathCurrentAll", pathAllString);
			model.addAttribute("path", request.getContextPath());
			return "index";
		} catch (Exception e) {
			System.out.println(e);
			return "redirect:/error-404";
		}
	}

	// Tìm kiếm sản phẩm Gid 3
	@RequestMapping(value = "shop-search", method = RequestMethod.GET)
	public String getSearch(@RequestParam("text") String text, @RequestParam("key1") String key1,
			@RequestParam("key2") int key2, @RequestParam("page") int page, @RequestParam("filter") String filter,
			@RequestParam("orderby") String orderby, Model model, HttpServletRequest request) {
		// Xử lý để có danh sách sản phẩm cuối cùng
		try {
			List<Tbl_Product> products = new ArrayList<Tbl_Product>();
			if (key1.equals("product"))
				products = productService.findByProductName(text);
			if (key1.equals("category"))
				products = productService.findByCategoryName(text);
			if (key1.equals("manufacturer"))
				products = productService.findByManufacturerName(text);
			products = ShopUtils.getProductsFilter(filter, products, key2);
			products = ShopUtils.getProductsOrderBy(orderby, products);
			// Xử lý user login header
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
			// Xử lý trang
			int productNumberInPage = 6;
			int totalProducts = products.size();
			String strPathPage = request.getContextPath() + "/shop-search?text=" + text + "&filter=" + filter + "&key1="
					+ key1 + "&key2=" + key2 + "&orderby=" + orderby + "&";
			int totalPages = ShopUtils.getTotalPage(totalProducts, productNumberInPage);
			String pages = ShopUtils.getPages(page, totalPages, strPathPage);
			// Set model
			String pathOrderbyString = request.getContextPath() + "/shop-search?text=" + text + "&filter=" + filter
					+ "&key1=" + key1 + "&key2=" + key2 + "&page=1";
			String pathFilterString = request.getContextPath() + "/shop-search?text=" + text + "&key1=" + key1
					+ "&orderby=" + orderby + "&page=1";
			String pathAllString = request.getContextPath() + "/shop-search?text=" + text + "&filter=all&key1=" + key1
					+ "&key2=-1&orderby=default&page=1";
			model.addAttribute("pages", pages);
			model.addAttribute("categories", categoryService.findAll());
			model.addAttribute("manufacturers", manufacturerService.findAll());
			model.addAttribute("list", ShopUtils.getListProductInPage(page, productNumberInPage, products));
			model.addAttribute("url", "Trang Chủ / Cửa Hàng / Kết Quả Tìm Kiếm");
			model.addAttribute("body", IndexController.getBody("body_shop_gid_3"));
			model.addAttribute("pathCurrentOrderBy", pathOrderbyString);
			model.addAttribute("pathCurrentFilter", pathFilterString);
			model.addAttribute("pathCurrentAll", pathAllString);
			model.addAttribute("path", request.getContextPath());
			return "index";
		} catch (Exception e) {
			System.out.println(e);
			return "redirect:/error-404";
		}
	}
}
