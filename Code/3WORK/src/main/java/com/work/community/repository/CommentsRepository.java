package com.work.community.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.work.community.entity.Comments;

public interface CommentsRepository extends JpaRepository<Comments, Integer>{

}
