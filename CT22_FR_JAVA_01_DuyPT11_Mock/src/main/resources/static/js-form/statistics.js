


function getQuarter() {
	var strOption = '';
	for (var i = 1; i <= 4; i++) {
		strOption += '<li><a class="dropdown-item border-radius-md" href="/statistics/total?key=quarter&value='+i+'">'+i+'</a></li>';
	}
	var str = '<h6>Danh sách loại sản phẩm</h6>'
		+ '<div class="dropdown">'
		+ '<a class="cursor-pointer btn btn-default dropdown-toggle" id="dropdown0" data-bs-toggle="dropdown" aria-expanded="false">'
		+ 'Chọn thời gian'
		+ '</a>'
		+ '<ul class="dropdown-menu px-2 py-3 ms-sm-n4 ms-n5" aria-labelledby="dropdown0">'
		+ '<li><a class="dropdown-item border-radius-md" href="${path}/statistics/total?key=year&value=-1">Năm</a></li>'
		+ '<li><a class="dropdown-item border-radius-md" onClick="getQuarter()" href="javascript:;">Quý</a></li>'
		+ '<li><a class="dropdown-item border-radius-md" href="javascript:;">Tháng</a></li>'
		+ '</ul>'
		+ '</div>'
		+ '<div class="dropdown ">'
		+ '<a class="cursor-pointer btn btn-default dropdown-toggle" id="dropdown1" data-bs-toggle="dropdown" aria-expanded="false">'
		+ 'Chọn quý'
		+ '</a>'
		+ '<ul class="dropdown-menu ms-sm-n4 ms-n5" aria-labelledby="dropdown1">'
		+ strOption
		+ '</ul>'
		+ '</div>';
	document.getElementById("showChangeTime").innerHTML = "";
	document.getElementById("showChangeTime").innerHTML = str;
}
