package com.work.community.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Cart extends BaseEntity{
	
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Integer cartno;
   
   // 회원 1명당 1개의 장바구니를 가짐 - 일대일 관계
   @OneToOne(fetch = FetchType.EAGER)
   @JoinColumn(name = "uno")
   private Users users;
   	   
   public static Cart createCart(Users users) {
        Cart existingCart = users.getCart(); // 이미 존재하는 장바구니 확인
        if (existingCart != null) {
            return existingCart; // 이미 존재하는 장바구니 반환
        }

        Cart newCart = new Cart();
        newCart.setUsers(users); // Users 엔티티를 설정
        return newCart;
    }
	   
}
