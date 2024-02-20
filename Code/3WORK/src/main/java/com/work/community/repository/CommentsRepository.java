package com.work.community.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.work.community.entity.Comments;
import com.work.community.entity.Users;

public interface CommentsRepository extends JpaRepository<Comments, Integer>{

//   Page<Comments> findByUsers(Pageable pageable);

}