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

import com.jsfw.models.Tbl_Product;
import com.jsfw.models.Tbl_User;
import com.jsfw.services.CategoryService;
import com.jsfw.services.ImageService;
import com.jsfw.services.ManufacturerService;
import com.jsfw.services.ProductService;
import com.jsfw.utils.PageUtils;

@Controller
@RequestMapping(value = "/products/")
public class ProductController {

	@Autowired
	ProductService service;
	@Autowired
	CategoryService categoryService;
	@Autowired
	ManufacturerService manufacturerService;
	@Autowired
	ImageService imageService;

	@RequestMapping(value = { "", "index", "list" }, method = RequestMethod.GET)
	public String getProduct(@RequestParam("page") int page, Model model, HttpServletRequest request) {
		try {
			model.addAttribute("body", IndexController.getBody("body_product"));
			model.addAttribute("path", request.getContextPath());
			model.addAttribute("currentPage", "Quản lý sản phẩm");
			model.addAttribute("url", "Trang chủ / Sản phẩm");
			List<Tbl_Product> products = service.findAll();
			int numObjInPage = 10;
			int totalPages = PageUtils.getTotalPage(products.size(), numObjInPage);
			String pathString = request.getContextPath() + "/products?";
			String pageString = PageUtils.getPages(page, totalPages, pathString);
			model.addAttribute("page", pageString);
			model.addAttribute("list", PageUtils.getListProductsInPage(page, numObjInPage, products));
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

	@RequestMapping(value = "detail", method = RequestMethod.GET)
	public String getProductDetail(@RequestParam("id") int id, Model model, HttpServletRequest request) {
		try {
			model.addAttribute("body", IndexController.getBody("body_product_detail"));
			model.addAttribute("path", request.getContextPath());
			model.addAttribute("currentPage", "Thông tin sản phẩm");
			model.addAttribute("url", "Trang chủ / Sản phẩm / Chi tiết sản phẩm");
			model.addAttribute("product", service.findById(id).get());
			model.addAttribute("images", imageService.findByProduct(service.findById(id)));
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
	public String getProductAdd(Model model, HttpServletRequest request) {
		try {
			model.addAttribute("body", IndexController.getBody("body_product_add"));
			model.addAttribute("path", request.getContextPath());
			model.addAttribute("currentPage", "Thêm mới sản phẩm");
			model.addAttribute("url", "Trang chủ / Sản phẩm / Thêm mới");
			model.addAttribute("categories", categoryService.findAll());
			model.addAttribute("manufacturers", manufacturerService.findAll());
			model.addAttribute("productAdd", new Tbl_Product());
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
	public String postProductAdd(@ModelAttribute("productAdd") Tbl_Product product, Model model,
			HttpServletRequest request) {
		try {
			model.addAttribute("mess", null);
			if (service.findByName(product.getName()) == null) {
				service.create(product);
				return "redirect:/products?page=1";
			} else {
				model.addAttribute("body", IndexController.getBody("body_product_add"));
				model.addAttribute("path", request.getContextPath());
				model.addAttribute("currentPage", "Thêm mới sản phẩm");
				model.addAttribute("url", "Trang chủ / Sản phẩm / Thêm mới");
				model.addAttribute("manufacturers", manufacturerService.findAll());
				model.addAttribute("categories", categoryService.findAll());
				model.addAttribute("productAdd", new Tbl_Product());
				model.addAttribute("mess", "Sản phẩm này đã tồn tại");
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
	public String getProductEdit(@RequestParam("id") int id, Model model, HttpServletRequest request) {
		try {
			model.addAttribute("body", IndexController.getBody("body_product_update"));
			model.addAttribute("path", request.getContextPath());
			model.addAttribute("currentPage", "Cập nhật sản phẩm");
			model.addAttribute("url", "Trang chủ / Sản phẩm / Cập nhật");
			model.addAttribute("productEdit", service.findById(id));
			model.addAttribute("manufacturers", manufacturerService.findAll());
			model.addAttribute("categories", categoryService.findAll());
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
	public String postProductEdit(@ModelAttribute("productEdit") Tbl_Product product, Model model,
			HttpServletRequest request) {
		try {
			model.addAttribute("mess", null);
			if (service.findByName(product.getName()) == null) {
				service.edit(product);
				return "redirect:/products?page=1";
			} else {
				if (service.findByName(product.getName()).getId() == product.getId()) {
					service.edit(product);
					return "redirect:/products?page=1";
				} else {
					model.addAttribute("body", IndexController.getBody("body_product_update"));
					model.addAttribute("path", request.getContextPath());
					model.addAttribute("currentPage", "Cập nhật sản phẩm");
					model.addAttribute("url", "Trang chủ / Sản phẩm / Cập nhật");
					model.addAttribute("categoryEdit", service.findById(product.getId()));
					model.addAttribute("manufacturers", manufacturerService.findAll());
					model.addAttribute("categories", categoryService.findAll());
					model.addAttribute("mess", "Sản phẩm này đã tồn tại");
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
	public String getProductDelete(@RequestParam("id") int id) {
		try {
			service.remove(id);
			return "redirect:/products?page=1";
		} catch (Exception e) {
			System.out.println(e);
			return "redirect:/error-404";
		}
	}
}
