<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CNS:회원정보 수정</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/member_update.css">
<script src="https://code.jquery.com/jquery.js"></script>
<script src="${pageContext.request.contextPath}/js/member_update.js"></script>
</head>
<body>
<input type="hidden" id="root" value="${pageContext.request.contextPath}"/>
<input type="hidden" id="pre_nickname" value="${logInfo.nickname}"/>
<input type="hidden" id="pwCheck" value="${pwCheck}"/>
	<div id="wrap">
	<jsp:include page="../header.jsp" />
		<div id="join_cavas">
			<div id="join_cavas2">
				<form action="update" method="post">
					<div class="blank"></div>
					<div class="join_input_panel">
						<input type="hidden" name="email" value="${logInfo.email}">
						<input class="join_input" id="join_input_id" type="text" value="${logInfo.email} (수정불가)" readonly>
					</div>
					<div class="blank"></div>
					
					<div class="join_input_panel">
						<input class="join_input" type="password" name="password" placeholder="새 비밀번호" value="${logInfo.password}" maxlength="20">
					</div>
					<div class="blank"></div>
					
					<div class="join_input_panel">
						<input class="join_input" type="password" name="password_check" placeholder="새 비밀번호 확인" value="${logInfo.password}" maxlength="20">
					</div>
					<div class="blank"></div>
					
					<div class="fail_msg" id="different_password_msg">비밀번호가 일치하지 않습니다.<div class="blank"></div></div>
					
					<div class="join_input_panel">
						<input class="join_input" type="text" name="nickname" placeholder="닉네임" value="${logInfo.nickname}" maxlength="10">
					</div>
					<div class="blank"></div>
					
					<div class="fail_msg" id="unusable_nickname_msg">이미 사용 중인 닉네임입니다.<div class="blank"></div></div>
					
					<div class="join_input_panel">
						<select name="cno" id="commu_select" class="join_input">
							<c:forEach var="community" items="${commuList}">
								<c:choose>
									<c:when test="${community.cno == logInfo.cno}"><option value="${community.cno}" selected>${community.cname}</option></c:when>
									<c:otherwise><option value="${community.cno}">${community.cname}</option></c:otherwise>
								</c:choose>
							</c:forEach>
						</select>
					</div>
					<div class="blank"></div>
					
					<div class="join_button_panel">
						<input class="join_button_box" type="button" value="회원정보 수정">
					</div>
				</form>
				<div class="blank"></div>
				<div class="update_sub">
					<a href="withdrawal">회원탈퇴</a>
				</div>
			</div>
		</div>
	<jsp:include page="../footer.jsp" />
	</div>
</body>
</html>