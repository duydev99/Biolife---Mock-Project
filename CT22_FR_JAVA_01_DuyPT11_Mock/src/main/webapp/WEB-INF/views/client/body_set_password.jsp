<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!--Hero Section-->
<div class="hero-section hero-background">
	<h1 class="page-title">Đặt Lại Mật Khẩu</h1>
</div>

<!--Navigation section-->
<div class="container">
	<nav class="biolife-nav">
		<span class="current-page">${url}</span>
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
						<span>${message}</span>
						<form action="/user/fogot_password/set_password/submit?token=${token}" method="post" id="frmChangePassword">
							<p class="form-row">
								<label for="fid-name">Mật khẩu mới:<span class="requite text-danger"> (*)</span></label>
								<input type="password" id="txtPassNew" name="newPassword" class="txt-input" />
							</p>
							<p class="form-row">
								<label for="fid-name">Xác nhận mật khẩu:<span class="requite text-danger"> (*)</span></label>
								<input type="password" id="txtPassConfirm" name="confirmPassword" class="txt-input" />
							</p>
							<p class="form-row wrap-btn">
								<button class="btn btn-submit btn-bold" type="button" onClick="changePassword();">Đặt Lại Mật Khẩu</button>
							</p>
						<form>
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
							<a href="${path }/register" class="btn btn-bold">Đăng ký</a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script src="/js-form/set-pass.js"></script>