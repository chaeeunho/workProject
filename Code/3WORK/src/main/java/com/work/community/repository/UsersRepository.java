package com.work.community.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.work.community.entity.Users;

public interface UsersRepository extends JpaRepository<Users,Integer>{

   //select * from member where member_id = ?;
   Optional<Users> findByUid(String string);
   
   
   Optional<Users> findByUnickname(String string);
   
   //main 검색창에서 친구 검색
   List<Users> findByUidContaining(String uid); // Like 검색을 위한 메서드 추가
   
   //이름과 전화번호 일치시 아이디찾기 
   Optional<Users> findByUnameAndUphone(String uname, String uphone);
   
   @Modifying
   @Query("UPDATE Users u SET u.hits = u.hits + 1 WHERE u.uno = :uno")
   public void updateHits(Integer uno);
}