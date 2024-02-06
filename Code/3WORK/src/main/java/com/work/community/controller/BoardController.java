package com.work.community.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.work.community.dto.FoodDTO;
import com.work.community.dto.ItemDTO;
import com.work.community.dto.MusicDTO;
import com.work.community.service.FoodService;
import com.work.community.service.ItemService;
import com.work.community.service.MusicService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class BoardController {
	
	private final MusicService musicService;
	
	private final FoodService foodService;
	
	private final ItemService itemService;

	// 노래 추천 페이지 이동 & 목록 불러오기
	@GetMapping("/board/musicboard")
	public String musicBoard(Model model) {
		List<MusicDTO> musicList = musicService.findAll();
		model.addAttribute("musicList", musicList);
		return "board/musicboard";
	}
	
	// 식품 추천 페이지 이동 & 목록 불러오기
	@GetMapping("/board/food")
	public String food(Model model) {
		List<FoodDTO> foodList = foodService.findAll();
		model.addAttribute("foodList", foodList);
		return "board/food";
	}
	
	// 아이템 추천 페이지 이동 & 목록 불러오기
	@GetMapping("/board/item")
	public String item(Model model) {
		List<ItemDTO> itemList = itemService.findAll();
		model.addAttribute("itemList", itemList);
		return "board/item";
	}
	
}
