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
import com.jsfw.models.Tbl_Manufacturer;
import com.jsfw.models.Tbl_User;
import com.jsfw.services.ManufacturerService;
import com.jsfw.utils.PageUtils;

@Controller
@RequestMapping(value = "/manufacturers/")
public class ManufacturerController {

	@Autowired
	ManufacturerService service;

	@RequestMapping(value = { "", "index", "list" }, method = RequestMethod.GET)
	public String getManufacturer(@RequestParam("page") int page, Model model, HttpServletRequest request) {
		try {
			model.addAttribute("body", IndexController.getBody("body_manufacturer"));
			model.addAttribute("path", request.getContextPath());
			model.addAttribute("currentPage", "Quản lý nhà cung cấp");
			model.addAttribute("url", "Trang chủ / Nhà cung cấp");
			List<Tbl_Manufacturer> manufacturers = service.findAll();
			int numObjInPage = 10;
			int totalPages = PageUtils.getTotalPage(manufacturers.size(), numObjInPage);
			String pathString = request.getContextPath() + "/manufacturers?";
			String pageString = PageUtils.getPages(page, totalPages, pathString);
			model.addAttribute("page", pageString);
			model.addAttribute("list", PageUtils.getListManufacturerInPage(page, numObjInPage, manufacturers));
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
	public String getManufacturerAdd(Model model, HttpServletRequest request) {
		try {
			model.addAttribute("body", IndexController.getBody("body_manufacturer_add"));
			model.addAttribute("path", request.getContextPath());
			model.addAttribute("currentPage", "Thêm mới nhà cung cấp");
			model.addAttribute("url", "Trang chủ / Nhà cung cấp / Thêm mới");
			model.addAttribute("manufacturerAdd", new Tbl_Category());
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
	public String postManufacturerAdd(@ModelAttribute("manufacturerAdd") Tbl_Manufacturer manufacturer, Model model,
			HttpServletRequest request) {
		try {
			model.addAttribute("mess", null);
			if (service.findByName(manufacturer.getName()) == null) {
				service.create(manufacturer);
				return "redirect:/manufacturers?page=1";
			} else {
				model.addAttribute("body", IndexController.getBody("body_manufacturer_add"));
				model.addAttribute("path", request.getContextPath());
				model.addAttribute("currentPage", "Thêm mới nhà cung cấp");
				model.addAttribute("url", "Trang chủ / Nhà cung cấp / Thêm mới");
				model.addAttribute("categoryAdd", new Tbl_Category());
				model.addAttribute("mess", "Nhà cung cấp này đã tồn tại");
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
	public String getManufacturerEdit(@RequestParam("id") int id, Model model, HttpServletRequest request) {
		try {
			model.addAttribute("body", IndexController.getBody("body_manufacturer_update"));
			model.addAttribute("path", request.getContextPath());
			model.addAttribute("currentPage", "Cập nhật nhà cung cấp");
			model.addAttribute("url", "Trang chủ / Nhà cung cấp / Cập nhật");
			model.addAttribute("manufacturerEdit", service.findById(id));
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
	public String postManufacturerEdit(@ModelAttribute("manufacturerEdit") Tbl_Manufacturer manufacturer, Model model,
			HttpServletRequest request) {
		try {
			model.addAttribute("mess", null);
			if (service.findByName(manufacturer.getName()) == null) {
				service.edit(manufacturer);
				return "redirect:/manufacturers?page=1";
			} else {
				if (service.findByName(manufacturer.getName()).getId() == manufacturer.getId()) {
					service.edit(manufacturer);
					return "redirect:/manufacturers?page=1";
				} else {
					model.addAttribute("body", IndexController.getBody("body_manufacturer_update"));
					model.addAttribute("path", request.getContextPath());
					model.addAttribute("currentPage", "Cập nhật nhà cung cấp");
					model.addAttribute("url", "Trang chủ / Nhà cung cấp / Cập nhật");
					model.addAttribute("manufacturerEdit", service.findById(manufacturer.getId()));
					model.addAttribute("mess", "Nhà cung cấp này đã tồn tại");
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
	public String getManufacturerDelete(@RequestParam("id") int id) {
		try {
			service.remove(id);
			return "redirect:/manufacturers?page=1";
		} catch (Exception e) {
			System.out.println(e);
			return "redirect:/error-404";
		}
	}
}
