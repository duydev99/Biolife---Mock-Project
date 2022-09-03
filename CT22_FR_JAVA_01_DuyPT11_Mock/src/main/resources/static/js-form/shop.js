function category(e, path) {
	var regex = /^[\d]{1,}$/g;
	const params = new URLSearchParams(window.location.search);
	if (e.value > 0) {
		if (!regex.exec(e.value)) {
			alert("Id không hợp lệ");
		} else {
			if (params.has("key1")) {
				document.getElementById("frmCategory").href = path + "&filter=category&key2=" + e.value;
			} else {
				document.getElementById("frmCategory").href = path + "&filter=category&key=" + e.value;
			}
		}
	} else {
		if (params.has("key1")){
			document.getElementById("frmCategory").href = path + "&filter=all&key2=-1";
		}else{
			document.getElementById("frmCategory").href = path + "&filter=all&key=-1";
		}
		
	}
}

function manufacturer(e, path) {
	var regex = /^[\d]{1,}$/g;
	const params = new URLSearchParams(window.location.search);
	if (e.value > 0) {
		if (!regex.exec(e.value)) {
			alert("Id không hợp lệ");
		} else {
			if (params.has("key1")){
				document.getElementById("frmManufacturer").href = path + "&filter=manufacturer&key2=" + e.value;
			}
			else{document.getElementById("frmManufacturer").href = path + "&filter=manufacturer&key=" + e.value;}
		}
	} else {
		if (params.has("key1")) {document.getElementById("frmManufacturer").href = path + "&filter=all&key2=-1";}
		else{document.getElementById("frmManufacturer").href = path + "&filter=all&key=-1";}
	}
}

function price(e, path) {
	var regex = /^[\d]{1,}$/g;
	const params = new URLSearchParams(window.location.search);
	if (e.value > 0) {
		if (!regex.exec(e.value)) {
			alert("Code không hợp lệ");
		} else {
			if (params.has("key1")){
				document.getElementById("frmPrice").href = path + "&filter=price&key2=" + e.value;
			}
			else{document.getElementById("frmPrice").href = path + "&filter=price&key=" + e.value;}
		}
	} else {
		if (params.has("key1")) {document.getElementById("frmPrice").href = path + "&filter=all&key2=-1";}
		else{document.getElementById("frmPrice").href = path + "&filter=all&key=-1";}
	}
}

function orderBy(e, path) {
	if (e.value != "default" && e.value != "name" && e.value != "rating" && e.value != "time" && e.value != "price-asc" && e.value != "price-desc") {
		alert("Key order by không hợp lệ");
	} else {
		document.getElementById("frmOrderBy").href = path + "&orderby=" + e.value;
	}
}