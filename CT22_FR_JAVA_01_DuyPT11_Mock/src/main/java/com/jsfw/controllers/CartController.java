package com.jsfw.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jsfw.models.Cart;
import com.jsfw.models.Tbl_Product;
import com.jsfw.models.Tbl_User;
import com.jsfw.services.CategoryService;
import com.jsfw.services.ManufacturerService;
import com.jsfw.services.ProductService;

@Controller
public class CartController {

	@Autowired
	ProductService productService;
	@Autowired
	CategoryService categoryService;
	@Autowired
	ManufacturerService manufacturerService;

	@RequestMapping(value = "get-data-cart", method = RequestMethod.POST)
	@ResponseBody
	public List<Cart> getCart(HttpServletRequest request) {
		if (request.getSession().getAttribute("sessionCart") == null)
			return new ArrayList<Cart>();
		else
			return (List<Cart>) request.getSession().getAttribute("sessionCart");
	}

	@RequestMapping(value = "add-to-cart", method = RequestMethod.POST)
	@ResponseBody
	public List<Cart> addToCart(@RequestBody Map<Integer, String> data, HttpServletRequest request) {
		List<Cart> cart = new ArrayList<Cart>();

		List<Cart> sessionCart = (List<Cart>) request.getSession().getAttribute("sessionCart");

		if (request.getSession().getAttribute("sessionCart") != null) {
			for (Cart c : sessionCart) {
				cart.add(c);
			}
		}

		int productId = Integer.valueOf(data.get(0));
		int amount = Integer.valueOf(data.get(1));

		// Set giá trị cho số lượng trong giỏ hàng không nhỏ hơn 1 và lớn hơn 20
		if (amount > 20)
			amount = 20;
		if (amount < 1)
			amount = 1;

		boolean isExist = false;

		// Kiểm tra xem sản phẩm được thêm vào đã tồn tại trong giỏ hàng chưa
		for (Cart c : cart) {
			if (c.getId() == productId)
				isExist = true;
		}

		// Kiểm tra xem sản phẩm đã tồn tại trong giỏ hàng chưa?
		if (!isExist) {
			// Lấy thông tin sản phẩm được thêm vào giỏ hàng
			Tbl_Product getTbl_Product = productService.findById(productId).get();

			// Tạo đối tượng product mới và set các giá trị id, name, giá
			Tbl_Product product = new Tbl_Product();
			product.setId(getTbl_Product.getId());
			product.setName(getTbl_Product.getName());
			product.setPrice(getTbl_Product.getPrice());

			// Tạo đối tượng CartUtil mới và set các giá trị rồi thêm vào giỏi hàng
			Cart newCart = new Cart();
			newCart.setId(productId);
			newCart.setAmount(amount);
			newCart.setProduct(product);
			newCart.setImage(getTbl_Product.getTblImageProducts().get(0).getSource());
			cart.add(newCart);
		} else {
			for (Cart c : cart) {
				if (c.getId() == productId) {
					if (c.getAmount() < 20)
						c.setAmount(c.getAmount() + amount);
				}
			}
		}

		// Set lại giỏ hàng
		request.getSession().setAttribute("sessionCart", cart);

		return (List<Cart>) request.getSession().getAttribute("sessionCart");
	}

	@RequestMapping(value = "update-amount", method = RequestMethod.POST)
	@ResponseBody
	public List<Cart> updateAmount(@RequestBody Map<Integer, String> data, HttpServletRequest request) {
		List<Cart> cart = (List<Cart>) request.getSession().getAttribute("sessionCart");
		int productId = Integer.valueOf(data.get(0));
		int amount = Integer.valueOf(data.get(1));

		for (Cart c : cart) {
			if (c.getId() == productId) {
				Tbl_Product product = productService.findById(productId).get();
				if (product.getAmount() < 1) {
					cart.remove(c);
				} else {
					if (amount > product.getAmount()) {
						c.setAmount(c.getAmount());
					} else {
						c.setAmount(amount);
					}
				}
			}
		}
		request.getSession().setAttribute("sessionCart", cart);
		return (List<Cart>) request.getSession().getAttribute("sessionCart");
	}

	@RequestMapping(value = "delete-product", method = RequestMethod.POST)
	@ResponseBody
	public List<Cart> deleteProduct(@RequestBody Map<Integer, String> data, HttpServletRequest request) {
		int productId = Integer.valueOf(data.get(0));
		List<Cart> cart = (List<Cart>) request.getSession().getAttribute("sessionCart");
		List<Cart> newCart = new ArrayList<Cart>();
		if (cart.size() < 2) {
			cart.clear();
			newCart.addAll(cart);
		} else {
			for (Cart c : cart) {
				if (c.getId() == productId) {
					cart.remove(cart);
				} else
					newCart.add(c);
			}
		}

		// Set lại giỏ hàng
		request.getSession().setAttribute("sessionCart", newCart);
		return (List<Cart>) request.getSession().getAttribute("sessionCart");
	}

	@RequestMapping(value = "cart-detail", method = RequestMethod.GET)
	public String getDetailCart(Model model, HttpServletRequest request) {
		try {// Nhận danh sách sản phẩm từ session
			List<Cart> carts = (List<Cart>) request.getSession().getAttribute("sessionCart");
			// Xử lý user login header
			Tbl_User sessionUser = (Tbl_User) request.getSession().getAttribute("userSession");
			if (sessionUser != null) {
				String str = "<a href='" + request.getContextPath()
						+ "/users/detail' class='login-link'><i class='biolife-icon icon-login'></i>"
						+ sessionUser.getUsername() + "</a>";
				model.addAttribute("changeHeader", str);
			} else {
				String str = "<a href='" + request.getContextPath()
						+ "/login' class='login-link'><i class='biolife-icon icon-login'></i>Login/Register</a>";
				model.addAttribute("changeHeader", str);
			}
			// set model
			int total = 0;
			for (Cart c : carts) {
				total += c.getProduct().getPrice();
			}
			model.addAttribute("categories", categoryService.findAll());
			model.addAttribute("manufacturers", manufacturerService.findAll());
			model.addAttribute("list", carts);
			model.addAttribute("total", total);
			model.addAttribute("url", "Trang Chủ / Chi Tiết Giỏ Hàng");
			model.addAttribute("body", IndexController.getBody("body_cart"));
			model.addAttribute("path", request.getContextPath());
			return "index";
		} catch (Exception e) {
			System.out.println(e);
			return "redirect:/error-404";
		}
	}

	@RequestMapping(value = "check-amount-product", method = RequestMethod.POST)
	@ResponseBody
	public List<String> addCheckAddValid(@RequestBody Map<Integer, String> data, HttpServletRequest request) {
		String rString = null;
		int productId = Integer.valueOf(data.get(0));
		int amount = Integer.valueOf(data.get(1));
		Tbl_Product product = productService.findById(productId).get();
		List<Cart> carts = (List<Cart>) request.getSession().getAttribute("sessionCart");
		boolean isExist = false;
		int amountInCart = 0;
		if (request.getSession().getAttribute("sessionCart") != null) {
			for (Cart c : carts) {
				if (c.getId() == productId) {
					isExist = true;
					amountInCart = c.getAmount() + amount;
				}
			}
		}

		if (isExist) {
			if (product.getAmount() < 1) {
				rString = "Sản phẩm này đã hết hàng";
			} else {
				if (product.getAmount() < amountInCart) {
					rString = "Vượt số lượng tồn kho: " + (amountInCart - product.getAmount()) + " sản phẩm";
				} else {
					rString = "valid";
				}
			}
		} else {
			if (product.getAmount() < 1) {
				rString = "Sản phẩm này đã hết hàng";
			} else {
				if (product.getAmount() < amount) {
					rString = "Vượt số lượng tồn kho: " + (amount - product.getAmount()) + " sản phẩm";
				} else {
					rString = "valid";
				}
			}
		}

		List<String> rs = new ArrayList<String>();
		rs.add(rString);
		return rs;
	}

	@RequestMapping(value = "check-amount-update-product", method = RequestMethod.POST)
	@ResponseBody
	public List<String> addCheckUpdateValid(@RequestBody Map<Integer, String> data, HttpServletRequest request) {
		String rString = null;
		int productId = Integer.valueOf(data.get(0));
		int amount = Integer.valueOf(data.get(1));
		Tbl_Product product = productService.findById(productId).get();
		List<Cart> carts = (List<Cart>) request.getSession().getAttribute("sessionCart");
		if (product.getAmount() < 1) {
			rString = "Sản phẩm này đã hết hàng";
			for (Cart c : carts) {
				if (c.getId() == productId)
					carts.remove(c);
			}
			request.getSession().setAttribute("sessionCart", carts);
		} else {
			if (product.getAmount() < amount) {
				rString = "Vượt số lượng tồn kho: " + (amount - product.getAmount()) + " sản phẩm";
			} else {
				rString = "valid";
			}
		}

		List<String> rs = new ArrayList<String>();
		rs.add(rString);
		return rs;
	}
}
