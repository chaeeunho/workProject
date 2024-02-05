package com.work.community.dto;

import com.work.community.entity.Item;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ItemDTO {
	
	private Integer ino;
	
	@NotEmpty(message = "곡명은 필수 입력 사항입니다.")
	private String iname;
	
	@NotEmpty(message = "곡명은 필수 입력 사항입니다.")
	private String icontent;
	
	@NotEmpty(message = "곡명은 필수 입력 사항입니다.")
	private String iprice;
	
	private String ifilename; 
	
	private String ifilepath;
	
	// entity -> dto 변환
	public static ItemDTO toSaveDTO(Item item) {
		ItemDTO itemDTO = ItemDTO.builder()
								 .ino(item.getIno())
								 .iname(item.getIname())
								 .icontent(item.getIcontent())
								 .ifilename(item.getIfilename())
								 .ifilepath(item.getIfilepath())
								 .build();
		return itemDTO;
	}

}
