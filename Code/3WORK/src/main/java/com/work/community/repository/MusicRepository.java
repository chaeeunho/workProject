package com.work.community.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.work.community.entity.Music;

//JpaRespository 상속
public interface MusicRepository extends JpaRepository<Music, Integer>{

	// select * from music where mno = ?
	// Optional<Music> findByMno(Integer integer);
		
}
