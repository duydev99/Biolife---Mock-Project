<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!--Hero Section-->
<div class="hero-section hero-background">
	<h1 class="page-title">Giỏ Hàng</h1>
</div>

<!--Navigation section-->
<div class="container">
	<nav class="biolife-nav">
		<span class="current-page">${url }</span>
	</nav>
</div>

<div class="page-contain shopping-cart">

	<!-- Main content -->
	<div id="main-content" class="main-content">
		<div class="container">
			<!--Cart Table-->
			<div class="shopping-cart-container">
				<div class="row">
					<div class="col-lg-9 col-md-12 col-sm-12 col-xs-12">
						<h3 class="box-title">Danh sách sản phẩm</h3>
						<form class="shopping-cart-form" action="#" method="post">

							<table class="shop_table cart-form">
								<thead>
									<tr>
										<th class="product-name">Sản phẩm</th>
										<th class="product-price">Đơn giá</th>
										<th class="product-quantity">Số lượng</th>
										<th class="product-subtotal">Thành tiền</th>
									</tr>
								</thead>
								<tbody id="cartDetailBodyTable">
									<c:forEach var="item" items="${list}">
										<tr class="cart_item ">
											<td class="product-thumbnail" data-title="Product Name">
												<a class="prd-thumb"
												href="${path}/product-detail?id=${item.id}">
													<figure>
														<img width="113" height="113"
															src="../images/${item.image}" alt="shipping cart">
													</figure>
											</a> <a class="prd-name"
												href="${path}/product-detail?id=${item.id}">${item.product.name}</a>
												<div class="action">
													<a href="#" class="edit"
														onClick="updateCartDetail(${item.id});"><i
														class="fa fa-pencil" aria-hidden="true"></i></a> <a href="#"
														class="remove" onClick="deleteProduct(${item.id});"><i
														class="fa fa-trash-o" aria-hidden="true"></i></a>
												</div>
											</td>
											<td class="product-price" data-title="Price">
												<div class="price price-contain">
													<ins>
														<span class="price-amount"><fmt:formatNumber
																type="number" groupingUsed="true"
																value="${item.product.price}" /><span
															class="currencySymbol"> <u>đ</u>
														</span></span>
													</ins>
												</div>
											</td>
											<td class="product-quantity " data-title="Quantity">
												<div class="quantity-box type1">
													<div class="qty-input">
														<input type="number" value="${item.amount}"
															id="amount${item.id}"
															onChange="changeEditAmountCartDetail(this);" max="20"
															min="1" step="1" /> <input type="hidden"
															value="${item.product.price }" id="priamount${item.id}" />

													</div>
												</div>
											</td>
											<td class="product-subtotal" data-title="Total">
												<div class="price price-contain">
													<ins>
														<span class="price-amount"
															id="detailPriceamount${item.id}"><fmt:formatNumber
																type="number" groupingUsed="true"
																value="${item.product.price * item.amount}" /><span
															class="currencySymbol"> <u>đ</u>
														</span></span>
													</ins>
												</div>
											</td>
										</tr>
									</c:forEach>
									<tr class="cart_item wrap-buttons">
										<td class="wrap-btn-control" colspan="4"><a href="${path}/shop?filter=all&orderby=default&key=-1&page=1"
											class="btn back-to-shop">Đến cửa hàng</a>
									</tr>
								</tbody>
							</table>
						</form>
					</div>
					<div class="col-lg-3 col-md-12 col-sm-12 col-xs-12">
						<div class="shpcart-subtotal-block">
							<div class="subtotal-line">
								<b class="stt-name">Tổng <span class="sub"></span></b>
								<span class="stt-price" id="cartTotalPriceDetail"><fmt:formatNumber
										type="number" groupingUsed="true"
										value="${total}" /> <u>đ</u></span>
							</div>
							
							<div class="btn-checkout">
								<a href="${path}/check-out" class="btn checkout">Thanh Toán</a>
							</div>
							
						</div>
					</div>
				</div>
			</div>

			<!--Related Product-->
			<div class="product-related-box single-layout">
				<div class="biolife-title-box lg-margin-bottom-26px-im">
					<span class="biolife-icon icon-organic"></span> <span
						class="subtitle">All the best item for You</span>
					<h3 class="main-title">Related Products</h3>
				</div>
				<ul
					class="products-list biolife-carousel nav-center-02 nav-none-on-mobile"
					data-slick='{"rows":1,"arrows":true,"dots":false,"infinite":false,"speed":400,"slidesMargin":0,"slidesToShow":5, "responsive":[{"breakpoint":1200, "settings":{ "slidesToShow": 4}},{"breakpoint":992, "settings":{ "slidesToShow": 3, "slidesMargin":20}},{"breakpoint":768, "settings":{ "slidesToShow": 2, "slidesMargin":10}}]}'>
					<li class="product-item">
						<div class="contain-product layout-default">
							<div class="product-thumb">
								<a href="#" class="link-to-product"> <img
									src="assets/images/products/p-13.jpg" alt="dd" width="270"
									height="270" class="product-thumnail">
								</a>
							</div>
							<div class="info">
								<b class="categories">Fresh Fruit</b>
								<h4 class="product-title">
									<a href="#" class="pr-name">National Fresh Fruit</a>
								</h4>
								<div class="price ">
									<ins>
										<span class="price-amount"><span class="currencySymbol">£</span>85.00</span>
									</ins>
									<del>
										<span class="price-amount"><span class="currencySymbol">£</span>95.00</span>
									</del>
								</div>
								<div class="slide-down-box">
									<p class="message">All products are carefully selected to
										ensure food safety.</p>
									<div class="buttons">
										<a href="#" class="btn wishlist-btn"><i
											class="fa fa-heart" aria-hidden="true"></i></a> <a href="#"
											class="btn add-to-cart-btn"><i
											class="fa fa-cart-arrow-down" aria-hidden="true"></i>add to
											cart</a> <a href="#" class="btn compare-btn"><i
											class="fa fa-random" aria-hidden="true"></i></a>
									</div>
								</div>
							</div>
						</div>
					</li>
					<li class="product-item">
						<div class="contain-product layout-default">
							<div class="product-thumb">
								<a href="#" class="link-to-product"> <img
									src="assets/images/products/p-14.jpg" alt="dd" width="270"
									height="270" class="product-thumnail">
								</a>
							</div>
							<div class="info">
								<b class="categories">Fresh Fruit</b>
								<h4 class="product-title">
									<a href="#" class="pr-name">National Fresh Fruit</a>
								</h4>
								<div class="price">
									<ins>
										<span class="price-amount"><span class="currencySymbol">£</span>85.00</span>
									</ins>
									<del>
										<span class="price-amount"><span class="currencySymbol">£</span>95.00</span>
									</del>
								</div>
								<div class="slide-down-box">
									<p class="message">All products are carefully selected to
										ensure food safety.</p>
									<div class="buttons">
										<a href="#" class="btn wishlist-btn"><i
											class="fa fa-heart" aria-hidden="true"></i></a> <a href="#"
											class="btn add-to-cart-btn"><i
											class="fa fa-cart-arrow-down" aria-hidden="true"></i>add to
											cart</a> <a href="#" class="btn compare-btn"><i
											class="fa fa-random" aria-hidden="true"></i></a>
									</div>
								</div>
							</div>
						</div>
					</li>
					<li class="product-item">
						<div class="contain-product layout-default">
							<div class="product-thumb">
								<a href="#" class="link-to-product"> <img
									src="assets/images/products/p-15.jpg" alt="dd" width="270"
									height="270" class="product-thumnail">
								</a>
							</div>
							<div class="info">
								<b class="categories">Fresh Fruit</b>
								<h4 class="product-title">
									<a href="#" class="pr-name">National Fresh Fruit</a>
								</h4>
								<div class="price">
									<ins>
										<span class="price-amount"><span class="currencySymbol">£</span>85.00</span>
									</ins>
									<del>
										<span class="price-amount"><span class="currencySymbol">£</span>95.00</span>
									</del>
								</div>
								<div class="slide-down-box">
									<p class="message">All products are carefully selected to
										ensure food safety.</p>
									<div class="buttons">
										<a href="#" class="btn wishlist-btn"><i
											class="fa fa-heart" aria-hidden="true"></i></a> <a href="#"
											class="btn add-to-cart-btn"><i
											class="fa fa-cart-arrow-down" aria-hidden="true"></i>add to
											cart</a> <a href="#" class="btn compare-btn"><i
											class="fa fa-random" aria-hidden="true"></i></a>
									</div>
								</div>
							</div>
						</div>
					</li>
					<li class="product-item">
						<div class="contain-product layout-default">
							<div class="product-thumb">
								<a href="#" class="link-to-product"> <img
									src="assets/images/products/p-10.jpg" alt="dd" width="270"
									height="270" class="product-thumnail">
								</a>
							</div>
							<div class="info">
								<b class="categories">Fresh Fruit</b>
								<h4 class="product-title">
									<a href="#" class="pr-name">National Fresh Fruit</a>
								</h4>
								<div class="price">
									<ins>
										<span class="price-amount"><span class="currencySymbol">£</span>85.00</span>
									</ins>
									<del>
										<span class="price-amount"><span class="currencySymbol">£</span>95.00</span>
									</del>
								</div>
								<div class="slide-down-box">
									<p class="message">All products are carefully selected to
										ensure food safety.</p>
									<div class="buttons">
										<a href="#" class="btn wishlist-btn"><i
											class="fa fa-heart" aria-hidden="true"></i></a> <a href="#"
											class="btn add-to-cart-btn"><i
											class="fa fa-cart-arrow-down" aria-hidden="true"></i>add to
											cart</a> <a href="#" class="btn compare-btn"><i
											class="fa fa-random" aria-hidden="true"></i></a>
									</div>
								</div>
							</div>
						</div>
					</li>
					<li class="product-item">
						<div class="contain-product layout-default">
							<div class="product-thumb">
								<a href="#" class="link-to-product"> <img
									src="assets/images/products/p-08.jpg" alt="dd" width="270"
									height="270" class="product-thumnail">
								</a>
							</div>
							<div class="info">
								<b class="categories">Fresh Fruit</b>
								<h4 class="product-title">
									<a href="#" class="pr-name">National Fresh Fruit</a>
								</h4>
								<div class="price">
									<ins>
										<span class="price-amount"><span class="currencySymbol">£</span>85.00</span>
									</ins>
									<del>
										<span class="price-amount"><span class="currencySymbol">£</span>95.00</span>
									</del>
								</div>
								<div class="slide-down-box">
									<p class="message">All products are carefully selected to
										ensure food safety.</p>
									<div class="buttons">
										<a href="#" class="btn wishlist-btn"><i
											class="fa fa-heart" aria-hidden="true"></i></a> <a href="#"
											class="btn add-to-cart-btn"><i
											class="fa fa-cart-arrow-down" aria-hidden="true"></i>add to
											cart</a> <a href="#" class="btn compare-btn"><i
											class="fa fa-random" aria-hidden="true"></i></a>
									</div>
								</div>
							</div>
						</div>
					</li>
					<li class="product-item">
						<div class="contain-product layout-default">
							<div class="product-thumb">
								<a href="#" class="link-to-product"> <img
									src="assets/images/products/p-21.jpg" alt="dd" width="270"
									height="270" class="product-thumnail">
								</a>
							</div>
							<div class="info">
								<b class="categories">Fresh Fruit</b>
								<h4 class="product-title">
									<a href="#" class="pr-name">National Fresh Fruit</a>
								</h4>
								<div class="price">
									<ins>
										<span class="price-amount"><span class="currencySymbol">£</span>85.00</span>
									</ins>
									<del>
										<span class="price-amount"><span class="currencySymbol">£</span>95.00</span>
									</del>
								</div>
								<div class="slide-down-box">
									<p class="message">All products are carefully selected to
										ensure food safety.</p>
									<div class="buttons">
										<a href="#" class="btn wishlist-btn"><i
											class="fa fa-heart" aria-hidden="true"></i></a> <a href="#"
											class="btn add-to-cart-btn"><i
											class="fa fa-cart-arrow-down" aria-hidden="true"></i>add to
											cart</a> <a href="#" class="btn compare-btn"><i
											class="fa fa-random" aria-hidden="true"></i></a>
									</div>
								</div>
							</div>
						</div>
					</li>
					<li class="product-item">
						<div class="contain-product layout-default">
							<div class="product-thumb">
								<a href="#" class="link-to-product"> <img
									src="assets/images/products/p-18.jpg" alt="dd" width="270"
									height="270" class="product-thumnail">
								</a>
							</div>
							<div class="info">
								<b class="categories">Fresh Fruit</b>
								<h4 class="product-title">
									<a href="#" class="pr-name">National Fresh Fruit</a>
								</h4>
								<div class="price">
									<ins>
										<span class="price-amount"><span class="currencySymbol">£</span>85.00</span>
									</ins>
									<del>
										<span class="price-amount"><span class="currencySymbol">£</span>95.00</span>
									</del>
								</div>
								<div class="slide-down-box">
									<p class="message">All products are carefully selected to
										ensure food safety.</p>
									<div class="buttons">
										<a href="#" class="btn wishlist-btn"><i
											class="fa fa-heart" aria-hidden="true"></i></a> <a href="#"
											class="btn add-to-cart-btn"><i
											class="fa fa-cart-arrow-down" aria-hidden="true"></i>add to
											cart</a> <a href="#" class="btn compare-btn"><i
											class="fa fa-random" aria-hidden="true"></i></a>
									</div>
								</div>
							</div>
						</div>
					</li>
				</ul>
			</div>

		</div>
	</div>
</div>