<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix ="f" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<div class="container-fluid py-4">
	<div class="row">
		<div class="col-12">
			<div class="card mb-4">
				<div class="card-header pb-0">
					<h6>Thông Kê Theo Khách Hàng</h6>
				</div>
				<div class="card-body px-0 pt-0 pb-2">
					<div class="table-responsive p-0">
						<table class="table align-items-center mb-0" id="example">
							<thead>
								<tr>
									<th	class="text-center text-uppercase text-secondary text-xxs font-weight-bolder opacity-7">Tên Khách Hàng</th>
									<th	class=" text-uppercase text-secondary text-xxs font-weight-bolder opacity-7 ps-2">Số Lượng</th>
									<th	class="text-center text-uppercase text-secondary text-xxs font-weight-bolder opacity-7 ps-2">Doanh Thu</th>
									<th	class="text-center text-uppercase text-secondary text-xxs font-weight-bolder opacity-7 ps-2">Giá Thấp Nhất</th>
									<th	class="text-center text-uppercase text-secondary text-xxs font-weight-bolder opacity-7 ps-2">Giá Cao Nhất</th>
									<th	class="text-center text-uppercase text-secondary text-xxs font-weight-bolder opacity-7 ps-2">Giá Trung Bình</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="item" items="${data}">
									<tr>
										<td  class="text-center">
											<p class="text-xs font-weight-bold mb-0">${item[0]}</p>
										</td>
										<td  >
											<p class="text-xs font-weight-bold mb-0">${item[1]}</p>
										</td>
										<td  class="text-center">
											<p class="text-xs font-weight-bold mb-0"><f:formatNumber value="${item[2]}" pattern="#,###"></f:formatNumber></p>
										</td>
										<td class="text-center">
											<p class="text-xs font-weight-bold mb-0"><f:formatNumber value="${item[3]}" pattern="#,###"></f:formatNumber></p>
										</td>
										<td  class="text-center">
											<p class="text-xs font-weight-bold mb-0"><f:formatNumber value="${item[4]}" pattern="#,###"></f:formatNumber></p>
										</td>
										<td  class="text-center">
											<p class="text-xs font-weight-bold mb-0"><f:formatNumber value="${item[5]}" pattern="#,###"></f:formatNumber></p>
										</td>
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
