package com.work.community.dto;

import com.work.community.entity.Food;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor  // 모든 필드를 매개변수로 갖는 생성자
@Data
public class FoodDTO {
	
	private Integer fno;
	
	@NotEmpty(message = "식품명은 필수 입력 사항입니다.")
	private String fname;
	
	@NotEmpty(message = "식품 설명은 필수 입력 사항입니다.")
	private String fcontent;
	
	@NotEmpty(message = "가격은 필수 입력 사항입니다.")
	private String fprice;
	
	@NotEmpty(message = "링크는 필수 입력 사항입니다.")
	private String flink; 
	
	private String ffilename; 
	
	private String ffilepath; 
	
	// entity -> dto 변환
	public static FoodDTO toSaveDTO(Food food) {
		FoodDTO foodDTO = FoodDTO.builder()
								 .fno(food.getFno())
								 .fname(food.getFname())
								 .fcontent(food.getFcontent())
								 .fprice(food.getFprice())
								 .flink(food.getFlink())
								 .ffilename(food.getFfilename())
								 .ffilepath(food.getFfilepath())
								 .build();
		return foodDTO;
	}

}
