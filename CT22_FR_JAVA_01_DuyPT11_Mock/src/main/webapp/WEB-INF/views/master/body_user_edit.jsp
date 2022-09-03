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
						<form:form action="${path}/user/postEdit" method="POST"
							modelAttribute="userEdit" id="frmUser" role="form">
							<label>Tên người dùng</label>
							<div class="mb-3">
							<form:input path="id" type="hidden"/>
								<form:input path="name" id="txtName" type="text"
									class="form-control" placeholder="Họ tên" aria-label="Name"
									aria-describedby="Name-addon" />
							</div>
							<label>Tài khoản</label>
							<div class="mb-3">
								<form:input path="username" id="txtUsername" type="text"
									class="form-control" placeholder="Tài khoản"
									aria-label="Username" aria-describedby="Username-addon" />
								<div class="font-monospace text-danger m-2"
									style="width: 18rem;">${messUsername }</div>
							</div>
							<label>Email</label>
							<div class="mb-3">
								<form:input path="email" id="txtEmail" type="email"
									class="form-control" placeholder="Email" aria-label="Email"
									aria-describedby="Email-addon" />
								<div class="font-monospace text-danger m-2"
									style="width: 18rem;">${messEmail }</div>
							</div>
							<label>Số điện thoại</label>
							<div class="mb-3">
								<form:input path="phone" id="txtPhone" type="text"
									class="form-control" placeholder="Số điện thoại"
									aria-label="Phone" aria-describedby="Phone-addon" />
								<div class="font-monospace text-danger m-2"
									style="width: 18rem;">${messPhone }</div>
							</div>
							<label>Địa chỉ</label>
							<div class="mb-3">
								<form:textarea path="address" id="txtAddress" rows="5"
									class="form-control" placeholder="Địa chỉ" aria-label="Address"
									aria-describedby="Address-addon" />
							</div>
							<label>Loại người dùng</label>
							<div class="mb-3">
								<form:select path="type" id="txtType"
									class="form-control" aria-label="Type"
									aria-describedby="Type-addon">
									<form:option value="0">Khách hàng</form:option>
									<form:option value="1">Quản trị</form:option>
								</form:select>
							</div>
							<div class="text-end">
								<form:button type="button" id="btnSubmit"
									class="btn bg-gradient-info w-15 mt-4 mb-0">Cập nhật</form:button>
							</div>
						</form:form>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script src="../js-form/user-edit-form.js"></script>