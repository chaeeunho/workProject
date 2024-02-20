package com.work.community.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.work.community.dto.CartItemDTO;
import com.work.community.entity.Cart;
import com.work.community.entity.CartItem;
import com.work.community.entity.Food;
import com.work.community.entity.Item;
import com.work.community.entity.Users;
import com.work.community.repository.CartItemRepository;
import com.work.community.repository.CartRepository;
import com.work.community.repository.FoodRepository;
import com.work.community.repository.ItemRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class CartService {
	
	private final CartRepository cartRepository;
	
	private final CartItemRepository cartItemRepository;
	
	private final ItemRepository itemRepository;
	
	private final FoodRepository foodRepository;

	// 장바구니 목록 가져오기
	public Page<CartItemDTO> findListAll(Pageable pageable) {
		int page = pageable.getPageNumber() - 1; // db는 현재페이지보다 1 작아야함
		int pageSize = 10;
		pageable = PageRequest.of(page, pageSize, Sort.Direction.DESC, "cino");
		
		Page<CartItem> cartList = cartItemRepository.findAll(pageable);
		
		log.info("cartList.isFirst()=" + cartList.isFirst());
		log.info("cartList.isLast()=" + cartList.isLast());
		log.info("cartList.getNumber()=" + cartList.getNumber());
		
		// 생성자 방식으로 musicDTOList 만들기
		Page<CartItemDTO> cartItemDTOList = cartList.map(cartItem -> 
		new CartItemDTO(cartItem.getCino(), cartItem.getCart(), cartItem.getItem(), cartItem.getFood()));
		
		return cartItemDTOList;
	}

	// 장바구니 담기 (아이템)
	@Transactional
	public void addCartItem(Users users, Item newItem) {
		// 회원번호로 해당 회원의 장바구니 찾기
		Cart cart = cartRepository.findByUsersUno(users.getUno());
		
		// 장바구니가 존재하지 않는다면
		if(cart == null) {
			cart = Cart.createCart(users);
			cartRepository.save(cart);
			
		}
		
		Item item = itemRepository.findByIno(newItem.getIno());
		CartItem cartItem = cartItemRepository.findByCart_CartnoAndItem_Ino(cart.getCartno(), item.getIno());
		
		// 장바구니에 상품이 없으면 상품을 담는 상품카트 생성 후 추가
		if(cartItem == null) {
			cartItem = CartItem.createCartItem(cart, item);
			cartItemRepository.save(cartItem);
		}
				
	}

	// 장바구니 삭제 (아이템)
	public void deleteById(Integer cino) {
		cartItemRepository.deleteById(cino);		
	}

	// 장바구니 담기 (식품)
	@Transactional
	public void addCartFood(Users users, Food newFood) {
		// 회원번호로 해당 회원의 장바구니 찾기
		Cart cart = cartRepository.findByUsersUno(users.getUno());
		
		// 장바구니가 존재하지 않으면
		if(cart == null) {
			cart = Cart.createCart(users);
			cartRepository.save(cart);
		}
		
		Food food = foodRepository.findByFno(newFood.getFno());
		CartItem cartItem = cartItemRepository.findByCart_CartnoAndFood_Fno(cart.getCartno(), food.getFno());
		
		// 장바구니에 상품이 없으면 상품을 담는 상품카트 생성 후 추가
		if(cartItem == null) {
			cartItem = CartItem.createCartFood(cart, food);
			cartItemRepository.save(cartItem);
		}
		
	}
	
}
