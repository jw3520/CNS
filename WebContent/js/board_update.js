function check(form) {
	if(!$('input[name=subject]').val().trim()){
		$('input[name=subject]').focus();
		alert('제목을 입력하세요.');
	} else if(CKEDITOR.instances.content.getData().length < 1){
		$('#content').focus();
		alert('내용을 입력하세요.');
	} else {
		form.submit();
	}
}

$(function() {
	var root = $('#root').val();
	$('input[name=link]').focusout(function() {
		if($(this).val().trim() != '' && !($(this).val().indexOf('http://') != -1 || $(this).val().indexOf('https://') != -1)) {
			$(this).val('http://' + $(this).val());
		}
	});
	
	//CK에디터
	CKEDITOR.replace('content', {
		width: '100%',
		height: '500px'
	});
	
	CKEDITOR.on('dialogDefinition', function (ev) {
        var dialogName = ev.data.name;
        var dialog = ev.data.definition.dialog;
        var dialogDefinition = ev.data.definition;
        if (dialogName == 'image') {
            dialog.on('show', function (obj) {
                this.selectPage('Upload'); //업로드 탭으로 시작
            });
            dialogDefinition.removeContents('advanced'); // 자세히 탭 제거
            dialogDefinition.removeContents('Link'); // 링크 탭 제거
        }
    });
});