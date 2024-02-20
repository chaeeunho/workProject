package com.work.community.service;

import java.io.File;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.work.community.dto.InfoDTO;
import com.work.community.entity.Info;
import com.work.community.repository.InfoRepository;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class InfoService {
   
   private final InfoRepository infoRepository;

   // 뉴스 이미지 처리
   public void save(@Valid InfoDTO infoDTO, MultipartFile inimage) throws Exception {   
      if(!inimage.isEmpty()) {         
         UUID uuid = UUID.randomUUID();
         
         String filename = uuid + "_" + inimage.getOriginalFilename();
         // 1. 윈도우 : C:/3workfiles/news/
         // 2. 맥 : /Users/rim-yeeun/3workfiles/news/
         String filepath = "C:\\3workfiles\\info\\" + filename;
               
         File savedFile = new File(filepath);
         inimage.transferTo(savedFile);
         
         infoDTO.setInfilename(filename);
         infoDTO.setInfilepath(filepath);
      }
      Info info = Info.toSaveEntity(infoDTO);
      infoRepository.save(info);
   }

   // 페이지 처리 전, 뉴스 전체 목록 가져오기
   /* public List<NewsDTO> findAll() {
      List<News> newsList = newsRepository.findAll();
      List<NewsDTO> newsDTOList = new ArrayList<>();
      for(News news : newsList) {
         NewsDTO newsDTO = NewsDTO.toSaveDTO(news);
         newsDTOList.add(newsDTO);
      }
      return newsDTOList;
   } */

   // 뉴스 삭제
   public void deleteById(Integer inno) {
      infoRepository.deleteById(inno);
   }

   // 뉴스 목록, 페이지 처리
   public Page<InfoDTO> findListAll(Pageable pageable) {
      int page = pageable.getPageNumber() - 1; // db는 현재페이지보다 1 작아야함
      int pageSize = 10;
      pageable = PageRequest.of(page, pageSize, Sort.Direction.DESC, "inno");
      
      Page<Info> infoList = infoRepository.findAll(pageable);
      
      log.info("infoList.isFirst()=" + infoList.isFirst());
      log.info("infoList.isLast()=" + infoList.isLast());
      log.info("infoList.getNumber()=" + infoList.getNumber());
      
      // 생성자 방식으로 musicDTOList 만들기
      Page<InfoDTO> infoDTOList = infoList.map(info -> 
      new InfoDTO(info.getInno(), info.getIntitle(), info.getIncontent(), info.getIndate(), info.getInfilename(), info.getInfilepath()));
      
      return infoDTOList;
   }

   public InfoDTO findById(Integer inno) {
      // DB에서 member를 꺼내옴
      Optional<Info> findInfo = infoRepository.findById(inno);
      // 변환
      InfoDTO infoDTO = InfoDTO.toSaveDTO(findInfo.get());
      
      return infoDTO;
   }
   
}