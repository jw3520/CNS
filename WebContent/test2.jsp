<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CNS:라이트박스 테스트</title>
<link rel="stylesheet" href="css/colorbox.css">
<script src="https://code.jquery.com/jquery.js"></script>
<script src="js/jquery.colorbox.js"></script>
<script>
	var boxWidth = $(window).width() * 0.9;
	var boxHeight = $(window).height() * 0.9;

	$(document).ready(function() {
		$('.open_cb').colorbox();
	});
</script>
<style>

</style>
</head>
<body>
	<a class="open_cb" href="board/read?bno=48">aaaaaa</a>
</body>
</html>