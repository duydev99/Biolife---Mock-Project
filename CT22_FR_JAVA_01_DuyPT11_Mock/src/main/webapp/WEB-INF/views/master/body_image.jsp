<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="container-fluid py-4">
	<div class="row">
		<div class="col-12">
			<div class="card mb-4">
				<div class="card-header pb-0">
					<h6>Danh sách hình ảnh của sản phẩm (${productName})</h6>
					<a href="${path}/products?page=1"
						class="text-success font-weight-bold text-xs"
						data-toggle="tooltip" data-original-title="Edit user"> <img
						alt="Trở lại" src="../images/icon/icon-back.png" width="25px"
						height="25px">
					</a>
				</div>

				<div class="card-body px-0 pt-0 pb-2 row">
					<div class="col-4 px-5 py-2">
						<form:form action="${path}/images-product/postAdd" method="POST"
							enctype="multipart/form-data" id="frmImage" role="form">
							<label>Thêm hình cho sản phẩm <img
								src="../images/icon/icon-img-file.png" width="35px" height="35px"><img
								src="../images/icon/icon-check-all.png" id="imgCheckAll" width="35px"
								height="35px">
							</label>
							<input type="hidden" name="productId" value="${productId}" />
							<div class="mb-3">
								<input id="txtSource" name="txtSource" type="file"
									multiple="multiple" class="form-control"
									placeholder="Hình ảnh sản phẩm" aria-label="Source"
									aria-describedby="Source-addon" />
								<div class="font-monospace text-primary m-2" id="fileAll"
									style="width: 26rem;"></div>
							</div>

							<div class="center">
								<button type="button" id="btnSubmit"
									class="btn bg-gradient-info w-100 mt-4 mb-0">Thêm</button>
							</div>
						</form:form>
					</div>
					<div class="col-8">
						<table class="table table-responsive align-items-center mb-0"
							id="example">
							<thead>
								<tr>
									<th
										class="text-center text-uppercase text-secondary text-xxs font-weight-bolder opacity-7">Mã
										hình ảnh</th>
									<th
										class=" text-uppercase text-secondary text-xxs font-weight-bolder opacity-7 ps-2">Hình
										sản phẩm</th>
									<th class="text-secondary opacity-7"></th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="item" items="${list}">
									<tr>
										<td class="text-center">
											<p class="text-xs font-weight-bold mb-0">${item.id}</p>
										</td>
										<td><img class="border border-1 broder-info" src="../images/${item.source}"
											width="50px" height="50px"></td>
										<td class="align-middle"><a
											href="${path}/images-product/delete?id=${item.id}&productId=${productId}"
											class="text-danger font-weight-bold text-xs"
											data-toggle="tooltip" data-original-title="Edit user"
											onclick="if(!(confirm('Bạn chắc chắn muốn xóa?'))) return false">
												<img alt="Xóa" src="../images/icon/icon-remove.png" width="25px"
												height="25px">
										</a></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script src="../js-form/image.js"></script>
