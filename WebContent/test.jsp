<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>이미지 업로드</title>
<script src="https://code.jquery.com/jquery.js"></script>
<script src="http://code.jquery.com/jquery-migrate-1.2.1.js"></script>
<script>
var count = 0;
	function checkFile(f) {
		var uploadFile = f.uploadFile;
		var ext = uploadFile.value;
		
		var file = uploadFile.files[0];
		var _URL = window.URL || window.webkitURL;
		var img = new Image();
		
		if(count >= 30) {
			alert('최대 30개까지만 업로드할 수 있습니다.');
			return false;
		}
		
		if (ext.indexOf('png') == -1 && ext.indexOf('gif') == -1) {
			alert('png/gif 파일만 올릴수 있습니다.');
			input_reset();
			return false;
		}
		
		img.src = _URL.createObjectURL(file);
		img.onload = function() {
			if(img.width != 408 || img.height != 408) {
				alert('이미지 크기를 가로 408px 세로 408px로 맞춰주세요.');
				input_reset();
				return false;
			}
			$('#img_list').append('<div>' + file.name + '</div>');
			$('#count').text(++count);
		}
		input_reset();
	}
	
	function input_reset() {
		if($.browser.msie) {
			$("#uploadFile").replaceWith( $("#filename").clone(true));
		} else {
			$("#uploadFile").val("");
		}
	}
	
	function checkCount() {
		if(count >= 5) {
			$('#wrap').remove();
			$('#last_button').attr('value', '업로드 화면으로')
							 .click(function() {
								 location.href; history.go(0);
							 });
		} else {
			alert('최소 5개의 이미지를 업로드해야 합니다.');
		}
	}
</script>
<style>
.blank {
	height: 50px;
}
font {
	color: gray;
}
</style>
</head>
<body>
	<div id="wrap">
		<ul>
			<li>최소 5개 최대 30개의 이미지</li>
			<li>이미지 크기는 408 x 408</li>
			<li>png 또는 gif의 확장자만 허용</li>
		</ul>
		<form method="post" action="" enctype="multipart/form-data">
			<input type="file" id="uploadFile" name="uploadFile" accept=".png, .gif">
			<input type="button" value="추가" onclick="checkFile(this.form)">
			<div class="blank"></div>
		</form>
	</div>
	<strong>업로드한 이미지 (<span id="count">0</span>)</strong><br>
	<div id="img_list"></div>
	<div class="blank"></div>
	<input id="last_button" type="button" value="작성" onclick="checkCount()">
</body>
</html>