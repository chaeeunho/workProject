package com.work.community.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.work.community.entity.News;

public interface NewsRepository extends JpaRepository<News, Integer>{

}
