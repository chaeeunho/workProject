package com.work.community.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.work.community.dto.NewsDTO;
import com.work.community.entity.News;
import com.work.community.repository.NewsRepository;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class NewsService {
	
	private final NewsRepository newsRepository;

	public void save(@Valid NewsDTO newsDTO, MultipartFile nimage) throws Exception {	
		if(!nimage.isEmpty()) {
			String filepath = System.getProperty("user.dir") + "/src/main/resources/static/upload/news/";
			
			UUID uuid = UUID.randomUUID();
			
			String filename = uuid + "_" + nimage.getOriginalFilename();
					
			File savedFile = new File(filepath, filename);
			nimage.transferTo(savedFile);
			
			newsDTO.setNfilename(filename);
			newsDTO.setNfilepath("/upload/news/" + filename);
		}
		News news = News.toSaveEntity(newsDTO);
		newsRepository.save(news);
	}

	public List<NewsDTO> findAll() {
		List<News> newsList = newsRepository.findAll();
		List<NewsDTO> newsDTOList = new ArrayList<>();
		for(News news : newsList) {
			NewsDTO newsDTO = NewsDTO.toSaveDTO(news);
			newsDTOList.add(newsDTO);
		}
		return newsDTOList;
	}
	
}
