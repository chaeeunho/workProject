package com.work.community.dto;

import com.work.community.entity.Cart;
import com.work.community.entity.CartItem;
import com.work.community.entity.Food;
import com.work.community.entity.Item;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CartItemDTO {
	
	 private Integer cino;
	
	 private Cart cart;
	
	 private Item item;
	 
	 private Food food;
	 
	 public static CartItemDTO toSaveDTO(CartItem cartItem) {
		 CartItemDTO cartItemDTO = CartItemDTO.builder()
				 							  .cino(cartItem.getCino())
				 							  .cart(cartItem.getCart())
				 							  .item(cartItem.getItem())
				 							  .food(cartItem.getFood())
				 							  .build();
		 return cartItemDTO;
	 }

}
