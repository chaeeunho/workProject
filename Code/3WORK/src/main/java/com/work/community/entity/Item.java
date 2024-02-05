package com.work.community.entity;

import com.work.community.dto.ItemDTO;

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
public class Item {
	
	@Id                                                     // PK (Primary Key)
	@GeneratedValue(strategy = GenerationType.IDENTITY)     // 자동순번
	private Integer ino;                                    // 음식 번호
	
	@Column(nullable = false)                               // not null
	private String iname;                                   // 식품명
	
	@Column(nullable = false)                               // not null
	private String icontent;                                // 식품 설명
	
	@Column(nullable = false)                               // not null
	private String iprice;                                  // 식품 가격
	
	@Column(nullable = false)                               // not null
	private String ifilename;                               // 식품 사진명
	
	@Column(nullable = false)                               // not null
	private String ifilepath; 
	
	// dto -> entity 변환
	public static Item toSaveEntity(ItemDTO itemDTO) {
		Item item = Item.builder()
						.ino(itemDTO.getIno())
						.iname(itemDTO.getIname())
						.icontent(itemDTO.getIcontent())
						.iprice(itemDTO.getIprice())
						.ifilename(itemDTO.getIfilename())
						.ifilepath(itemDTO.getIfilepath())
						.build();
		return item;
	}
	
}
