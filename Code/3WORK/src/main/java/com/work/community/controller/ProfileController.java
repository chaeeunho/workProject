package com.work.community.controller;

import org.springframework.stereotype.Controller;

import com.work.community.service.ProfileService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class ProfileController {
	
	private final ProfileService profileService;

	//프로필 유저 홈페이지에 출력
	/* @GetMapping("/user/userpage/{pno}")
	public String profile(@PathVariable Integer pno,
			Model model) {
		Profile profile = profileService.findById(pno);
		model.addAttribute("profile", profile);
		return "user/userpage";
	} */
}
