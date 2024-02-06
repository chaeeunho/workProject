package com.work.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {
	
	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	@GetMapping("/main2")
	public String main() {
		return "main2";
	}

}
