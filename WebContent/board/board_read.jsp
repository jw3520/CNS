<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CNS:${board.subject}</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/board_read.css">
<script src="https://code.jquery.com/jquery.js"></script>
<script src="${pageContext.request.contextPath}/js/board_read.js"></script>
</head>
<body>
<input type="hidden" id="root" value="${pageContext.request.contextPath}"/>
<input type="hidden" id="logInfo_mno" value="${logInfo.mno}"/>
<input type="hidden" id="logInfo_writer_rere" value="${logInfo.nickname}[${logInfo_cname}]"/>
<input type="hidden" id="writer_mno" value="${board.mno}"/>
<input type="hidden" id="vote" value="${vote}"/>
<input type="hidden" id="history" value="${history}"/>
	<div id="wrap">
	<jsp:include page="../header.jsp" />
		<div class="read_cavas">
			<div class="read_cavas2">
				<div class="blank"></div>
				<div class="subject">${board.subject}</div>
				<div class="blank"></div>
				<div class="subInfo">
					<span class="info">
						<a id="info_writer" title="${board.writer}의 작성 글 검색" href="${pageContext.request.contextPath}/board/list?type=writer&keyword=${board.writer.substring(0, board.writer.indexOf('['))}">${board.writer}</a>
						<span class="line">&nbsp;|&nbsp;</span>
					</span>
					<span class="info" title="${board.reg_date.toString().substring(0, 19)}">${board.getTypeChangeDate()}</span>
					<span class="count" id="vote_count2" title="추천 ${board.up_count} | 반대 ${board.down_count}">투표 수 ${board.up_count - board.down_count}</span>
					<span class="count">댓글 수 ${reply_count}<span class="line">&nbsp;|&nbsp;</span></span>
					<span class="count">조회 수 ${board.view_count}<span class="line">&nbsp;|&nbsp;</span></span>
				</div>
				
				<hr>
				<div class="content_panel">
					<div class="content" id="content">${board.content}</div>
				</div>
				<div class="blank"></div>
				<div class="blank"></div>
				<div class="blank"></div>
				<div class="blank"></div>
				<div class="blank"></div>
				<div class="link_panel">
					<div class="vote_panel">
						<div id="vote_up"><img src="${pageContext.request.contextPath}/css/images/vote_up.png"></div>
						<div id="vote_count" title="추천 ${board.up_count} | 반대 ${board.down_count}">${board.up_count - board.down_count}</div>
						<div id="vote_down"><img src="${pageContext.request.contextPath}/css/images/vote_down.png"></div>
					</div>
					<a href="${board.link}" class="link" target="_blank">${board.link}</a>
				</div>
				<hr>
				
				<div class="read_button_panel">
					<!-- 게시글 조회 페이지에서 로그인을 시도 했을 경우 이전페이지가 로그인 페이지이기 때문에 해당 경로를 처리 -->
					<c:choose>
						<c:when test="${history == null}">
							<input class="read_button_box" id="back_button" type="button" value="뒤로가기" onclick="location.href='list${pageInfo.getListLink('?')}'">
						</c:when>
						<c:otherwise>
							<input class="read_button_box" id="back_button" type="button" value="뒤로가기" onclick="location.href='${history}'">
						</c:otherwise>
					</c:choose>
					
					<form action="modify" method="post">
						<input type="hidden" id="bno" name="bno" value="${board.bno}"/>
						<input type="hidden" name="mno" value="${board.mno}"/>
						<input class="read_button_box2" id="update_button" type="button" value="수정">
					</form>
					<form action="remove" method="post">
						<input type="hidden" name="bno" value="${board.bno}"/>
						<input type="hidden" name="mno" value="${board.mno}"/>
						<input class="read_button_box2" id="delete_button" type="button" value="삭제">
					</form>
				</div>
				<div class="blank"></div>
			</div>
		</div>
		<div class="blank"></div>
		
		<!---------- 댓글 ---------->
		<div class="read_cavas">
			<div class="reply_wrap">
			<div class="read_cavas2">
				<div class="reply_input_panel">
					<c:choose>
						<c:when test="${logInfo != null}">
							<span id="logInfo_writer"><c:out value="${logInfo.nickname}[${logInfo_cname}]"></c:out></span>
							<textarea id="reply_input" name="reply_input" style="resize: none" maxlength="500" rows="10" cols="50" wrap="hard" placeholder="명예훼손 및 권리침해 등의 문제를 야기하는 댓글은 제재를 받을 수 있습니다."></textarea>
						</c:when>
						<c:otherwise>
							<span><c:out value="댓글 작성"></c:out></span>
							<textarea id="reply_input" name="reply_input" style="resize: none" maxlength="500" placeholder="댓글 작성은 로그인이 필요합니다." readonly="readonly"></textarea>
						</c:otherwise>
					</c:choose>
					<div class="read_button_panel" id="reply_button_panel">
						<input class="read_button_box2" id="reply_button" type="button" value="작성">
					</div>
				</div>
				<div style="clear: both;"></div>
			</div>
			</div>
			<table class="reply_table">
				<c:forEach var="reply" items="${replyList}">
					<tr>
						<td>
							<span class="re_sign"><input type="hidden" name="re_togle" value="${reply.rno != reply.master}"></span>
							<span class="reply_info">
								<c:choose>
									<c:when test="${(reply.writer).equals('삭제된 댓글')}">
										<c:set var="re_nickname" value="${null}"/>
									</c:when>
									<c:otherwise>
										<c:set var="re_nickname" value="${reply.writer.substring(0, reply.writer.indexOf('['))}"/>
									</c:otherwise>
								</c:choose>
								<a class="reply_writer" title="${re_nickname}의 작성 글 검색" href="${pageContext.request.contextPath}/board/list?type=writer&keyword=${re_nickname}">${reply.writer}<input type="hidden" value="${re_nickname}" class="re_nick"></a>
								<span class="line">&nbsp;|&nbsp;</span>
								<span title="${reply.reg_date.toString().substring(0, 19)}">${reply.getTypeChangeDate()}</span>
							</span>
						</td>
						<td>
							<span class="reply_sub_button">
								<span class="reply_reg"><img src="${pageContext.request.contextPath}/css/images/reply_insert.png"> 댓글 쓰기</span>
								<span class="reply_remove">
									<span class="line">&nbsp;|&nbsp;</span>
									<input class="reply_rno" type="hidden" value="${reply.rno}">
									<input class="reply_mno" type="hidden" value="${reply.mno}">
									<input class="reply_master" type="hidden" value="${reply.master}">
									<img src="${pageContext.request.contextPath}/css/images/reply_delete.png"> 삭제</span>
							</span>
						</td>
					</tr>
					<tr>
						<td class="td_rc" colspan="2">
							<span class="re_sign2"></span>
							<div class="reply_content">${reply.content}</div>
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	<jsp:include page="../footer.jsp" />
	</div>
</body>
</html>