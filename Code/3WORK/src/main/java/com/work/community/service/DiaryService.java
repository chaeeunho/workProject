package com.work.community.service;

import java.io.File;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.work.community.dto.UserDiaryDTO;
import com.work.community.entity.UserDiary;
import com.work.community.repository.DiaryRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class DiaryService {
   
   private final DiaryRepository diaryRepository;

   //글쓰기 처리
   public void save(UserDiaryDTO userDiaryDTO, MultipartFile boardFile) throws Exception {
      UserDiary userDiary;
      
      if (!boardFile.isEmpty()) {
           // 이미지 파일이 업로드된 경우 처리
           UUID uuid = UUID.randomUUID();
           String boardFilename = uuid + "_" + boardFile.getOriginalFilename();
           String boardFilepath = "C:\\3workfiles\\diary\\" + boardFilename;
           File savedImageFile = new File(boardFilepath);
           boardFile.transferTo(savedImageFile);
           
           userDiaryDTO.setDfilename(boardFilename);
           userDiaryDTO.setDfilepath(boardFilepath);
      } /*
          * else { // 이미지 파일이 업로드되지 않은 경우 기존의 파일 정보를 유지
          * userDiaryDTO.setDfilename(findById(userDiaryDTO.getDno()).getDfilename());
          * userDiaryDTO.setDfilepath(findById(userDiaryDTO.getDno()).getDfilepath()); }
          */
      
       userDiary = UserDiary.saveToEntity(userDiaryDTO); // 수정된 부분
       diaryRepository.save(userDiary);
   }
   
   //글목록 불러오기 (페이지 처리)
   public Page<UserDiaryDTO> findAll(Pageable pageable) {
      int page = pageable.getPageNumber() - 1; // db는 현재페이지보다 1 작아야함
      int pageSize = 9;
      pageable = PageRequest.of(page, pageSize, Sort.Direction.DESC, "dno");
      
      Page<UserDiary> diaryList = diaryRepository.findAll(pageable);
      
      log.info("diaryList.isFirst()=" + diaryList.isFirst());
      log.info("diaryList.isLast()=" + diaryList.isLast());
      log.info("diaryList.getNumber()=" + diaryList.getNumber());
      /*
       * List<UserDiaryDTO> diaryDTOList = new ArrayList<>(); for(UserDiary userDiary
       * : diaryList) { UserDiaryDTO memberDTO = UserDiaryDTO.toSaveDTO(userDiary);
       * diaryDTOList.add(memberDTO); }
       */
      Page<UserDiaryDTO> userDiaryDTOList = diaryList.map(diary ->
      new UserDiaryDTO(diary.getDno(), diary.getDtitle(), diary.getDcontent(), diary.getDfilename(), 
            diary.getDfilepath(), diary.getUsers(), diary.getCreatedDate(), diary.getUpdatedDate()));
      
      return userDiaryDTOList;
   }
   
   //글 상세보기
   public UserDiary findById(Integer dno) {
      return diaryRepository.findById(dno).get();
   }
   //글 삭제
   public void deleteById(Integer dno) {
      diaryRepository.deleteById(dno);
   }
   
   //글 수정 (파일 저장 포함)
   public void update(UserDiaryDTO userDiaryDTO, MultipartFile dimage) throws Exception {
      UserDiary userdiary;
      if (!dimage.isEmpty()) {
           // 이미지 파일이 업로드된 경우 처리
           UUID uuid = UUID.randomUUID();
           String imageFilename = uuid + "_" + dimage.getOriginalFilename();
           String imageFilepath = "C:\\3workfiles\\diary\\" + imageFilename;
           File savedImageFile = new File(imageFilepath);
           dimage.transferTo(savedImageFile);
           
           userDiaryDTO.setDfilename(imageFilename);
           userDiaryDTO.setDfilepath(imageFilepath);
       } else {
           // 이미지 파일이 업로드되지 않은 경우 기존의 파일 정보를 유지
          userDiaryDTO.setDfilename(findById(userDiaryDTO.getDno()).getDfilename());
          userDiaryDTO.setDfilepath(findById(userDiaryDTO.getDno()).getDfilepath());
       }
      userdiary = UserDiary.saveToUpdate(userDiaryDTO);
      diaryRepository.save(userdiary);
   }

   public UserDiary findByDno(Integer dno) {
        return diaryRepository.findById(dno).orElse(null);
    }

}