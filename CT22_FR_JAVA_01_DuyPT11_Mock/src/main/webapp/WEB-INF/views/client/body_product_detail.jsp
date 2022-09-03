<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!--Hero Section-->
<div class="hero-section hero-background">
	<h1 class="page-title">Thông Tin Sản Phẩm</h1>
</div>

<!--Navigation section-->
<div class="container">
	<nav class="biolife-nav">
		<span class="current-page">${url}</span>
	</nav>
</div>

<div class="page-contain single-product">
	<div class="container">

		<!-- Main content -->
		<div id="main-content" class="main-content">

			<!-- summary info -->
			<div class="sumary-product single-layout">
				<div class="media">
					<ul class="biolife-carousel slider-for"
						data-slick='{"arrows":false,"dots":false,"slidesMargin":30,"slidesToShow":1,"slidesToScroll":1,"fade":true,"asNavFor":".slider-nav"}'>
						<c:forEach var="item" items="${product.tblImageProducts}">
							<li><img src="../images/${item.source}" alt="" width="500"
								height="500"></li>
						</c:forEach>
					</ul>
					<ul class="biolife-carousel slider-nav"
						data-slick='{"arrows":false,"dots":false,"centerMode":false,"focusOnSelect":true,"slidesMargin":10,"slidesToShow":4,"slidesToScroll":1,"asNavFor":".slider-for"}'>
						<c:forEach var="item" items="${product.tblImageProducts}">
							<li><img src="../images/${item.source}" alt="" width="88"
								height="88"></li>
						</c:forEach>
					</ul>
				</div>
				<div class="product-attribute">
					<h3 class="title">${product.name }</h3>
					<div class="rating">
						<p class="star-rating">
							<span class="width-${avgStar * 20}percent"></span>
						</p>
					</div>
					<span class="sku">Code: #${product.id }</span>
					<p class="excerpt">
						<span>Loại: ${product.tblCategory.name }</span> <b
							class="category">Cung cấp bởi: ${product.tblManufacturer.name }</b>
					</p>
					<div class="price">
						<ins>
							<span class="price-amount"><fmt:formatNumber type="number"
									groupingUsed="true" value="${product.price}" /><span
								class="currencySymbol"> <u>đ</u></span></span>
						</ins>
						<!-- <del>
							<span class="price-amount"><span class="currencySymbol">£</span>95.00</span>
						</del> -->
					</div>

					<div class="shipping-info">
						<p class="shipping-day">Giao hàng sau 3 ngày</p>
						<p class="for-today">Hãy mua hàng ngay hôm nay</p>
					</div>
					<input type="hidden" value="${product.price}" id="txtPrice">
				</div>

				<div class="action-form">
					<form id="frmAddToCart">
						<div class="quantity-box">
							<span class="title">Số lượng:</span>
							<div class="qty-input">
								<input type="number" value="0" max="20"
									onChange="changeTotalPrice()" id="txtAmount" min="1" step="1">
							</div>
						</div>
						<div class="total-price-contain">
							<span class="title">Tổng giá:</span>
							<p class="price" id="rsTotal"></p>
						</div>
						<div class="buttons">
							<button type="button" class="btn add-to-cart-btn"
								onClick="clickAddToCartDetail(${product.id});">Thêm vào
								giỏ hàng</button>
						</div>
					</form>

				</div>
			</div>

			<!-- Tab info -->
			<div class="product-tabs single-layout biolife-tab-contain">
				<div class="tab-head">
					<ul class="tabs">
						<li class="tab-element active"><a href="#tab_1st"
							class="tab-link">Thông tin sản phẩm</a></li>
						<li class="tab-element"><a href="#tab_2rd" class="tab-link">Các
								câu hỏi liên quan</a></li>
						<li class="tab-element"><a href="#tab_3th" class="tab-link">Bình
								luận và đánh giá<sup></sup>
						</a></li>
					</ul>
				</div>
				<div class="tab-content">
					<div id="tab_1st" class="tab-contain desc-tab active">
						<p class="desc">${product.description }</p>
						<div class="desc-expand">
							<span class="title">Trái Cây Tươi</span>
							<ul class="list">
								<li>100% thành phần trái cây thật</li>
								<li>100 túi trái cây tươi được gói riêng</li>
								<li>Pha trộn giữa truyền thống phương Đông và phương Tây,
									một cách tự nhiên</li>
							</ul>
						</div>
					</div>
					<div id="tab_2rd" class="tab-contain shipping-delivery-tab">
						<div class="accodition-tab biolife-accodition">
							<ul class="tabs">
								<li class="tab-item"><span class="title btn-expand">Mất
										bao lâu để nhận được đơn đặt hàng của tôi?</span>
									<div class="content">
										<p>Các đơn đặt hàng trước 3 giờ chiều theo giờ ở Việt Nam
											thường sẽ được xử lý và giao hàng vào ngày làm việc tiếp
											theo. Đối với các đơn đặt hàng nhận được sau 3 giờ chiều,
											chúng thường sẽ được xử lý và giao hàng vào ngày làm việc thứ
											hai. Ví dụ: nếu bạn đặt hàng sau 3 giờ chiều ngày Thứ Hai,
											đơn hàng sẽ được giao vào Thứ Tư. Ngày làm việc không bao gồm
											Thứ Bảy và Chủ Nhật và tất cả các ngày Lễ. Vui lòng cho phép
											thêm thời gian xử lý nếu bạn đặt hàng vào cuối tuần hoặc ngày
											lễ. Khi đơn đặt hàng được xử lý, tốc độ giao hàng sẽ được xác
											định như sau dựa trên phương thức vận chuyển đã chọn:</p>
										<div class="desc-expand">
											<span class="title">Chế độ vận chuyển</span>
											<ul class="list">
												<li>Tiêu chuẩn (vận chuyển 3-5 ngày làm việc)</li>
												<li>Ưu tiên (vận chuyển 2-3 ngày làm việc)</li>
												<li>Chuyển phát nhanh (vận chuyển 1-2 ngày làm việc)</li>
												<!-- <li>Gift Card Orders are shipped via USPS First Class
													Mail. First Class mail will be delivered within 8 business
													days</li> -->
											</ul>
										</div>
									</div></li>
								<li class="tab-item"><span class="title btn-expand">Chi
										phí vận chuyển được tính như thế nào?</span>
									<div class="content">
										<p>Bạn sẽ trả phí vận chuyển dựa trên trọng lượng và kích
											thước của đơn hàng. Các mặt hàng lớn hoặc nặng có thể bao gồm
											phí xử lý quá khổ. Bên cạnh đó, phí vận chuyển còn phụ thuộc
											vào khoảng cách giữa bạn và trạm vận chuyển gần nhất. Tổng
											phí vận chuyển được hiển thị trong giỏ hàng của bạn. Vui lòng
											tham khảo sau.</p>
									</div></li>
								<li class="tab-item"><span class="title btn-expand">Tại
										sao đơn hàng của tôi không đủ điều kiện để được giao hàng MIỄN
										PHÍ?</span>
									<div class="content">
										<p>Những được hàng được giao hàng MIỄN PHÍ phải thỏa điều
											kiện sau:</p>
										<div class="desc-expand">
											<span class="title">Điều kiện miễn phí vận chuyển</span>
											<ul class="list">
												<li>Đơn hàng trong nội ô thành phố của chúng tôi</li>
												<li>Đơn hàng từ 1,000,000 VNĐ trở lên</li>

											</ul>
										</div></li>
								<li class="tab-item"><span class="title btn-expand">Các
										gói hàng không gửi được?</span>
									<div class="content">
										<p>Đôi khi, các gói hàng được trả lại cho chúng tôi do
											hãng vận chuyển không gửi được. Khi người vận chuyển trả lại
											một gói hàng không gửi được cho chúng tôi, chúng tôi sẽ hủy
											đơn hàng và hoàn lại giá mua trừ đi phí vận chuyển.</p>
									</div></li>
							</ul>
						</div>
					</div>
					<div id="tab_3th" class="tab-contain review-tab">
						<div class="container">
							<div class="row">
								<div class="col-lg-5 col-md-5 col-sm-6 col-xs-12">
									<div class="rating-info">
										<p class="index">
											<strong class="rating">${avgRating}</strong>out of 5
										</p>
										<div class="rating">
											<p class="star-rating">
												<span class="width-${avgStar * 20}percent"></span>
											</p>
										</div>
										<!-- <p class="see-all">See all 68 reviews</p> -->
										<ul class="options">
											<li>
												<div class="detail-for">
													<span class="option-name">5 <i class="fa fa-star-o" ></i></span> <span
														class="progres"> <span class="line-100percent"><span
															class="percent width-${starRating5}percent"></span></span>
													</span> <span class="number text-left">${starRating5} %</span>
												</div>
											</li>
											<li>
												<div class="detail-for">
													<span class="option-name">4 <i class="fa fa-star-o"></i></span> <span
														class="progres"> <span class="line-100percent"><span
															class="percent width-${starRating4}percent"></span></span>
													</span> <span class="number">${starRating4} %</span>
												</div>
											</li>
											<li>
												<div class="detail-for">
													<span class="option-name">3 <i class="fa fa-star-o" aria-hidden="true"></i></span> <span
														class="progres"> <span class="line-100percent"><span
															class="percent width-${starRating3}percent"></span></span>
													</span> <span class="number">${starRating3} %</span>
												</div>
											</li>
											<li>
												<div class="detail-for">
													<span class="option-name">2 <i class="fa fa-star-o" aria-hidden="true"></i></span> <span
														class="progres"> <span class="line-100percent"><span
															class="percent width-${starRating2}percent"></span></span>
													</span> <span class="number">${starRating2} %</span>
												</div>
											</li>
											<li>
												<div class="detail-for">
													<span class="option-name">1 <i class="fa fa-star-o" aria-hidden="true"></i></span> <span
														class="progres"> <span class="line-100percent"><span
															class="percent width-${starRating1}percent"></span></span>
													</span> <span class="number">${starRating1} %</span>
												</div>
											</li>
										</ul>
									</div>
								</div>
								<div class="col-lg-7 col-md-7 col-sm-6 col-xs-12">
									<div class="review-form-wrapper">
										<span class="title">Nhận Xét Sản Phẩm</span>
										<form action="#" id="frmReview" method="post">
											<div class="comment-form-rating">
												<label>1. Đánh giá sản phẩm:</label>
												<p class="stars">
													<span> <a class="btn-rating" data-value="star-1"
														name="rate[]" href="#"><i class="fa fa-star-o"
															aria-hidden="true"></i> </a> <a class="btn-rating"
														name="rate[]" data-value="star-2" href="#"><i
															class="fa fa-star-o" aria-hidden="true"></i></a> <a
														class="btn-rating" name="rate[]" data-value="star-3"
														href="#"><i class="fa fa-star-o" aria-hidden="true"></i></a>
														<a class="btn-rating" name="rate[]" data-value="star-4"
														href="#"><i class="fa fa-star-o" aria-hidden="true"></i></a>
														<a class="btn-rating" name="rate[]" data-value="star-5"
														href="#"><i class="fa fa-star-o" aria-hidden="true"></i></a>
													</span>
												</p>
											</div>
											<p class="form-row">
												<textarea name="comment" id="txtComment" cols="30" rows="10"
													placeholder="Lời bình của bạn..."></textarea>
											</p>
											<p class="form-row buttons">
												<button type="button" class="btn btn-bold"
													onClick="reviewProduct(${product.id});" name="submit">Gửi Nhận Xét</button>
											</p>
										</form>
									</div>
								</div>
							</div>
							<div id="comments">
								<ol class="commentlist" id="reviews">
									<c:forEach var="item" items="${comments}">
										<li class="review">
											<div class="comment-container">
												<div class="row">
													<div
														class="comment-content col-lg-12 col-md-12 col-sm-12 col-xs-12">
														<p class="comment-in">
															<span class="post-name">Chất lượng là cách sống của chúng tôi</span><span
																class="post-date">${item.time }</span>
														</p>
														<div class="rating">
															<p class="star-rating">
																<span class="width-${item.rating * 20 }percent"></span>
															</p>
														</div>
														<p class="author">
															by: <b>${item.user }</b>
														</p>
														<p class="comment-text">${item.cmt }</p>
													</div>
												</div>
											</div>
										</li>
									</c:forEach>
								</ol>
<!-- 								<div class="biolife-panigations-block version-2">
									<ul class="panigation-contain">
										<li><span class="current-page">1</span></li>
										<li><a href="#" class="link-page">2</a></li>
										<li><a href="#" class="link-page">3</a></li>
										<li><span class="sep">....</span></li>
										<li><a href="#" class="link-page">20</a></li>
										<li><a href="#" class="link-page next"><i
												class="fa fa-angle-right" aria-hidden="true"></i></a></li>
									</ul>
									<div class="result-count">
										<p class="txt-count">
											<b>1-5</b> of <b>126</b> reviews
										</p>
										<a href="#" class="link-to">See all<i
											class="fa fa-caret-right" aria-hidden="true"></i></a>
									</div>
								</div> -->
							</div>
						</div>
					</div>
				</div>
			</div>

			<!-- related products -->
			<div class="product-related-box single-layout">
				<div class="biolife-title-box lg-margin-bottom-26px-im">
					<span class="biolife-icon icon-organic"></span> <span
						class="subtitle">Tất cả các mặt hàng tốt nhất cho bạn</span>
					<h3 class="main-title">Sản Phẩm Liên Quan</h3>
				</div>
				<ul
					class="products-list biolife-carousel nav-center-02 nav-none-on-mobile"
					data-slick='{"rows":1,"arrows":true,"dots":false,"infinite":false,"speed":400,"slidesMargin":0,"slidesToShow":5, "responsive":[{"breakpoint":1200, "settings":{ "slidesToShow": 4}},{"breakpoint":992, "settings":{ "slidesToShow": 3, "slidesMargin":20 }},{"breakpoint":768, "settings":{ "slidesToShow": 2, "slidesMargin":10}}]}'>
					<c:forEach items="${relatedProducts}" var="item">
						<li class="product-item">
							<div class="contain-product layout-default">
								<div class="product-thumb">
									<a href="${path}/product-detail?id=${item.id}"
										class="link-to-product"> <img
										src="../images/${item.tblImageProducts[0].source}" alt="dd"
										style="width:100%;  height:200px " class="product-thumnail">
									</a>
								</div>
								<div class="info">
									<b class="categories">${item.tblCategory.name }</b>
									<h4 class="product-title">
										<a href="${path}/product-detail?id=${item.id}" class="pr-name">${item.name }</a>
									</h4>
									<div class="price">
										<ins>
											<span class="price-amount"><fmt:formatNumber
													type="number" groupingUsed="true" value="${item.price}" />
												<span class="currencySymbol">Đ</span></span>
										</ins>
									</div>
									<div class="slide-down-box">
										<p class="message">Tất cả các sản phẩm đều được lựa chọn
											kỹ lưỡng để đảm bảo an toàn vệ sinh thực phẩm.</p>
										<div class="buttons text-center">
											<!-- <a href="#" class="btn wishlist-btn"><i
														class="fa fa-heart" aria-hidden="true"></i></a> -->
											<button type="button" class="btn add-to-cart-btn"
												onClick="clickAddToCart(${item.id});">
												<i class="fa fa-cart-arrow-down" aria-hidden="true"></i>Thêm
												Vào Giỏ Hàng
											</button>
											<!-- <a href="#" class="btn compare-btn"><i
														class="fa fa-random" aria-hidden="true"></i></a> -->
										</div>
									</div>
								</div>
							</div>
						</li>
					</c:forEach>
				</ul>
			</div>

		</div>
	</div>
</div>
