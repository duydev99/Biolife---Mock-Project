package com.jsfw.controllers;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.jsfw.models.Tbl_User;
import com.jsfw.models.Tbl_Vote;
import com.jsfw.services.CommentService;
import com.jsfw.services.ProductService;
import com.jsfw.services.VoteService;
import com.jsfw.utils.PageUtils;

@Controller
@RequestMapping(value = "/vote/")
public class VoteController {
	@Autowired
	CommentService commentService;
	@Autowired
	ProductService productService;
	@Autowired
	VoteService voteService;

	@RequestMapping(value = { "delete" }, method = RequestMethod.GET)
	public String getDelete(@RequestParam("id") int id) {
		try {
			voteService.remove(voteService.findById(id));
			return "redirect:/vote/index?page=1";
		} catch (Exception e) {
			System.out.println(e);
			return "redirect:/error-404";
		}
	}

	@RequestMapping(value = { "postEdit" }, method = RequestMethod.POST)
	public String postCommentsEdit(@ModelAttribute("voteEdit") Tbl_Vote vote, HttpServletRequest request) {
		try {
			Tbl_User masterTbl_User = (Tbl_User) request.getSession().getAttribute("master");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			Tbl_Vote newVote = new Tbl_Vote();
			newVote.setId(vote.getId());
			newVote.setTblProduct(vote.getTblProduct());
			newVote.setTblUser(masterTbl_User);
			newVote.setStar(vote.getStar());
			newVote.setCreateTime(sdf.format(timestamp));
			voteService.edit(newVote);
			return "redirect:/vote/index?page=1";
		} catch (Exception e) {
			System.out.println(e);
			return "redirect:/error-404";
		}
	}

	@RequestMapping(value = { "edit" }, method = RequestMethod.GET)
	public String getVoteEdit(@RequestParam("id") int id, Model model, HttpServletRequest request) {
		try {
			model.addAttribute("voteEdit", voteService.findById(id));
			model.addAttribute("products", productService.findAll());
			model.addAttribute("body", IndexController.getBody("body_vote_edit"));
			model.addAttribute("path", request.getContextPath());
			model.addAttribute("currentPage", "Cập nhật đánh giá");
			model.addAttribute("url", "Trang chủ / Đánh giá / Cập nhật");
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
	public String postVotesAdd(@ModelAttribute("voteAdd") Tbl_Vote vote, HttpServletRequest request) {
		try {
			Tbl_User masterTbl_User = (Tbl_User) request.getSession().getAttribute("master");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			Tbl_Vote newVote = new Tbl_Vote();
			newVote.setTblProduct(vote.getTblProduct());
			newVote.setTblUser(masterTbl_User);
			newVote.setStar(vote.getStar());
			newVote.setCreateTime(sdf.format(timestamp));
			voteService.create(newVote);
			return "redirect:/vote/index?page=1";
		} catch (Exception e) {
			System.out.println(e);
			return "redirect:/error-404";
		}
	}

	@RequestMapping(value = { "add" }, method = RequestMethod.GET)
	public String getCommentsAdd(Model model, HttpServletRequest request) {
		try {
			model.addAttribute("voteAdd", new Tbl_Vote());
			model.addAttribute("products", productService.findAll());
			model.addAttribute("body", IndexController.getBody("body_vote_add"));
			model.addAttribute("path", request.getContextPath());
			model.addAttribute("currentPage", "Thêm đánh giá");
			model.addAttribute("url", "Trang chủ / Đánh giá / Thêm mới");
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
	public String getVote(@RequestParam("page") int page, Model model, HttpServletRequest request) {
		try {
			model.addAttribute("body", IndexController.getBody("body_vote"));
			model.addAttribute("path", request.getContextPath());
			model.addAttribute("currentPage", "Quản lý đánh giá");
			model.addAttribute("url", "Trang chủ / Đánh giá");
			List<Tbl_Vote> votes = voteService.findAll();
			int numObjInPage = 10;
			int totalPages = PageUtils.getTotalPage(votes.size(), numObjInPage);
			String pathString = request.getContextPath() + "/vote/index?";
			String pageString = PageUtils.getPages(page, totalPages, pathString);
			model.addAttribute("page", pageString);
			model.addAttribute("list", PageUtils.getListVotesInPage(page, numObjInPage, votes));
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
