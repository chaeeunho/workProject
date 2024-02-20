package com.work.community.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.work.community.entity.Cart;

public interface CartRepository extends JpaRepository<Cart, Integer> {
	
    Cart findByUsersUno(@Param("uno") Integer uno);
    
}