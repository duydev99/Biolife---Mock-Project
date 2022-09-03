<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!--Hero Section-->
<div class="hero-section hero-background">
	<h1 class="page-title">Cửa Hàng Biolife</h1>
</div>

<!--Navigation section-->
<div class="container">
	<nav class="biolife-nav">
		<span class="current-page">${url}</span>
	</nav>
</div>

<div class="page-contain category-page no-sidebar">
	<div class="container">
		<div class="row">

			<!-- Main content -->
			<div id="main-content"
				class="main-content col-lg-12 col-md-12 col-sm-12 col-xs-12">

				<div class="product-category grid-style">

					<div id="top-functions-area" class="top-functions-area">
						<div class="flt-item to-left group-on-mobile">
							<span class="flt-title">Lọc</span> <a href="#"
								class="icon-for-mobile"> <span></span> <span></span> <span></span>
							</a>
							<div class="wrap-selectors">
								<span class="title-for-mobile">Lọc Sản Phẩm</span>
								<div data-title="Giá:" class="selector-item">
									<a id="frmPrice"> <select class="selector"
										onChange="price(this,'${pathCurrentFilter}');" name="code">
											<option value="-1">Giá</option>
											<option value="1">Dưới 50,000</option>
											<option value="2">50,000-100,000</option>
											<option value="3">100,000-250,000</option>
											<option value="4">250,000-500,000</option>
											<option value="5">500,000-1,000,000</option>
											<option value="6">1,000,000-2,000,000</option>
											<option value="7">Trên 2,000,000</option>
									</select>
									</a>
								</div>

								<div data-title="Loại sản phẩm:" class="selector-item">
									<a id="frmCategory"> <select class="selector"
										onChange="category(this,'${pathCurrentFilter}');" name="id">
											<option value="-1">Loại sản phẩm</option>
											<c:forEach items="${categories}" var="item">
												<option value="${item.id}">${item.name}</option>
											</c:forEach>
									</select>
									</a>
								</div>

								<div data-title="Nhà cung cấp:" class="selector-item">
									<a id="frmManufacturer"> <select name="id" class="selector"
										onChange="manufacturer(this,'${pathCurrentFilter}');">
											<option value="-1">Nhà cung cấp</option>
											<c:forEach items="${manufacturers}" var="item">
												<option value="${item.id}">${item.name}</option>
											</c:forEach>
									</select>
									</a>
								</div>
								<div data-title="Tất cả:" class="selector-item">
									<a href="${pathCurrentAll }" class="btn btn-info" style="background-color:#e73918;border-color:#e73918">Tất cả</a>
								</div>
							</div>
						</div>
						<div class="flt-item to-right">
							<span class="flt-title">Hiển thị Theo</span>
							<div class="wrap-selectors">
								<div class="selector-item orderby-selector">
									<a id="frmOrderBy"> <select name="orderby" class="orderby"
										aria-label="Shop order"
										onChange="orderBy(this,'${pathCurrentOrderBy}');">
											<option value="default" selected="selected">Mặc định</option>
											<option value="name">Tên sản phẩm</option>
											<option value="time">Thời gian</option>
											<option value="price-asc">Giá: thấp-cao</option>
											<option value="price-desc">Giá: cao-thấp</option>
									</select>
									</a>
								</div>
								<div class="selector-item viewmode-selector">
									<a href="${path}/shop-gid-3" class="viewmode grid-mode active"><i
										class="biolife-icon icon-grid"></i></a>
									<%-- <a
										href="${path}/shop-gid-6"
										class="viewmode detail-mode"><i
										class="biolife-icon icon-list"></i></a> --%>
								</div>
							</div>
						</div>
					</div>

					<div class="row">
						<ul class="products-list">
							<c:forEach var="item" items="${list}">
								<li class="product-item col-lg-4 col-md-4 col-sm-6 col-xs-12">
									<div class="contain-product layout-default">
										<div class="product-thumb">
											<a href="${path}/product-detail?id=${item.id}"
												class="link-to-product"> <img
												src="../images/${item.tblImageProducts[0].source}" alt="dd"
												style="width:100%; height:270px; text-align:center" class="product-thumnail">
											</a>
										</div>
										<div class="info">
											<b class="categories">${item.tblCategory.name}</b>
											<h4 class="product-title">
												<a href="${path}/product-detail?id=${item.id}"
													class="pr-name">${item.name }</a>
											</h4>
											<div class="price">
												<ins>
													<span class="price-amount"><fmt:formatNumber
															type="number" groupingUsed="true" value="${item.price}" /> <u>đ</u>
													</span>
												</ins>
											</div>
											<div class="shipping-info">
												<p class="shipping-day">Nhận Hàng Sau 3 Ngày</p>
												<p class="for-today">Mua Ngay Hôm Nay</p>
											</div>
											<div class="slide-down-box">
												<p class="message">Tất cả các sản phẩm đều được lựa chọn
													kỹ lưỡng để đảm bảo an toàn vệ sinh thực phẩm.</p>
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

					<div class="biolife-panigations-block">
						<ul class="panigation-contain">
							${pages}
							<!-- <li><span class="current-page">1</span></li>
							<li><a href="#" class="link-page">2</a></li>
							<li><a href="#" class="link-page">3</a></li>
							<li><span class="sep">....</span></li>
							<li><a href="#" class="link-page">20</a></li>
							<li><a href="#" class="link-page next"><i
									class="fa fa-angle-right" aria-hidden="true"></i></a></li> -->
						</ul>
					</div>

				</div>

			</div>
		</div>
	</div>
</div>
<script src="js-form/shop.js"></script>