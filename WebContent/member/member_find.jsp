<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CNS:아이디/비밀번호 찾기</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/member_find.css">
<script src="https://code.jquery.com/jquery.js"></script>
<script src="${pageContext.request.contextPath}/js/member_find.js"></script>
</head>
<body>
<input type="hidden" id="root" value="${pageContext.request.contextPath}"/>
<input type="hidden" id="fail" value="${fail}"/>
	<div id="wrap">
	<jsp:include page="../header.jsp" />
		<div id="find_cavas">
			<div id="find_cavas2">
				<form action="find" method="post">
					<div class="blank"></div>
					<div class="find_input_panel">
						<input class="email_input" type="text" name="email" placeholder="이메일" maxlength="30" value="${fail}">
						<input class="send_button_box" type="button" value="전송">
					</div>
					<div class="blank"></div>
					
					<div class="good_msg" id="exist_email_msg">이 이메일로 코드를 보내시려면 전송 버튼을 누르세요.<div class="blank"></div></div>
					<div class="fail_msg" id="unusable_email_msg">해당 이메일로 가입된 계정이 없습니다.<div class="blank"></div></div>
					<div class="fail_msg" id="wrong_email_msg">이메일 형식(OOO@OOO.OOO)만 입력할 수 있습니다.<div class="blank"></div></div>
					
					<div class="find_input_panel">
						<input class="find_input" type="text" name="code" placeholder="전송코드" maxlength="20">
					</div>
					<div class="blank"></div>
					
					<div class="fail_msg" id="different_code_msg">전송코드가 일치하지 않습니다.<div class="blank"></div></div>
					
					<div class="find_button_panel">
						<input class="find_button_box" type="button" value="확인">
					</div>
				</form>
			</div>
		</div>
	<jsp:include page="../footer.jsp" />
	</div>
</body>
</html>