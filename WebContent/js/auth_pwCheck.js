$(function() {
	var fail = $('#fail').val();
	
	if(fail != '') {
		$('#logIn_cavas').css('height', 370);
	}
	
	$('input[type=password]').keypress(function(e) {
		if(e.which == 13){
			check(this.form);
		}
	});
});

function check(form) {
	if(!form.password.value.trim()) {
		alert('비밀번호를 입력하세요.');
		form.password.focus();
	} else {
		form.submit();
	}
}