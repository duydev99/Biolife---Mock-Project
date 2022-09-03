document.addEventListener("DOMContentLoaded", function() {
	$.ajax({
		type: "POST",
		contentType: "application/json",
		url: "get-data-cart",
		data: "",
		dataType: 'json',
		cache: false,
		timeout: 600000,
		success: function(data) {
			setCartProducts(data);
		},
		error: function(e) {
			console.log(e);
		}
	});
})

function changeTotalPrice() {
	// Create our number formatter.
	var formatter = new Intl.NumberFormat('vi', {
		style: 'currency',
		currency: 'VND',
	});
	var price = document.getElementById("txtPrice").value;
	var amount = document.getElementById("txtAmount");
	if (amount.value < 1) {
		alert("Giá trị không hợp lệ");
		amount.value = 1;

	}

	if (amount.value > 20) {
		alert("Giá trị không hợp lệ");
		amount.value = 20;
	}



	var rs = formatter.format(price * amount.value);
	document.getElementById("rsTotal").innerHTML = rs;
}

function clickAddToCartDetail(productId) {
	var amount = document.getElementById("txtAmount").value;
	if (amount <= 0) {
		alert("Giá trị không hợp lệ. Số lượng phải >= 1");
		document.getElementById("txtAmount").value = 1;
		amount = 1;
	} else {
		var data = { 0: productId, 1: amount };
		$.ajax({
			type: "POST",
			contentType: "application/json",
			url: "check-amount-product",
			data: JSON.stringify(data),
			dataType: 'json',
			cache: false,
			timeout: 600000,
			success: function(d1) {
				if (d1[0] == "valid") {
					$.ajax({
						type: "POST",
						contentType: "application/json",
						url: "add-to-cart",
						data: JSON.stringify(data),
						dataType: 'json',
						cache: false,
						timeout: 600000,
						success: function(d2) {
							setCartProducts(d2);
						},
						error: function(e2) {
							console.log(e2);
						}
					});
				} else {
					alert(d1[0]);
				}
			},
			error: function(e1) {
				console.log(e1);
			}
		});

	}
}

function clickAddToCart(productId) {
	var data = { 0: productId, 1: 1 };
	$.ajax({
		type: "POST",
		contentType: "application/json",
		url: "check-amount-product",
		data: JSON.stringify(data),
		dataType: 'json',
		cache: false,
		timeout: 600000,
		success: function(d1) {
			if (d1[0] == "valid") {
				$.ajax({
					type: "POST",
					contentType: "application/json",
					url: "add-to-cart",
					data: JSON.stringify(data),
					dataType: 'json',
					cache: false,
					timeout: 600000,
					success: function(d2) {
						setCartProducts(d2);
					},
					error: function(e2) {
						console.log(e2);
					}
				});
			} else {
				alert(d1[0]);
			}
		},
		error: function(e1) {
			console.log(e1);
		}
	});
}

function setCartProducts(data) {
	var listCart = '';
	var formatter = new Intl.NumberFormat('vi', {
		style: 'currency',
		currency: 'VND',
	});
	var priceTotal = 0;

	if (data.length > 0) {
		for (var i = 0; i < data.length; i++) {
			priceTotal += (data[i].amount * data[i].product.price);

			listCart += '<li><div class="minicart-item" id="miniCartEdit' + data[i].id + '">' +
				'<div class="thumb">' +
				'<a href="/product-detail?id=' + data[i].id + '"><img src="images/' + data[i].image + '" width="90" height="90"></a>' +
				'</div>' +
				'<div class="left-info">' +
				'<div class="product-title">' +
				'<a href="/product-detail?id=' + data[i].id + '" class="product-name">' + data[i].product.name + '</a>' +
				'</div>' +
				'<div class="price">' +
				'<ins>' +
				'<span class="price-amount">' + formatter.format(data[i].product.price) + '</span>' +
				'</ins>' +
				'</div>' +
				'<div class="qty">' +
				'<label for="">Qty:</label>' +
				'<input type="number" class="input-qty" onChange="changeAmountProduct(this)" id="' + data[i].id + '" min="1" max="20" step="1" value="' + data[i].amount + '" disabled>' +
				'</div>' +
				'</div>' +
				'<div class="action">' +
				'<a href="#" class="edit" onClick="updateAmountProduct(' + data[i].id + ')"><i class="fa fa-pencil" aria-hidden="true"></i></a>' +
				'<a href="#" class="" onClick = "deleteProduct(' + data[i].id + ')"><i class="fa fa-trash-o" aria-hidden="true"></i></a>' +
				'</div>' +
				'</div></li>';
		}

		document.getElementById("cartTotalPrice").innerHTML = formatter.format(priceTotal);
		if (document.getElementById("cartTotalPriceDetail") != null) {
			document.getElementById("cartTotalPriceDetail").innerHTML = formatter.format(priceTotal);
		}

		document.getElementById("cartQtyProducts").innerHTML = Object.keys(data).length;
		document.getElementById("innerCart").innerHTML =
			'<ul class="products" id="miniCart">' +
			'</ul>' +
			'<p class="btn-control">' +
			'<a href="/cart-detail" class="btn view-cart">Chi tiết</a>' +
			'<a href="/check-out"class="btn">Thanh toán</a>' +
			'</p>';

		document.getElementById("miniCart").innerHTML = listCart;
	}
	else {
		document.getElementById("cartTotalPrice").innerHTML = formatter.format(0);
		if (document.getElementById("cartTotalPriceDetail") != null) {
			document.getElementById("cartTotalPriceDetail").innerHTML = formatter.format(0);
		}
		document.getElementById("cartQtyProducts").innerHTML = Object.keys(data).length;
		document.getElementById("innerCart").innerHTML = '<p class="minicart-empty">Không có sản phẩm nào trong giỏ hàng</p>';
	}

}


function changeAmountProduct(e) {
	if (e.value < 1) {
		alert("Giá trị không hợp lệ");
		e.value = 1;
	}

	if (e.value > 20) {
		alert("Giá trị không hợp lệ");
		e.value = 20;
	}
}

function updateAmountProduct(productId) {

	var amount = document.getElementById(productId).value;
	var editing = document.getElementById("miniCartEdit" + productId).className;

	if (editing == "minicart-item editing") {
		var data = { 0: productId, 1: amount };
		$.ajax({
			type: "POST",
			contentType: "application/json",
			url: "check-amount-update-product",
			data: JSON.stringify(data),
			dataType: 'json',
			cache: false,
			timeout: 600000,
			success: function(d1) {
				if (d1[0] != "valid") {
					alert(d1[0]);
				} 
				$.ajax({
						type: "POST",
						contentType: "application/json",
						url: "update-amount",
						data: JSON.stringify(data),
						dataType: 'json',
						cache: false,
						timeout: 600000,
						success: function(d2) {
							setCartProducts(d2);
							setCartDetail(d2);
						},
						error: function(e2) {
							console.log(e2);
						}
					});
			},
			error: function(e1) {
				console.log(e1);
			}
		});
	}
}

function deleteProduct(productId, path) {

	var conf = confirm("Bạn chắc chắn muốn xóa?");
	if (conf) {
		var data = { 0: productId };
		$.ajax({
			type: "POST",
			contentType: "application/json",
			url: "delete-product",
			data: JSON.stringify(data),
			dataType: 'json',
			cache: false,
			timeout: 600000,
			success: function(data) {
				setCartProducts(data);
				setCartDetail(data);
			},
			error: function(e) {
				console.log(e);
			}
		});
	}
}

function reviewProduct(productId) {
	var rates = document.getElementsByName("rate[]");
	var cmt = document.getElementById("txtComment").value;
	var rating = 0;
	for (var i = 0; i < rates.length; i++) {
		if (rates[i].className == "btn-rating selected") rating = i + 1;
	}
	const regex = /[<|>|/|"|'|-|_|!|~|`|@|#|$|%|^|&|*|?|+|=]/;
	if (cmt.trim() == "") {
		alert("Hãy để lại lời bình của bạn cho sản phẩm");
	} else if (regex.test(cmt)) {
		alert("Chỉ được phép nhập chữ và số");
	} else {
		$.ajax({
			type: "POST",
			contentType: "text/html",
			url: "comments/check-login",
			data: "check",
			dataType: 'text',
			cache: false,
			timeout: 600000,
			success: function(response1) {
				console.log(response1);
				if (response1 !== "true") window.location.replace(response1);
				else {
					var data = { rating: rating, cmt: cmt, productId: productId };
					sendReview(data);
				}
			},
			error: function(e) {
				console.log(e);
			}
		});
	}
	console.log(cmt);
	console.log(rating);
}

function sendReview(data) {
	$.ajax({
		type: "POST",
		contentType: "application/json",
		url: "comments/product-detail",
		data: JSON.stringify(data),
		dataType: 'json',
		cache: false,
		timeout: 600000,
		success: function(response2) {
			console.log(response2);
			reloadComments(response2);
		},
		error: function(e) {
			console.log(e);
		}
	});
}

function reloadComments(data) {
	var str = '';

	for (var i = 0; i < data.length; i++) {
		str += '<li class="review">' +
			'<div class="comment-container">' +
			'<div class="row">' +
			'<div class="comment-content col-lg-12 col-md-12 col-sm-12 col-xs-12">' +
			'<p class="comment-in">' +
			'<span class="post-name">Chất lượng là cách sống của chúng tôi</span>' +
			'<span class="post-date">' + data[i].time + '</span>' +
			'</p>' +
			'<div class="rating">' +
			'<p class="star-rating">' +
			'<span class="width-' + data[i].rating * 20 + 'percent"></span>' +
			'</p>' +
			'</div>' +
			'<p class="author"> by: <b>' + data[i].user + '</b></p>' +
			'<p class="comment-text">' + data[i].cmt + '</p>' +
			'</div>' +
			'</div>' +
			'</div>' +
			'</li> ';
	}
	document.getElementById("reviews").innerHTML = "";
	document.getElementById("txtComment").innerHTML = "";
	document.getElementById("reviews").innerHTML = str;
}

function clickSearch() {
	var text = document.getElementById("text").value;
	var type = document.getElementById("type").value;

	if (type == -1) {
		alert("Bạn muốn tìm kiếm theo cái gì?");
	} else if (text.trim().length < 1) {
		alert("Hãy nhập nội dung muốn tìm");
	} else if (type !== "product" && type !== "category" && type !== "manufacturer") {
		alert("Loại tìm kiếm không hợp lệ");
	} else document.getElementById("frmSearch").href = "/shop-search?text=" + text + "&key1=" + type + "&key2=-1&filter=all&orderby=default&page=1";

}

function changeEditAmountCartDetail(e) {
	var price = document.getElementById("pri" + e.id).value;
	var formatter = new Intl.NumberFormat('vi', {
		style: 'currency',
		currency: 'VND',
	});
	var regex = /^[\d]{1,2}$/g;
	if (e.value < 1) {
		alert("Giá trị không hợp lệ");
		e.value = 1;
	}
	if (e.value > 20) {
		alert("Giá trị không hợp lệ");
		e.value = 20;
	}
	if (!regex.exec(e.value)) {
		alert("Giá trị không hợp lệ");
		e.value = 1;
		e.type = "number";
	}
	document.getElementById("detailPrice" + e.id).innerHTML = "";
	document.getElementById("detailPrice" + e.id).innerHTML = formatter.format(e.value * price);
}

function setCartDetail(data) {
	var formatter = new Intl.NumberFormat('vi', {
		style: 'currency',
		currency: 'VND',
	});

	var str = '';

	for (var i = 0; i < data.length; i++) {
		str += '<tr class="cart_item">' +
			'<td class="product-thumbnail" data-title="Product Name">' +
			'<a class="prd-thumb" href="/product-detail?id=' + data[i].id + '">' +
			'<figure>' +
			'<img width="113" height="113" src="../images/' + data[i].image + '" alt="shipping cart">' +
			'</figure>' +
			'</a>' +
			'<a class="prd-name" href="/product-detail?id=' + data[i].id + '">' + data[i].product.name + '</a>' +
			'<div class="action">' +
			'<a href="#" class="edit" onClick="updateCartDetail(' + data[i].id + ');"><i class="fa fa-pencil" aria-hidden="true"></i></a>' +
			'<a href="#" class="remove" onClick="deleteProduct(' + data[i].id + ');"><i class="fa fa-trash-o" aria-hidden="true"></i></a>' +
			'</div>' +
			'</td>' +
			'<td class="product-price" data-title="Price">' +
			'<div class="price price-contain">' +
			'<ins>' +
			'<span class="price-amount">' +
			formatter.format(data[i].product.price) +
			'</span>' +
			'</ins>' +
			'</div>' +
			'</td>' +
			'<td class="product-quantity " data-title="Quantity">' +
			'<div class="quantity-box type1">' +
			'<div class="qty-input">' +
			'<input type="number" value="' + data[i].amount + '" id="amount' + data[i].id + '" onChange="changeEditAmountCartDetail(this);" max="20" min="1" step="1"/>' +
			'<input type="hidden" value="' + data[i].product.price + '" id="priamount' + data[i].id + '"/>' +
			'</div>' +
			'</div>' +
			'</td>' +
			'<td class="product-subtotal" data-title="Total">' +
			'<div class="price price-contain">' +
			'<ins>' +
			'<span class="price-amount" id="detailPriceamount' + data[i].id + '">' +
			formatter.format(data[i].product.price * data[i].amount) +
			'</span>' +
			'</ins>' +
			'</div>' +
			'</td>' +
			'</tr>';
	}
	var strFooter = '<tr class="cart_item wrap-buttons">' +
		'<td class="wrap-btn-control" colspan = "4" >' +
		'<a class="btn back-to-shop" href="/shop?filter=all&orderby=default&key=-1&page=1">Đến cửa hàng</a>' +
		'</td>' +
		'</tr>';

	if (document.getElementById("cartDetailBodyTable") != null) {
		document.getElementById("cartDetailBodyTable").innerHTML = "";
		document.getElementById("cartDetailBodyTable").innerHTML = str + strFooter;
	}


}

function updateCartDetail(productId) {
	var amount = document.getElementById("amount" + productId).value;
	var data = { 0: productId, 1: amount };
	$.ajax({
			type: "POST",
			contentType: "application/json",
			url: "check-amount-update-product",
			data: JSON.stringify(data),
			dataType: 'json',
			cache: false,
			timeout: 600000,
			success: function(d1) {
				if (d1[0] != "valid") {
					alert(d1[0]);
				}
				$.ajax({
						type: "POST",
						contentType: "application/json",
						url: "update-amount",
						data: JSON.stringify(data),
						dataType: 'json',
						cache: false,
						timeout: 600000,
						success: function(d2) {
							setCartProducts(d2);
							setCartDetail(d2);
						},
						error: function(e2) {
							console.log(e2);
						}
					});
			},
			error: function(e1) {
				console.log(e1);
			}
		});
}


