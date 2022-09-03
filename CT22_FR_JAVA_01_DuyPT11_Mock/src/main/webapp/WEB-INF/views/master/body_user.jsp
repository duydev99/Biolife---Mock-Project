<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="container-fluid py-4">
	<div class="row">
		<div class="col-12">
			<div class="card mb-4">
				<div class="card-header pb-0">
					<h6>Danh sách người dùng</h6>
					<a href="${path}/users/add"
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
										class=" text-uppercase text-secondary text-xxs font-weight-bolder opacity-7 ps-2">Họ
										tên</th>
									<th
										class=" text-uppercase text-secondary text-xxs font-weight-bolder opacity-7 ps-2">Tài
										khoản</th>
									<th
										class=" text-uppercase text-secondary text-xxs font-weight-bolder opacity-7 ps-2">Email</th>
									<th
										class="text-center text-uppercase text-secondary text-xxs font-weight-bolder opacity-7 ps-2">Số
										điện thoại</th>
									<th
										class=" text-uppercase text-secondary text-xxs font-weight-bolder opacity-7 ps-2">Loại</th>
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
										<td>
											<p class="text-xs font-weight-bold mb-0">${item.username}</p>
										</td>
										<td>
											<p class="text-xs font-weight-bold mb-0">${item.email}</p>
										</td>
										<td class="text-center">
											<p class="text-xs font-weight-bold mb-0">${item.phone}</p>
										</td>
										<td>
											<p class="text-xs font-weight-bold mb-0">
												<c:if test="${item.type == 0}">
												Khách hàng
											</c:if>
												<c:if test="${item.status == 1}">
												Quản trị
											</c:if>
											</p>
										</td>
										<td class="align-middle"><a
											href="${path}/users/edit?id=${item.id}"
											class="text-info font-weight-bold text-xs"
											data-toggle="tooltip" data-original-title="Edit user">
												<img alt="Cập nhật" src="../images/icon/icon-edit.png" width="25px" height="25px"> </a>|<a href="${path}/users/change-pass?id=${item.id}"
											class="text-danger font-weight-bold text-xs"
											data-toggle="tooltip" data-original-title="user">
												Change Pass </a></td>
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
