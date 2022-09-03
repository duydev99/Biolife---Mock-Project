document.getElementById("btnSubmit").addEventListener("click",function(){
	var name = document.getElementById("txtName").value;
	var price = document.getElementById("txtPrice").value;
	var amount = document.getElementById("txtAmount").value;
	var category = document.getElementById("txtCategory").value;
	var manufacturer = document.getElementById("txtManufacturer").value;
	if(!checkName(name)){}
	else if(!checkPrice(price)){}
	else if(!checkAmount(amount)){}
	else if(!checkCategory(category)){}
	else if(!checkManufacturer(manufacturer)){}
	else document.getElementById("frmProduct").submit();

})

function checkName(name){
	const regex = /[<|>|/|"|'|-|_|!|~|`|@|#|$|%|^|&|*|?|+|=]/;
	if(name == "" || name == null){
		alert("Hãy nhập tên sản phẩm");
		return false;
	}else if(regex.exec(name)){
		alert("Tên sản phẩm không được có ký tự đặc biệt");
		return false;
	}else if(name.length<6 || name.length >50){
		alert("Tên sản phẩm phải từ 6 đến 50 ký tự");
	}
	 else return true
}

function checkPrice(price){
	var regex = /^[1-9][\d]{3,}$/g;
	if(price == "" || price == null){
		alert("Hãy nhập giá sản phẩm");
		return false;
	}else if (!regex.exec(price)){
		alert("Giá sản phẩm phải là số và ký tự bắt đầu phải từ 1-9. Giá trị từ 1000 VNĐ trở lên");
		return false;
	}else return true;
}

function checkAmount(amount){
	var regex = /^[\d]{1,}$/g;
	if(amount == "" || amount == null){
		alert("Hãy nhập số lượng sản phẩm");
		return false;
	}else if (!regex.exec(amount)){
		alert("Số lượng sản phẩm phải là số");
		return false;
	}else return true;
}

function checkCategory(category){
	var regex = /^[\d]{1,}$/g;
	if(category == 0){
		alert("Hãy chọn loại sản phẩm");
		return false;
	}else if(!regex.exec(category)){
		alert("Bạn đang cố sửa mã của loại sản phẩm? Mã của loại sản phẩm phải là số");
		return false;
	}else return true;
}

function checkManufacturer(manufacturer){
	var regex = /^[\d]{1,}$/g;
	if(manufacturer == 0){
		alert("Hãy chọn nhà cung cấp");
		return false;
	}else if(!regex.exec(manufacturer)){
		alert("Bạn đang cố sửa mã của nhà cung cấp? Mã của nhà cung cấp phải là số");
		return false;
	}else return true;
}