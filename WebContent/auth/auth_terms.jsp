<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CNS:회원가입</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/auth_terms.css">
<script src="https://code.jquery.com/jquery.js"></script>
<script src="${pageContext.request.contextPath}/js/auth_terms.js"></script>
</head>
<body>
	<div id="wrap">
		<jsp:include page="../header.jsp"/>
		<div id="terms_canvas">
			<div id="terms_canvas2">
				<div class="terms_panel">
					<h3 class="terms_title">ㆍ서비스 이용 약관</h3>
					<textarea rows="10" cols="10" style="resize: none" readonly></textarea>
					<span class="terms_check"><input type="checkbox"/> 내용을 확인하였으며, 동의합니다.</span>
					<div class="blank"></div>
				</div>
				
				<div class="terms_panel">
					<h3 class="terms_title">ㆍ개인정보 수집 및 이용 약관</h3>
					<textarea rows="10" cols="10" style="resize: none" readonly></textarea>
					<span class="terms_check"><input type="checkbox"/> 내용을 확인하였으며, 동의합니다.</span>
					<div class="blank"></div>
					<div class="blank"></div>
				</div>
				
				<div class="agree_button_panel">
					<input class="agree_button_box" type="button" onclick="location.href='logIn'" value="취소">
					<div class="blank2"></div>
					<input id="agree_button" class="agree_button_box" type="button" value="동의">
				</div>
				<div class="blank"></div>
			</div>
		</div>
		<jsp:include page="../footer.jsp"/>
	</div>
</body>
</html>