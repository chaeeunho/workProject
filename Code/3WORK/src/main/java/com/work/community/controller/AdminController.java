package com.work.community.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import com.work.community.dto.FoodDTO;
import com.work.community.dto.ItemDTO;
import com.work.community.dto.MusicDTO;
import com.work.community.dto.NewsDTO;
import com.work.community.dto.UsersDTO;
import com.work.community.service.FoodService;
import com.work.community.service.ItemService;
import com.work.community.service.MusicService;
import com.work.community.service.NewsService;
import com.work.community.service.UsersService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class AdminController {
	
	private final UsersService usersService;
	
	private final MusicService musicService;

	private final FoodService foodService;
	
	private final ItemService itemService;
	
	private final NewsService newsService;
	
	// 관리자 - 페이지 이동
	@GetMapping("/admin/admin_main")
	public String main() {
		return "admin/admin_main";
	}
	@GetMapping("/test")
	public String test() {
		return "test";
	}
	
	
	
	// 회원 관리 페이지 이동 & 목록 불러오기
	@GetMapping("/admin/usermanage")
	public String userList(Model model) {
		List<UsersDTO> usersDTOList = usersService.findAll();
		model.addAttribute("usersList", usersDTOList);
		return "admin/usermanage";
	}
	
	// 회원 정보 상세보기
	@GetMapping("/admin/userdetail/{uno}")
	public String userDetail(@PathVariable Integer uno, Model model) {                  
		UsersDTO usersDTO = usersService.findById(uno);
		model.addAttribute("users", usersDTO);
		return "admin/userdetail";
	}
	
	// 회원 정보 삭제 - 처리
	@GetMapping("/admin/userdelete/{uno}")
	public String userDelete(@PathVariable Integer uno) {
		usersService.deleteById(uno);
		return "redirect:/admin/usermanage";
	}
	
	// 회원목록
	@GetMapping("/admin/list")
	public String list() {
		return "admin/list";
	}
	
	

	// 노래 목록 페이지 이동 & 목록 불러오기
	@GetMapping("/admin/musiclist")
	public String musicList(@PageableDefault(page = 1) Pageable pageable, Model model) {
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
		
		return "admin/musiclist";
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
		return "redirect:/admin/musiclist";
	}
	
	// 노래 추천 - 삭제
	@GetMapping("/admin/musicdelete/{mno}")
	public String musicDelete(@PathVariable Integer mno) {
		musicService.deleteById(mno);
		return "redirect:/admin/musiclist";
	}
	
	
	
	// 건강식품 목록 페이지 이동 & 목록 불러오기
	@GetMapping("/admin/foodlist")
	public String foodList(@PageableDefault(page = 1) Pageable pageable, Model model) {
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
		
		return "admin/foodlist";
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
		return "redirect:/admin/foodlist";
	}
	
	// 건강 식품 - 삭제
	@GetMapping("/admin/fooddelete/{fno}")
	public String foodDelete(@PathVariable Integer fno) {
		foodService.deleteById(fno);
		return "redirect:/admin/foodlist";
	}
	
	
	
	// 아이템 목록 페이지 이동 & 목록 불러오기
	@GetMapping("/admin/itemlist")
	public String itemList(@PageableDefault(page = 1) Pageable pageable, Model model) {
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
		
		return "admin/itemlist";
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
		return "redirect:/admin/itemlist";
	}
	
	// 아이템 - 삭제
	@GetMapping("/admin/itemdelete/{ino}")
	public String itemDelete(@PathVariable Integer ino) {
		itemService.deleteById(ino);
		return "redirect:/admin/itemlist";
	}
	
	
	
	// 뉴스 목록 페이지 이동 & 목록 불러오기
	@GetMapping("/admin/newslist")
	public String newsList(@PageableDefault(page = 1) Pageable pageable, Model model) {
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
		
		return "admin/newslist";
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
		return "redirect:/admin/newslist";
	}
	
	// 뉴스 - 삭제
	@GetMapping("/admin/newsdelete/{nno}")
	public String newsDelete(@PathVariable Integer nno) {
		newsService.deleteById(nno);
		return "redirect:/admin/newslist";
	}
	
	
	
	// 효과 - 페이지 이동
	
	
	// 효과 - 처리
	
}
