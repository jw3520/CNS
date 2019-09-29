function checkLogIn() {
	if($('#checkLogInfo').val()) {
		location.href = 'write';
	} else {
		location.href = '../auth/logIn';
	}
}

function parseBoolean(str) {
	if(str == 'true'){
		return true;
	} else {
		return false;
	}
}

$(function() {
	var root = $('#root').val();
	var pageNum = $('#pageNum').val();
	var amount = $('#amount').val();
	var sort = $('#sort').val();
	var type = $('#type').val();
	var keyword = $('#keyword').val();
	var hasMoreList = true;
	var timer;
	
	setting();
	
	if(sort == ''){
		sort = 'new';
	}
	
	/* 리스트 정렬은 <최신>이 기본 값  */
	if(sort == 'hot') {
		$('#button_hot img').css('-webkit-filter', 'grayscale(0%)');
		$('#button_hot a').css('font-weight', 'bold').css('color', '#ff6e6e');
	} else if(sort == 'top') {
		$('#button_top img').css('-webkit-filter', 'grayscale(0%)');
		$('#button_top a').css('font-weight', 'bold').css('color', '#fcae42');
	} else {
		$('#button_new img').css('-webkit-filter', 'grayscale(0%)');
		$('#button_new a').css('font-weight', 'bold').css('color', '#3bae9a');
	}
	
	
	/* 게시판 정렬 메뉴 */
	$('.board_sort').hover(function() {
		$(this).find('img').css('-webkit-filter', 'grayscale(0%)');
		$(this).find('a').css('font-weight', 'bold');
		if($(this).attr('id').indexOf('new') != -1) {
			$(this).find('a').css('color', '#3bae9a');
		} else if($(this).attr('id').indexOf('hot') != -1) {
			$(this).find('a').css('color', '#ff6e6e');
		} else if($(this).attr('id').indexOf('top') != -1) {
			$(this).find('a').css('color', '#fcae42');
		}
	}, function() {
		if($(this).attr('id').indexOf(sort) == -1) {
			$(this).find('img').css('-webkit-filter', 'grayscale(100%)');
			$(this).find('a').css('font-weight', 'normal').css('color', 'gray');
		}
	});
	
	//ScrollTop
	$('#control_top').css('bottom', '0px');
	$('#control_top').click( function() {
		$('html, body').animate({scrollTop : 0}, 300);
		return false;
	});
	
	$(window).scroll(function() {
		//ScrollTop 버튼
		if ($(this).scrollTop() > 50) {
			$('#control_top').fadeIn();
		} else {
			$('#control_top').fadeOut();
        }
		
		//마지막 스크롤에서 ScrollPaging
		if(Math.abs($(document).height() - $(window).height() - $(window).scrollTop()) < 200) {
			moreList();
		}
	});
	
	//초기화면에서 ScrollPaging
	if(Math.abs($(document).height() - $(window).height()) < 200) {
		moreList();
	}
	
	//ScrollPaging(쓰로틀링)
	function moreList() {
		if(hasMoreList && !timer){
			$('#loading_panel').fadeIn();
			timer = setTimeout(function() {
				timer = null;
				$.ajax({
					type : 'post',
					url : root + '/ajax',
					data : {pageNum: ++pageNum, amount: amount, sort: sort, type: type, keyword: keyword},
					dataType : 'json',
					success : function(list) {
						if(list == '') {
							hasMoreList = false;
						} else {
							$('#pageNum').val(pageNum);
							list.forEach(function(item) {
								var str =    '<tr class="tr_main">'
									  		+'	<td class="td_thumb" rowspan="2"><div><img src="' + item.thumb_url + '"></div></td>'
									  		+'	<td class="td_subject" colspan="2">'
									  		+'		<a href="read?bno=' + item.bno + '&pageNum=' + pageNum + '&amount=' + amount + '&sort=' + sort + '&type=' + type + '&keyword=' + keyword + '">' + item.subject + '</a>'
									  		+'		<span class="reply_count" title="' + item.reply_count + '개의 댓글">[' + item.reply_count + ']</span><br>'
									  		+'	</td>'
									  		+'	<td class="td_vote" rowspan="2" data-case="' + item.voteCase + '" title="추천 ' + item.up_count + ' | 반대 ' + item.down_count + '">' + (item.up_count-item.down_count) + '</td>'
									  		+'</tr>'
									  		+'<tr class="tr_sub">'
											+'	<td class="td_date" title="' + item.reg_date + '">'
											+		item.typeChangeDate
											+'	</td>'
											+'	<td class="td_writer">' + item.writer + '</td>'
											+'</tr>';
								$('.tr_sub:last').after(str);
								setting();
							});
						}
					}
				});
			}, 1000);
			$('#loading_panel').fadeOut();
		}
	}
	
	function setting() {
		/* 게시판 추천수 case별 css */
		$('.td_vote').each(function() {
			var voteCase = $(this).attr('data-case');
			
			if(parseInt(voteCase/10) == 1) {
				$(this).css('font-size', '1em');
			} else if(parseInt(voteCase/10) == 2) {
				$(this).css('font-weight', 'bold');
			} else if(parseInt(voteCase/10) == 3) {
				$(this).css('font-weight', 'bold').css('font-size', '1.1em');
			}
			
			if(voteCase%10 == 1) {
				$(this).css('color', '#ff6266');
			} else if(voteCase%10 == 2) {
				$(this).css('color', '#5383e8');
			} else if(voteCase%10 == 3) {
				$(this).css('color', '#a765a9');
			}
		});
		
		$('.board_sort').click(function() {
			location.href = $(this).find('a').attr('href');
		});
		
		/* a태그 tr태그로 확장(리스트) */
		$('.td_subject a').click(function(e) {
			e.preventDefault();
		});

		$('.tr_main').hover(function() {
			$(this).css('background', '#C1DFF2');
			$(this).next().css('background', '#C1DFF2');
		}, function() {
			$(this).css('background', 'white');
			$(this).next().css('background', 'white');
		}).click(function() {
			location.href = $(this).find('a').attr('href');
		});
		
		$('.tr_sub').hover(function() {
			$(this).css('background', '#C1DFF2');
			$(this).prev().css('background', '#C1DFF2');
		}, function() {
			$(this).css('background', 'white');
			$(this).prev().css('background', 'white');
		}).click(function() {
			location.href = $(this).prev().find('a').attr('href');
		});
		
		/* 썸네일 이미지 크기 조정 */
		$('.td_thumb img').each(function() {
			var thumb_size = 84;
			if($(this).width() != $(this).height()) {
				if($(this).width() > $(this).height()) {
					$(this).attr({
						width: 'auto',
						height: '100%'
					});
					
					$(this).css({
						'-webkit-transform' : 'translate(-25%,0%)',
						'-ms-transform' : 'translate(-25%,0%)',
						'transform' : 'translate(-25%,0%)'
					});
				} else {
					$(this).attr({
						width: '100%',
						height: 'auto'
					});
					
					$(this).css({
						'-webkit-transform' : 'translate(0%, -25%)',
						'-ms-transform' : 'translate(0%, -25%)',
						'transform' : 'translate(0%, -25%)'
					});
				}
			} else {
				$(this).attr({
					width: thumb_size,
					height: thumb_size
				});
			}
		});
	}
});