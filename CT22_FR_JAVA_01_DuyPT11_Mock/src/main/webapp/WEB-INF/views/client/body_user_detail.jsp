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
						<h3 class="box-title">
							Thông tin <a href="${path}/user-update-info" class="btn btn-info"
								style="background-color: #e73918; border-color: #e73918">Cập
								nhật</a> <a href="${path}/user-change-password" class="btn btn-info"
								style="background-color: #e73918; border-color: #e73918">Đổi
								mật khẩu</a> <a href="${path}/security/logoff" class="btn btn-info">Đăng
								xuất</a>
						</h3>
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
						<h3 class="box-title">Danh sách đơn hàng</h3>
						<form class="shopping-cart-form" action="#" method="post">
							<table class="shop_table cart-form">
								<thead>
									<tr>
										<th class="">#</th>
										<th class="">Thời gian</th>
										<th class="product-name">Địa chỉ</th>
										<th class="">Số điện thoại</th>
										<th class="">Hình thức</th>
										<th class="">Tổng tiền</th>
										<th class="">Trạng thái</th>
										<th></th>
									</tr>
								</thead>
								<tbody id="userDetailBodyTable">
									<c:forEach var="item" items="${orders}">
										<tr>
											<td>${item.id }</td>
											<td>${item.createTime}</td>
											<td>${item.address }</td>
											<td>${item.phone }</td>
											<td>${item.tblPayment.method}</td>
											<td><fmt:formatNumber type="number" groupingUsed="true"
													value="${item.total}" /><span class="currencySymbol">
													<u>đ</u></td>
											<td><c:if test="${item.status == 0}">
													<a
														href="${path}/user-change-status-order?orderId=${item.id}"
														class="btn btn-warning">Đặt hàng</a>
												</c:if> <c:if test="${item.status == 3}">
													<span class="btn btn-danger">Đã hủy</span>
												</c:if>
												<c:if test="${item.status == 1}">
													<span class="btn btn-primary">Đang Giao</span>
												</c:if><c:if test="${item.status == 2}">
													<span class="btn btn-info">Đã Nhận</span>
												</c:if>
												</td>
											<td><a
												href="${path}/user-order-detail?orderId=${item.id}"
												class="btn btn-default">Chi tiết</a></td>
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