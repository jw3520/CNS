<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CNS:글 수정</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/board_update.css">
<script src="https://code.jquery.com/jquery.js"></script>
<script src="${pageContext.request.contextPath}/js/board_update.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/ckeditor/ckeditor.js"></script>
</head>
<body>
<input type="hidden" id="root" value="${pageContext.request.contextPath}"/>
	<div id="wrap">
	<jsp:include page="../header.jsp" />
		<div id="write_cavas">
			<div id="write_cavas2">
				<form action="modify" method="post">
					<input type="hidden" name="bno" value="${board.bno}">
					<input type="hidden" name="mno" value="${board.mno}">
					<div class="blank"></div>
					<div class="write_input_panel">
						<input class="write_input" type="text" name="subject" placeholder="제목" maxlength="30" value="${board.subject}">
					</div>
					<div class="blank"></div>
					
					<div class="write_input_panel">
						<input class="write_input" type="url" name="link" placeholder="동영상 또는 사이트 링크를 입력해주세요. (선택) " maxlength="100" value="${board.link}">
					</div>
					<div class="blank"></div>
					
					<div class="content_input_panel">
						<textarea class="content_input" id="content" name="content" placeholder="내용" rows="20" cols="10" style="resize: none">${board.content}</textarea>
					</div>
					<div class="blank"></div>
					
					<div class="write_button_panel">
						<input class="write_button_box" id="cancel_button" type="button" value="취소" onclick="history.back()">
						<input class="write_button_box" id="write_button" type="button" value="수정" onclick="check(this.form)">
					</div>
					<div class="blank"></div>
				</form>
			</div>
		</div>
	<jsp:include page="../footer.jsp" />
	</div>
</body>
</html>