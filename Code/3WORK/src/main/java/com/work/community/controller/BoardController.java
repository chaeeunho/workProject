package com.work.community.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.work.community.dto.FoodDTO;
import com.work.community.dto.ItemDTO;
import com.work.community.dto.MusicDTO;
import com.work.community.dto.NewsDTO;
import com.work.community.service.FoodService;
import com.work.community.service.ItemService;
import com.work.community.service.MusicService;
import com.work.community.service.NewsService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class BoardController {
	
	private final MusicService musicService;
	
	private final FoodService foodService;
	
	private final ItemService itemService;
	
	private final NewsService newsService;
	
	// 노래 추천 페이지 이동 & 목록 불러오기
	@GetMapping("/board/musicboard")
	public String musicBoard(@PageableDefault(page = 1) Pageable pageable, Model model) {
		Page<MusicDTO> musicDTOList = musicService.findListAll(pageable);
		
		//하단의 페이지 블럭 만들기
		int blockLimit = 10;  //하단에 보여줄 페이지 개수
		//시작 페이지 1, 11, 21    12/10 = 1.2 -> 2.2 -> 2-1, 1*10+1 =11
		int startPage = ((int)(Math.ceil((double)pageable.getPageNumber()/blockLimit))-1)*blockLimit+1;
		//마지막 페이지 10, 20, 30 //12page -> 12 마지막
		int endPage = (startPage+blockLimit-1) > musicDTOList.getTotalPages() ?
				musicDTOList.getTotalPages() : startPage+blockLimit-1;
		
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		model.addAttribute("musicList", musicDTOList);
		
		return "board/musicboard";
	}
	
	// 식품 추천 페이지 이동 & 목록 불러오기
	@GetMapping("/board/food")
	public String food(@PageableDefault(page = 1) Pageable pageable, Model model) {
		Page<FoodDTO> foodDTOList = foodService.findListAll(pageable);
		
		//하단의 페이지 블럭 만들기
		int blockLimit = 9;  //하단에 보여줄 페이지 개수
		//시작 페이지 1, 11, 21    12/10 = 1.2 -> 2.2 -> 2-1, 1*10+1 =11
		int startPage = ((int)(Math.ceil((double)pageable.getPageNumber()/blockLimit))-1)*blockLimit+1;
		//마지막 페이지 10, 20, 30 //12page -> 12 마지막
		int endPage = (startPage+blockLimit-1) > foodDTOList.getTotalPages() ?
				foodDTOList.getTotalPages() : startPage+blockLimit-1;
		
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		model.addAttribute("foodList", foodDTOList);
		return "board/food";
	}
	
	// 아이템 추천 페이지 이동 & 목록 불러오기
	@GetMapping("/board/item")
	public String item(@PageableDefault(page = 1) Pageable pageable, Model model) {
		Page<ItemDTO> itemDTOList = itemService.findListAll(pageable);
		
		//하단의 페이지 블럭 만들기
		int blockLimit = 10;  //하단에 보여줄 페이지 개수
		//시작 페이지 1, 11, 21    12/10 = 1.2 -> 2.2 -> 2-1, 1*10+1 =11
		int startPage = ((int)(Math.ceil((double)pageable.getPageNumber()/blockLimit))-1)*blockLimit+1;
		//마지막 페이지 10, 20, 30 //12page -> 12 마지막
		int endPage = (startPage+blockLimit-1) > itemDTOList.getTotalPages() ?
				itemDTOList.getTotalPages() : startPage+blockLimit-1;
		
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		model.addAttribute("itemList", itemDTOList);
		return "board/item";
	}
	
	// 뉴스 페이지 이동 & 뉴스 불러오기
	@GetMapping("/board/news")
	public String news(@PageableDefault(page = 1) Pageable pageable, Model model) {
		Page<NewsDTO> newsDTOList = newsService.findListAll(pageable);
		
		//하단의 페이지 블럭 만들기
		int blockLimit = 10;  //하단에 보여줄 페이지 개수
		//시작 페이지 1, 11, 21    12/10 = 1.2 -> 2.2 -> 2-1, 1*10+1 =11
		int startPage = ((int)(Math.ceil((double)pageable.getPageNumber()/blockLimit))-1)*blockLimit+1;
		//마지막 페이지 10, 20, 30 //12page -> 12 마지막
		int endPage = (startPage+blockLimit-1) > newsDTOList.getTotalPages() ?
				newsDTOList.getTotalPages() : startPage+blockLimit-1;
		
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		model.addAttribute("newsList", newsDTOList);
		return "board/news";
	}
	
	// 뉴스 상세보기
	@GetMapping("/board/news/{nno}")
	public String getNewsDetail(@PathVariable Integer nno, Model model) {
		NewsDTO newsDTO = newsService.findById(nno);
		model.addAttribute("news", newsDTO);
		return "board/newsdetail";
	}
	
	// 지도 페이지 이동
	@GetMapping("/board/map")
	public String map(){
		return "board/map";
	}
	
}
