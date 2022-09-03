<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="container-fluid py-4">
	<div class="row mt-3">
		<div class="col-xl-3 col-sm-6 mb-xl-0 mb-4">
			<a href="${path}/statistics/index">
				<div class="card">
					<div class="card-body p-3">
						<div class="row">
							<div class="col-8">
								<div class="numbers">
									<p class="text-sm mb-0 text-capitalize font-weight-bold">Tổng
										doanh thu</p>
								</div>
							</div>
							<div class="col-4 text-end">
								<div
									class="icon icon-shape bg-gradient-primary shadow text-center border-radius-md">
									<i class="ni ni-money-coins text-lg opacity-10"
										aria-hidden="true"></i>
								</div>
							</div>
						</div>
					</div>
				</div>
			</a>
		</div>
		<div class="col-xl-3 col-sm-6 mb-xl-0 mb-4">
			<div class="card">
				<div class="card-body p-3">
					<div class="row">
						<div class="col-8">
							<div class="numbers">
								<p class="text-sm mb-0 text-capitalize font-weight-bold">Sản
									phẩm</p>
								<div class="dropdown">
									<a class="cursor-pointer" id="dropdownProduct"
										data-bs-toggle="dropdown" aria-expanded="false"> <i
										class="fa fa-ellipsis-v text-secondary"></i>
									</a>
									<ul class="dropdown-menu px-2 py-3 ms-sm-n4 ms-n5"
										aria-labelledby="dropdownProduct">
										<c:forEach var="item" items="${products }">
											<li><a class="dropdown-item border-radius-md"
												href="${path}/statistics/products?id=${item.id}&key=-1&value1=-1&value2=-1">${item.name }</a></li>
										</c:forEach>
									</ul>
								</div>

							</div>
						</div>
						<div class="col-4 text-end">
							<div
								class="icon icon-shape bg-gradient-primary shadow text-center border-radius-md">
								<i class="ni ni-money-coins text-lg opacity-10"
									aria-hidden="true"></i>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="col-xl-3 col-sm-6 mb-xl-0 mb-4">
			<div class="card">
				<div class="card-body p-3">
					<div class="row">
						<div class="col-8">
							<div class="numbers">
								<p class="text-sm mb-0 text-capitalize font-weight-bold">Loại
									sản phẩm</p>
								<div class="dropdown">
									<a class="cursor-pointer" id="dropdownCategory"
										data-bs-toggle="dropdown" aria-expanded="false"> <i
										class="fa fa-ellipsis-v text-secondary"></i>
									</a>
									<ul class="dropdown-menu px-2 py-3 ms-sm-n4 ms-n5"
										aria-labelledby="dropdownCategory">
										<c:forEach var="item" items="${categories }">
											<li><a class="dropdown-item border-radius-md"
												href="${path}/statistics/categories?id=${item.id}&key=-1&value1=-1&value2=-1">${item.name }</a></li>
										</c:forEach>
									</ul>
								</div>
							</div>
						</div>
						<div class="col-4 text-end">
							<div
								class="icon icon-shape bg-gradient-primary shadow text-center border-radius-md">
								<i class="ni ni-money-coins text-lg opacity-10"
									aria-hidden="true"></i>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="col-xl-3 col-sm-6 mb-xl-0 mb-4">
			<div class="card">
				<div class="card-body p-3">
					<div class="row">
						<div class="col-8">
							<div class="numbers">
								<p class="text-sm mb-0 text-capitalize font-weight-bold">Nhà
									cung cấp</p>
									<div class="dropdown">
									<a class="cursor-pointer" id="dropdownManufacturers"
										data-bs-toggle="dropdown" aria-expanded="false"> <i
										class="fa fa-ellipsis-v text-secondary"></i>
									</a>
									<ul class="dropdown-menu px-2 py-3 ms-sm-n4 ms-n5"
										aria-labelledby="dropdownManufacturers">
										<c:forEach var="item" items="${manufacturers }">
											<li><a class="dropdown-item border-radius-md"
												href="${path}/statistics/manufacturers?id=${item.id}&key=-1&value1=-1&value2=-1">${item.name }</a></li>
										</c:forEach>
									</ul>
								</div>
							</div>
						</div>
						<div class="col-4 text-end">
							<div
								class="icon icon-shape bg-gradient-primary shadow text-center border-radius-md">
								<i class="ni ni-money-coins text-lg opacity-10"
									aria-hidden="true"></i>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="row mt-5">
		<div class="col-12">
			<div class="card mb-4">
				<div class="card-header pb-0" id="showChangeTime">
					<h6>Kết quả thống kê</h6>
					<div class="dropdown">
						<a class="cursor-pointer btn btn-default dropdown-toggle"
							id="dropdown0" data-bs-toggle="dropdown" aria-expanded="false">Chọn
							thời gian </a>
						<ul class="dropdown-menu px-2 py-3 ms-sm-n4 ms-n5"
							aria-labelledby="dropdownTable">
							<li><a class="dropdown-item border-radius-md"
								href="${path}/statistics/manufacturers?id=${manufacturerId}&key=year&value1=-1&value2=-1">Năm</a></li>
							<li><a class="dropdown-item border-radius-md"
								href="${path}/statistics/manufacturers?id=${manufacturerId}&key=quarter&value1=-1&value2=-1">Quý</a></li>
							<li><a class="dropdown-item border-radius-md"
								href="${path}/statistics/manufacturers?id=${manufacturerId}&key=month&value1=-1&value2=-1">Tháng</a></li>
						</ul>
					</div>
				</div>
				<div class="card-body row px-0 pt-0 pb-2">
					<div class="table-responsive p-0">
						<table class="table align-items-center mb-0" id="example">
							<thead>
								<tr>
									<th
										class="text-center text-uppercase text-secondary text-xxs font-weight-bolder opacity-7">Thời
										gian</th>
									<th
										class="text-center text-uppercase text-secondary text-xxs font-weight-bolder opacity-7 ps-2">Doanh
										thu</th>
									<th
										class="text-center text-uppercase text-secondary text-xxs font-weight-bolder opacity-7 ps-2">Tăng/giảm</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="item" items="${list}" varStatus="myIndex">
									<tr>
										<td class="text-center"><a
											href="${path}/statistics/manufacturers?id=${manufacturerId}&key=${key}&value1=${item.time}&value2=${value2}">
												<p class="text-xs font-weight-bold mb-0">${item.time}</p>
										</a></td>
										<td class="text-center">
											<p class="text-xs font-weight-bold mb-0">
												<fmt:formatNumber type="number" groupingUsed="true"
													value="${item.total}" />
												<u>đ</u>
											</p>
										</td>
										<td class="text-center"><p
												class="text-xs font-weight-bold mb-0">${item.change}%</p></td>
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
</div>

<script src="../js-form/statistics.js"></script>
