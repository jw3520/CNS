$(function() {
	var msg = '\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n';
	
	$('.terms_panel textarea').html(msg);
	
	if($('input:checkbox:checked').length == 2){
		$('#agree_button').css({background: '#3B5997',
								cursor: 'pointer'});
		$('#agree_button').attr('onclick', 'location.href="../member/join"');
	}
	
	$('.terms_check').click(function() {
		var cb = $(this).find('input:checkbox');
		cb.prop('checked', !cb.prop('checked'));
		
		if($('input:checkbox:checked').length == 2){
			$('#agree_button').css({background: '#3B5997',
									cursor: 'pointer'});
			$('#agree_button').attr('onclick', 'location.href="../member/join"');
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