$(function() {
	var refresh = $('#refresh').val();
	var slide = $('#slide').val();
	var commu_count = $('#commu_count').val();
	var slide_togle = false;
	var timer;
	
	$('#commu_slider').css('width', commu_count * 800);
	
	$('.td_rank').each(function() {
		if(parseInt($(this).text()) <= 3) {
			$(this).css('color', 'red');
		}
	});
	
	//초기화면 랜덤 슬라이드
	if(refresh == '') {
		$('#commu_slider').css('left', Math.floor(Math.random() * commu_count) * -800);
	} else {
		$('#commu_slider').css('left', refresh * -800);
	}
	
	//자동 우측방향 슬라이드
	if(slide == 'stop' || slide == '') {
		$('.control_stop').css('margin-left', '-50px');
		$('.control_stop').attr('title', '슬라이드 재생');
		slide_togle = false;
	} else {
		$('.control_stop').css('margin-left', '0px');
		$('.control_stop').attr('title', '슬라이드 정지');
		var intervalId = setInterval(moveRight, 10000);
		slide_togle = true;
	}
	
	//좌슬라이드 함수(쓰로틀링)
	function moveLeft() {
		var now = parseInt($('#commu_slider').css('left'));
		var after_left = now >= 0 ? (-800 * (commu_count - 1)) : now + 800;
		if(after_left != 0 && after_left % 800 != 0) {
			after_left = parseInt(after_left / 800) * 800;
		}
		if(!timer) {
			timer = setTimeout(function() {
				timer = null;
				$('#commu_slider').animate({
					left : after_left
				}, 'fast');
			}, 300);
		}
	}

	//우슬라이드 함수(쓰로틀링)
	function moveRight() {
		var now = parseInt($('#commu_slider').css('left'));
		var after_right = now <= (-800 * (commu_count - 1)) ? 0 : now - 800;
		if(after_right != 0 && after_right % 800 != 0) {
			after_right = parseInt(after_right / 800) * 800;
		}
		if(!timer) {
			timer = setTimeout(function() {
				timer = null;
				$('#commu_slider').animate({
					left : after_right
				}, 'fast');
			}, 300);
		}
	}

	/* 슬라이더 좌측 버튼 */
	$('.control_left').hover(function() {
		$(this).css('opacity', 0.8);
	}, function() {
		$(this).css('opacity', 0.4);
	}).click(function() {
		moveLeft();
	});

	/* 슬라이더 우측 버튼 */
	$('.control_right').hover(function() {
		$(this).css('opacity', 0.8);
	}, function() {
		$(this).css('opacity', 0.4);
	}).click(function() {
		moveRight();
	});
	
	/* 슬라이더 정지 버튼 */
	$('.control_stop').click(function() {
		if(slide_togle) {
			$(this).css('margin-left', '-50px');
			$(this).attr('title', '슬라이드 재생');
			clearInterval(intervalId);
		} else {
			$(this).css('margin-left', '0px');
			$(this).attr('title', '슬라이드 정지');
			intervalId = setInterval(moveRight, 10000);
		}
		slide_togle = !slide_togle;
	});
	
	/* 슬라이더 새로고침 버튼 */
	$('.control_refresh').click(function() {
		location.href = 'main?refresh=' + Math.round(parseInt($('#commu_slider').css('left'))/-800) + '&slide=' + (slide_togle ? 'play' : 'stop');
	});

	/* a태그 tr태그로 확장 */
	$('.td_title a').click(function(e) {
		e.preventDefault();
	});
	
	$('.commu_table tr').hover(function() {
		if ($(this).children().is('td')) {
			$(this).css('background', '#C1DFF2');
		}
	}, function() {
		$(this).css('background', 'white');
	}).click(function() {
		if ($(this).children().is('td')) {
			window.open($(this).find('a').attr('href'), '_blank');
		}
	}).find('th').parent().css('cursor', 'auto');

	/* 제목 40자 이상 생략처리 */
	$('.td_title a').each(function() {
		if ($(this).text().length > 40) {
			$(this).text($(this).text().substr(0, 40) + '...');
		}
	});
});