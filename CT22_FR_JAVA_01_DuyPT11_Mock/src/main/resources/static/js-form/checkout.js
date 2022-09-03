function clickCheckForm(){
	var phone = document.getElementById("txtPhone").value;
	var address = document.getElementById("txtAddress").value;
	var payment = document.getElementById("selPayments").value;
	if(!checkPhone(phone)){}
	else if(!checkAddress(address)){}
	else if(!checkPayment(payment)){}
	else {
		document.getElementById("frmCheckOut").submit();
	}
}

function checkPhone(phone){
	var regex = /^[0|+84][\d]{9}$/g;
	if(phone == "" || phone == null){
		alert("Số điện thoại không được rỗng");
		return false;
	}
	else if(!regex.exec(phone)){
		alert("Số điện thoại bắt đầu bằng 0 hoặc +84 và 9 ký tự số theo sau");
		return false;
	}else return true;
}

function checkAddress(address){
	const regex = /[<|>|/|"|'|-|_|!|~|`|@|#|$|%|^|&|*|?|+|=]/;
	if(address.trim().length<1){
		alert("Hãy nhập địa chỉ");
		return false;
	}else if(address.trim().length<6){
		alert("Địa chỉ phải từ 6 ký tự trở lên");
		return false;
	}else if(regex.test(address)){
		alert("Không được phép nhập ký tự đặc biệt");
		return false;
	}else return true;
}

function checkPayment(payment){
	var regex = /^[\d]{1,}$/g;
	if(payment < 1){
		alert("Hãy chọn hình thức thanh toán");
		return false;
	}else if(!regex.test(payment)){
		alert("Mã hình thức thanh toán phải là số");
		return false;
	}else return true;
}