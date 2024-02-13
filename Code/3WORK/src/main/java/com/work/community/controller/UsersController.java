package com.work.community.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.work.community.config.SecurityUser;
import com.work.community.dto.CommentsDTO;
import com.work.community.dto.FoodDTO;
import com.work.community.dto.UsersDTO;
import com.work.community.entity.Comments;
import com.work.community.entity.Users;
import com.work.community.service.CommentsService;
import com.work.community.service.UsersService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class UsersController {

	private final UsersService usersService;
	private final CommentsService commentsService;

	// 로그인 페이지 요청 : /login
	@GetMapping("/login")
	public String loginForm() {
		return "login"; // login.html
	}

//	//로그인 처리
//	@PostMapping("/login")
//	public String login(@ModelAttribute Users users, HttpSession session) {
//		Users loginUser = usersService.login(users);
//		if(loginUser != null
//				&& loginUser.getUpassword().equals(users.getUpassword())) {
//			//아이디 비번 일치해서 로그인 되면 세션 발급
//			session.setAttribute("sessionId", loginUser.getUid());
//			return "main";
//		}else {
//			return "login";
//		}
//	}
	
	//유저 홈페이지
	@GetMapping("/user/userpage/{uno}")
    public String userPage(@PathVariable Integer uno,
		@PageableDefault(page = 1) Pageable pageable,
	                    Model model) {
		Page<CommentsDTO> commentList = commentsService.findListAll(pageable);
	    Users users = usersService.findById(uno);
	//하단에 페이지 영역 만들기
			int blockLimit = 10; //하단에 보여줄 페이지 개수
			//시작 페이지 1, 11, 21 / ex)12를 10으로 나누면 1.2가 나오니 반올림후 실수로 변경하면 2
			//1을 빼주고 x 10 + 1 = 11
			int startPage = ((int)(Math.ceil((double)pageable.getPageNumber() / blockLimit)) - 1)* blockLimit + 1;
			//마지막 페이지 10, 20, 30 // 만약 12페이지가 마지막페이지면
			int endPage = (startPage + blockLimit - 1) > commentList.getTotalPages() ?
					commentList.getTotalPages() : startPage + blockLimit - 1;
        
        model.addAttribute("commentList", commentList);
        model.addAttribute("Userinfo", users);
        model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
        return "user/userpage";
    }

	// 회원 가입 페이지
	@GetMapping("/user/join")
	public String joinForm(UsersDTO usersDTO) {
		return "user/join";
	}

	// 회원 가입 처리
	// @Valid : 필드의 유효성 검사
	// BindingResult: 에러 처리 클래스
	@PostMapping("/user/join")
	public String join(@Valid UsersDTO usersDTO, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			// 에러가 있으면 회원 가입 페이지에 머무름
			return "user/join";
		}
		usersService.save(usersDTO);
		return "user/join_wel";
	}

	// 이메일 중복 검사
	@PostMapping("/user/check-email")
	public @ResponseBody String checkId(@RequestParam("uid") String uid) {
		String resultText = usersService.checkId(uid);
		return resultText; // res
	}

	// 닉네임 중복 검사
	@PostMapping("/user/check-nickname")
	public @ResponseBody String checkNickname(@RequestParam("unickname") String unickname) {
		String result = usersService.checkNickname(unickname);
		return result;
	}

	// 회원 목록
	@GetMapping("/user/list")
	public String getList(Model model) {
		List<UsersDTO> usersDTOList = usersService.findAll();
		model.addAttribute("usersList", usersDTOList);
		return "user/list";
	}

	/*
	 * // 회원 상세 보기
	 * 
	 * @GetMapping("/user/{uno}") public String getMember(@PathVariable Integer uno,
	 * Model model) { Users users = usersService.findById(uno);
	 * model.addAttribute("users", users); return "user/detail"; }
	 */

	// 회원 삭제
	@GetMapping("/user/delete/{uno}")
	public String deleteUsers(@PathVariable Integer uno) {
		usersService.deleteById(uno);
		return "redirect:/user/list";
	}

	// 아이디 찾기 페이지
	@GetMapping("/user/id_search")
	public String idSearchForm() {
		return "user/id_search"; // 아이디 찾기 폼 페이지 반환
	}

	// 아이디 찾기 처리
	@PostMapping("/user/id_search")
	public String idSearch(@RequestParam("uid") String email, Model model) {
		// uid를 사용하여 사용자 아이디 찾기 로직 구현
		// 찾은 아이디를 model에 추가하여 결과 페이지에 표시
		return "user/id_result"; // 결과 페이지 반환
	}

	// 비밀번호 찾기 페이지
	@GetMapping("/user/pw_search")
	public String pwSearchForm() {
		return "user/pw_search"; // 비밀번호 찾기 폼 페이지 반환
	}

	// 비밀번호 찾기 처리
	@PostMapping("/user/pw_search")
	public String pwSearch(@RequestParam("uid") String userId, Model model) {
		// 사용자 ID를 사용하여 비밀번호 찾기 로직 구현
		// 임시 비밀번호 발급 또는 비밀번호 재설정 링크를 이메일로 전송
		return "user/pw_result"; // 결과 페이지 반환
	}

	//회원 수정 페이지
	//@AuthenticationPrincipal - 회원을 인가하는 클래스
	@GetMapping("/user/userupdate")
	public String updateUserForm(
			@AuthenticationPrincipal SecurityUser principal,
			Model model) {
		UsersDTO usersDTO = usersService.findByUid(principal);
		model.addAttribute("users", usersDTO);
		return "user/userupdate";
	}
		
	//회원 수정 처리 - 상세보기로 이동
	@PostMapping("/user/userupdate")
	public String update(@ModelAttribute UsersDTO usersDTO,
			 MultipartFile uimage, Model model) throws Exception {
		usersService.saveImage(usersDTO, uimage);
		model.addAttribute("users", usersDTO);
		return "redirect:/user/userpage/" + usersDTO.getUno();
	}


}
