document.getElementById("btnSubmit").addEventListener("click",function(){
	var name  = document.getElementById("txtName").value;
	if(name == null || name == ""){
		alert("Hãy nhập hình thức thanh toán");
	}
	else if(name.length<3){
		alert("Hình thức thanh toán phải từ 3 ký tự trở lên");
	}else document.getElementById("frmPayment").submit();
})