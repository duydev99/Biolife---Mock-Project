document.getElementById("btnSubmit").addEventListener("click",function(){
	var name = document.getElementById("txtName").value;
	var email = document.getElementById("txtEmail").value;
	var address = document.getElementById("txtAddress").value;
	var phone = document.getElementById("txtPhone").value;

	if(!checkName(name)){}
	else if(!checkEmail(email)){}
	else if(!checkAddress(address)){}
	else if(!checkPhone(phone)){}
	else document.getElementById("frmUserClientUpdate").submit();
})

function checkName(name){
	var regex = /^[\D]{6,50}$/g;
	if(name == "" || name == null){
		alert("Họ tên không được rỗng");
		return false;
	}
	else if(!regex.exec(name)){
		alert("Họ tên phải là chữ và từ 6 đến 50 ký tự");
		return false;
	}else return true;
}

function checkEmail(email){
	if(email == "" || email == null){
		alert("Email không được rỗng");
		return false;
	}
	else return true;
}

function checkAddress(address){
	var regex = /[<|>|/|"|'|-|_|!|~|`|@|#|$|%|^|&|*|?|+|=]/;
	if(address == "" || address == null){
		alert("Địa chỉ không được rỗng");
		return false;
	}
	else if(address < 6 || address > 50){
		alert("Địa chỉ phải từ 6 đến 50 ký tự");
		return false;
	}
	else if(regex.exec(address)){
		alert("Địa chỉ không được nhập ký tự đặc biệt");
		return false;
	}else return true;
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