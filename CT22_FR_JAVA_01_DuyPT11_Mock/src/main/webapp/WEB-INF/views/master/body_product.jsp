<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="container-fluid py-4">
	<div class="row">
		<div class="col-12">
			<div class="card mb-4">
				<div class="card-header pb-0">
					<h6>Danh sách sản phẩm</h6>
					<a href="${path}/products/add"
						class="text-success font-weight-bold text-xs"
						data-toggle="tooltip" data-original-title="Edit user">
						<img alt="Thêm mới" src="../images/icon/icon-add.png" width="25px" height="25px">
						</a>
				</div>
				<div class="card-body px-0 pt-0 pb-2">
					<div class="table-responsive p-0">
						<table class="table align-items-center mb-0" id="example">
							<thead>
								<tr>
									<th
										class="text-center text-uppercase text-secondary text-xxs font-weight-bolder opacity-7">Mã</th>
									<th
										class=" text-uppercase text-secondary text-xxs font-weight-bolder opacity-7 ps-2">Tên
										sản phẩm</th>
									<th
										class="text-center text-uppercase text-secondary text-xxs font-weight-bolder opacity-7 ps-2">Đơn
										giá</th>
									<th
										class="text-center text-uppercase text-secondary text-xxs font-weight-bolder opacity-7 ps-2">Số
										lượng</th>
									<!-- <th
										class=" text-uppercase text-secondary text-xxs font-weight-bolder opacity-7 ps-2">Thông
										tin</th> -->
									<th
										class=" text-uppercase text-secondary text-xxs font-weight-bolder opacity-7 ps-2">Loại
										sản phẩm</th>
									<th
										class=" text-uppercase text-secondary text-xxs font-weight-bolder opacity-7 ps-2">Nhà
										cung cấp</th>
									<th class="text-secondary opacity-7"></th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="item" items="${list}">
									<tr>
										<td class="text-center">
											<p class="text-xs font-weight-bold mb-0">${item.id}</p>
										</td>
										<td>
											<p class="text-xs font-weight-bold mb-0">${item.name}</p>
										</td>
										<td class="text-center">
											<p class="text-xs font-weight-bold mb-0">${item.price}</p>
										</td>
										<td class="text-center">
											<p class="text-xs font-weight-bold mb-0">${item.amount}</p>
										</td>
										<%-- <td>
											<p class="text-xs font-weight-bold mb-0">${item.description}</p>
										</td> --%>
										<td>
											<p class="text-xs font-weight-bold mb-0">${item.tblCategory.name}</p>
										</td>
										<td>
											<p class="text-xs font-weight-bold mb-0">${item.tblManufacturer.name}</p>
										</td>
										<td class="align-middle"><a
											href="${path}/products/edit?id=${item.id}"
											class="text-primary font-weight-bold text-xs"
											data-toggle="tooltip" data-original-title="Edit user">
												<img alt="Cập nhật" src="../images/icon/icon-edit.png" width="25px" height="25px">
												</a>|<a href="${path}/products/delete?id=${item.id}"
											class="text-danger font-weight-bold text-xs"
											data-toggle="tooltip" data-original-title="Edit user"
											onclick="if(!(confirm('Bạn chắc chắn muốn xóa?'))) return false">
												<img alt="Xóa" src="../images/icon/icon-remove.png" width="25px" height="25px">
												</a>|<a
											href="${path}/products/detail?id=${item.id}"
											class="text-secondary font-weight-bold text-xs"
											data-toggle="tooltip" data-original-title="Edit user">
												<img alt="Chi tiết" src="../images/icon/icon-detail.png" width="25px" height="25px">
												</a>|<a
											href="${path}/images-product?id=${item.id}"
											class="text-info font-weight-bold text-xs"
											data-toggle="tooltip" data-original-title="Edit user">
												<img alt="Hình ảnh" src="../images/icon/icon-img.png" width="25px" height="25px">
												</a></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
				<div class="card-footer px-0 pt-0 mt-3 pb-2 row">
					<div class="col-12 text-center">
						<div class="btn-group mr-2" role="group">
							${page}
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
