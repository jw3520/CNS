<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CNS:회원가입</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/member_insert.css">
<script src="https://code.jquery.com/jquery.js"></script>
<script src="${pageContext.request.contextPath}/js/member_insert.js"></script>
</head>
<body>
<input type="hidden" id="root" value="${pageContext.request.contextPath}"/>
	<div id="wrap">
	<jsp:include page="../header.jsp" />
		<div id="join_cavas">
			<div id="join_cavas2">
				<form action="join" method="post">
					<div class="blank"></div>
					<div class="join_input_panel">
						<input class="join_input" type="text" name="email" placeholder="이메일" maxlength="30" value="${fail}">
					</div>
					<div class="blank"></div>
					
					<div class="fail_msg" id="unusable_email_msg">이미 가입되어있는 이메일입니다.<div class="blank"></div></div>
					<div class="fail_msg" id="wrong_email_msg">이메일 형식(OOO@OOO.OOO)만 입력할 수 있습니다.<div class="blank"></div></div>
					
					<div class="join_input_panel">
						<input class="join_input" type="password" name="password" placeholder="비밀번호" maxlength="20">
					</div>
					<div class="blank"></div>
					
					<div class="join_input_panel">
						<input class="join_input" type="password" name="password_check" placeholder="비밀번호 확인" maxlength="20">
					</div>
					<div class="blank"></div>
					
					<div class="fail_msg" id="different_password_msg">비밀번호가 일치하지 않습니다.<div class="blank"></div></div>
					
					<div class="join_input_panel">
						<input class="join_input" type="text" name="nickname" placeholder="닉네임" maxlength="10">
					</div>
					<div class="blank"></div>
					
					<div class="fail_msg" id="unusable_nickname_msg">이미 사용 중인 닉네임입니다.<div class="blank"></div></div>
					
					<div class="join_input_panel">
						<select name="cno" class="join_input">
							<c:forEach var="community" items="${commuList}">
								<option value="${community.cno}">${community.cname}</option>
							</c:forEach>
						</select>
					</div>
					<div class="blank"></div>
					
					<div class="join_button_panel">
						<input class="join_button_box" type="button" value="회원가입">
					</div>
				</form>
			</div>
		</div>
	<jsp:include page="../footer.jsp" />
	</div>
</body>
</html>