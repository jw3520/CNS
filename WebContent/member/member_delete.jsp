<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CNS:회원탈퇴</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/member_delete.css">
<script src="https://code.jquery.com/jquery.js"></script>
<script src="${pageContext.request.contextPath}/js/member_delete.js"></script>
</head>
<body>
	<div id="wrap">
		<jsp:include page="../header.jsp"/>
		<div id="terms_canvas">
			<div id="terms_canvas2">
				<form action="withdrawal" method="post">
					<div class="terms_panel">
						<h3 class="terms_title">ㆍ탈퇴 이유</h3>
						<div class="reason_input_panel">
							<select name="reason" class="reason_input">
								<option value="기타(아래 항목에 작성)">기타(아래 항목에 작성)</option>
								<option value="일시적으로 활동을 쉬고 싶음(향후 재가입 의사 있음)">일시적으로 활동을 쉬고 싶음(향후 재가입 의사 있음)</option>
								<option value="타 사이트의 유사서비스 이용">타 사이트의 유사서비스 이용</option>
								<option value="속도가 느림">속도가 느림</option>
								<option value="회원 정보 변경을 위한 재가입">회원 정보 변경을 위한 재가입</option>
								<option value="자주 이용하지 않음">자주 이용하지 않음</option>
								<option value="찾고자 하는 정보가 없음">찾고자 하는 정보가 없음</option>
							</select>
						</div>
						<div class="blank"></div>
						
						<textarea name="detailed_reason" rows="10" cols="10" placeholder="&#13;&#10;ㆍ 탈퇴 이유 또는 건의사항을 5자 이상 써주세요. (최대 100자)" style="resize: none" maxlength="100"></textarea>
					</div>
					
					<div class="terms_panel">
						<h3 class="terms_title">ㆍ서비스 탈퇴 약관</h3>
						<textarea id="withdrawal_terms" rows="10" cols="10" style="resize: none" readonly></textarea>
						<span class="terms_check"><input type="checkbox"/> 내용을 확인하였으며, 동의합니다.</span>
						<div class="blank"></div>
						<div class="blank"></div>
					</div>
					
					<div class="agree_button_panel">
						<input class="agree_button_box" type="button" onclick="history.back()" value="취소">
						<div class="blank2"></div>
						<input id="agree_button" class="agree_button_box" type="button" value="회원탈퇴">
					</div>
					<div class="blank"></div>
				</form>
			</div>
		</div>
		<jsp:include page="../footer.jsp"/>
	</div>
</body>
</html>