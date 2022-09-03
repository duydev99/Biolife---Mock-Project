package com.jsfw.utils;

import java.util.ArrayList;
import java.util.List;

import com.jsfw.models.Tbl_Category;
import com.jsfw.models.Tbl_Comment;
import com.jsfw.models.Tbl_Manufacturer;
import com.jsfw.models.Tbl_Order;
import com.jsfw.models.Tbl_Payment;
import com.jsfw.models.Tbl_Product;
import com.jsfw.models.Tbl_User;
import com.jsfw.models.Tbl_Vote;

public class PageUtils {

	public static int getTotalPage(int totalObjects, int objInPage) {
		int totalPages = 0;
		if (totalObjects < objInPage) {
			totalPages = 1;
		} else {
			if (totalObjects % objInPage == 0)
				totalPages = totalObjects / objInPage;
			else
				totalPages = totalObjects / objInPage + 1;
		}
		return totalPages;
	}
	
	public static String getPages(int currentPage, int totalPages, String path) {
		String rsString = "";
		int nextPage = 0;
		int previousPage = 0;
		String nextString = "";
		String previousString = "";

		// Kiểm tra số trang hiện tại có < 1 hay lớn hơn tổng số trang hay không?
		if (currentPage < 1)
			currentPage = 1;
		if (currentPage > totalPages)
			currentPage = totalPages;

		if (totalPages < 5) {
			for (int i = 1; i <= totalPages; i++) {
				if (i == currentPage) {
					rsString += "<span class='btn btn-info'>" + i + "</span>";
				} else {
					rsString += "<a href='" + path + "page=" + i + "' class='btn btn-default'>" + i + "</a>";
				}
			}
			if (totalPages > 1) {
				if (currentPage == 1) {
					nextPage = currentPage + 1;
					nextString = "<a href='" + path + "page=" + nextPage
							+ "' class='btn btn-default'><i class='fa fa-angle-right' aria-hidden='true'></i></a>";
					rsString += nextString;
				} else if (currentPage == totalPages) {
					previousPage = currentPage - 1;
					previousString = "<a href='" + path + "page=" + previousPage
							+ "' class='btn btn-default'><i class='fa fa-angle-left' aria-hidden='true'></i></a>";
					rsString = previousString + rsString;
				} else {
					nextPage = currentPage + 1;
					nextString = "<a href='" + path + "page=" + nextPage
							+ "' class='btn btn-default'><i class='fa fa-angle-right' aria-hidden='true'></i></a>";
					previousPage = currentPage - 1;
					previousString = "<a href='" + path + "page=" + previousPage
							+ "' class='btn btn-default'><i class='fa fa-angle-left' aria-hidden='true'></i></a>";
					rsString = previousString + rsString + nextString;
				}
			}

		} else {
			int nxtPage = 4;
			int current = currentPage;
			if((totalPages - currentPage) < 4){
				nxtPage = totalPages - currentPage;
				current = totalPages - 4;
			}
			for (int i = current; i <= currentPage + nxtPage; i++) {
				if (i == currentPage) {
					rsString += "<span class='btn btn-info'>" + i + "</span></li>";
				} else {
					rsString += "<a href='" + path + "page=" + i + "' class='btn btn-default'>" + i + "</a>";
				}
			}
			if (currentPage == 1) {
				nextPage = currentPage + 1;
				nextString = "<a href='" + path + "page=" + nextPage
						+ "' class='btn btn-default'><i class='fa fa-angle-right' aria-hidden='true'></i></a>";
				rsString += nextString;
			} else if (currentPage == totalPages) {
				previousPage = currentPage - 1;
				previousString = "<a href='" + path + "page=" + previousPage
						+ "' class='btn btn-default'><i class='fa fa-angle-left' aria-hidden='true'></i></a>";
				rsString = previousString + rsString;
			} else {
				nextPage = currentPage + 1;
				nextString = "<a href='" + path + "page=" + nextPage
						+ "' class='btn btn-default'><i class='fa fa-angle-right' aria-hidden='true'></i></a>";
				previousPage = currentPage - 1;
				previousString = "<a href='" + path + "page=" + previousPage
						+ "' class='btn btn-default'><i class='fa fa-angle-left' aria-hidden='true'></i></a>";
				rsString = previousString + rsString + nextString;
			}
		}
		return rsString;
	}

	public static List<Tbl_Category> getListCategoryInPage(int currentPage, int objsInPage,
			List<Tbl_Category> categories) {
		List<Tbl_Category> objects = new ArrayList<Tbl_Category>();
		int maxIndex = objsInPage * currentPage;
		int minIndex = maxIndex - objsInPage;
		if (categories.size() < maxIndex)
			maxIndex = categories.size();
		objects = categories.subList(minIndex, maxIndex);
		return objects;
	}
	
	public static List<Tbl_Manufacturer> getListManufacturerInPage(int currentPage, int objsInPage,
			List<Tbl_Manufacturer> manufacturers) {
		List<Tbl_Manufacturer> objects = new ArrayList<Tbl_Manufacturer>();
		int maxIndex = objsInPage * currentPage;
		int minIndex = maxIndex - objsInPage;
		if (manufacturers.size() < maxIndex)
			maxIndex = manufacturers.size();
		objects = manufacturers.subList(minIndex, maxIndex);
		return objects;
	}
	
	public static List<Tbl_Payment> getListPaymentsInPage(int currentPage, int objsInPage,
			List<Tbl_Payment> payments) {
		List<Tbl_Payment> objects = new ArrayList<Tbl_Payment>();
		int maxIndex = objsInPage * currentPage;
		int minIndex = maxIndex - objsInPage;
		if (payments.size() < maxIndex)
			maxIndex = payments.size();
		objects = payments.subList(minIndex, maxIndex);
		return objects;
	}
	
	public static List<Tbl_Product> getListProductsInPage(int currentPage, int objsInPage,
			List<Tbl_Product> products) {
		List<Tbl_Product> objects = new ArrayList<Tbl_Product>();
		int maxIndex = objsInPage * currentPage;
		int minIndex = maxIndex - objsInPage;
		if (products.size() < maxIndex)
			maxIndex = products.size();
		objects = products.subList(minIndex, maxIndex);
		return objects;
	}
	
	public static List<Tbl_Comment> getListCommentsInPage(int currentPage, int objsInPage,
			List<Tbl_Comment> comments) {
		List<Tbl_Comment> objects = new ArrayList<Tbl_Comment>();
		int maxIndex = objsInPage * currentPage;
		int minIndex = maxIndex - objsInPage;
		if (comments.size() < maxIndex)
			maxIndex = comments.size();
		objects = comments.subList(minIndex, maxIndex);
		return objects;
	}
	
	public static List<Tbl_Vote> getListVotesInPage(int currentPage, int objsInPage,
			List<Tbl_Vote> votes) {
		List<Tbl_Vote> objects = new ArrayList<Tbl_Vote>();
		int maxIndex = objsInPage * currentPage;
		int minIndex = maxIndex - objsInPage;
		if (votes.size() < maxIndex)
			maxIndex = votes.size();
		objects = votes.subList(minIndex, maxIndex);
		return objects;
	}
	
	public static List<Tbl_Order> getListOrdersInPage(int currentPage, int objsInPage,
			List<Tbl_Order> orders) {
		List<Tbl_Order> objects = new ArrayList<Tbl_Order>();
		int maxIndex = objsInPage * currentPage;
		int minIndex = maxIndex - objsInPage;
		if (orders.size() < maxIndex)
			maxIndex = orders.size();
		objects = orders.subList(minIndex, maxIndex);
		return objects;
	}
	
	public static List<Tbl_User> getListUsersInPage(int currentPage, int objsInPage,
			List<Tbl_User> users) {
		List<Tbl_User> objects = new ArrayList<Tbl_User>();
		int maxIndex = objsInPage * currentPage;
		int minIndex = maxIndex - objsInPage;
		if (users.size() < maxIndex)
			maxIndex = users.size();
		objects = users.subList(minIndex, maxIndex);
		return objects;
	}
}
