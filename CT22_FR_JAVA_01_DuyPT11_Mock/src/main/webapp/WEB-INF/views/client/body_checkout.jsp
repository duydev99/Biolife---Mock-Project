<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!--Hero Section-->
<div class="hero-section hero-background">
	<h1 class="page-title">Thanh Toán</h1>
</div>

<!--Navigation section-->
<div class="container">
	<nav class="biolife-nav">
		<span class="current-page">${url}</span>
	</nav>
</div>

<div class="page-contain checkout">

	<!-- Main content -->
	<div id="main-content" class="main-content">
		<div class="container sm-margin-top-37px">
			<div class="row">

				<!--checkout progress box-->
				<div class="col-lg-7 col-md-7 col-sm-6 col-xs-12">
					<div class="checkout-progress-wrap">
						<div class="top-functions-area">
							<div class="flt-item">
								<div class="checkout-act active">
									<h3 class="title-box">Thông Tin Thanh Toán</h3>
									<div class="box-content">
										<p class="txt-desc">Nhập đầy đủ thông tin để tiến hành
											thanh toán</p>
										<div class="login-on-checkout">
											<form action="${path}/save-check-out" id="frmCheckOut" method="post">
												<input type="hidden" value="${total}" id="total"
													name="total" readonly>
												<p class="form-row">
												<div class="col-12">
													<label for="">Số Điện Thoại</label> <input
														class="form-control" type="text" name="phone"
														id="txtPhone" placeholder="Nhập số điện thoại">
												</div>
												</p>
												<p class="form-row">
												<div>
													<label for="">Địa Chỉ</label>
													<textarea rows="5" placeholder="Nhập địa chỉ"
														id="txtAddress" name="address" class="form-control"></textarea>
												</div>
												</p>
												<p class="form-row">
													<lable>Hình Thức Thanh Toán</lable>
												<div class="wrap-selectors">
													<div class="selector-item">
														<select id="selPayments" name="payment">
															<option value="-1">--Choose--</option>
															<c:forEach var="item" items="${payments}">
																<option value="${item.id}">${item.method }</option>
															</c:forEach>
														</select>
													</div>
												</div>
												</p>
												<p class="form-row">
													<button class="btn btn-submit btn-bold" id="btnCheckOut" onClick="clickCheckForm();"
														type="button">Xác Nhận</button>
												</p>
											</form>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>

				<!--Order Summary-->
				<div
					class="col-lg-5 col-md-5 col-sm-6 col-xs-12 sm-padding-top-48px sm-margin-bottom-0 xs-margin-bottom-15px">
					<div class="order-summary sm-margin-bottom-80px">
						<div class="title-block">
							<h3>Hóa Đơn</h3>
							<a href="#" class="link-forward">Trở lại giỏ hàng</a>
						</div>
						<div class="cart-list-box short-type">
							<span class="number">${numberProducts} sản phẩm</span>
							<ul class="cart-list">
								<c:forEach var="item" items="${list}">
									<li class="cart-elem">
										<div class="cart-item">
											<div class="product-thumb">
												<a class="prd-thumb"
													href="${path}/product-detail?id=${item.id}">
													<figure>
														<img src="../images/${item.image}" width="113"
															height="113" alt="shop-cart">
													</figure>
												</a>
											</div>
											<div class="info">
												<span class="txt-quantity">${item.amount}X</span> <a
													href="${path}/product-detail?id=${item.id}" class="pr-name">${item.product.name }</a>
											</div>
											<div class="price price-contain">
												<ins>
													<span class="price-amount"><fmt:formatNumber
															type="number" groupingUsed="true"
															value="${item.product.price}" /> <u>đ</u></span>
												</ins>
											</div>
										</div>
									</li>
								</c:forEach>
							</ul>
							<ul class="subtotal">
								<li>
									<div class="subtotal-line">
										<b class="stt-name">Tổng:</b> <span class="stt-price"><fmt:formatNumber
												type="number" groupingUsed="true" value="${total}" /> <u>đ</u></span>
									</div>
								</li>

							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<script src="../js-form/checkout.js"></script>