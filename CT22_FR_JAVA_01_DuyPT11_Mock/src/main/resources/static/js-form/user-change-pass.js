document.getElementById("btnSubmit").addEventListener("click",function(){
	var password = document.getElementById("txtPassword").value;
	if(!checkPassword(password)){}
	else document.getElementById("frmUser").submit();
})

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
