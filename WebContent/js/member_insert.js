function join(form) {
	alert('회원가입이 완료되었습니다.');
	form.submit();
}

$(function() {
	var root = $('#root').val();
	var msg_size = 41;
	var email_togle = false;
	var email_togle2 = false;
	var pw_togle = false;
	var nickname_togle = false;
	var timer;
	
	//이메일 확인(디바운싱)
	$('input[name=email]').keyup(function() {
		var email = $(this).val();
		
		if(timer) {
			clearTimeout(timer);
		}
		
		tiemr = setTimeout(function() {
			//이메일 형식 확인
			if(email.indexOf('@') != -1 && email.indexOf('.') != -1 && email.indexOf(' ') == -1) {
				$('#wrong_email_msg').hide();
				if(email_togle2) {
					$('#join_cavas').css('height', parseInt($('#join_cavas').css('height')) - msg_size);
					email_togle2 = !email_togle2;
				}
			} else {
				$('#wrong_email_msg').show();
				if(!email_togle2) {
					$('#join_cavas').css('height', parseInt($('#join_cavas').css('height')) + msg_size);
					email_togle2 = !email_togle2;
				}
			}
			
			//이메일 중복 확인
			$.ajax({
				type : 'post',
				url : root + '/ajax',
				data : 'email=' + email,
				success : function(data) {
					if(data != 1) {
						$('#unusable_email_msg').hide();
						if(email_togle) {
							$('#join_cavas').css('height', parseInt($('#join_cavas').css('height')) - msg_size);
							email_togle = !email_togle;
						}
					} else {
						$('#unusable_email_msg').show();
						if(!email_togle) {
							$('#join_cavas').css('height', parseInt($('#join_cavas').css('height')) + msg_size);
							email_togle = !email_togle;
						}
					}
				}
			});
		}, 200);
		
	});
	
	//비밀번호 확인(디바운싱)
	$(':password').keyup(function() {
		var pw1 = $('input[name=password]').val();
		var pw2 = $('input[name=password_check]').val();
		
		if(timer) {
			clearTimeout(timer);
		}
		
		tiemr = setTimeout(function() {
			if(pw1.trim() != '' && pw2.trim() != '') {
				if(pw1 == pw2) {
					$('#different_password_msg').hide();
					if(pw_togle) {
						$('#join_cavas').css('height', parseInt($('#join_cavas').css('height')) - msg_size);
						pw_togle = !pw_togle;
					}
				} else {
					$('#different_password_msg').show();
					if(!pw_togle){
						$('#join_cavas').css('height', parseInt($('#join_cavas').css('height')) + msg_size);
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
		
		tiemr = setTimeout(function() {
			$.ajax({
				type : 'post',
				url : root + '/ajax',
				data : 'nickname=' + nickname,
				success : function(data) {
					if(data != 1) {
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
			}).attr('onclick', 'join(this.form)');
		} else {
			$('.join_button_box').css({background: 'lightgray',
									   cursor: 'auto'
			}).attr('onclick', '');
		}
	});
	
	function fail_check() {
		return !email_togle && !email_togle2 && !pw_togle && !nickname_togle;
	}
	
	function empty_check(form) {
		if(form.email.value && form.password.value &&
		   form.password_check.value && form.nickname.value) {
			return true;
		}
		return false;
	}
});