$(function () {
	var root = $('#root').val();
	var info = $('#info').val();
	var url = location.href;
	var sideBar_togle = false;
	var sideBar_width = 400;
	var sideBar_height = 350;
	
	/* 메뉴 버튼 */
	if(url.indexOf('main') != -1) {
		$('#main').addClass('active');
	} else if(url.indexOf('sort=new') != -1) {
		$('#new_board').addClass('active');
	} else if(url.indexOf('sort=hot') != -1) {
		$('#hot_board').addClass('active');
	} else if(url.indexOf('sort=top') != -1) {
		$('#top_board').addClass('active');
	} else {
		if(url.indexOf('board/list') != -1) {
			$('#new_board').addClass('active');
		} else {
			$('#main').addClass('active');
		}
	}
	
	$('.menu_button').click(function() {
		var now = parseInt($('.menu_list').css('left'));
		
		if(sideBar_togle) {
			$('.sideBar_panel').animate({width: 0}, 'fast').hide();
			$('.option_button').animate({marginLeft: '0px', marginRight: '0px'}, 'fast').hide();
			$('.control_hide_panel').hide();
			sideBar_togle = !sideBar_togle;
		}
		
		if(now < 0){
			$('.menu_list').animate({
				opacity: 1,
				left: 0
			}, 'fast');
        }else{
        	$('.menu_list').animate({
        		opacity: 0,
        		left: -500
			}, 'fast');
        };
    });
	
	/* 검색 버튼 */
	$('.search_input').focusin(function() {
		$('.menu_list').animate({
    		opacity: 0,
    		left: -500
		}, 'fast');
		
		$('.search_type').animate({
    		right: 380
		}, 'fast');
	});
	
	$('.search_input').focusout(function() {
		$('.search_type').animate({
    		right: 70
		}, 'fast');
	});
	
	$('.search_type').click(function() {
		if($(this).val() == '제 목') {
			$(this).val('내 용');
			$('input[name=type]').val('content');
		} else if ($(this).val() == '내 용') {
			$(this).val('작성자');
			$('input[name=type]').val('writer');
		} else {
			$(this).val('제 목');
			$('input[name=type]').val('title');
		}
	});
	
	/* 사이드바 */
	function sideBar() {
		var move_center = (sideBar_width - 60 - parseInt($('.login_button').css('width')) - parseInt($('#logInfo').css('width')))/2;
		if(sideBar_togle) {
			$('.sideBar_panel').animate({width: 0}, 'fast').hide();
			$('.option_button').animate({marginLeft: '0px', marginRight: '0px'}, 'fast').hide();
			$('.control_hide_panel').hide();
			sideBar_togle = !sideBar_togle;
		} else {
			$('.sideBar_panel').css('height', sideBar_height).animate({width: sideBar_width}, 'fast').show();
			$('.option_button').animate({marginLeft: move_center, marginRight: move_center}, 'fast').show();
			$('.control_hide_panel').css('top', sideBar_height/2 - parseInt($('.control_hide_panel').css('height'))/2).show();
			sideBar_togle = !sideBar_togle;
			
			$('.menu_list').animate({
        		opacity: 0,
        		left: -500
			}, 'fast');
				
			$.ajax({
				type : 'post',
				url : root + '/ajax',
				data : 'info=' + info
			});
		}
	}
	
	$('#logInfo').click(function() {sideBar()});
	$('.control_hide_panel').click(function() {sideBar()});
	
	$('.option_button').click(function() {
		location.href = root + '/auth/pwCheck';
	})
	
	$('.login_button[value=로그아웃]').click(function() {
		alert('로그아웃되었습니다.');
		location.href = root + '/auth/logOut';
	});
});