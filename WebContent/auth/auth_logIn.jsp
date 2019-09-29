<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CNS:로그인</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/auth_logIn.css">
<script src="https://code.jquery.com/jquery.js"></script>
<script src="${pageContext.request.contextPath}/js/auth_logIn.js"></script>
<script>
if (window.location.protocol != 'https:' && document.location.href.indexOf('localhost') == -1)
    location.href = location.href.replace('http:', 'https:');
</script>
</head>
<body>
<input type="hidden" id="fail" value="${fail}"/>
	<div id="wrap">
	<jsp:include page="../header.jsp"/>
		<div id="logIn_cavas">
			<div id="logIn_cavas2">
				<form action="${pageContext.request.contextPath}/auth/logIn" method="post">
					<div class="blank"></div>
					<div class="logIn_input_panel">
						<input class="logIn_input" type="text" name="email" placeholder="이메일" maxlength="20" value="${fail}">
					</div>
					<div class="blank"></div>
					
					<div class="logIn_input_panel">
						<input class="logIn_input" type="password" name="password" placeholder="비밀번호" maxlength="20">
					</div>
					<div class="blank"></div>
					<c:if test="${fail != null}">
						<div id="logIn_fail_msg">아이디 또는 비밀번호를 다시 확인하세요.<br>등록되지 않은 아이디거나, 잘못 입력하셨습니다.
							<div class="blank"></div>
						</div>
					</c:if>
					
					<div class="logIn_button_panel">
						<input class="logIn_button_box" type="button" onclick="check(this.form)" value="로그인">
					</div>
				</form>
				<div class="blank"></div>
				<div class="logIn_sub">
					<a href="terms">회원가입</a> | <a href="../member/find">아이디/비밀번호 찾기</a>
				</div>
			</div>
		</div>
	<jsp:include page="../footer.jsp"/>
	</div>
</body>
</html>