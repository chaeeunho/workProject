package com.work.community.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.work.community.config.SecurityUser;
import com.work.community.entity.Comments;
import com.work.community.service.CommentsService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class CommentsController {

   private final CommentsService commentsService;
   
   //방명록 쓰기
   @PostMapping("/comments")
   @ResponseBody
   public String insertComment(@RequestBody Comments comments,
         @AuthenticationPrincipal SecurityUser principal) {
      comments.setUsers(principal.getUsers());
      commentsService.insertComment(comments);
      return "방명록 등록 성공!";
   }
   
   //방명록 삭제
   @DeleteMapping("/comments/{cno}")
   @ResponseBody
   public String deleteComment(@PathVariable Integer cno) {
      commentsService.deleteById(cno);
      return "방명록 삭제 완료";
   }
}