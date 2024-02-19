package com.work.community.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import com.work.community.config.SecurityUser;
import com.work.community.dto.UserDiaryDTO;
import com.work.community.entity.UserDiary;
import com.work.community.service.DiaryService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class DiaryController {
   
   private final DiaryService diaryService;

   //유저 건강일지 페이지
    @GetMapping("/user/userdiary/{uno}")
    public String userDiary(@PathVariable Integer uno,
          Model model,
          @PageableDefault(page = 1) Pageable pageable) {
       Page<UserDiaryDTO> diaryList = diaryService.findAll(pageable);
       
       //하단에 페이지 영역 만들기
        int blockLimit = 10; //하단에 보여줄 페이지 개수
        //시작페이지
        int startPage = ((int)(Math.ceil((double)pageable.getPageNumber() / blockLimit)) - 1)* blockLimit + 1;
        //마지막 페이지
        int endPage = (startPage + blockLimit - 1) > diaryList.getTotalPages() ?
           diaryList.getTotalPages() : startPage + blockLimit - 1;
       
        model.addAttribute("uno", uno);
      model.addAttribute("diary", diaryList);
      model.addAttribute("startPage", startPage);
      model.addAttribute("endPage", endPage);
       return "/user/userdiary";
    }
    
    //유저 건강일지 글쓰기페이지
    @GetMapping("/user/diarywrite")
    public String writeDiary(@AuthenticationPrincipal SecurityUser principal,
          Model model) {
       Integer uno = principal.getUsers().getUno();
       model.addAttribute("uno", uno);
       return "user/diarywrite";
    }
    
    //글쓰기
     @PostMapping("/user/diarywrite/{uno}")
     public String write(@ModelAttribute UserDiaryDTO userDiaryDTO, 
           @PathVariable Integer uno,
           MultipartFile boardFile, Model model,
           @AuthenticationPrincipal SecurityUser principal) throws Exception {
        userDiaryDTO.setUsers(principal.getUsers());
        diaryService.save(userDiaryDTO, boardFile);
        model.addAttribute("diary", boardFile);
        return "redirect:/user/userdiary/" + uno;
     }
     
     //글 삭제
     @GetMapping("/delete/{dno}")
     public String delete(@PathVariable Integer dno) {
        UserDiary userDiary = diaryService.findByDno(dno);
        diaryService.deleteById(dno);
        return "redirect:/user/userdiary/" + userDiary.getUsers().getUno();
     }
     
     //글 상세보기
     @GetMapping("/userdiary/{dno}")
   public String getDiary(@PathVariable Integer dno,
         Model model) {
      UserDiary diaryDetail = diaryService.findById(dno);
      model.addAttribute("diaryDetail", diaryDetail);
      model.addAttribute("dno", dno);
      return "user/diarydetail";
   }
     
     //글 수정페이지
   @GetMapping("/user/diaryupdate/{dno}")
   public String diaryUpdate(@PathVariable Integer dno,
         Model model) {
      //해당 아이디의 수정할 게시글 가져오기
      UserDiary userDiary = diaryService.findById(dno);
      model.addAttribute("diary", userDiary);
      model.addAttribute("dno", dno);
      return "user/diaryupdate";
   }
   
   //글 수정처리
   @PostMapping("/user/diaryupdate")
   public String update(@ModelAttribute UserDiaryDTO userDiaryDTO,
         MultipartFile dimage,
         @AuthenticationPrincipal SecurityUser principal) throws Exception {
      userDiaryDTO.setUsers(principal.getUsers());
      diaryService.update(userDiaryDTO, dimage);
      return "redirect:/user/userdiary/" + userDiaryDTO.getDno();
   }
     
}