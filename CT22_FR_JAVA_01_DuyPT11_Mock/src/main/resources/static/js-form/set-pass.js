
function changePassword(){
	var newPass = document.getElementById("txtPassNew").value;
	var confirmPass = document.getElementById("txtPassConfirm").value;
	
	if(!checkPasswordNew(newPass)){}
	else if(!checkPasswordConfirm(confirmPass)){}
	else{
		if(newPass != confirmPass){
			alert("Xác nhận mật khẩu không đúng");
		}else{
			document.getElementById("frmChangePassword").submit();
		}
	}
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