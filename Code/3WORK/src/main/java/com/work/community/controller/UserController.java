package com.work.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@GetMapping("/user/join_wel")
	public String join_wel() {
		return "user/join_wel";
	}
	
	@GetMapping("/user/id_search")
	public String id_search() {
		return "user/id_search";
	}
	
	@GetMapping("/user/id_result")
	public String id_result() {
		return "user/id_result";
	}
	
	@GetMapping("/user/pw_search")
	public String pw_search() {
		return "user/pw_search";
	}
	
	@GetMapping("/user/pw_result")
	public String pw_result() {
		return "user/pw_result";
	}
	
	@GetMapping("/user/update")
	public String update() {
		return "user/update";
	}
	
}
