package com.work.community.entity;

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

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class CartItem {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cino;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cart_id") // foreign key (cart_id) references Cart (cno)
    private Cart cart;

	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "item_id")
    private Item item;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "food_id")
	private Food food;
	
	public static CartItem createCartItem(Cart cart, Item item, Food food) {
        CartItem cartItem = new CartItem();
        cartItem.setCart(cart);
        cartItem.setItem(item);
        cartItem.setFood(food);
        return cartItem;
    }
	
}
