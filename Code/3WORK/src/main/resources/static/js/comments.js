/* 방명록 스크립트  */
// 방명록 작성
$(document).ready(function () {
    $('#comment-form').submit(function (event) {
      console.log(event);
        event.preventDefault();
        var commentContent = $('#comment').val(); // 폼에서 댓글 내용 가져오기
        let userNo = $("#uno").val();
        
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
            url: '/comments/' + userNo,
            contentType: 'application/json',
            data: JSON.stringify({ ccontent: commentContent }),
            success: function (response) {
                alert(response);
                location.href="/user/userpage/" + userNo;
                $('#comment').val(''); 
            },
            error: function (xhr, status, error) {
                console.error(xhr.responseText);
                alert('방명록 작성 실패!');
            }
        });
    });
});

// 방명록 삭제
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


//수정
let commentObject = {
   init: function() {
      $("comment_up").click(() => {
        console.log("comment_up");
         this.UpdateComment();   // this = 클릭한 대상 = 필수
      })
   },
      
   UpdateComment: function(uno, cno) {
      
      let originalContent = $('#originalContent' + cno).text().trim(); // 기존 댓글 내용 가져오기
      console.log(originalContent);
         $('#commentContent' + cno).attr('placeholder', originalContent);
          $('#replyModal' + cno).css('display', 'block'); // 모달 창 띄우기
         $('#updateReplyBtn_cancel' + cno).click(() => {
             $('#replyModal' + cno).css('display', 'none'); // 모달 창 숨기기
         });
          $('#updateReplyBtn_confirm' + cno).click(() => {
              let updatedContent = $('#commentContent' + cno).val().trim(); // 수정된 내용 가져오기
              if (!updatedContent) {
                  alert("댓글 내용을 입력하세요");
                  return;
              }
      
              let reply = {
                  ccontent: updatedContent
              };
              
              let header = $("meta[name='_csrf_header']").attr('content');
            let token = $("meta[name='_csrf']").attr('content');

           $.ajax({
               type: "PUT",
               beforeSend: function(xhr){
               xhr.setRequestHeader(header, token);
              },
               url: '/comments/' + cno,
               data: JSON.stringify(reply),
               contentType: "application/json; charset=utf-8"
           }).done(function(response) {
               console.log(response);
               location.href="/user/userpage/" + uno; // 수정된 내용을 반영하기 위해 페이지를 새로고침
           }).fail(function(error) {
               alert("에러 발생: " + error);
           });
       });
   }
}
commentObject.init();   // init() 함수 호출