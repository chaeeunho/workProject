/* 방명록 작성 스크립트  */

$(document).ready(function () {
    // 방명록 작성 폼 제출 이벤트
    $('#comment-form').submit(function (event) {
        event.preventDefault(); // 기본 동작 중단
        var commentContent = $('#comment').val(); // 폼에서 댓글 내용 가져오기
        
        if(commentContent == ""){
            alert("내용을 입력해주세요");
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
            url: '/comments',
            contentType: 'application/json',
            data: JSON.stringify({ ccontent: commentContent }), // JSON으로 댓글 내용 전송
            success: function (response) {
                alert(response);
                $('#comment').val(''); 
            },
            error: function (xhr, status, error) {
                console.error(xhr.responseText);
                alert('방명록 작성 실패!'); // 오류 메시지 표시
            }
        });
    });
});

// deleteComment 함수 
function deleteComment(uno, cno) {
    alert("방명록 삭제 완료");

    let header = $("meta[name='_csrf_header']").attr('content');
    let token = $("meta[name='_csrf']").attr('content');

    $.ajax({
        type: "DELETE",
        beforeSend: function(xhr){
            xhr.setRequestHeader(header, token);
        },
        url: "/comments/" + cno
    }).done(function(response){
        console.log(response);
        location.href = '/user/userpage/' + uno;
    }).fail(function(error){
        alert("에러 발생: " + error);
    });
}
