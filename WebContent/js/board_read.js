$(function() {
	var root = $('#root').val();
	var bno = $('#bno').val();
	var writer_mno = $('#writer_mno').val();
	var logInfo_mno = $('#logInfo_mno').val();
	var logInfo_writer_rere = $('#logInfo_writer_rere').val();
	var vote = $('#vote');
	var rereTogle = false;
	var timer;
	
	setting();
	
	//글 내용 높이만큼 컨테이너 높이 조정
	$('.content').css('height', $('.content').prop('scrollHeight'));
	
	//작성 버튼 활성화
	$('input[value=작성]').show();
	
	// 로그인 정보 키 값과 해당 게시글의 작성자의 키 값이 일치한다면 수정/삭제 버튼 보이기
	if(writer_mno == logInfo_mno) {
		$('.read_button_box2').show();
		$('#update_button').click(function() {
			$(this).parent().submit();
		});
		$('#delete_button').click(function() {
			var check = confirm('정말로 삭제하시겠습니까?');
			if(check) {
				$(this).parent().submit();
			}
		});
	}
	
	//로그인 정보에 대한 vote 정보 값이 있다면 표시
	if(vote.val() == '1') {
		$('#vote_up img').css('-webkit-filter', 'grayscale(0%)');
	} else if(vote.val() == '-1') {
		$('#vote_down img').css('-webkit-filter', 'grayscale(0%)');
	}
	
	//추천 버튼(디바운싱)
	$('#vote_up img').click(function() {
		var order = '';
		
		if(timer) {
			clearTimeout(timer);
		}
		
		timer = setTimeout(function() {
			if(logInfo_mno == '') {
				alert('로그인이 필요합니다.');
			} else {
				if(vote.val() == '1') {
					order = 'du';
					vote.val('');
				} else if (vote.val() == '') {
					order = 'iu';
					vote.val('1');
				} else {
					alert('추천과 반대 중 하나만 가능합니다.');
				}
				
				if(order != '') {
					$.ajax({
						type : 'post',
						url : root + '/ajax',
						data : 'vote=' + order + bno,
						success : function() {
							if(order == 'du') {
								$('#vote_up img').css('-webkit-filter', 'grayscale(100%)');
								$('#vote_count').text(parseInt($('#vote_count').text()) - 1);
								vote.val('');
							} else {
								$('#vote_up img').css('-webkit-filter', 'grayscale(0%)');
								$('#vote_count').text(parseInt($('#vote_count').text()) + 1);
								vote.val('1');
							}
							$('#vote_count2').text('추천 ' + $('#vote_count').text());
						}
					});
				}
			}
		}, 1000);
	});
	
	//반대 버튼(디바운싱)
	$('#vote_down img').click(function() {
		var order = '';
		
		if(timer) {
			clearTimeout(timer);
		}
		
		timer = setTimeout(function() {
			if(logInfo_mno == '') {
				alert('로그인이 필요합니다.');
			} else {
				if(vote.val() == '-1') {
					order = 'dd';
					vote.val('');
				} else if (vote.val() == '') {
					order = 'id';
					vote.val('-1');
				} else {
					alert('추천과 반대 중 하나만 가능합니다.');
				}
				
				if(order != '') {
					$.ajax({
						type : 'post',
						url : root + '/ajax',
						data : 'vote=' + order + bno,
						success : function() {
							if(order == 'dd') {
								$('#vote_down img').css('-webkit-filter', 'grayscale(100%)');
								$('#vote_count').text(parseInt($('#vote_count').text()) + 1);
								vote.val('');
							} else {
								$('#vote_down img').css('-webkit-filter', 'grayscale(0%)');
								$('#vote_count').text(parseInt($('#vote_count').text()) - 1);
								vote.val('-1');
							}
							$('#vote_count2').text('추천 ' + $('#vote_count').text());
						}
					});
				}
			}
		}, 1000);
	});
	
	//업로드한 이미지가 큰 경우에는 사이즈 조절
	$('#content img').each(function() {
		if($(this).width() > 700) {
			var width = $(this).width();
			var height = $(this).height();
			$(this).width(700);
			$(this).height(height * 700 / width);
			$('#content').height($('#content').height() - (height - height * 700 / width));
		}
	});
	
	/*------------ 댓글 ------------*/
	//게시물에 댓글 쓰기
	$('#reply_button').click(function() {
		write_reply();
	});
	
	//댓글 리스트
	function refresh_ReplyList() {
		$.ajax({
			type : 'post',
			url : root + '/ajax',
			data : 'bno=' + bno,
			dataType : 'json',
			success : function(list) {
				var str = '<table class="reply_table">'
				list.forEach(function(item) {
					str    +=  	'<tr>'
							+	'	<td>'
							+	'		<span class="re_sign"><input type="hidden" name="re_togle" value="' + (!(item.rno == item.master)).toString() + '"></span>'
							+	'		<span class="reply_info">'
							+	(item.writer == "삭제된 댓글" ? '<c:set var="re_nickname" value="${null}"/>' : '<c:set var="re_nickname" value="' + item.writer.substring(0, item.writer.indexOf("[")) + '"/>')
							+	'			<a class="reply_writer" title="'+ item.writer.substring(0, item.writer.indexOf("[")) +'의 작성 글 검색" href="' + root + '/board/list?type=writer&keyword='+ item.writer.substring(0, item.writer.indexOf('[')) +'">' + item.writer + '<input type="hidden" value="'+ item.writer.substring(0, item.writer.indexOf('[')) +'" class="re_nick"></a>'
							+	'			<span class="line">&nbsp;|&nbsp;</span>'
							+	'			<span title="' + item.reg_date + '">' + item.typeChangeDate.substring(0, 19) + '</span>'
							+	'		</span>'
							+	'	</td>'
							+	'	<td>'
							+	'		<span class="reply_sub_button">'
							+	'			<span class="reply_reg"><img src="' + root + '/css/images/reply_insert.png"> 댓글 쓰기</span>'
							+	'			<span class="reply_remove">'
							+	'			<span class="line">&nbsp;|&nbsp;</span>'
							+	'			<input class="reply_rno" type="hidden" value="' + item.rno + '">'
							+	'			<input class="reply_mno" type="hidden" value="' + item.mno + '">'
							+	'			<input class="reply_master" type="hidden" value="' + item.master + '">'
							+	'			<img src="' + root + '/css/images/reply_delete.png"> 삭제</span>'
							+	'		</span>'
							+	'	</td>'
							+	'</tr>'
							+	'<tr>'
							+	'	<td class="td_rc" colspan="2">'
							+	'		<span class="re_sign2"></span>'
							+	'		<div class="reply_content">' + item.content + '</div>'
							+	'	</td>'
							+	'</tr>';
				});
				str += '</table>';
				$('.reply_table').remove();
				$('.reply_wrap').after(str);
				setting();
			}
		});
	}
	
	//댓글 작성
	function write_reply(m) {
		if(logInfo_mno == null || logInfo_mno == '') {
			alert('로그인이 필요합니다.');
		} else if((m == null || m == '') && ($('#reply_input').val() == null || $('#reply_input').val().trim() == '')) {
			$('#reply_input').focus();
			alert('댓글 내용을 입력하세요.');
		} else if (m != null && m != '' && ($('#reply_input_rere').val() == null || $('#reply_input_rere').val().trim() == '')) {
			$('#reply_input_rere').focus();
			alert('댓글 내용을 입력하세요.');
		} else {
			var master = m;
			var writer;
			var content;
			
			if(m == null || m == '') {
				master = 0;
				writer = $('#logInfo_writer').text();
				content = $('#reply_input').val();
			} else {
				writer = logInfo_writer_rere;
				content = $('#reply_input_rere').val();
			}
			
			$.ajax({
				type : 'post',
				url : root + '/ajax',
				data : {bno: bno, master: master, mno: logInfo_mno, writer: writer, content: content},
				dataType : 'json',
				success : function(data) {
					if(data > 0) {
						refresh_ReplyList();
						$('#reply_input').val('');
						$('#rere_wrap').remove();
						rereTogle = false;
						alert('댓글을 작성했습니다.');
						if(master == 0) {
							$('html, body').animate({scrollTop : $('#wrap')[0].scrollHeight}, 300);
						}
					}
				}
			});
		}
	}
	
	//댓글 삭제
	function remove_reply(rno, mno, master) {
		if(logInfo_mno == null || logInfo_mno == '' || logInfo_mno != mno) {
			alert('비정상적인 접근입니다. 다시 로그인을 해주세요.');
		} else if (rno == 0) {
			alert('방금 작성한 게시물입니다. 새로고침 후 삭제가 가능합니다.');
		} else if (rno == master) {
			var check = confirm('정말로 삭제하시겠습니까?\n(대댓글이 있으면  흔적이 남습니다.)');
			if(check) {
				$.ajax({
					type : 'post',
					url : root + '/ajax',
					data : 'rno=' + rno,
					success : function(data) {
						if(data != -1) {
							if(data == 0) {
								$('.reply_rno').each(function() {
									if($(this).val() == rno) {
										$(this).parents('tr').next().hide();
										$(this).parents('tr').hide();
									}
								});
							} else {
								$('.reply_rno').each(function() {
									if($(this).val() == rno) {
										$(this).parents('tr').find('.reply_writer').removeAttr('title')
																				   .attr('href', '#')
																				   .css('color', 'gray')
																				   .text('삭제된 댓글');
										$(this).parents('tr').next().find('.reply_content').text('삭제된 댓글입니다.');
									}
								});
							}
							alert('댓글을 삭제했습니다.');
						}
					}
				});
			}
		} else {
			var check = confirm('정말로 삭제하시겠습니까?');
			if(check) {
				$.ajax({
					type : 'post',
					url : root + '/ajax',
					data : 'rno2=' + rno,
					success : function(data) {
						if(data > 0) {
							$('.reply_rno').each(function() {
								if($(this).val() == rno) {
									$(this).parents('tr').next().hide();
									$(this).parents('tr').hide();
								}
							});
							alert('댓글을 삭제했습니다.');
						}
					}
				});
			}
		}
	}
	
	function setting() {
		//대댓글 쓰기(대댓글 창 생성/삭제)
		$('.reply_reg').click(function() {
			if(logInfo_mno == null || logInfo_mno == '') {
				alert('로그인이 필요합니다.');
			} else if(rereTogle) {
				$('#rere_wrap').remove();
				rereTogle = false;
			} else {
				var str = + ''
						  + '<tr id="rere_wrap">'
						  + '<td colspan="2">'
						  + '	<div class="reply_wrap">'
						  +	'	<div class="read_cavas2">'
						  + '		<div class="reply_input_panel">'
						  + '			<span id="logInfo_writer_rere">' + logInfo_writer_rere + '</span>'
						  + '			<textarea id="reply_input_rere" name="reply_input_rere" style="resize: none" maxlength="500" rows="10" cols="50" wrap="hard" placeholder="명예훼손 및 권리침해 등의 문제를 야기하는 댓글은 제재를 받을 수 있습니다."></textarea>'
						  + '			<div class="read_button_panel" id="reply_button_panel_rere">'
						  + '				<input class="read_button_box2" id="reply_button_rere" type="button" value="작성">'
						  + '			</div>'
						  + '		</div>'
						  + '		<div style="clear: both;"></div>'
						  + '	</div>'
						  + '	</div>'
						  + '</td>'
						  + '</tr>'
						  + '';
				$(this).parents('tr').next().after(str);
				rereTogle = !rereTogle;
				$('#reply_input_rere').focus();
				var master = $(this).next().find('.reply_master').val();
				//대댓글에서 작성버튼 활성화, click이벤트
				$('input[value=작성]').show();
				$('#reply_button_rere').click(function() {
					write_reply(master);
				});
			}
		});
		
		//댓글 삭제
		$('.reply_remove').click(function() {
			remove_reply($(this).find('.reply_rno').val(), $(this).find('.reply_mno').val(), $(this).find('.reply_master').val());
		});
		
		//writer 색깔 변경 및 삭제버튼 표시
		$('.reply_mno').each(function() {
			//자신이 쓴 댓글
			if($(this).val() == logInfo_mno) {
				$(this).parent().show();
				$(this).parents('tr').find('.reply_writer').css('color', '#5383e8')
														   .attr('title', '내가 쓴 글 검색');
				//$(this).parents('tr').find('.reply_writer').attr('style', 'color: red !important')
			} else if($(this).val() == writer_mno) {
				$(this).parents('tr').find('.reply_writer').css('color', '#46cfa7')
				   										   .attr('title', '작성자의 다른 글 검색');
			}
		});
		
		//대댓글인 경우 re_sign
		$('.re_sign').each(function() {
			if($(this).find('input[name=re_togle]').val() == 'true'){
				$(this).text('ㄴ');
				$(this).parents('tr').next().find('.re_sign2').show();
			}
		});
		
		//삭제된 댓글 속성 및 css 수정
		$('.re_nick').each(function() {
			if($(this).val() == null || $(this).val() == '') {
				$(this).parent().removeAttr('title')
				   				.attr('href', '#')
				   				.css('color', 'gray');
			} 
		});
	}
});