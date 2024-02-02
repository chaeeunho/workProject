package com.khit.board.controller;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.khit.board.config.SecurityUser;
import com.khit.board.dto.UsersDTO;
import com.khit.board.entity.Users;
import com.khit.board.service.UsersService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class UsersController {
	
	private final UsersService usersService;
	
	//로그인 페이지 요청 :  /login
		@GetMapping("/login")
		public String loginForm() {
			return "login";  //login.html
		}
	//메인 페이지
	@GetMapping("/main")
	public String main() {
		return "main";  //main.html
	}
	
	

	//회원 가입 페이지
	@GetMapping("/user/join")
	public String joinForm( ) {
		return "user/join";
	}
	
	//회원 가입 처리
	//@Valid : 필드의 유효성 검사
	//BindingResult: 에러 처리 클래스
	@PostMapping("/user/join")
	public String join(@Valid UsersDTO usersDTO,
			BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			//에러가 있으면 회원 가입 페이지에 머무름
			return "user/join";
		}
		usersService.save(usersDTO);
		return "redirect:/user/login";
	}
	
	   //회원 목록
		@GetMapping("/user/list")
		public String getList(Model model) {
			List<UsersDTO> usersDTOList = usersService.findAll();
			model.addAttribute("usersList", usersDTOList);
			return "user/list";
		}
		
		//회원 상세 보기
		@GetMapping("/user/{uno}")
		public String getMember(@PathVariable Integer uno,
				Model model) {
			UsersDTO usersDTO = usersService.findById(uno);
			model.addAttribute("users",usersDTO);
			return "user/detail";
		}
	
		//회원 삭제
		@GetMapping("/user/delete/{uno}")
		public String deleteUsers(@PathVariable Integer uno) {
			usersService.deleteById(uno);
			return "redirect:/user/list";
		}
		
		
}
