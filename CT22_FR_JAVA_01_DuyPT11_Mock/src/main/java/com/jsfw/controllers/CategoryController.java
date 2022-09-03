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

import com.jsfw.models.Tbl_Category;
import com.jsfw.models.Tbl_User;
import com.jsfw.services.CategoryService;
import com.jsfw.utils.PageUtils;

@Controller
@RequestMapping(value = "/categories/")
public class CategoryController {

	@Autowired
	CategoryService service;

	@RequestMapping(value = { "", "index", "list" }, method = RequestMethod.GET)
	public String getCategory(@RequestParam("page") int page, Model model, HttpServletRequest request) {
		try {
			model.addAttribute("body", IndexController.getBody("body_category"));
			model.addAttribute("path", request.getContextPath());
			model.addAttribute("currentPage", "Quản lý loại sản phẩm");
			model.addAttribute("url", "Trang chủ / Loại sản phẩm");
			List<Tbl_Category> categories = service.findAll();
			int numObjInPage = 10;
			int totalPages = PageUtils.getTotalPage(categories.size(), numObjInPage);
			String pathString = request.getContextPath() + "/categories?";
			String pageString = PageUtils.getPages(page, totalPages, pathString);
			model.addAttribute("page", pageString);
			model.addAttribute("list", PageUtils.getListCategoryInPage(page, numObjInPage, categories));
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
	public String getCategoryAdd(Model model, HttpServletRequest request) {
		try {
			model.addAttribute("body", IndexController.getBody("body_category_add"));
			model.addAttribute("path", request.getContextPath());
			model.addAttribute("currentPage", "Thêm mới loại sản phẩm");
			model.addAttribute("url", "Trang chủ / Loại sản phẩm / Thêm mới");
			model.addAttribute("categoryAdd", new Tbl_Category());
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
	public String postCategoryAdd(@ModelAttribute("categoryAdd") Tbl_Category category, Model model,
			HttpServletRequest request) {
		try {
			model.addAttribute("mess", null);
			if (service.findByName(category.getName()) == null) {
				service.create(category);
				return "redirect:/categories?page=1";
			} else {
				model.addAttribute("body", IndexController.getBody("body_category_add"));
				model.addAttribute("path", request.getContextPath());
				model.addAttribute("currentPage", "Thêm mới loại sản phẩm");
				model.addAttribute("url", "Trang chủ / Loại sản phẩm / Thêm mới");
				model.addAttribute("categoryAdd", new Tbl_Category());
				model.addAttribute("mess", "Loại sản phẩm này đã tồn tại");
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
	public String getCategoryEdit(@RequestParam("id") int id, Model model, HttpServletRequest request) {

		try {
			model.addAttribute("body", IndexController.getBody("body_category_update"));
			model.addAttribute("path", request.getContextPath());
			model.addAttribute("currentPage", "Cập nhật loại sản phẩm");
			model.addAttribute("url", "Trang chủ / Loại sản phẩm / Cập nhật");
			model.addAttribute("categoryEdit", service.findById(id));
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
	public String postCategoryEdit(@ModelAttribute("categoryEdit") Tbl_Category category, Model model,
			HttpServletRequest request) {
		try {
			model.addAttribute("mess", null);
			if (service.findByName(category.getName()) == null) {
				service.edit(category);
				return "redirect:/categories?page=1";
			} else {
				if (service.findByName(category.getName()).getId() == category.getId()) {
					service.edit(category);
					return "redirect:/categories?page=1";
				} else {
					model.addAttribute("body", IndexController.getBody("body_category_update"));
					model.addAttribute("path", request.getContextPath());
					model.addAttribute("currentPage", "Cập nhật loại sản phẩm");
					model.addAttribute("url", "Trang chủ / Loại sản phẩm / Cập nhật");
					model.addAttribute("categoryEdit", service.findById(category.getId()));
					model.addAttribute("mess", "Loại sản phẩm này đã tồn tại");
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
	public String getCategoryDelete(@RequestParam("id") int id) {
		try {
			service.remove(id);
			return "redirect:/categories?page=1";
		} catch (Exception e) {
			System.out.println(e);
			return "redirect:/error-404";
		}
	}
}
