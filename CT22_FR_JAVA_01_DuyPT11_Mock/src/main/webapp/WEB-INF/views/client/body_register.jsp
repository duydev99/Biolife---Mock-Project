<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!--Hero Section-->
<div class="hero-section hero-background">
	<h1 class="page-title">Đăng Ký</h1>
</div>

<!--Navigation section-->
<div class="container">
	<nav class="biolife-nav">
		<span class="current-page">${url }</span>
	</nav>
</div>
<div class="page-contain">
	<!-- Main content -->
	<div id="main-content" class="main-content">
		<div class="container">
			<div class="row">

				<!--Form Sign In-->
				<div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">

					<div class="signin-container">
						<form:form action="${path}/register-save" name="frm-login"
							id="frmRegister" method="post" modelAttribute="userRegister">
							<p class="form-row">
								<label for="fid-name">Họ tên<span
									class="requite text-danger"> (*)</span></label>
								<form:input path="name" type="text" id="txtName" name="txtName"
									class="txt-input" />
							</p>
							<p class="form-row">
								<label for="fid-name">Tài khoản<span
									class="requite text-danger"> (*) ${messUsername}</span></label>
								<form:input path="username" type="text" id="txtUsername"
									name="txtUsername" class="txt-input" />
							</p>
							<p class="form-row">
								<label for="fid-name">Email<span
									class="requite text-danger"> ${messEmail}</span></label>
								<form:input path="email" type="email" id="txtEmail"
									 class="txt-input" />
							</p>
							<p class="form-row">
								<label for="fid-pass">Mật khẩu<span
									class="requite text-danger"> (*)</span></label>
								<form:input path="password" type="password" id="txtPassword"
									name="txtPassword" class="txt-input" />
							</p>
							<p class="form-row">
								<label for="fid-pass">Số điện thoại<span
									class="requite text-danger"> (*) ${messPhone}</span></label>
								<form:input path="phone" type="text" id="txtPhone"
									name="txtPhone" class="txt-input" />
							</p>
							<p class="form-row">
								<label for="fid-pass">Địa chỉ<span
									class="requite text-danger"></span></label>
								<form:textarea path="address" id="txtAddress" name="txtAddress"
									class="txt-input" rows="5" cols="100" />
							</p>
							<p class="form-row wrap-btn">
								<form:button class="btn btn-submit btn-bold" type="button"
									id="btnSubmit">Xác nhận</form:button>
							</p>
						</form:form>
					</div>
				</div>

				<!--Go to Register form-->
				<div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
					<div class="register-in-container">
						<div class="intro">
							<h4 class="box-title">Thành viên mới?</h4>
							<p class="sub-title">Tạo một tài khoản với chúng tôi và bạn
								sẽ có thể:</p>
							<ul class="lis">
								<li>Kiểm tra nhanh hơn</li>
								<li>Truy cập lịch sử đặt hàng của bạn</li>
								<li>Đặt hàng từ chúng tôi</li>
								<li>Bình luận và đánh giá sản phẩm</li>
								<li>Quản lý đơn hàng của bạn</li>
							</ul>
							<a href="${path }/login" class="btn btn-bold">Đăng nhập</a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<script src="js-form/register.js"></script>