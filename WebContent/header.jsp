<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css">
<script src="https://code.jquery.com/jquery.js"></script>
<script src="${pageContext.request.contextPath}/js/header.js"></script>
<script>
$(function() {
	/* 게시물 조회 페이지에서 로그인 또는 로그아웃을 요청하면 세션에 referer을 저장 */
	$('.login_button').click(function() {
		var url = document.location.href;
		var root = $('#root').val();
		var referer = document.referrer;
		if(url.indexOf('read') != -1) {
			$.ajax({
				type : 'post',
				url : root + '/ajax',
				data : 'history=' + referer
			});
		}
		
		if($(this).val() == '로그인') {
			location.href= root + '/auth/logIn';
		} else if($(this).val() == '로그아웃') {
			location.href= root + '/auth/logOut';
		}
	});
});
</script>
</head>
<header>
<input type="hidden" id="root" value="${pageContext.request.contextPath}"/>
<input type="hidden" id="info" value="${logInfo.email}"/>
	<div class="logInfo_panel">
		<a class="home" href="${pageContext.request.contextPath}/main">CNS</a>
		<c:choose>
			<c:when test="${logInfo == null}">
				<input class="login_button" type="button" value="로그인"/>
			</c:when>
			<c:otherwise>
				<input class="login_button" type="button" value="로그아웃"/>
				<input class="option_button" type="button"/>
				<div id="logInfo"><img src="${pageContext.request.contextPath}/css/images/logInfo.png"/><p>${logInfo.nickname}</p></div>
			</c:otherwise>
		</c:choose>
	</div>
	
	<div class="menu_panel">
		<!-- 메뉴 -->
		<input class="menu_button" type="button"/>
		<div class="menu_list">
			<ul>
				<li><a href="${pageContext.request.contextPath}/main"><span id="main">홈</span></a></li>
				<li><a href="${pageContext.request.contextPath}/board/list"><span id="new_board">게시판</span></a></li>
				<li><a href="${pageContext.request.contextPath}/board/list?sort=hot"><span id="hot_board">인기게시판</span></a></li>
				<li><a href="${pageContext.request.contextPath}/board/list?sort=top"><span id="top_board">TOP게시판</span></a></li>
			</ul>
		</div>
		<!-- 검색 -->
		<form action="${pageContext.request.contextPath}/board/list" autocomplete="off">
			<input type="hidden" name="pageNum" value="${pageInfo.pageNum != null ? pageInfo.pageNum : 1}"/>
			<input type="hidden" name="amount" value="${pageInfo.amount != null ? pageInfo.amount : 10}"/>
			<c:choose>
				<c:when test="${pageInfo.type == null || pageInfo.type.equals('title')}">
					<input type="button" class="search_type" value="제 목">
					<input type="hidden" name="type" value="title"/>
				</c:when>
				<c:when test="${pageInfo.type != null && pageInfo.type.equals('content')}">
					<input type="button" class="search_type" value="내 용">
					<input type="hidden" name="type" value="content"/>
				</c:when>
				<c:when test="${pageInfo.type != null && pageInfo.type.equals('writer')}">
					<input type="button" class="search_type" value="작성자">
					<input type="hidden" name="type" value="writer"/>
				</c:when>
			</c:choose>
			<input type="text" class="search_input" name="keyword" value="${pageInfo.keyword}"/>
			<input class="search_submit" type="submit"/>
		</form>
	</div>
	
	<div class="logo_panel">
	
	</div>
	
	<div class="sideBar_panel">
		<div class="control_hide_panel"><div class="control_hide"></div></div>
		<div class="detailInfo_panel">
			<div id="nicknameInfo">
				<img src="${pageContext.request.contextPath}/css/images/logInfo.png"/>
				<p id="nick_commu">${logInfo.nickname}<br><a href="${myCommunity.url}" target="_blank" title="${myCommunity.cname} 바로가기">[ ${myCommunity.cname} ]</a></p>
			</div>
			<div>
				<p>이메일 : ${logInfo.email}</p>
				<p>포인트 : <fmt:formatNumber value="${logInfo.point}" pattern="#,###"/> P</p>
				<p>가입일 : ${logInfo.reg_date.toString().substring(0, 10)}</p>
			</div>
		</div>
		<div class="myLog_panel">
			<input class="log_button" type="button" value="내가 쓴 글" onclick="location.href='${pageContext.request.contextPath}/board/list?type=writer&keyword=${logInfo.nickname}'"/>
			<input class="log_button" type="button" value="내가 쓴 댓글" onclick="alert('아직 지원하지 않는 기능입니다.')"/>
		</div>
	</div>
</header>
</html>