<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div class="header-top bg-main hidden-xs">
	<div class="container">
		<div class="top-bar left">
			<ul class="horizontal-menu">
				<li><a href="#"><i class="fa fa-envelope"
						aria-hidden="true"></i>DuyPT11@fsoft.com.vn</a></li>
				<li><a href="#">Free Shipping cho tất cả các đơn hàng trên
						1,000,000 Đ</a></li>
			</ul>
		</div>
		<div class="top-bar right">
			<!-- <ul class="social-list">
				<li><a href="#"><i class="fa fa-twitter" aria-hidden="true"></i></a></li>
				<li><a href="#"><i class="fa fa-facebook"
						aria-hidden="true"></i></a></li>
				<li><a href="#"><i class="fa fa-pinterest"
						aria-hidden="true"></i></a></li>
			</ul> -->
			<ul class="horizontal-menu">
				<!-- <li class="horz-menu-item currency"><select name="currency">
						<option value="eur">€ EUR (Euro)</option>
						<option value="usd" selected>$ USD (Dollar)</option>
						<option value="usd">£ GBP (Pound)</option>
						<option value="usd">¥ JPY (Yen)</option>
				</select></li> -->
				<!-- <li class="horz-menu-item lang"><select name="language">
						<option value="fr">French (EUR)</option>
						<option value="en" selected>English (USD)</option>
						<option value="ger">Germany (GBP)</option>
						<option value="jp">Japan (JPY)</option>
				</select></li> -->
				<li>${changeHeader }</li>
			</ul>
		</div>
	</div>
</div>
<div class="header-middle biolife-sticky-object ">
	<div class="container">
		<div class="row">
			<div class="col-lg-3 col-md-2 col-md-6 col-xs-6">
				<a href="${path}/index" class="biolife-logo"><img
					src="/assets/images/organic-3.png" alt="biolife logo" width="135"
					height="34"></a>
			</div>
			<div class="col-lg-6 col-md-7 hidden-sm hidden-xs">
				<div class="primary-menu">
					<ul class="menu biolife-menu clone-main-menu clone-primary-menu"
						id="primary-menu" data-menuname="main menu">
						<li class="menu-item"><a href="${path}/index">Trang Chủ</a></li>
						<li class="menu-item"><a
							href="${path}/shop?filter=all&orderby=default&key=-1&page=1"
							class="menu-name" data-title="Shop">Cửa Hàng</a></li>
						<li class="menu-item menu-item-has-children has-child"><a
							href="#" class="menu-name" data-title="Manufacturers">Nhà
								Cung Cấp</a>
							<ul class="sub-menu">
								<c:forEach items="${manufacturers}" var="item">
									<li class="menu-item"><a
										href="${path}/shop-manufacturer?filter=all&orderby=default&key1=${item.id}&key2=-1&page=1">${item.name }</a></li>
								</c:forEach>
								</select>
							</ul></li>
						<li class="menu-item menu-item-has-children has-child"><a
							href="#" class="menu-name" data-title="Categories">Loại Sản
								Phẩm</a>
							<ul class="sub-menu">
								<c:forEach items="${categories}" var="item">
									<li class="menu-item"><a
										href="${path}/shop-category?filter=all&orderby=default&key1=${item.id}&key2=-1&page=1">${item.name }</a></li>
								</c:forEach>
								</select>
							</ul></li>
					</ul>
				</div>
			</div>
			<div class="col-lg-3 col-md-3 col-md-6 col-xs-6">
				<div class="biolife-cart-info">
					<div class="mobile-search">
						<a href="javascript:void(0)" class="open-searchbox"><i
							class="biolife-icon icon-search"></i></a>
						<div class="mobile-search-content">
							<a> <a href="#" class="btn-close"><span
									class="biolife-icon icon-close-menu"></span></a> <input type="text"
								name="s" class="input-text" value="" placeholder="Tìm kiếm...">
								<select name="category">
									<option value="-1" selected>Tìm Kiếm Theo</option>
									<option value="vegetables">Sản Phẩm</option>
									<option value="fresh_berries">Loại Sản Phẩm</option>
									<option value="ocean_foods">Nhà Cung Cấp</option>
							</select>
								<button type="submit" class="btn-submit">go</button>
							</a>
						</div>
					</div>
					<div class="minicart-block">
						<div class="minicart-contain">
							<a href="#" class="link-to"> <span class="icon-qty-combine">
									<i class="icon-cart-mini biolife-icon"></i> <span class="qty"
									id="cartQtyProducts"> </span>
							</span> <span class="title">Giỏ Hàng -</span> <span class="sub-total"
								id="cartTotalPrice"> </span>
							</a>
							<div class="cart-content">
								<div class="cart-inner" id="innerCart">
									<ul class="products" id="miniCart">
									</ul>
									<p class="btn-control">
										<a href="/cart-detail" class="btn view-cart">Chi tiết</a> <a
											href="#" class="btn">Thanh toán</a>
									</p>
								</div>
							</div>
						</div>
					</div>
					<div class="mobile-menu-toggle">
						<a class="btn-toggle" data-object="open-mobile-menu"
							href="javascript:void(0)"> <span></span> <span></span> <span></span>
						</a>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<div class="header-bottom hidden-sm hidden-xs">
	<div class="container">
		<div class="row">
			<div class="col-lg-3 col-md-4"></div>
			<div class="col-lg-9 col-md-8 padding-top-2px">
				<div class="header-search-bar layout-01">
					<a id="frmSearch"> <input type="text" name="text" id="text"
						class="input-text" placeholder="Tìm kiếm..."> <select
						name="search" id="type">
							<option value="-1" selected>Tìm Kiếm Theo</option>
							<option value="product">Sản Phẩm</option>
							<option value="category">Loại Sản Phẩm</option>
							<option value="manufacturer">Nhà Cung Cấp</option>
					</select>
						<button type="button" onClick="clickSearch();" class="btn-submit">
							<i class="biolife-icon icon-search"></i>
						</button>
					</a>
				</div>
				<div class="live-info">
					<p class="telephone">
						<i class="fa fa-phone" aria-hidden="true"></i><b
							class="phone-number">(+84) 123 123 123</b>
					</p>
					<p class="working-time">T2-T6: 8:30am-7:30pm; T7-CN:
						9:30am-4:30pm</p>
				</div>
			</div>
		</div>
	</div>
</div>