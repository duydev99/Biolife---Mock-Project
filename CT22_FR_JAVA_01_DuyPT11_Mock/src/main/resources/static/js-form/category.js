document.getElementById("btnSubmit").addEventListener("click",function(){
	var name  = document.getElementById("txtName").value;
	if(name == null || name == ""){
		alert("Hãy nhập loại sản phẩm");
	}
	else if(name.length<3){
		alert("Loại sản phẩm phải từ 3 ký tự trở lên");
	}else document.getElementById("frmCategory").submit();
})