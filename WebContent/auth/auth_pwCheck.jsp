<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CNS:비밀번호 확인</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/auth_pwCheck.css">
<script src="https://code.jquery.com/jquery.js"></script>
<script src="${pageContext.request.contextPath}/js/auth_pwCheck.js"></script>
</head>
<body>
<input type="hidden" id="fail" value="${fail}"/>
	<div id="wrap">
	<jsp:include page="../header.jsp"/>
		<div id="logIn_cavas">
			<div id="logIn_cavas2">
				<form action="pwCheck" method="post">
					<h3>회원정보 수정은<br>비밀번호 확인이 필요합니다.</h3>
					<div class="logIn_input_panel">
						<input class="logIn_input" type="text" name="email" placeholder="이메일" maxlength="20" value="${email!=null?email:fail}" readonly>
					</div>
					<div class="blank"></div>
					
					<div class="logIn_input_panel">
						<input class="logIn_input" type="password" name="password" placeholder="비밀번호" maxlength="20">
					</div>
					<div class="blank"></div>
					<c:if test="${fail != null}">
						<div id="logIn_fail_msg">틀린 비밀번호를 입력하셨습니다.<br>다시 입력해주세요.<div class="blank"></div></div>
					</c:if>
					
					<div class="logIn_button_panel">
						<input class="logIn_button_box" type="button" onclick="check(this.form)" value="확인">
					</div>
				</form>
				<div class="blank"></div>
			</div>
		</div>
	<jsp:include page="../footer.jsp"/>
	</div>
</body>
</html>