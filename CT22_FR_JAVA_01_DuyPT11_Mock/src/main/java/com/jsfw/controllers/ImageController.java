package com.jsfw.controllers;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.jsfw.models.Tbl_Image_Product;
import com.jsfw.models.Tbl_User;
import com.jsfw.services.ImageService;
import com.jsfw.services.ProductService;
import com.jsfw.utils.UploadImage;

@Controller
@RequestMapping(value = "/images-product/")
public class ImageController {
	@Autowired
	ImageService service;
	@Autowired
	ProductService productService;

	@RequestMapping(value = { "", "index", "list" }, method = RequestMethod.GET)
	public String getImage(@RequestParam("id") int id, Model model, HttpServletRequest request) {
		try {
			model.addAttribute("body", IndexController.getBody("body_image"));
			model.addAttribute("path", request.getContextPath());
			model.addAttribute("currentPage", "Quản lý hình ảnh sản phẩm");
			model.addAttribute("url", "Trang chủ / Hình ảnh sản phẩm");
			model.addAttribute("productId", id);
			model.addAttribute("productName", productService.findById(id).get().getName());
			model.addAttribute("list", service.findByProduct(productService.findById(id)));
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
	public String saveImage(@RequestParam("txtSource") MultipartFile[] files, @RequestParam("productId") String id,
			HttpServletRequest request) {
		try {
			UploadImage.uploadImageUtil(files, id);
			for (MultipartFile img : files) {
				Tbl_Image_Product image_Product = new Tbl_Image_Product();
				image_Product.setSource(id + "_" + img.getOriginalFilename());
				image_Product.setTblProduct(productService.findById(Integer.valueOf(id)).get());
				service.create(image_Product);
			}
			return "redirect:/images-product?id=" + id;
		} catch (Exception e) {
			System.out.println(e);
			return "redirect:/error-404";
		}
	}

	@RequestMapping(value = "delete", method = RequestMethod.GET)
	public String deleteImage(@RequestParam("id") int id, @RequestParam("productId") String productId) {
		try {
			Tbl_Image_Product img = service.findById(id).get();
			service.remove(id);
			File file = new File(UploadImage.CURRENT_FOLDER.toString() + "/" + img.getSource());
			file.delete();
			return "redirect:/images-product?id=" + productId;
		} catch (Exception e) {
			System.out.println(e);
			return "redirect:/error-404";
		}
	}
}
