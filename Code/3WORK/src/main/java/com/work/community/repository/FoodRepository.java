package com.work.community.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.work.community.entity.Food;

//JpaRespository 상속
public interface FoodRepository extends JpaRepository<Food, Integer>{
	
	Food findByFno(Integer fno);
	
}
