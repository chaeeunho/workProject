package com.work.community.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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
public class Cart extends BaseEntity{
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cno;
	
	@OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private Users users; // 회원
	
	private int count; // 카트에 담긴 총 상품 개수
	
	@OneToMany(mappedBy = "cart")
    private List<CartItem> cartItem = new ArrayList<>();
	
	// 회원 1명당 카트 1개 생성
	public static Cart createCart(Users users) {
        Cart cart = new Cart();
        cart.setCount(0);
        cart.setUsers(users);
        return cart;
    }

}
