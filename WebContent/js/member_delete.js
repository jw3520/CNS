function reconfirm(form) {
	if(confirm('정말로 탈퇴하시겠습니까?')) {
		form.submit();
	} else {
		return false;
	}
}

$(function() {
	var msg = '\nㆍ 게시글 및 댓글은 탈퇴 후에도 자동으로 삭제되지 않습니다.\n\n'
			+ 'ㆍ 탈퇴는 되돌릴 수 없으니, 신중히 결정하시길 바랍니다.\n\n'
			+ 'ㆍ 서비스를 이용해주셔서 감사합니다. 발전하는 서비스로 다시 고객님이 돌아오시도록 더 노력하겠습니다.\n\n'
			+ '\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n';
	$('#withdrawal_terms').html(msg);
	
	if($('input:checkbox:checked').length == 1 && $('textarea').val().length >= 5){
		$('#agree_button').css({background: '#3B5997',
								cursor: 'pointer'});
		$('#agree_button').attr('onclick', 'reconfirm(this.form)');
	}
	
	$('textarea').focusout(function(){
		if($('input:checkbox:checked').length == 1 && $(this).val().length >= 5){
			$('#agree_button').css({background: '#3B5997',
									cursor: 'pointer'});
			$('#agree_button').attr('onclick', 'reconfirm(this.form)');
		} else {
			$('#agree_button').css({background: 'lightgray',
									cursor: 'auto'});
			$('#agree_button').attr('onclick', '');
		}
	});
	
	$('.terms_check').click(function() {
		var cb = $(this).find('input:checkbox');
		cb.prop('checked', !cb.prop('checked'));
		
		if($('input:checkbox:checked').length == 1 && $('textarea').val().length >= 5){
			$('#agree_button').css({background: '#3B5997',
									cursor: 'pointer'});
			$('#agree_button').attr('onclick', 'reconfirm(this.form)');
		} else {
			$('#agree_button').css({background: 'lightgray',
									cursor: 'auto'});
			$('#agree_button').attr('onclick', '');
		}
	});
	
	$('input:checkbox').click(function(){
		$(this).parent().trigger('click');
	});
});