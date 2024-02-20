package com.work.community.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.work.community.config.SecurityUser;
import com.work.community.dto.CartItemDTO;
import com.work.community.entity.Cart;
import com.work.community.entity.Food;
import com.work.community.entity.Item;
import com.work.community.entity.Users;
import com.work.community.service.CartService;
import com.work.community.service.FoodService;
import com.work.community.service.ItemService;
import com.work.community.service.UsersService;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Controller
public class CartController {
	
	private final CartService cartService;
	
	private final UsersService usersService;
	
	private final ItemService itemService;
	
	private final FoodService foodService;

	// 장바구니 페이지 이동
	@GetMapping("/user/mybag/{uno}")
	public String myBag(@PathVariable Integer uno, @PageableDefault(page = 1) Pageable pageable, 
			Model model, @AuthenticationPrincipal SecurityUser principal) {
		
		// 로그인이 되어있는 유저의 id와 장바구니에 접속하는 id가 같아야 함
	    if (principal.getUsers().getUno() == uno) {
	    	
	    	Users users = usersService.findById(uno);
	        // 로그인 되어 있는 유저에 해당하는 장바구니 가져오기
	        Cart userCart = users.getCart();
	        
	        // 장바구니에 들어있는 아이템 모두 가져오기
	        Page<CartItemDTO> cartDTOList = cartService.findListAll(pageable);
	        users = usersService.findById(uno);
	        
	        //하단에 페이지 영역 만들기
	        int blockLimit = 10; //하단에 보여줄 페이지 개수
	        //시작 페이지 1, 11, 21 / ex)12를 10으로 나누면 1.2가 나오니 반올림후 실수로 변경하면 2
	        //1을 빼주고 x 10 + 1 = 11
	        int startPage = ((int)(Math.ceil((double)pageable.getPageNumber() / blockLimit)) - 1)* blockLimit + 1;
	        //마지막 페이지 10, 20, 30 // 만약 12페이지가 마지막페이지면
	        int endPage = (startPage + blockLimit - 1) > cartDTOList.getTotalPages() ?
	        		cartDTOList.getTotalPages() : startPage + blockLimit - 1;
	        
	        model.addAttribute("cartList", cartDTOList);
	        model.addAttribute("Userinfo", users);
	        model.addAttribute("startPage", startPage);
	        model.addAttribute("endPage", endPage);
	        model.addAttribute("userCart", userCart);
	        model.addAttribute("principal", principal);

	        return "user/mybag";
	    }
	    // 로그인 id와 장바구니 접속 id가 같지 않는 경우
	    else {
	        return "redirect:/main";
	    }
	    
	}
	
	// 장바구니 담기 (아이템)
	@PostMapping("/user/mybagitem/{uno}/{ino}")
	public String addMyBagItem(@PathVariable Integer uno, @PathVariable Integer ino) {
		Users users = usersService.findById(uno);
		Item item = itemService.findById(ino);
		
		cartService.addCartItem(users, item);
		
		return "redirect:/user/mybag/{uno}";
	}
	
	// 장바구니 담기 (식품)
	@PostMapping("/user/mybagfood/{uno}/{fno}")
	public String addMyBagFood(@PathVariable Integer uno, @PathVariable Integer fno) {
		Users users = usersService.findById(uno);
		Food food = foodService.findById(fno);
		
		cartService.addCartFood(users, food);
		
		return "redirect:/user/mybag/{uno}";
	}
	
	// 장바구니 삭제 (아이템)
	@GetMapping("/user/mybag/{uno}/{ino}/{cino}")
	public String deleteMyBagItem(@PathVariable Integer uno, @PathVariable Integer ino, @PathVariable Integer cino) {
        cartService.deleteById(cino);
		return "redirect:/user/mybag/{uno}";
	}
	
	// 장바구니 삭제 (식품)
	@PostMapping("/user/mybag/{uno}/{fno}/{cino}")
	public String deleteByBagFood(@PathVariable Integer uno, @PathVariable Integer fno, @PathVariable Integer cino) {
		cartService.deleteById(cino);
		return "redirect:/user/mybag/{uno}";
	}
	
}
