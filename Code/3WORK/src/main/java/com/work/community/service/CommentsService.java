package com.work.community.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.work.community.dto.CommentsDTO;
import com.work.community.entity.Comments;
import com.work.community.repository.CommentsRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class CommentsService {

   private final CommentsRepository commentsRepository;
   
   //방명록 쓰기 
   public void insertComment(Comments comments) {
      commentsRepository.save(comments);
   }
   //방명록 목록
   /* public List<Comments> getAllComments() {
      return commentsRepository.findAll();
   } */
   
   
   //방명록 목록 + 페이지처리
   public Page<CommentsDTO> findListAll(Pageable pageable) {
      int page = pageable.getPageNumber() - 1; //db는 현재 페이지보다 1 작음
      int pageSize = 4;
      pageable = PageRequest.of(page, pageSize, Sort.Direction.DESC, "cno");
      
      Page<Comments> commentsList = commentsRepository.findAll(pageable);
      
      log.info("boardList.isFirst()=" + commentsList.isFirst());
      log.info("boardList.isLast()=" + commentsList.isLast());
      log.info("boardList.isLast()=" + commentsList.getNumber());
      
      //생성자 방식으로 boardDTOList 만들기
      Page<CommentsDTO> commentsDTOList = commentsList.map(comments ->
            new CommentsDTO(comments.getCno(), comments.getCcontent(),
               comments.getUsers(),comments.getCreatedDate(), comments.getUpdatedDate()));
      
      return commentsDTOList;
   }
   
   //방명록 삭제
      public void deleteById(Integer cno) {
         commentsRepository.deleteById(cno);
      }
   
   //수정할 방명록 확인
   public Comments updateComment(Integer cno) {
      return commentsRepository.findById(cno).orElse(null);
   }

   //방명록 수정
   public void save(Comments upComments) {
      commentsRepository.save(upComments);
   }
   
}