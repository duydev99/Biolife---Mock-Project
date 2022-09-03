document.getElementById("btnSubmit").addEventListener("click",function(){
	var product  = document.getElementById("txtProduct").value;
	var content  = document.getElementById("txtContent").value;
	const regex1 = /[<|>|/|"|'|-|_|!|~|`|@|#|$|%|^|&|*|?|+|=]/;
	const regex2 = /^[\d]{1,}$/g;
	if(product < 1){
		alert("Hãy chọn sản phẩm");
	}
	else if(!regex2.exec(product)){
		alert("Mã sản phẩm có vấn đề");
	}
	else if(content.length<6 || content.length>50){
		alert("Nội dung phải từ 6 đến 50 ký tự");
	}
	else if(regex1.exec(content)){
		alert("Không được nhập ký tự đặc biệt");
	}else document.getElementById("frmComment").submit();
})