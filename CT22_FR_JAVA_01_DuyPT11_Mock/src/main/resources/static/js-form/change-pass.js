
function changePassword(){
	var oldPass = document.getElementById("txtPassOld").value;
	var newPass = document.getElementById("txtPassNew").value;
	var confirmPass = document.getElementById("txtPassConfirm").value;
	
	if(!checkPasswordOld(oldPass)){}
	else if(!checkPasswordNew(newPass)){}
	else if(!checkPasswordConfirm(confirmPass)){}
	else{
		if(newPass != confirmPass){
			alert("Xác nhận mật khẩu không đúng");
		}else{
			document.getElementById("frmChangePassword").submit();
		}
	}
}

function checkPasswordOld(pass){
	var regex = /^[\w]{6,18}$/g;
	if(pass == "" || pass == null){
		alert("Mật khẩu cũ không được rỗng");
		return false;
	}
	else if(!regex.exec(pass)){
		alert("Mật khẩu cũ phải là chữ số không dấu và từ 6 đến 18 ký tự");
		return false;
	}else return true;
}

function checkPasswordNew(pass){
	var regex = /^[\w]{6,18}$/g;
	if(pass == "" || pass == null){
		alert("Mật khẩu mới không được rỗng");
		return false;
	}
	else if(!regex.exec(pass)){
		alert("Mật khẩu mới phải là chữ số không dấu và từ 6 đến 18 ký tự");
		return false;
	}else return true;
}

function checkPasswordConfirm(pass){
	var regex = /^[\w]{6,18}$/g;
	if(pass == "" || pass == null){
		alert("Mật khẩu xác nhận không được rỗng");
		return false;
	}
	else if(!regex.exec(pass)){
		alert("Mật khẩu xác nhận phải là chữ số không dấu và từ 6 đến 18 ký tự");
		return false;
	}else return true;
}