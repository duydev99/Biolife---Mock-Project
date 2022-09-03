package com.jsfw.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.jsfw.models.Tbl_Product;
import com.jsfw.services.ProductService;

public class ShopUtils {

	// Lọc sản phẩm theo loại sản phẩm
	public static List<Tbl_Product> findProductByCategory(List<Tbl_Product> products, int categoryId) {
		List<Tbl_Product> rsProductByCategory = new ArrayList<Tbl_Product>();
		// rsProductByCategory.clear();
		for (Tbl_Product p : products) {
			if (p.getTblCategory().getId() == categoryId)
				rsProductByCategory.add(p);
		}
		return rsProductByCategory;
	}

	// Lọc sản phẩm theo nhà cung cấp
	public static List<Tbl_Product> findProductByManufacturer(List<Tbl_Product> products, int manufacturerId) {
		List<Tbl_Product> rsProductByManufacturer = new ArrayList<Tbl_Product>();
		// rsProductByManufacturer.clear();
		for (Tbl_Product p : products) {
			if (p.getTblManufacturer().getId() == manufacturerId)
				rsProductByManufacturer.add(p);
		}
		return rsProductByManufacturer;
	}

	// Lọc sản phẩm theo giá sản phẩm
	public static List<Tbl_Product> findProductByPrice(List<Tbl_Product> products, int priceCode) {
		List<Tbl_Product> rsProductByPrice = new ArrayList<Tbl_Product>();
		// rsProductByPrice.clear();
		for (Tbl_Product p : products) {
			switch (priceCode) {
			case 1:
				if (p.getPrice() < 50000)
					rsProductByPrice.add(p);
				break;
			case 2:
				if (p.getPrice() >= 50000 && p.getPrice() <= 100000)
					rsProductByPrice.add(p);
				break;
			case 3:
				if (p.getPrice() >= 100000 && p.getPrice() <= 250000)
					rsProductByPrice.add(p);
				break;
			case 4:
				if (p.getPrice() >= 250000 && p.getPrice() <= 500000)
					rsProductByPrice.add(p);
				break;
			case 5:
				if (p.getPrice() >= 500000 && p.getPrice() <= 1000000)
					rsProductByPrice.add(p);
				break;

			case 6:
				if (p.getPrice() >= 1000000 && p.getPrice() <= 2000000)
					rsProductByPrice.add(p);
				break;
			case 7:
				if (p.getPrice() > 2000000)
					rsProductByPrice.add(p);
				break;
			default:
				break;
			}
		}
		return rsProductByPrice;
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
					rsString += "<li><span class='current-page'>" + i + "</span></li>";
				} else {
					rsString += "<li><a href='" + path + "page=" + i + "' class='link-page'>" + i + "</a></li>";
				}
			}
			if (totalPages > 1) {
				if (currentPage == 1) {
					nextPage = currentPage + 1;
					nextString = "<li><a href='" + path + "page=" + nextPage
							+ "' class='link-page'><i class='fa fa-angle-right' aria-hidden='true'></i></a></li>";
					rsString += nextString;
				} else if (currentPage == totalPages) {
					previousPage = currentPage - 1;
					previousString = "<li><a href='" + path + "page=" + previousPage
							+ "' class='link-page'><i class='fa fa-angle-left' aria-hidden='true'></i></a></li>";
					rsString = previousString + rsString;
				} else {
					nextPage = currentPage + 1;
					nextString = "<li><a href='" + path + "page=" + nextPage
							+ "' class='link-page'><i class='fa fa-angle-right' aria-hidden='true'></i></a></li>";
					previousPage = currentPage - 1;
					previousString = "<li><a href='" + path + "page=" + previousPage
							+ "' class='link-page'><i class='fa fa-angle-left' aria-hidden='true'></i></a></li>";
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
					rsString += "<li><span class='current-page'>" + i + "</span></li>";
				} else {
					rsString += "<li><a href='" + path + "page=" + i + "' class='link-page'>" + i + "</a></li>";
				}
			}
			if (currentPage == 1) {
				nextPage = currentPage + 1;
				nextString = "<li><a href='" + path + "page=" + nextPage
						+ "' class='link-page'><i class='fa fa-angle-right' aria-hidden='true'></i></a></li>";
				rsString += nextString;
			} else if (currentPage == totalPages) {
				previousPage = currentPage - 1;
				previousString = "<li><a href='" + path + "page=" + previousPage
						+ "' class='link-page'><i class='fa fa-angle-left' aria-hidden='true'></i></a></li>";
				rsString = previousString + rsString;
			} else {
				nextPage = currentPage + 1;
				nextString = "<li><a href='" + path + "page=" + nextPage
						+ "' class='link-page'><i class='fa fa-angle-right' aria-hidden='true'></i></a></li>";
				previousPage = currentPage - 1;
				previousString = "<li><a href='" + path + "page=" + previousPage
						+ "' class='link-page'><i class='fa fa-angle-left' aria-hidden='true'></i></a></li>";
				rsString = previousString + rsString + nextString;
			}
		}
		return rsString;
	}

	public static int getTotalPage(int totalProducts, int productNumberInPage) {
		int totalPages = 0;
		if (totalProducts < productNumberInPage) {
			totalPages = 1;
		} else {
			if (totalProducts % productNumberInPage == 0)
				totalPages = totalProducts / productNumberInPage;
			else
				totalPages = totalProducts / productNumberInPage + 1;
		}
		return totalPages;
	}

	public static List<Tbl_Product> getListProductInPage(int currentPage, int productsInPage,
			List<Tbl_Product> products) {
		List<Tbl_Product> tbl_Products = new ArrayList<Tbl_Product>();
		int maxIndex = productsInPage * currentPage;
		int minIndex = maxIndex - productsInPage;
		if (products.size() < maxIndex)
			maxIndex = products.size();
		tbl_Products = products.subList(minIndex, maxIndex);
		return tbl_Products;
	}

	public static List<Tbl_Product> getProductsFilter(String filter, List<Tbl_Product> products, int key) {
		List<Tbl_Product> rsProducts = new ArrayList<Tbl_Product>();
		switch (filter) {
		case "all":
			rsProducts = products;
			break;
		case "category":
			rsProducts = findProductByCategory(products, key);
			break;
		case "manufacturer":
			rsProducts = findProductByManufacturer(products, key);
			break;
		case "price":
			rsProducts = findProductByPrice(products, key);
			break;
		default:
			break;
		}
		return rsProducts;
	}

	public static List<Tbl_Product> getProductsOrderBy(String orderby, List<Tbl_Product> products){
		List<Tbl_Product> rsProducts = new ArrayList<Tbl_Product>();
		switch (orderby) {
		case "default":
			rsProducts = products;
			break;
		case "name":
			rsProducts = sortByName(products);
			break;
		case "time":
			rsProducts = sortByTime(products);
			break;
		case "price-asc":
			rsProducts = sortByPriceAsc(products);
			break;
		case "price-desc":
			rsProducts = sortByPriceDesc(products);
			break;
		default:
			break;
		}
		return rsProducts;
	}

	public static List<Tbl_Product> sortByName(List<Tbl_Product> products){
		Collections.sort(products, new Comparator<Tbl_Product>() {
	        @Override
	        public int compare(Tbl_Product p1, Tbl_Product p2) {
	            return p1.getName().compareTo(p2.getName());
	        }
	       });
		return products ;
	}
	
	public static List<Tbl_Product> sortByTime(List<Tbl_Product> products){
		Collections.sort(products, new Comparator<Tbl_Product>() {
	        @Override
	        public int compare(Tbl_Product p1, Tbl_Product p2) {
	            return p2.getId() - p1.getId();
	        }
	       });
		return products ;
	}
	
	public static List<Tbl_Product> sortByPriceAsc(List<Tbl_Product> products){
		Collections.sort(products, new Comparator<Tbl_Product>() {
	        @Override
	        public int compare(Tbl_Product p1, Tbl_Product p2) {
	            return (int) (p1.getPrice() - p2.getPrice());
	        }
	       });
		return products ;
	}
	
	public static List<Tbl_Product> sortByPriceDesc(List<Tbl_Product> products){
		Collections.sort(products, new Comparator<Tbl_Product>() {
	        @Override
	        public int compare(Tbl_Product p1, Tbl_Product p2) {
	            return (int) (p2.getPrice() - p1.getPrice());
	        }
	       });
		return products ;
	}
}
