document.getElementById("btnSubmit").addEventListener("click",function(){
	var name = document.getElementById("txtName").value;
	var username = document.getElementById("txtUsername").value;
	var password = document.getElementById("txtPassword").value;
	var phone = document.getElementById("txtPhone").value;

	if(!checkName(name)){}
	else if(!checkUsername(username)){}
	else if(!checkPassword(password)){}
	else if(!checkPhone(phone)){}
	else document.getElementById("frmRegister").submit();
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

function checkUsername(name){
	var regex = /^[\w]{6,18}$/g;
	if(name == "" || name == null){
		alert("Tài khoản không được rỗng");
		return false;
	}
	else if(!regex.exec(name)){
		alert("Tài khoản phải là chữ số không dấu và từ 6 đến 18 ký tự");
		return false;
	}else return true;
}

function checkPassword(pass){
	var regex = /^[\w]{6,18}$/g;
	if(pass == "" || pass == null){
		alert("Mật khẩu không được rỗng");
		return false;
	}
	else if(!regex.exec(pass)){
		alert("Mật khẩu phải là chữ số không dấu và từ 6 đến 18 ký tự");
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