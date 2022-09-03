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
								mật khẩu</a> <a href="${path}/client-logout" class="btn btn-info">Đăng
								xuất</a>
						</h3>
						<div style="margin-left: 30px;">
							<form:form action="${path}/save-edit-user-client" method="post" id="frmUserClientUpdate"
								modelAttribute="user">
								<p class="form-row">
									<label>Họ tên</label>
									<form:input path="name" type="text" class="form-control"
										id="txtName" placeholder="Nhập họ tên" />
										<form:input path="username" type="hidden"/>
										<form:input path="password" type="hidden"/>
										<form:input path="id" type="hidden"/>
										<form:input path="status" type="hidden"/>
										<form:input path="type" type="hidden"/>
								</p>
								<p class="form-row">
									<label>Email</label>
									<form:input path="email" type="email" class="form-control"
										id="txtEmail" placeholder="Nhập email" />
								</p>
								<p class="form-row">
									<label>Số điện thoại</label>
									<form:input path="phone" type="text" class="form-control"
										id="txtPhone"
										placeholder="Nhập số điện thoại" />
								</p>
								<p class="form-row">
									<label>Địa chỉ</label>
									<form:textarea path="address" id="txtAddress" rows="5"
										class="form-control" placeholder="Nhập địa chỉ" />
								</p>
								<p class="form-row">
									<form:button class="btn btn-bold" type="button" id="btnSubmit">Xác nhận</form:button>
								</p>
							</form:form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script src="../js-form/user-edit.js"></script>