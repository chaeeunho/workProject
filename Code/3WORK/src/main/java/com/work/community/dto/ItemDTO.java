package com.work.community.dto;

import com.work.community.entity.Item;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor  // 모든 필드를 매개변수로 갖는 생성자
@Data
public class ItemDTO {
	
	private Integer ino;
	
	@NotEmpty(message = "아이템명은 필수 입력 사항입니다.")
	private String iname;
	
	@NotEmpty(message = "아이템 설명은 필수 입력 사항입니다.")
	private String icontent;
	
	@NotEmpty(message = "가격은 필수 입력 사항입니다.")
	private String iprice;
	
	@NotEmpty(message = "링크는 필수 입력 사항입니다.")
	private String ilink; 
	
	private String ifilename; 
	
	private String ifilepath;
	
	// entity -> dto 변환
	public static ItemDTO toSaveDTO(Item item) {
		ItemDTO itemDTO = ItemDTO.builder()
								 .ino(item.getIno())
								 .iname(item.getIname())
								 .icontent(item.getIcontent())
								 .iprice(item.getIprice())
								 .ilink(item.getIlink())
								 .ifilename(item.getIfilename())
								 .ifilepath(item.getIfilepath())
								 .build();
		return itemDTO;
	}

}
