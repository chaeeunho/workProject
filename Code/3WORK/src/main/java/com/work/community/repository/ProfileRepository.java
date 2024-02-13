package com.work.community.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.work.community.entity.Profile;

public interface ProfileRepository extends JpaRepository<Profile, Integer>{

	Profile findByPno(Integer integer);
}
