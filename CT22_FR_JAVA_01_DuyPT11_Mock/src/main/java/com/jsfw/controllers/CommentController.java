package com.jsfw.controllers;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jsfw.models.Rate;
import com.jsfw.models.Tbl_Comment;
import com.jsfw.models.Tbl_User;
import com.jsfw.models.Tbl_Vote;
import com.jsfw.services.CommentService;
import com.jsfw.services.ProductService;
import com.jsfw.services.VoteService;
import com.jsfw.utils.PageUtils;

@Controller
@RequestMapping(value = "/comments/")
public class CommentController {

	@Autowired
	CommentService commentService;
	@Autowired
	ProductService productService;
	@Autowired
	VoteService voteService;

	@RequestMapping(value = { "delete" }, method = RequestMethod.GET)
	public String getDelete(@RequestParam("id") int id) {
		try {
			commentService.remove(commentService.findById(id));
			return "redirect:/comments/index?page=1";
		} catch (Exception e) {
			System.out.println(e);
			return "redirect:/error-404";
		}
	}

	@RequestMapping(value = { "postEdit" }, method = RequestMethod.POST)
	public String postCommentsEdit(@ModelAttribute("commentEdit") Tbl_Comment comment, HttpServletRequest request) {
		try {
			Tbl_User masterTbl_User = (Tbl_User) request.getSession().getAttribute("master");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			Tbl_Comment cmt = new Tbl_Comment();
			cmt.setId(comment.getId());
			cmt.setTblProduct(comment.getTblProduct());
			cmt.setContent(comment.getContent());
			cmt.setTblUser(masterTbl_User);
			cmt.setCreateTime(sdf.format(timestamp));
			commentService.edit(cmt);
			return "redirect:/comments/index?page=1";
		} catch (Exception e) {
			System.out.println(e);
			return "redirect:/error-404";
		}
	}

	@RequestMapping(value = { "edit" }, method = RequestMethod.GET)
	public String getCommentsEdit(@RequestParam("id") int id, Model model, HttpServletRequest request) {
		try {
			model.addAttribute("commentEdit", commentService.findById(id));
			model.addAttribute("products", productService.findAll());
			model.addAttribute("body", IndexController.getBody("body_comments_edit"));
			model.addAttribute("path", request.getContextPath());
			model.addAttribute("currentPage", "Cập nhật bình luận");
			model.addAttribute("url", "Trang chủ / Bình luận / Cập nhật");
			model.addAttribute("list", commentService.findAll());
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

	@RequestMapping(value = { "postAdd" }, method = RequestMethod.POST)
	public String postCommentsAdd(@ModelAttribute("commentAdd") Tbl_Comment comment, HttpServletRequest request) {
		try {
			Tbl_User masterTbl_User = (Tbl_User) request.getSession().getAttribute("master");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			Tbl_Comment cmt = new Tbl_Comment();
			cmt.setTblProduct(comment.getTblProduct());
			cmt.setContent(comment.getContent());
			cmt.setTblUser(masterTbl_User);
			cmt.setCreateTime(sdf.format(timestamp));
			commentService.create(cmt);
			return "redirect:/comments/index?page=1";
		} catch (Exception e) {
			System.out.println(e);
			return "redirect:/error-404";
		}
	}

	@RequestMapping(value = { "add" }, method = RequestMethod.GET)
	public String getCommentsAdd(Model model, HttpServletRequest request) {
		try {
			model.addAttribute("commentAdd", new Tbl_Comment());
			model.addAttribute("products", productService.findAll());
			model.addAttribute("body", IndexController.getBody("body_comments_add"));
			model.addAttribute("path", request.getContextPath());
			model.addAttribute("currentPage", "Thêm bình luận");
			model.addAttribute("url", "Trang chủ / Bình luận / Thêm mới");
			model.addAttribute("list", commentService.findAll());
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

	@RequestMapping(value = { "", "index", "list" }, method = RequestMethod.GET)
	public String getComments(@RequestParam("page") int page, Model model, HttpServletRequest request) {
		try {
			model.addAttribute("body", IndexController.getBody("body_comments"));
			model.addAttribute("path", request.getContextPath());
			model.addAttribute("currentPage", "Quản lý bình luận");
			model.addAttribute("url", "Trang chủ / Bình luận");
			List<Tbl_Comment> comments = commentService.findAll();
			int numObjInPage = 10;
			int totalPages = PageUtils.getTotalPage(comments.size(), numObjInPage);
			String pathString = request.getContextPath() + "/comments/index?";
			String pageString = PageUtils.getPages(page, totalPages, pathString);
			model.addAttribute("page", pageString);
			model.addAttribute("list", PageUtils.getListCommentsInPage(page, numObjInPage, comments));
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

	@RequestMapping(value = "check-login", method = RequestMethod.POST)
	@ResponseBody
	public String checkLogin(@RequestBody String data, HttpServletRequest request) {
		try {
			Tbl_User userSessionTbl_User = (Tbl_User) request.getSession().getAttribute("userSession");
			if (userSessionTbl_User == null) {
				return request.getContextPath() + "/login";
			} else {
				return "true";
			}
		} catch (Exception e) {
			System.out.println(e);
			return "redirect:/error-404";
		}
	}

	@RequestMapping(value = "product-detail", method = RequestMethod.POST)
	@ResponseBody
	public List<Rate> postCommentProductDetail(@RequestBody Map<String, String> data, HttpServletRequest request) {
		int productId = Integer.valueOf(data.get("productId"));
		int rating = Integer.valueOf(data.get("rating"));
		String cmt = data.get("cmt");
		Tbl_User user = (Tbl_User) request.getSession().getAttribute("userSession");
		List<Tbl_Vote> votes = voteService.findByProductAndUser(productService.findById(productId).get(), user);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		if (rating > 0) {
			if (votes.isEmpty()) {
				// Thêm rating cho sản phẩm lần đầu được user vote star
				Tbl_Vote ratingTbl_Vote = new Tbl_Vote();
				ratingTbl_Vote.setStar(rating);
				ratingTbl_Vote.setTblProduct(productService.findById(productId).get());
				ratingTbl_Vote.setTblUser(user);
				ratingTbl_Vote.setCreateTime(sdf.format(timestamp));
				voteService.create(ratingTbl_Vote);

			} else {
				// Thêm rating cho sản phẩm
				Tbl_Vote vote = votes.get(0);
				vote.setStar(rating);
				vote.setCreateTime(sdf.format(timestamp));
				voteService.edit(vote);
			}
		}

		// Thêm comment cho sản phẩm
		Tbl_Comment tbl_Comment = new Tbl_Comment();
		tbl_Comment.setTblProduct(productService.findById(productId).get());
		tbl_Comment.setTblUser(user);
		tbl_Comment.setContent(cmt);
		tbl_Comment.setCreateTime(sdf.format(timestamp));
		commentService.create(tbl_Comment);

		List<Rate> rates = new ArrayList<Rate>();
		List<Tbl_Comment> comments = commentService.findByProduct(productService.findById(productId).get());
		for (Tbl_Comment c : comments) {
			Rate rate = new Rate();
			rate.setCmt(c.getContent());
			rate.setRating(voteService.findByProductAndUser(productService.findById(productId).get(), c.getTblUser())
					.get(0).getStar());
			rate.setUser(c.getTblUser().getUsername());
			rate.setTime(c.getCreateTime());
			rates.add(rate);
		}

		return rates;
	}
}

//                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                          