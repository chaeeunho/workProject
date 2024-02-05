package com.work.community.service;

import java.io.File;
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
			String filepath = "/Users/rim-yeeun/Finalteam1/Code/3WORK/src/main/resources/static/upload";
			
			UUID uuid = UUID.randomUUID();
			
			String filename = uuid + "_" + nimage.getOriginalFilename();
					
			File savedFile = new File(filepath, filename);
			nimage.transferTo(savedFile);
			
			newsDTO.setNfilename(filename);
			newsDTO.setNfilepath(filepath);
		}
		News news = News.toSaveEntity(newsDTO);
		newsRepository.save(news);
	}
	
}
