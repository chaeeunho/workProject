package com.work.community.service;

import java.io.File;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.work.community.dto.FoodDTO;
import com.work.community.entity.Food;
import com.work.community.repository.FoodRepository;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class FoodService {

	private final FoodRepository foodRepository;
	
	// 건강식품 이미지 처리
	public void save(@Valid FoodDTO foodDTO, MultipartFile fimage) throws Exception {
		// 1. 파일을 서버에 저장하고, 
		if(!fimage.isEmpty()) { // 전달된 파일이 있으면		  
		  UUID uuid = UUID.randomUUID();  // 무작위 아이디 생성(중복파일의 이름을 생성해줌)
		  
		  String filename = uuid + "_" + fimage.getOriginalFilename(); // 원본 파일
		  // 1. 윈도우 : C:/3workfiles/food/
		  // 2. 맥 : /Users/rim-yeeun/3workfiles/food/
		  String filepath = "/Users/rim-yeeun/3workfiles/food/" + filename;
		  
		  // File 클래스 객체 생성
		  File savedFile = new File(filepath); // upload 폴더에 저장
		  fimage.transferTo(savedFile); 
		
		  // 2. 파일 이름은 db에 저장
		  foodDTO.setFfilename(filename);
		  foodDTO.setFfilepath(filepath); // 파일 경로 설정함
		}
		// dto -> entity로 변환
		Food food = Food.toSaveEntity(foodDTO);
		// entity를 db에 저장
		foodRepository.save(food);
	}

	// 페이지 처리 전, 건강식품 전체 목록 가져오기
	/* public List<FoodDTO> findAll() {
		List<Food> foodList = foodRepository.findAll();
		List<FoodDTO> foodDTOList = new ArrayList<>();
		for(Food food : foodList) {
			FoodDTO foodDTO = FoodDTO.toSaveDTO(food);
			foodDTOList.add(foodDTO);
		}
		return foodDTOList;
	} */
	
	// 건강식품 삭제
	public void deleteById(Integer fno) {
		foodRepository.deleteById(fno);
	}

	// 건강식품 목록, 페이지 처리
	public Page<FoodDTO> findListAll(Pageable pageable) {
		int page = pageable.getPageNumber() - 1; // db는 현재페이지보다 1 작아야함
		int pageSize = 9;
		pageable = PageRequest.of(page, pageSize, Sort.Direction.DESC, "fno");
		
		Page<Food> foodList = foodRepository.findAll(pageable);
		
		log.info("foodList.isFirst()=" + foodList.isFirst());
		log.info("foodList.isLast()=" + foodList.isLast());
		log.info("foodList.getNumber()=" + foodList.getNumber());
		
		// 생성자 방식으로 musicDTOList 만들기
		Page<FoodDTO> foodDTOList = foodList.map(food -> 
		new FoodDTO(food.getFno(), food.getFname(), food.getFcontent(), food.getFprice(), food.getFlink(), food.getFfilename(), food.getFfilepath()));
		
		return foodDTOList;
	}
	
}
