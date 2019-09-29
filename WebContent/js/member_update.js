function reconfirm(form) {
	if(confirm('입력한 정보로 수정하시겠습니까?')) {
		form.submit();
	} else {
		return false;
	}
}

$(function() {
	var root = $('#root').val();
	var pre_nickname = $('#pre_nickname').val();
	var pwCheck = $('#pwCheck').val();
	var msg_size = 41;
	var pw_togle = false;
	var nickname_togle = false;
	var timer;
	
	//이동한 목적에 따라
	if(pwCheck == 'find') {
		$('input[name=password]').attr('value', '');
		$('input[name=password_check]').attr('value', '');
	}
	
	//비밀번호 확인(디바운싱)
	$(':password').keyup(function() {
		var pw1 = $('input[name=password]').val();
		var pw2 = $('input[name=password_check]').val();
		
		if(timer) {
			clearTimeout(timer);
		}
		
		timer = setTimeout(function(){
			if(pw1.trim() != '' && pw2.trim() != '') {
				if(pw1 == pw2) {
					$('#different_password_msg').hide();
					if(pw_togle) {
						$('#join_cavas').css('height', parseInt($('#join_cavas')
										.css('height')) - msg_size);
						pw_togle = !pw_togle;
					}
				} else {
					$('#different_password_msg').show();
					if(!pw_togle){
						$('#join_cavas').css('height', parseInt($('#join_cavas')
										.css('height')) + msg_size);
						pw_togle = !pw_togle;
					}
				}
			}
		}, 200);
	});
	
	//닉네임 중복 확인(디바운싱)
	$('input[name=nickname]').keyup(function() {
		var nickname = $(this).val();
		
		if(timer) {
			clearTimeout(timer);
		}
		
		timer = setTimeout(function() {
			$.ajax({
				type : 'post',
				url : root + '/ajax',
				data : 'nickname=' + nickname,
				success : function(data) {
					if(data != 1 || pre_nickname == nickname) {
						$('#unusable_nickname_msg').hide();
						if(nickname_togle) {
							$('#join_cavas').css('height', parseInt($('#join_cavas').css('height')) - msg_size);
							nickname_togle = !nickname_togle;
						}
					} else {
						$('#unusable_nickname_msg').show();
						if(!nickname_togle) {
							$('#join_cavas').css('height', parseInt($('#join_cavas').css('height')) + msg_size);
							nickname_togle = !nickname_togle;
						}
					}
				}
			});
		}, 200);
	});
	
	//버튼 활성화
	$('input').focusout(function() {
		if(fail_check() && empty_check(this.form)){
			$('.join_button_box').css({background: '#3B5997',
									   cursor: 'pointer'
			}).attr('onclick', 'reconfirm(this.form)');
		} else {
			$('.join_button_box').css({background: 'lightgray',
									   cursor: 'auto'
			}).attr('onclick', '');
		}
	});
	
	$('input').keyup(function() {
		if(fail_check() && empty_check(this.form)){
			$('.join_button_box').css({background: '#3B5997', cursor: 'pointer'}).attr('onclick', 'reconfirm(this.form)');
		} else {
			$('.join_button_box').css({background: 'lightgray', cursor: 'auto'}).attr('onclick', '');
		}
	});
	
	$('#commu_select').change(function() {
		if(fail_check() && empty_check(this.form)){
			$('.join_button_box').css({background: '#3B5997',
									   cursor: 'pointer'
			}).attr('onclick', 'reconfirm(this.form)');
		}
	});
	
	function fail_check() {
		return !pw_togle && !nickname_togle;
	}
	
	function empty_check(form) {
		if(form.email.value.trim() && form.password.value.trim() &&
		   form.password_check.value.trim() && form.nickname.value.trim()) {
			return true;
		}
		return false;
	}
});