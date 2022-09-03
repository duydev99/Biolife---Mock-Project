<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div class="page-contain">
	<!-- Main content -->
	<div id="main-content" class="main-content">
		<!--Block 01: Main slide-->
		<div class="main-slide block-slider">
			<ul class="biolife-carousel nav-none-on-mobile"
				data-slick='{"arrows": true, "dots": false, "slidesMargin": 0, "slidesToShow": 1, "infinite": true, "speed": 800}'>
				<li>
					<div class="slide-contain slider-opt03__layout01">
						<div class="media">
							<div class="child-elememt">
								<a href="#" class="link-to"> <img
									src="assets/images/home-03/slide-01-child-01.png" width="604"
									height="580" alt="">
								</a>
							</div>
						</div>
						<div class="text-content">
							<i class="first-line">Biolife</i>
							<h3 class="second-line">100% Từ Tự Nhiên</h3>
							<p class="third-line">Nước Ép Trái Cây Tươi Mát</p>
							<p class="buttons">
								<a href="${path}/shop?filter=all&orderby=default&key=-1&page=1"
									class="btn btn-bold">Mua Ngay</a>
							</p>
						</div>
					</div>
				</li>
				<li>
					<div class="slide-contain slider-opt03__layout01">
						<div class="media">
							<div class="child-elememt">
								<a href="#" class="link-to"><img
									src="assets/images/home-03/slide-01-child-01.png" width="604"
									height="580" alt=""></a>
							</div>
						</div>
						<div class="text-content">
							<i class="first-line">Biolife</i>
							<h3 class="second-line">100% Từ Tự Nhiên</h3>
							<p class="third-line">Nước Ép Trái Cây Tươi Mát</p>
							<p class="buttons">
								<a href="${path}/shop?filter=all&orderby=default&key=-1&page=1"
									class="btn btn-bold">Mua Ngay</a>
							</p>
						</div>
					</div>
				</li>
			</ul>
		</div>

		<!--Block 02: Banner-->
		<div class="special-slide">
			<div class="container">
				<ul class="biolife-carousel dots_ring_style"
					data-slick='{"arrows": false, "dots": true, "slidesMargin": 30, "slidesToShow": 1, "infinite": true, "speed": 800, "responsive":[{"breakpoint":1200, "settings":{ "slidesToShow": 1}},{"breakpoint":768, "settings":{ "slidesToShow": 2, "slidesMargin":20, "dots": false}},{"breakpoint":480, "settings":{ "slidesToShow": 1}}]}'>
					<li>
						<div class="slide-contain biolife-banner__special">
							<div class="banner-contain">
								<div class="media">
									<a href="#" class="bn-link">
										<figure>
											<img src="assets/images/home-03/bn_special_org.jpg"
												width="616" height="483" alt="">
										</figure>
									</a>
								</div>
								<div class="text-content">
									<b class="first-line">An Toàn Thực Phẩm</b> <span
										class="second-line"></span> <span class="third-line"><i>Bảo
											Vệ Sức Khỏe Cho Bạn</i></span>
									<div class="product-detail">
										<div class="buttons">
											<a
												href="${path}/shop?filter=all&orderby=default&key=-1&page=1"
												class="btn add-to-cart-btn">Mua Ngay</a>
										</div>
									</div>
								</div>
							</div>
						</div>
					</li>
					<li>
						<div class="slide-contain biolife-banner__special">
							<div class="banner-contain">
								<div class="media">
									<a href="#" class="bn-link">
										<figure>
											<img src="assets/images/home-03/bn_special_org.jpg"
												width="616" height="483" alt="">
										</figure>
									</a>
								</div>
								<div class="text-content">
									<b class="first-line">An Toàn Thực Phẩm</b> <span
										class="second-line"></span> <span class="third-line"><i>Bảo
											Vệ Sức Khỏe Cho Bạn</i></span>
									<div class="product-detail">
										<div class="buttons">
											<a
												href="${path}/shop?filter=all&orderby=default&key=-1&page=1"
												class="btn add-to-cart-btn">Mua Ngay</a>
										</div>
									</div>
								</div>
							</div>
						</div>
					</li>
					<li>
						<div class="slide-contain biolife-banner__special">
							<div class="banner-contain">
								<div class="media">
									<a href="#" class="bn-link">
										<figure>
											<img src="assets/images/home-03/bn_special_org.jpg"
												width="616" height="483" alt="">
										</figure>
									</a>
								</div>
								<div class="text-content">
									<b class="first-line">An Toàn Thực Phẩm</b> <span
										class="second-line"></span> <span class="third-line"><i>Bảo
											Vệ Sức Khỏe Cho Bạn</i></span>
									<div class="product-detail">
										<div class="buttons">
											<a
												href="${path}/shop?filter=all&orderby=default&key=-1&page=1"
												class="btn add-to-cart-btn">Mua Ngay</a>
										</div>
									</div>
								</div>
							</div>
						</div>
					</li>
					<li>
						<div class="slide-contain biolife-banner__special">
							<div class="banner-contain">
								<div class="media">
									<a href="#" class="bn-link">
										<figure>
											<img src="assets/images/home-03/bn_special_org.jpg"
												width="616" height="483" alt="">
										</figure>
									</a>
								</div>
								<div class="text-content">
									<b class="first-line">An Toàn Thực Phẩm</b> <span
										class="second-line"></span> <span class="third-line"><i>Bảo
											Vệ Sức Khỏe Cho Bạn</i></span>
									<div class="product-detail">
										<div class="buttons">
											<a
												href="${path}/shop?filter=all&orderby=default&key=-1&page=1"
												class="btn add-to-cart-btn">Mua Ngay</a>
										</div>
									</div>
								</div>
							</div>
						</div>
					</li>
				</ul>
				<div
					class="biolife-service type01 biolife-service__type01 sm-margin-top-0 xs-margin-top-45px">
					<b class="txt-show-01">100%Nature</b> <i class="txt-show-02">Trái
						Cây Tươi</i>
					<ul class="services-list">
						<li>
							<div class="service-inner">
								<span class="number">1</span> <span
									class="biolife-icon icon-beer"></span> <a class="srv-name"
									href="#">Sản Phẩm Xuất Xứ Rõ Ràng</a>
							</div>
						</li>
						<li>
							<div class="service-inner">
								<span class="number">2</span> <span
									class="biolife-icon icon-schedule"></span> <a class="srv-name"
									href="#">Giao Hàng Tận Nơi Và Đúng Thời Gian</a>
							</div>
						</li>
						<li>
							<div class="service-inner">
								<span class="number">3</span> <span
									class="biolife-icon icon-car"></span> <a class="srv-name"
									href="#">Miễn Phí Ship Trong Nội Ô Thành Phố</a>
							</div>
						</li>
					</ul>
				</div>
			</div>
		</div>

		<!--Block 03: Product Tab-->
		<div
			class="product-tab z-index-20 sm-margin-top-193px xs-margin-top-30px">
			<div class="container">
				<div class="biolife-title-box">
					<span class="subtitle">Tất cả sản phẩm tốt nhất cho bạn</span>
					<h3 class="main-title">Sản Phẩm Liên Quan</h3>
				</div>
				<div class="biolife-tab biolife-tab-contain sm-margin-top-34px">
					<div class="tab-head tab-head__icon-top-layout icon-top-layout">
						<ul class="tabs md-margin-bottom-35-im xs-margin-bottom-40-im">
							<li class="tab-element active"><a href="#tab01_1st"
								class="tab-link">Mới Nhất</a></li>
						</ul>
					</div>
					<div class="tab-content">
						<div id="tab01_1st" class="tab-contain active">
							<ul
								class="products-list biolife-carousel nav-center-02 nav-none-on-mobile eq-height-contain"
								data-slick='{"rows":2 ,"arrows":true,"dots":false,"infinite":true,"speed":400,"slidesMargin":10,"slidesToShow":4, "responsive":[{"breakpoint":1200, "settings":{ "slidesToShow": 4}},{"breakpoint":992, "settings":{ "slidesToShow": 3, "slidesMargin":25 }},{"breakpoint":768, "settings":{ "slidesToShow": 2, "slidesMargin":15}}]}'>
								<c:forEach var="item" items="${newProducts}">
									<li class="product-item">
										<div class="contain-product layout-default">
											<div class="product-thumb">
												<a href="${path}/product-detail?id=${item.id}"
													class="link-to-product"> <img
													src="../images/${item.tblImageProducts[0].source}"
													style="width: 100%; height: 220px" class="product-thumnail">
												</a>
											</div>
											<div class="info">
												<b class="categories">${item.tblCategory.name}</b>
												<h4 class="product-title">
													<a href="${path}/product-detail?id=${item.id}"
														class="pr-name">${item.name }</a>
												</h4>
												<div class="price ">
													<ins>
														<span class="price-amount"><fmt:formatNumber
																type="number" groupingUsed="true" value="${item.price}" />
															<u>đ</u> </span>
													</ins>
												</div>
												<div class="slide-down-box">
													<p class="message">Tất cả các sản phẩm đều được lựa
														chọn kỹ lưỡng để đảm bảo an toàn vệ sinh thực phẩm.</p>
													<div class="buttons text-center">
														<!-- <a href="#" class="btn wishlist-btn"><i
														class="fa fa-heart" aria-hidden="true"></i></a> -->
														<button type="button" class="btn add-to-cart-btn"
															onClick="clickAddToCart(${item.id});">
															<i class="fa fa-cart-arrow-down" aria-hidden="true"></i>Thêm
															Vào Giỏ Hàng
														</button>
														<!-- <a href="#" class="btn compare-btn"><i
														class="fa fa-random" aria-hidden="true"></i></a> -->
													</div>
												</div>
											</div>
										</div>
									</li>
								</c:forEach>
							</ul>
						</div>
					</div>
				</div>
			</div>

			
			<!--Block 06: Products-->
			<div class="Product-box sm-margin-top-96px xs-margin-top-0">
				<div class="container">
					<div class="row">
						<div class="col-lg-12 col-md-12 col-sm-12">
							<div class="advance-product-box">
								<div
									class="biolife-title-box bold-style biolife-title-box__bold-style">
									<h3 class="title">Sản Phẩm Tốt Nhất</h3>
								</div>
								<ul
									class="products biolife-carousel eq-height-contain nav-center-03 nav-none-on-mobile row-space-29px"
									data-slick='{"rows":2,"arrows":true,"dots":false,"infinite":false,"speed":400,"slidesMargin":30,"slidesToShow":2,"responsive":[{"breakpoint":1200,"settings":{ "rows":2, "slidesToShow": 2}},{"breakpoint":992, "settings":{ "rows":2, "slidesToShow": 1}},{"breakpoint":768, "settings":{ "rows":2, "slidesToShow": 2}},{"breakpoint":500, "settings":{ "rows":2, "slidesToShow": 1}}]}'>
									<c:forEach var="item" items="${listMap}">
										<li class="product-item">
											<div
												class="contain-product right-info-layout contain-product__right-info-layout">
												<div class="product-thumb">
													<a href="${path}/product-detail?id=${item.key.id}" class="link-to-product"> <img
														src="../images/${item.key.tblImageProducts[0].source }" alt="dd" width="270"
														height="270" class="product-thumnail">
													</a>
												</div>
												<div class="info">
													<b class="categories">${item.key.tblCategory.name}</b>
													<h4 class="product-title">
														<a href="${path}/product-detail?id=${item.key.id}" class="pr-name">${item.key.name}</a>
													</h4>
													<div class="price ">
														<ins>
														<span class="price-amount"><fmt:formatNumber
																type="number" groupingUsed="true" value="${item.key.price}" />
															<u>đ</u> </span>
													</ins>
													</div>
													<div class="rating">
														<p class="star-rating">
															<span class="width-${item.value*20}percent"></span>
														</p>
													</div>
												</div>
											</div>
										</li>
									</c:forEach>

								</ul>
								<div
									class="biolife-banner style-01 biolife-banner__style-01 xs-margin-top-80px-im sm-margin-top-30px-im">
									<div class="banner-contain">
										<a
											href="${path}/shop?filter=all&orderby=default&key=-1&page=1"
											class="bn-link"></a>
										<div class="text-content">
											<span class="first-line">Daily Fresh</span> <b
												class="second-line">Natural</b> <i class="third-line">Fresh
												Food</i> <span class="fourth-line">Premium Quality</span>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>