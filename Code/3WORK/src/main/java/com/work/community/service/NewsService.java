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

import com.work.community.dto.NewsDTO;
import com.work.community.entity.News;
import com.work.community.repository.NewsRepository;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class NewsService {
   
   private final NewsRepository newsRepository;

   // 뉴스 이미지 처리
   public void save(@Valid NewsDTO newsDTO, MultipartFile nimage) throws Exception {   
      if(!nimage.isEmpty()) {         
         UUID uuid = UUID.randomUUID();
         
         String filename = uuid + "_" + nimage.getOriginalFilename();
         // 1. 윈도우 : C:/3workfiles/news/
         // 2. 맥 : /Users/rim-yeeun/3workfiles/news/
         String filepath = "C:\\3workfiles\\news\\" + filename;
               
         File savedFile = new File(filepath);
         nimage.transferTo(savedFile);
         
         newsDTO.setNfilename(filename);
         newsDTO.setNfilepath(filepath);
      }
      News news = News.toSaveEntity(newsDTO);
      newsRepository.save(news);
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
   public void deleteById(Integer nno) {
      newsRepository.deleteById(nno);
   }

   // 뉴스 목록, 페이지 처리
   public Page<NewsDTO> findListAll(Pageable pageable) {
      int page = pageable.getPageNumber() - 1; // db는 현재페이지보다 1 작아야함
      int pageSize = 10;
      pageable = PageRequest.of(page, pageSize, Sort.Direction.DESC, "nno");
      
      Page<News> newsList = newsRepository.findAll(pageable);
      
      log.info("newsList.isFirst()=" + newsList.isFirst());
      log.info("newsList.isLast()=" + newsList.isLast());
      log.info("newsList.getNumber()=" + newsList.getNumber());
      
      // 생성자 방식으로 musicDTOList 만들기
      Page<NewsDTO> newsDTOList = newsList.map(news -> 
      new NewsDTO(news.getNno(), news.getNtitle(), news.getNcontent(), news.getNdate(), news.getNfilename(), news.getNfilepath()));
      
      return newsDTOList;
   }

   public NewsDTO findById(Integer nno) {
      // DB에서 member를 꺼내옴
      Optional<News> findNews = newsRepository.findById(nno);
      // 변환
      NewsDTO newsDTO = NewsDTO.toSaveDTO(findNews.get());
      
      return newsDTO;
   }
   
}