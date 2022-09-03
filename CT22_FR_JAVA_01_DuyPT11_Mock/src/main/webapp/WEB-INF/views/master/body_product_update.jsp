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
						<a href="${path}/products?page=1"
							class="text-infor font-weight-bold text-xs" data-toggle="tooltip"
							data-original-title="Edit user"><img
						alt="Trở lại" src="../images/icon/icon-back.png" width="25px"
						height="25px"></a>
					</h6>

				</div>
				<div class="card-body ">
					<div class="p-0 align-items-center mb-0">
						<form:form action="${path}/products/postEdit" method="POST"
							modelAttribute="productEdit" id="frmProduct" role="form">
							<label>Mã</label>
							<div class="mb-3">
								<form:input path="id" id="txtId" type="text" readonly="true"
									class="form-control" placeholder="Mã sản phẩm"
									aria-label="Id" aria-describedby="Id-addon" />
							</div>
							<label>Tên sản phẩm</label>
							<div class="mb-3">
								<form:input path="name" id="txtName" type="text"
									class="form-control" placeholder="Sản phẩm" aria-label="Name"
									aria-describedby="Name-addon" />
								<div class="font-monospace text-danger m-2"
									style="width: 18rem;">${mess }</div>
							</div>
							<label>Giá (VNĐ)</label>
							<div class="mb-3">
								<form:input path="price" id="txtPrice" type="number" step="1000"
									min="0" class="form-control" placeholder="Giá sản phẩm"
									aria-label="Price" aria-describedby="Price-addon" />
							</div>
							<label>Số lượng</label>
							<div class="mb-3">
								<form:input path="amount" id="txtAmount" type="number" step="1"
									min="0" class="form-control" placeholder="Số lượng"
									aria-label="Amount" aria-describedby="Amount-addon" />
							</div>
							<label>Thông tin sản phẩm</label>
							<div class="mb-3">
								<form:textarea path="description" id="txtDescription" rows="5"
									class="form-control" placeholder="Thông tin sản phẩm"
									aria-label="Description" aria-describedby="Description-addon" />
							</div>
							<label>Loại sản phẩm</label>
							<div class="mb-3">
								<form:select path="tblCategory.id" id="txtCategory"
									class="form-control" aria-label="Category"
									aria-describedby="Category-addon">
									<form:option value="0">--Chọn loại sản phẩm--</form:option>
									<c:forEach items="${categories}" var="item">
										<form:option value="${item.id}">${item.name}</form:option>
									</c:forEach>
								</form:select>
							</div>
							<label>Nhà cung cấp</label>
							<div class="mb-3">
								<form:select path="tblManufacturer.id" id="txtManufacturer"
									class="form-control" aria-label="Manufacturer"
									aria-describedby="Manufacturer-addon">
									<form:option value="0">--Chọn nhà cung cấp--</form:option>
									<c:forEach items="${manufacturers}" var="item">
										<form:option value="${item.id}">${item.name}</form:option>
									</c:forEach>
								</form:select>
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
<script src="../js-form/product.js"></script>