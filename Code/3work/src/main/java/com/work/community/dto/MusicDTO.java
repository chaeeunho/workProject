package com.work.community.dto;

import com.work.community.entity.Music;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class MusicDTO {
	
	private Integer mno;
	
	@NotEmpty(message = "곡명은 필수 입력 사항입니다.")
	private String mname;
	
	@NotEmpty(message = "앨범명은 필수 입력 사항입니다.")
	private String malbum;
	
	@NotEmpty(message = "가수명은 필수 입력 사항입니다.")
	private String msinger;
	
	@NotEmpty(message = "카데고리는 필수 입력 사항입니다.")
	private String mcategory;

	// Entity(model<db>에 저장됨) -> DTO(view로 보기)로 변환
	// 목록보기, 상세보기
	public static MusicDTO toSaveDTO(Music music) {
		MusicDTO musicDTO = MusicDTO.builder()
									   .mno(music.getMno())
									   .mname(music.getMname())
									   .malbum(music.getMalbum())
									   .msinger(music.getMsinger())
									   .mcategory(music.getMcategory())
									   .build();
		
		return musicDTO;
	}
	
}
