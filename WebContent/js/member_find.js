function send(form) {
	var email = form.email.value;
	$.ajax({
		type : 'post',
		url : $('#root').val() + '/ajax',
		data : 'send_email=' + email,
		success : function() {
			alert('이메일로 코드를 전송했습니다.');
		}
	});
	$('#exist_email_msg').html('이메일로 전송 받은 코드를 입력하세요.<div class="blank">');
	$('#exist_email_msg').css('color', 'blue');
}

function code_check(form) {
	form.submit();
}

$(function() {
	var root = $('#root').val();
	var msg_size = 41;
	var first = true;
	var first2 = true;
	var email_togle = false;
	var email_togle2 = false;
	var fail = $('#fail').val();
	var timer;
	
	
	//틀린 코드 입력 후 최초 화면
	if(fail) {
		$('#find_cavas').css('height', parseInt($('#find_cavas').css('height')) + msg_size * 2);
		$('#exist_email_msg').show();
		$('#different_code_msg').show();
		$('#exist_email_msg').html('코드를 다시 입력하거나 새로운 코드를 전송 받으세요.<div class="blank">');
		$('.send_button_box').css({background: '#3B5997', cursor: 'pointer'})
							 .attr('onclick', 'send(this.form)');
		$('.send_button_box').css({background: '#3B5997', cursor: 'pointer'})
							 .attr('onclick', 'send(this.form)');
	}
	
	//틀린코드 입력 후 코드 수정
	$('input[name=code]').keyup(function() {
		if(fail && first) {
			$('#different_code_msg').hide();
			$('#find_cavas').css('height', parseInt($('#find_cavas').css('height')) - msg_size);
			first = !first;
		}
	});
	
	//이메일 확인(디바운싱)
	$('input[name=email]').keyup(function() {
		var email = $(this).val();
		
		if(timer) {
			clearTimeout(timer);
		}
		
		timer = setTimeout(function() {
			if(first2) {
				$('#find_cavas').css('height', parseInt($('#find_cavas').css('height')) + msg_size);
				first2 = !first2;
			}
			
			//이메일 존재 확인
			if(email != '') {
				$.ajax({
					type : 'post',
					url : root + '/ajax',
					data : 'email=' + email,
					success : function(data) {
						if(data == 1) {
							$('#unusable_email_msg').hide();
							$('#exist_email_msg').show();
							$('.send_button_box').css({background: '#3B5997',
													   cursor: 'pointer'
							}).attr('onclick', 'send(this.form)');
							if(email_togle) {
								email_togle = !email_togle;
							}
						} else {
							$('#unusable_email_msg').show();
							$('#exist_email_msg').hide();
							$('.send_button_box').css({background: 'lightgray',
													   cursor: 'auto'
							}).attr('onclick', '');
							if(!email_togle) {
								email_togle = !email_togle;
							}
						}
					}
				});
			}
			
			//이메일 형식 확인
			if(email.indexOf('@') != -1 && email.indexOf('.') != -1 && email.indexOf(' ') == -1) {
				$('#wrong_email_msg').hide();
				if(email_togle2) {
					$('#find_cavas').css('height', parseInt($('#find_cavas')
									.css('height')) - msg_size);
					email_togle2 = !email_togle2;
				}
			} else {
				$('#wrong_email_msg').show();
				if(!email_togle2) {
					$('#find_cavas').css('height', parseInt($('#find_cavas')
								    .css('height')) + msg_size);
					email_togle2 = !email_togle2;
				}
			}
		}, 200);
	});
	
	
	//확인 버튼 활성화
	$('input').focusout(function() {
		if(fail_check() && empty_check(this.form)){
			$('.find_button_box').css({background: '#3B5997', cursor: 'pointer'})
								 .attr('onclick', 'code_check(this.form)');
		} else {
			$('.find_button_box').css({background: 'lightgray', cursor: 'auto'})
								 .attr('onclick', '');
		}
	});
	
	$('input').keyup(function() {
		if(fail_check() && empty_check(this.form)){
			$('.find_button_box').css({background: '#3B5997', cursor: 'pointer'})
								 .attr('onclick', 'code_check(this.form)');
		} else {
			$('.find_button_box').css({background: 'lightgray', cursor: 'auto'})
								 .attr('onclick', '');
		}
	});
	
	function fail_check() {
		return !email_togle && !email_togle2;
	}
	
	function empty_check(form) {
		if(form.email.value && form.code.value) {
			return true;
		}
		return false;
	}
});