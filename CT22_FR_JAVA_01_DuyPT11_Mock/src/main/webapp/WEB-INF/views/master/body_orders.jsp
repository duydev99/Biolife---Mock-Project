<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="container-fluid py-4">
	<div class="row">
		<div class="col-12">
			<div class="card mb-4">
				<div class="card-header pb-0">
					<h6>Danh sách hóa đơn</h6>
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
										class=" text-uppercase text-secondary text-xxs font-weight-bolder opacity-7 ps-2">Địa
										chỉ</th>
									<th
										class=" text-uppercase text-secondary text-xxs font-weight-bolder opacity-7 ps-2">Số
										điện thoại</th>
									<th
										class=" text-uppercase text-secondary text-xxs font-weight-bolder opacity-7 ps-2">Hình
										thức</th>
									<th
										class=" text-uppercase text-secondary text-xxs font-weight-bolder opacity-7 ps-2">Ngày
										tạo</th>
									<th
										class=" text-uppercase text-secondary text-xxs font-weight-bolder opacity-7 ps-2">Trạng
										thái</th>
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
											<p class="text-xs font-weight-bold mb-0">${item.address}</p>
										</td>
										<td>
											<p class="text-xs font-weight-bold mb-0">${item.phone}</p>
										</td>
										<td>
											<p class="text-xs font-weight-bold mb-0">${item.tblPayment.method}</p>
										</td>
										<td>
											<p class="text-xs font-weight-bold mb-0">${item.createTime}</p>
										</td>
										<td><c:if test="${item.status == 0}">
												<a href="${path}/order-change-status?orderId=${item.id}"
													class="btn btn-warning">Xác nhận</a>
											</c:if> <c:if test="${item.status == 1}">
												<span class="btn btn-primary">Đã xác nhận</span>
											</c:if> <c:if test="${item.status == 2}">
												<span class="btn btn-success">Đã nhận</span>
											</c:if> <c:if test="${item.status == 3}">
												<span class="btn btn-danger">Đã hủy</span>
											</c:if></td>
										<td><a
											href="${path}/order-detail-server?orderId=${item.id}"
											class="btn btn-warning">Chi tiết</a></td>
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
