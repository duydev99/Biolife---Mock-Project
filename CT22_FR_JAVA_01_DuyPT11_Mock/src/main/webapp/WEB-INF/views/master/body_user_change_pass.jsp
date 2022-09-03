<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="container-fluid py-4">
	<div class="row">
		<div class="col-12">
			<div class="card mb-4">
				<div class="card-header pb-0">
					<h6>
						<a href="${path}/users/index?page=1"
							class="text-infor font-weight-bold text-xs" data-toggle="tooltip"
							data-original-title="Edit user"><img
						alt="Trở lại" src="../images/icon/icon-back.png" width="25px"
						height="25px"></a>
					</h6>

				</div>
				<div class="card-body ">
					<div class="p-0 align-items-center mb-0">
						<form action="${path}/users/postChange" method="POST"
							id="frmUser" role="form">
							<label>Mật khẩu</label>
							<div class="mb-3">
								<input type="hidden" name="id" value="${id}"/>
								<input id="txtPassword" type="password" name="pass"
									class="form-control" placeholder="Mật khẩu"
									aria-label="password" aria-describedby="password-addon" />
							</div>
							<div class="text-end">
								<button type="button" id="btnSubmit"
									class="btn bg-gradient-info w-15 mt-4 mb-0">Thay đổi</button>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script src="../js-form/user-change-pass.js"></script>