package com.work.community.entity;

import java.util.Objects;

import com.work.community.dto.CartItemDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class CartItem {
	
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Integer cino; // 장바구니에 담는 상품번호
   	   
   // 하나의 상품은 여러 장바구니에 담길수 있으므로 다대일 관계
   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn
   private Cart cart;
   
   // 하나의 장바구니에 여러 개의 아이템이 담김 (다대일 관계)
   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "ino")
   private Item item;
   
   // 하나의 장바구니에 여러 개의 식품이 담김 (다대일 관계)
   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "fno")
   private Food food;
   
   // 장바구니 품목(아이템) 생성
   public static CartItem createCartItem(Cart cart, Item item) {
      CartItem cartItem = new CartItem();
      cartItem.setCart(cart);
      cartItem.setItem(item);
      return cartItem;
   }
   
   // 장바구니에 품목(식품) 생성
   public static CartItem createCartFood(Cart cart, Food food) {
	   CartItem cartItem = new CartItem();
	   cartItem.setCart(cart);
	   cartItem.setFood(food);
	   return cartItem;
   }
   
   // dto -> entity 변환
	public static CartItem toSaveEntity(CartItemDTO cartItemDTO) {
		CartItem cartItem = CartItem.builder()
						.cino(cartItemDTO.getCino())
						.cart(cartItemDTO.getCart())
						.item(cartItemDTO.getItem())
						.food(cartItemDTO.getFood())
						.build();
		return cartItem;
	}
	
	
	// 이미 담긴 상품일 경우, 중복 담기 불가능
	@Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        CartItem cartItem = (CartItem) obj;

        // 장바구니와 상품이 같으면 중복으로 간주
        return cart.equals(cartItem.cart) && item.equals(cartItem.item);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cart, item);
    }
	  
}