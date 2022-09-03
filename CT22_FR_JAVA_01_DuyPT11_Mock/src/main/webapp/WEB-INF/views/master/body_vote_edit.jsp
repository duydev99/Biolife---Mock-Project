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
						<a href="${path}/vote/index?page=1"
							class="text-infor font-weight-bold text-xs" data-toggle="tooltip"
							data-original-title="Edit user"><img
						alt="Trở lại" src="../images/icon/icon-back.png" width="25px"
						height="25px"></a>
					</h6>

				</div>
				<div class="card-body ">
					<div class="p-0 align-items-center mb-0">
						<form:form action="${path}/vote/postEdit" method="POST"
							modelAttribute="voteEdit" id="frmVote" role="form">
							<label>Sản phẩm</label>
							<div class="mb-3">
								<form:input path="id" type="hidden"/>
								<form:input path="createTime" type="hidden"/>
								<form:input path="id" type="hidden"/>
								<form:select path="tblProduct" id="txtProduct"
									class="form-control" aria-label="Products"
									aria-describedby="Products-addon">
									<form:option value="0">--Chọn--</form:option>
									<c:forEach items="${products}" var="item">
										<form:option value="${item.id}">${item.name}</form:option>
									</c:forEach>
								</form:select>
							</div>
							<label>Đánh giá sao</label>
							<div class="mb-3">
								<form:select path="star" id="txtStar"
									class="form-control" aria-label="Stars"
									aria-describedby="Stars-addon">
									<form:option value="0">--Chọn--</form:option>
									<form:option value="1">1 sao</form:option>
									<form:option value="2">2 sao</form:option>
									<form:option value="3">3 sao</form:option>
									<form:option value="4">4 sao</form:option>
									<form:option value="5">5 sao</form:option>
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
<script src="../js-form/vote.js"></script>