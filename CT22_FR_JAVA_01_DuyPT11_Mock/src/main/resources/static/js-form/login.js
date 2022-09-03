document.getElementById("btnSubmit").addEventListener("click",function(){
	var username = document.getElementById("txtUsername").value;
	var password = document.getElementById("txtPassword").value;
	if(!checkUsername(username)){}
	else if(!checkPassword(password)){}
	else document.getElementById("frmLogin").submit();
})

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
