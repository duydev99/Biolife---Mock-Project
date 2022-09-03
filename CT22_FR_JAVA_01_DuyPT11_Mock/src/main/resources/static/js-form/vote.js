document.getElementById("btnSubmit").addEventListener("click",function(){
	var product  = document.getElementById("txtProduct").value;
	var star  = document.getElementById("txtStar").value;
	var starConvert = parseInt(star);
	const regex2 = /^[\d]{1,}$/g;
	if(product < 1){
		alert("Hãy chọn sản phẩm");
	}
	else if(!regex2.exec(product)){
		alert("Mã sản phẩm có vấn đề");
	}
	else if(starConvert < 1){
		alert("Hãy đánh giá sao cho sản phẩm");
	}
	else document.getElementById("frmVote").submit();
})