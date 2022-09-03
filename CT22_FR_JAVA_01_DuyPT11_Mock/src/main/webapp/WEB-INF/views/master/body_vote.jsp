<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="container-fluid py-4">
	<div class="row">
		<div class="col-12">
			<div class="card mb-4">
				<div class="card-header pb-0">
					<h6>Danh sách đánh giá</h6>
					<a href="${path}/vote/add"
						class="text-success font-weight-bold text-xs"
						data-toggle="tooltip" data-original-title="Edit user"><img alt="Thêm mới" src="../images/icon/icon-add.png" width="25px" height="25px"></a>
				</div>
				<div class="card-body px-0 pt-0 pb-2">
					<div class="table-responsive p-0">
						<table class="table align-items-center mb-0" id="example">
							<thead>
								<tr>
									<th
										class="text-center text-uppercase text-secondary text-xxs font-weight-bolder opacity-7">#</th>
									<th
										class=" text-uppercase text-secondary text-xxs font-weight-bolder opacity-7 ps-2">Người
										dùng</th>
									<th
										class=" text-uppercase text-secondary text-xxs font-weight-bolder opacity-7 ps-2">Sản
										phẩm</th>
									<th
										class=" text-uppercase text-secondary text-xxs font-weight-bolder opacity-7 ps-2">Sao</th>
									<th
										class=" text-uppercase text-secondary text-xxs font-weight-bolder opacity-7 ps-2">Ngày
										tạo</th>
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
											<p class="text-xs font-weight-bold mb-0">${item.tblUser.name}</p>
										</td>
										<td>
											<p class="text-xs font-weight-bold mb-0">${item.tblProduct.name}</p>
										</td>
										<td>
											<p class="text-xs font-weight-bold mb-0">${item.star}</p>
										</td>
										<td>
											<p class="text-xs font-weight-bold mb-0">${item.createTime}</p>
										</td>
										<td class="align-middle">
										<a href="${path}/vote/edit?id=${item.id}"
											class="text-info font-weight-bold text-xs"
											data-toggle="tooltip" data-original-title="Edit user">
												<img alt="Cập nhật" src="../images/icon/icon-edit.png" width="25px" height="25px"> </a>|<a href="${path}/vote/delete?id=${item.id}"
											class="text-danger font-weight-bold text-xs"
											data-toggle="tooltip" data-original-title="Edit user" onclick="if(!(confirm('Bạn chắc chắn muốn xóa?'))) return false">
												<img alt="Xóa" src="../images/icon/icon-remove.png" width="25px" height="25px"> </a>
												</td>
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
