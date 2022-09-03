<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div class="container-fluid py-4">
	<div class="row">
		<div class="col-12">
			<div class="card mb-4">
				<div class="card-header pb-0">
					<h6>Các sản phẩm trong hóa đơn</h6>
					<a href="${path}/orders?page=1"
						class="text-infor font-weight-bold text-xs" data-toggle="tooltip"
						data-original-title="Edit user"><img
						alt="Trở lại" src="../images/icon/icon-back.png" width="25px"
						height="25px"></a>
				</div>
				<div class="card-body px-0 pt-0 pb-2">
					<div class="table-responsive p-0">
						<table class="table align-items-center mb-0" id="example">
							<thead>
								<tr>
									<th
										class="text-center text-uppercase text-secondary text-xxs font-weight-bolder opacity-7">#</th>
									<th
										class=" text-uppercase text-secondary text-xxs font-weight-bolder opacity-7 ps-2">Sản
										phẩm</th>
									<th
										class="text-center text-uppercase text-secondary text-xxs font-weight-bolder opacity-7 ps-2">Đơn
										giá</th>
									<th
										class="text-center text-uppercase text-secondary text-xxs font-weight-bolder opacity-7 ps-2">Số
										lượng</th>
									<th
										class="text-center text-uppercase text-secondary text-xxs font-weight-bolder opacity-7 ps-2">Thành
										tiền</th>
									<th
										class=" text-uppercase text-secondary text-xxs font-weight-bolder opacity-7 ps-2">Ngày
										tạo</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="item" items="${list}">
									<tr>
										<td class="text-center">
											${item.id}
										</td>
										<td>${item.tblProduct.name}</td>
										<td class="text-center"><fmt:formatNumber type="number"
												groupingUsed="true" value="${item.price}" /> <u>đ</u></td>
										<td class="text-center">${item.amount}</td>
										<td class="text-center"><fmt:formatNumber type="number"
												groupingUsed="true" value="${item.price * item.amount}" />
											<u>đ</u></td>
										<td>${item.createTime}</td>
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
