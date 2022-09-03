<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="apple-touch-icon" sizes="76x76"
	href="../admin/assets/img/apple-icon.png">
<link rel="shortcut icon" type="image/x-icon"
	href="../assets/images/favicon.png" />
<title>Biolife master - Đăng nhập</title>
<!--     Fonts and icons     -->
<link
	href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700"
	rel="stylesheet" />
<!-- Nucleo Icons -->
<link href="../admin/assets/css/nucleo-icons.css" rel="stylesheet" />
<link href="../admin/assets/css/nucleo-svg.css" rel="stylesheet" />
<!-- Font Awesome Icons -->
<script src="https://kit.fontawesome.com/42d5adcbca.js"
	crossorigin="anonymous"></script>
<link href="../admin/assets/css/nucleo-svg.css" rel="stylesheet" />
<!-- CSS Files -->
<link id="pagestyle"
	href="../admin/assets/css/soft-ui-dashboard.css?v=1.0.6"
	rel="stylesheet" />
</head>

<body class="">
	<div class="container position-sticky z-index-sticky top-0">
		<div class="row">
			<div class="col-12">
				<!-- Navbar -->
				<nav
					class="navbar navbar-expand-lg blur blur-rounded top-0 z-index-3 shadow position-absolute my-3 py-2 start-0 end-0 mx-4">
					<div class="container-fluid pe-0">
						<a class="navbar-brand font-weight-bolder ms-lg-0 ms-3 " href="${path}/master_login">
							<img src="../assets/images/organic-3.png" alt="biolife logo"
							width="135" height="34">
						</a>
					</div>
				</nav>
				<!-- End Navbar -->
			</div>
		</div>
	</div>
	<main class="main-content  mt-0">
		<section>
			<div class="page-header min-vh-75">
				<div class="container">
					<div class="row">
						<div class="col-xl-4 col-lg-5 col-md-6 d-flex flex-column mx-auto">
							<div class="card card-plain mt-8">
								<div class="card-header pb-0 text-left bg-transparent">
									<h3 class="font-weight-bolder text-info text-gradient">Welcome
										back</h3>
									<p class="mb-0">Hãy nhập tài khoản và mật khẩu của bạn để
										đăng nhập</p>
								</div>

								<div class="card-body">
									<form:form action="${path}/master_login/save" method="POST"
										modelAttribute="masterLogin" id="frmLogin" role="form">
										<label>Tài khoản</label>
										<div class="mb-3">
											<form:input path="username" id="txtUsername" type="text"
												class="form-control" placeholder="Username"
												aria-label="Username" aria-describedby="username-addon" />
											<div class="font-monospace text-danger m-2" style="width: 18rem;">${messUsername }</div>
										</div>

										<label>Mật khẩu</label>
										<div class="mb-3">
											<form:input path="password" type="password" id="txtPassword"
												class="form-control" placeholder="Password"
												aria-label="Password" aria-describedby="password-addon" />
											<div class="font-monospace text-danger m-2" style="width: 18rem;">${messPass }</div>
										</div>

										<div class="form-check form-switch">
											<input class="form-check-input" type="checkbox"
												id="rememberMe" checked=""> <label
												class="form-check-label" for="rememberMe">Remember
												me</label>
										</div>
										<div class="text-center">
											<form:button type="button" id="btnSubmit"
												class="btn bg-gradient-info w-100 mt-4 mb-0">Đăng nhập</form:button>
										</div>
									</form:form>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div
								class="oblique position-absolute top-0 h-100 d-md-block d-none me-n8">
								<div
									class="oblique-image bg-cover position-absolute fixed-top ms-auto h-100 z-index-0 ms-n6"
									style="background-image: url('../admin/assets/img/curved-images/curved6.jpg')"></div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</section>
	</main>
	<!-- -------- START FOOTER 3 w/ COMPANY DESCRIPTION WITH LINKS & SOCIAL ICONS & COPYRIGHT ------- -->
	<footer class="footer py-5">
		<div class="container">
			<div class="row">
				<div class="col-lg-8 mb-4 mx-auto text-center">MOCK PROJECT</div>
				<div class="col-lg-8 mx-auto text-center mb-4 mt-2">PHẠM THANH
					DUY - DUYPT11</div>
				<div class="col-lg-8 mx-auto text-center mb-4 mt-2">WEB & GAME
					DEVELOPER</div>
			</div>
			<div class="row">
				<div class="col-8 mx-auto text-center mt-1">
					<p class="mb-0 text-secondary">
						Copyright ©
						<script>
							document.write(new Date().getFullYear())
						</script>
						by Biolife.
					</p>
				</div>
			</div>
		</div>
	</footer>
	<!-- -------- END FOOTER 3 w/ COMPANY DESCRIPTION WITH LINKS & SOCIAL ICONS & COPYRIGHT ------- -->
	<!--   Core JS Files   -->
	<script src="../admin/assets/js/core/popper.min.js"></script>
	<script src="../admin/assets/js/core/bootstrap.min.js"></script>
	<script src="../admin/assets/js/plugins/perfect-scrollbar.min.js"></script>
	<script src="../admin/assets/js/plugins/smooth-scrollbar.min.js"></script>
	<script>
		var win = navigator.platform.indexOf('Win') > -1;
		if (win && document.querySelector('#sidenav-scrollbar')) {
			var options = {
				damping : '0.5'
			}
			Scrollbar.init(document.querySelector('#sidenav-scrollbar'),
					options);
		}
	</script>
	<script src="../admin/assets/js/soft-ui-dashboard.min.js?v=1.0.6"></script>
	<script src="../js-form/login.js"></script>
</body>

</html>