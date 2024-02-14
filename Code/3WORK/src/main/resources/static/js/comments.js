/* 방명록 작성 스크립트  */

$(document).ready(function () {
    // 방명록 작성 폼 제출 이벤트
    $('#comment-form').submit(function (event) {
        event.preventDefault(); // 기본 동작 중단
        var commentContent = $('#comment').val(); // 폼에서 댓글 내용 가져오기
        
        if(commentContent == ""){
			alert("댓글을 입력해주세요");
			$("#comment").focus();
			return false;
		}
        
        let header = $("meta[name='_csrf_header']").attr('content');
		let token = $("meta[name='_csrf']").attr('content');
        
        $.ajax({
            type: 'POST',
            beforeSend: function(xhr){
		        xhr.setRequestHeader(header, token);
		    },
            url: '/comments', // 댓글 작성 엔드포인트 URL
            contentType: 'application/json',
            data: JSON.stringify({ ccontent: commentContent }), // JSON으로 댓글 내용 전송
            success: function (response) {
                alert(response); // 성공 메시지 표시
                $('#comment').val(''); // 입력 초기화
                // TODO: 댓글 목록을 다시 불러와서 화면에 업데이트
            },
            error: function (xhr, status, error) {
                console.error(xhr.responseText); // 오류 로그 출력
                alert('댓글 작성 실패!'); // 오류 메시지 표시
            }
        });
    });
});