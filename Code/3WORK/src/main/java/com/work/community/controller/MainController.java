package com.work.community.controller;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.work.community.config.SecurityUser;
import com.work.community.dto.UsersDTO;
import com.work.community.entity.Users;
import com.work.community.service.UsersService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class MainController {

	private final UsersService usersService;
	
	@GetMapping("/main")
	public String main(@ModelAttribute Users users,
			Model model, @AuthenticationPrincipal SecurityUser principal) {
		List<UsersDTO> usersDTOList = usersService.findAll();
		model.addAttribute("principal", principal);
		model.addAttribute("users", usersDTOList);
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
	@GetMapping("/submenu/faq")
	public String faq() {
		return "submenu/faq";
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