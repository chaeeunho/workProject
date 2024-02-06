package com.work.community.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.work.community.dto.FoodDTO;
import com.work.community.entity.Food;
import com.work.community.repository.FoodRepository;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class FoodService {

	private final FoodRepository foodRepository;
	
	public void save(@Valid FoodDTO foodDTO, MultipartFile fimage) throws Exception {
		// 1. 파일을 서버에 저장하고, 
		if(!fimage.isEmpty()) { // 전달된 파일이 있으면
		  String filepath = System.getProperty("user.dir") + "/src/main/resources/static/upload/food/";
		  
		  UUID uuid = UUID.randomUUID();  // 무작위 아이디 생성(중복파일의 이름을 생성해줌)
		  
		  String filename = uuid + "_" + fimage.getOriginalFilename(); // 원본 파일
		  
		  // File 클래스 객체 생성
		  File savedFile = new File(filepath, filename); // upload 폴더에 저장
		  fimage.transferTo(savedFile); 
		
		  // 2. 파일 이름은 db에 저장
		  foodDTO.setFfilename(filename);
		  foodDTO.setFfilepath("/upload/food/" + filename); // 파일 경로 설정함
		}
		// dto -> entity로 변환
		Food food = Food.toSaveEntity(foodDTO);
		// entity를 db에 저장
		foodRepository.save(food);
	}

	public List<FoodDTO> findAll() {
		List<Food> foodList = foodRepository.findAll();
		List<FoodDTO> foodDTOList = new ArrayList<>();
		for(Food food : foodList) {
			FoodDTO foodDTO = FoodDTO.toSaveDTO(food);
			foodDTOList.add(foodDTO);
		}
		return foodDTOList;
	}
	
}
