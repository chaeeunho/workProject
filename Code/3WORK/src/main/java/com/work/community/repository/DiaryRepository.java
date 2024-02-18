package com.work.community.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.work.community.entity.UserDiary;

public interface DiaryRepository extends JpaRepository<UserDiary, Integer>{


}