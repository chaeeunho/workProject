package com.work.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.work.community.dto.FoodDTO;
import com.work.community.dto.ItemDTO;
import com.work.community.dto.MusicDTO;
import com.work.community.dto.NewsDTO;
import com.work.community.service.FoodService;
import com.work.community.service.ItemService;
import com.work.community.service.MusicService;
import com.work.community.service.NewsService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class AdminController {
	
	private final MusicService musicService;

	private final FoodService foodService;
	
	private final ItemService itemService;
	
	private final NewsService newsService;
	
	// 관리자 - 페이지 이동
	@GetMapping("/admin/main")
	public String main() {
		return "admin/main";
	}
	
	// 회원목록
	@GetMapping("/admin/list")
	public String list() {
		return "admin/list";
	}
	
	// 노래 추천 - 페이지 이동
	@GetMapping("/admin/musicform")
	public String musicForm() {
		return "admin/musicform";
	}
	
	// 노래 추천 - 처리
	@PostMapping("/admin/musicform")
	public String music(@Valid MusicDTO musicDTO) {
		musicService.save(musicDTO);
		return "redirect:/admin/main";
	}
	
	
	// 건강 식품 - 페이지 이동
	@GetMapping("/admin/foodform")
	public String foodForm() {
		return "admin/foodform";
	}
	
	// 건강 식품 - 처리
	@PostMapping("/admin/foodform")
	public String food(@Valid FoodDTO foodDTO, MultipartFile fimage) throws Exception {
		foodService.save(foodDTO, fimage);
		return "redirect:/admin/main";
	}
	
	
	// 아이템 - 페이지 이동
	@GetMapping("/admin/itemform")
	public String itemForm() {
		return "admin/itemform";
	}
	
	// 아이템 - 처리
	@PostMapping("/admin/itemform")
	public String item(@Valid ItemDTO itemDTO, MultipartFile iimage) throws Exception {
		itemService.save(itemDTO, iimage);
		return "redirect:/admin/main";
	}
	
	// 뉴스 - 페이지 이동
	@GetMapping("/admin/newsform")
	public String newsForm() {
		return "admin/newsform";
	}
	
	// 뉴스 - 처리
	@PostMapping("/admin/newsform")
	public String news(@Valid NewsDTO newsDTO, MultipartFile nimage) throws Exception {
		newsService.save(newsDTO, nimage);
		return "redirect:/admin/main";
	}
	
	
	// 효과 - 페이지 이동
	
	
	// 효과 - 처리
	
}
