package com.work.community.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.work.community.entity.Users;

public interface UsersRepository extends JpaRepository<Users,Integer>{

   //select * from member where member_id = ?;
   Optional<Users> findByUid(String string);
   
   Optional<Users> findByUnickname(String string);
   
   
   
   

}