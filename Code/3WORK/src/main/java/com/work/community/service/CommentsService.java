package com.work.community.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.work.community.dto.CommentsDTO;
import com.work.community.entity.Comments;
import com.work.community.entity.Users;
import com.work.community.repository.CommentsRepository;
import com.work.community.repository.UsersRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class CommentsService {

   private final CommentsRepository commentsRepository;
   private final UsersRepository usersRepository;
   
   //방명록 쓰기 
   public void insertComment(Comments comments, Integer uno) {
      Users users = usersRepository.findById(uno).get();
      comments.setUsers(users);
      commentsRepository.save(comments);
   }
   //방명록 목록
   /* public List<Comments> getAllComments() {
      return commentsRepository.findAll();
   } */
   
   
   //방명록 목록 + 페이지처리
   public Page<CommentsDTO> findByUno(Pageable pageable, Integer uno) {
      int page = pageable.getPageNumber() - 1; //db는 현재 페이지보다 1 작음
      int pageSize = 5;
      pageable = PageRequest.of(page, pageSize, Sort.Direction.DESC, "cno");
      
      Optional<Users> users = usersRepository.findById(uno);
      Page<Comments> commentsList = commentsRepository.findByUsers(pageable, users.get());
      
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