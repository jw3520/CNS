<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>커뮤니티 네트워크 서비스</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css">
<script src="https://code.jquery.com/jquery.js"></script>
<script src="${pageContext.request.contextPath}/js/main.js"></script>
<script>
if (window.location.protocol != 'https:' && document.location.href.indexOf('localhost') == -1)
    location.href = location.href.replace('http:', 'https:');
$(function() {
	console.log($('#e_msg').val());
});
</script>
</head>
<body>
<input type="hidden" id="refresh" value="${refresh}"/>
<input type="hidden" id="slide" value="${slide}"/>
<input type="hidden" id="commu_count" value="${crawlerList.size()}"/>
<input type="hidden" id="e_msg" value="${e_msg}"/>
	<div id="wrap">
	<jsp:include page="header.jsp"/>
		<div class="control_left_panel">
			<input type="button" class="control_left" title="이전 커뮤니티">
		</div>
		
		<div class="control_right_panel">
			<input type="button" class="control_right" title="다음 커뮤니티">
		</div>
		
		<div id="commu_canvas">
			<div id="commu_slider">
				<c:forEach var="crawler" items="${crawlerList}">
				<c:set var="rank" value="${1}"/>
				<div class="commu">
					<table class="commu_table">
						<tr><th colspan="2">
							<a class="logo" href="${crawler.getHomeUrl()}" target="_blank">
								<img src="${crawler.getLogo()}"/>
							</a>
						</th></tr>
					<c:forEach var="articles" items="${crawler.articles}" begin="${0}" end="${4}">
						<tr title='${articles.title}'>
							<td class="td_rank">${rank}</td><c:set var="rank" value="${rank + 1}"/>
							<td class="td_title">
								<a href="${articles.url}">${articles.title}</a>
								<font class="sub1">${articles.commentCount}</font><br>
								<font class="sub2">${articles.date}</font>
							</td>
						</tr>
					</c:forEach>
					</table>
					<table class="commu_table">
						<tr><th colspan="2">
							<a class="more" href="${crawler.getMoreUrl()}" target="_blank">
								<img src="css/images/button_more.png"/>
							</a>
						</th></tr>
					<c:forEach var="articles" items="${crawler.getArticles()}" begin="${5}" end="${9}">
						<tr title='${articles.title}'>
							<td class="td_rank">${rank}</td><c:set var="rank" value="${rank + 1}"/>
							<td class="td_title">
								<a href="${articles.url}">${articles.title}</a>
								<font class="sub1">${articles.commentCount}</font><br>
								<font class="sub2">${articles.date}</font>
							</td>
						</tr>
					</c:forEach>
					</table>
				</div>
				</c:forEach>
			</div>
		</div><br>
		
		<div class="control_stop_panel">
			<img class="control_stop" src="css/images/button_stop.png" title="슬라이드 재생">
		</div>
		<div class="control_refresh_panel">
			<img class="control_refresh" src="css/images/button_refresh.png" title="새로고침">
		</div>
		
		<div class="blank"></div>
		<div class="blank"></div>
		<div class="blank"></div>
		
		<div id="commu_canvas">
			<div id="commu_slider">
				<c:set var="rank" value="${1}"/>
				<div class="commu">
					<table class="commu_table">
						<tr><th colspan="2">
							<a class="logo" href="${pageContext.request.contextPath}/board/list">
								<font>CNS <img src="css/images/board_${sort}.png"/> 게시판</font>
							</a>
						</th></tr>
						<c:forEach var="board" items="${boardList}" begin="${0}" end="${4}" varStatus="status">
						<tr title='${board.subject}'>
							<td class="td_rank">${rank}</td><c:set var="rank" value="${rank + 1}"/>
							<td class="td_title">
								<a href="${pageContext.request.contextPath}/board/read?bno=${board.bno}">${board.subject}</a>
								<font class="sub1">[${rcList[status.index]}]</font><br>
								<font class="sub2">${board.getTypeChangeDate()}</font>
							</td>
						</tr>
						</c:forEach>
					</table>
					<table class="commu_table">
						<tr><th colspan="2">
							<a class="more" href="${pageContext.request.contextPath}/board/list?sort=${sort}">
								<img src="css/images/button_more.png"/>
							</a>
						</th></tr>
						<c:forEach var="board" items="${boardList}" begin="${5}" end="${9}" varStatus="status">
						<tr title='${board.subject}'>
							<td class="td_rank">${rank}</td><c:set var="rank" value="${rank + 1}"/>
							<td class="td_title">
								<a href="${pageContext.request.contextPath}/board/read?bno=${board.bno}">${board.subject}</a>
								<font class="sub1">[${rcList[status.index]}]</font><br>
								<font class="sub2">${board.getTypeChangeDate()}</font>
							</td>
						</tr>
						</c:forEach>
					</table>
				</div>
			</div>
		</div><br>
		
	<jsp:include page="footer.jsp"/>
	</div>
</body>
</html>