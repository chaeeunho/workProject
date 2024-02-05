package com.work.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
	
	@GetMapping("/main")
	public String main() {
		return "main";
	}
	
	@GetMapping("/submenu/privacy")
	public String privacy() {
		return "submenu/privacy";
	}
	
	@GetMapping("/submenu/rule")
	public String rule() {
		return "submenu/rule";
	}
	
	@GetMapping("/submenu/contact")
	public String contact() {
		return "submenu/contact";
	}
	
	@GetMapping("/submenu/way")
	public String way() {
		return "submenu/way";
	}

}