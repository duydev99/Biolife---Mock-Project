<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!--Hero Section-->
<div class="hero-section hero-background">
	<h1 class="page-title">Thông Tin Người Dùng</h1>
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
					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
						<h3 class="box-title">Thông tin <a href="${path}/user-update-info" class="btn btn-info"
								style="background-color: #e73918; border-color: #e73918">Cập
								nhật</a> <a href="${path}/user-change-password" class="btn btn-info"
								style="background-color: #e73918; border-color: #e73918">Đổi
								mật khẩu</a> <a href="${path}/client-logout" class="btn btn-info">Đăng
								xuất</a></h3>
						<div style="margin-left: 30px;">
							<p class="form-row">
								<label>Họ Tên: ${user.name }</label>
							</p>
							<p class="form-row">
								<label>Email: ${user.email}</label>
							</p>
							<p class="form-row">
								<label>Tài Khoản: ${user.username}</label>
							</p>
							<p class="form-row">
								<label>Số Điện Thoại: ${user.phone}</label>
							</p>
							<p class="form-row">
								<label>Địa Chỉ: ${user.address}</label>
							</p>
						</div>
					</div>
					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
						<h3 class="box-title">Danh sách sản phẩm trong đơn hàng</h3>
						<form class="shopping-cart-form" action="#" method="post">
							<table class="shop_table cart-form">
								<thead>
									<tr>
										<th class="">#</th>
										<th class="">Sản phẩm</th>
										<th class="">Đơn giá</th>
										<th class="">Số lượng</th>
										<th class="">Thành tiền</th>
										<th>Thời gian</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="item" items="${orders}">
										<tr>
											<td>${item.id }</td>
											<td>${item.tblProduct.name}</td>
											<td><fmt:formatNumber type="number" groupingUsed="true"
													value="${item.price}" /><span class="currencySymbol">
													<u>đ</u></td>
											<td>${item.amount }</td>
											<td><fmt:formatNumber type="number" groupingUsed="true"
													value="${item.price * item.amount}" /><span
												class="currencySymbol"> <u>đ</u></td>
											<td>${item.updateTime}</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>