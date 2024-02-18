package com.work.community.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.work.community.entity.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Integer>{
		
	CartItem findByCart_CartnoAndItem_Ino(Integer cartno, Integer ino);
	
	CartItem findByCart_CartnoAndFood_Fno(Integer cartno, Integer fno);
	
	CartItem findByCart_CartnoAndItem_InoAndFood_Fno(Integer cartno, Integer ino, Integer fno);
	
	CartItem findByCino(Integer cino);
			
}
