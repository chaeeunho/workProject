package com.work.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MemberController {
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@GetMapping("/member/join_wel")
	public String join_wel() {
		return "member/join_wel";
	}
	
	@GetMapping("/member/id_search")
	public String id_search() {
		return "member/id_search";
	}
	
	@GetMapping("/member/id_result")
	public String id_result() {
		return "member/id_result";
	}
	
	@GetMapping("/member/pw_search")
	public String pw_search() {
		return "member/pw_search";
	}
	
	@GetMapping("/member/pw_result")
	public String pw_result() {
		return "member/pw_result";
	}
	
	@GetMapping("/member/update")
	public String update() {
		return "member/update";
	}
	
}
