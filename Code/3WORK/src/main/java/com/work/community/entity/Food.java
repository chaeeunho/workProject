package com.work.community.entity;

import com.work.community.dto.FoodDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Food {
	
	@Id                                                     // PK (Primary Key)
	@GeneratedValue(strategy = GenerationType.IDENTITY)     // 자동순번
	private Integer fno;                                    // 음식 번호
	
	@Column(nullable = false)                               // not null
	private String fname;                                   // 식품명
	
	@Column(nullable = false)                               // not null
	private String fcontent;                                // 식품 설명
	
	@Column(nullable = false)                               // not null
	private String fprice;                                  // 식품 가격
	 
	@Column(nullable = false)                               // not null
	private String flink;                                   // 식품 판매 링크
	
	private String ffilename;                               // 식품 사진명
	
	private String ffilepath;                               // 식품 사진 경로

	// dto -> entity 변환
	public static Food toSaveEntity(FoodDTO foodDTO) {
		Food food = Food.builder()
						.fno(foodDTO.getFno())
						.fname(foodDTO.getFname())
						.fcontent(foodDTO.getFcontent())
						.fprice(foodDTO.getFprice())
						.flink(foodDTO.getFlink())
						.ffilename(foodDTO.getFfilename())
						.ffilepath(foodDTO.getFfilepath())
						.build();
		return food;
	}
	
}
