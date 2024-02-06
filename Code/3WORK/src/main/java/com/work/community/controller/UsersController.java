package com.work.community.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.work.community.dto.UsersDTO;
import com.work.community.service.UsersService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class UsersController {
	
	 private final UsersService usersService;
	 
	 @GetMapping("/user/userpage")
	   public String userPage() {
	      return "user/userpage";  //login.html
	   }
	 
	 @GetMapping("/user/userupdate")
	   public String userUpdate() {
	      return "user/userupdate";  //login.html
	   }
	
	  //로그인 페이지 요청 :  /login
	   @GetMapping("/login")
	   public String loginForm() {
	      return "login";  //login.html
	   }
	   
	   //회원 가입 페이지
	   @GetMapping("/user/join")
	   public String joinForm(UsersDTO usersDTO) {
	      return "user/join";
	   }
	   
	   //회원 가입 처리
	   //@Valid : 필드의 유효성 검사
	   //BindingResult: 에러 처리 클래스
	   @PostMapping("/user/join")
	   public String join(@Valid UsersDTO usersDTO,
	         BindingResult bindingResult) {
	      if(bindingResult.hasErrors()) {
	         //에러가 있으면 회원 가입 페이지에 머무름
	         return "user/join";
	      }
	      usersService.save(usersDTO);
	      return "redirect:/login";
	   }
	   
	   //이메일 중복 검사
      @PostMapping("/user/check-email")
      public @ResponseBody String checkId(@RequestParam("uid")
            String uid) {
         String resultText = usersService.checkId(uid);
         return resultText; //res
      }
      
      //회원 목록
      @GetMapping("/user/list")
      public String getList(Model model) {
         List<UsersDTO> usersDTOList = usersService.findAll();
         model.addAttribute("usersList", usersDTOList);
         return "user/list";
      }
      
      //회원 상세 보기
      @GetMapping("/user/{uno}")
      public String getMember(@PathVariable Integer uno,
            Model model) {
         UsersDTO usersDTO = usersService.findById(uno);
         model.addAttribute("users",usersDTO);
         return "user/detail";
      }

      //회원 삭제
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
	
//    //회원 수정 페이지
//    //@AuthenticationPrincipal - 회원을 인가하는 클래스
//    @GetMapping("/user/update")
//    public String updateMemberForm(
//          @AuthenticationPrincipal SecurityUser principal,
//          Model model) {
//       UsersDTO usersDTO = usersService.findByUid(principal);
//       model.addAttribute("users", usersDTO);
//       return "user/update";
//    }
//    
//    //회원 수정 처리 - 상세보기로 이동
//    @PostMapping("/user/update")
//    public String update(@ModelAttribute UsersDTO usersDTO) {
//       usersService.update(usersDTO);
//       return "redirect:/users/" + usersDTO.getUid();
//    }
	
}
