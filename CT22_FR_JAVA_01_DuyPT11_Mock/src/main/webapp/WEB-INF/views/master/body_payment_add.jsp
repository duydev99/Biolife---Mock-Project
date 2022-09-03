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
						<a href="${path}/payments?page=1"
							class="text-infor font-weight-bold text-xs" data-toggle="tooltip"
							data-original-title="Edit user"><img
						alt="Trở lại" src="../images/icon/icon-back.png" width="25px"
						height="25px"></a>
					</h6>

				</div>
				<div class="card-body ">
					<div class="p-0 align-items-center mb-0">
						<form:form action="${path}/payments/postAdd" method="POST"
							modelAttribute="paymentAdd" id="frmPayment" role="form">
							<label>Tên hình thức thanh toán</label>
							<div class="mb-3">
								<form:input path="method" id="txtName" type="text"
									class="form-control" placeholder="Hình thức thanh toán" aria-label="Name"
									aria-describedby="Name-addon" />
								<div class="font-monospace text-danger m-2"
									style="width: 18rem;">${mess }</div>
							</div>
							<div class="text-end">
								<form:button type="button" id="btnSubmit"
									class="btn bg-gradient-info w-15 mt-4 mb-0">Thêm</form:button>
							</div>
						</form:form>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script src="../js-form/payment.js"></script>