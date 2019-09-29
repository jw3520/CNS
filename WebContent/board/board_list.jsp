<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CNS:게시판 리스트</title>
<link id="list_css" rel="stylesheet" href="${pageContext.request.contextPath}/css/board_list.css">
<script src="https://code.jquery.com/jquery.js"></script>
<script id="list_js" src="${pageContext.request.contextPath}/js/board_list.js"></script>
</head>
<body>
<input type="hidden" id="root" value="${pageContext.request.contextPath}"/>
<input type="hidden" id="checkLogInfo" value="${session.getAttribute('logInfo')}"/>
<input type="hidden" id="pageNum" value="${pageInfo.pageNum}"/>
<input type="hidden" id="amount" value="${pageInfo.amount}"/>
<input type="hidden" id="sort" value="${pageInfo.sort}"/>
<input type="hidden" id="type" value="${pageInfo.type}"/>
<input type="hidden" id="keyword" value="${pageInfo.keyword}"/>
	<div id="wrap">
	<jsp:include page="../header.jsp" />
		<div id="board_canvas">
			<div>
				<div class="board_button">
					<a href="list?sort=${pageInfo.sort}" title="새로고침">
						<img id="button_refresh" src="${pageContext.request.contextPath}/css/images/board_refresh.png"/>
					</a>
				</div>
				<c:if test="${pageInfo.type != null}">
					<c:set var="search_param" value="&type=${pageInfo.type}&keyword=${pageInfo.keyword}"/>
				</c:if>
				<div class="board_sort" id="button_new" title="최신순으로 전체 글을 보여줍니다."><img src="${pageContext.request.contextPath}/css/images/board_new.png"/><a href="list?sort=new${search_param}">최신</a></div>
				<div class="board_sort" id="button_hot" title="인기순으로 24시간 내의 글을 보여줍니다."><img src="${pageContext.request.contextPath}/css/images/board_hot.png"/><a href="list?sort=hot${search_param}">인기</a></div>
				<div class="board_sort" id="button_top" title="인기순으로 전체 글을 보여줍니다."><img src="${pageContext.request.contextPath}/css/images/board_top.png"/><a href="list?sort=top${search_param}">TOP</a></div>
				<div class="board_button"><a href="javascript:checkLogIn()" title="글쓰기"><img id="button_write" src="${pageContext.request.contextPath}/css/images/board_write.png"/></a></div>
			</div>
			<c:if test="${boardList.size() == 0}">
				<c:choose>
					<c:when test="${pageInfo.sort.equals('hot')}">
						<div id="empty_list"><h3>24시간 내에 올라온 게시물이 없습니다.</h3></div>
					</c:when>
					<c:when test="${pageInfo.type != null}">
						<div id="empty_list"><h3>"${pageInfo.keyword}"로 검색된 게시물이 없습니다.</h3></div>
					</c:when>
					<c:otherwise>
						<div id="empty_list"><h3>"게시물이 없습니다.</h3></div>
					</c:otherwise>
				</c:choose>
			</c:if>
			<table class="board_table">
			<c:forEach var="board" items="${boardList}" varStatus="status">
				<tr class="tr_main">
					<td class="td_thumb" rowspan="2"><div><img src="${board.getThumb()}"></div></td>
					<td class="td_subject" colspan="2">
						<a href="read?bno=${board.bno}${pageInfo.getListLink('&')}">${board.subject}&nbsp;</a>
						<span class="reply_count" title="${rcList[status.index]}개의 댓글">[${rcList[status.index]}]</span><br>
					</td>
					<td class="td_vote" rowspan="2" data-case="${board.getVoteCase()}" title="추천 ${board.up_count} | 반대 ${board.down_count}">${board.up_count - board.down_count}</td>
				</tr>
				<tr class="tr_sub">
					<td class="td_date" title="${board.reg_date.toString().substring(0, 19)}">
						${board.getTypeChangeDate()}
					</td>
					<td class="td_writer">
						${board.writer}
					</td>
				</tr>
			</c:forEach>
			<c:if test="${boardList.size() != 0}">
				<tr><td colspan="4" id="loading_panel"><img src="${pageContext.request.contextPath}/css/images/list_loading.gif"></td></tr>
			</c:if>
			</table>
		</div>
		
		<div class="control_side_panel">
			<input type="button" id="control_top" title="맨 위로 가기">
		</div>
	<jsp:include page="../footer.jsp" />
	</div>
</body>
</html>