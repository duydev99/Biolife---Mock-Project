document.getElementById("txtSource").addEventListener("change", function(e) {
	const files = e.target.files;
	var strAll = "";
	for (var i = 0; i < files.length; i++) {
		strAll += "<p class='m-1'>" + files[i].name + "</p>";

	}
	document.getElementById("fileAll").innerHTML = strAll;
})

document.getElementById("imgCheckAll").addEventListener("click", function() {
	const validImageType = ['image/gif', 'image/jpeg', 'image/png'];
	const files = document.getElementById("txtSource").files;
	var strAll = "";
	for (var i = 0; i < files.length; i++) {
		if (!validImageType.includes(files[i].type)) {
			strAll += "<p class='m-1 text-danger'>" + files[i].name + " <img src='../images/icon/icon-img-cancel.png' width='35px' height='35px'/></p>";
		} else {
			strAll += "<p class='m-1 text-success'>" + files[i].name + " <img src='../images/icon/icon-img-complated.png' width='35px' height='35px'/></p>";
		}
		document.getElementById("fileAll").innerHTML = strAll;
	}
})

document.getElementById("btnSubmit").addEventListener("click", function() {
	const validImageType = ['image/gif', 'image/jpeg', 'image/png'];
	const files = document.getElementById("txtSource").files;
	var strAll = "";
	var c = 0;
	for (var i = 0; i < files.length; i++) {
		if (!validImageType.includes(files[i].type)) {
			c += 1;
			alert('File "' + files[i].name + '" không phải hình ảnh');
			strAll += "<p class='m-1 text-danger'>" + files[i].name + " <img src='../images/icon/icon-img-cancel.png' width='35px' height='35px'/></p>";
		} else {
			strAll += "<p class='m-1 text-success'>" + files[i].name + " <img src='../images/icon/icon-img-complated.png' width='35px' height='35px'/></p>";
		}
		document.getElementById("fileAll").innerHTML = strAll;
	}

	if (files.length < 1) {
		alert("Hãy chọn hình ảnh muốn thêm");
		return;
	}
	if (c < 1) {
		document.getElementById("frmImage").submit();
		
	}
})
