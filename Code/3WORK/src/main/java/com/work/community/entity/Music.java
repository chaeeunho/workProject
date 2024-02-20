package com.work.community.entity;

import com.work.community.dto.MusicDTO;

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
public class Music {
	
	@Id                                                     // PK (Primary Key)
	@GeneratedValue(strategy = GenerationType.IDENTITY)     // 자동순번
	private Integer mno;                                    // 노래 번호
	
	@Column(nullable = false)                               // not null
	private String mname;                                   // 곡명
	
	@Column(nullable = false)                               // not null
	private String malbum;                                  // 앨범명
	
	@Column(nullable = false)                               // not null
	private String msinger;                                 // 가수명
	
	@Column(nullable = false)                               // not null
	private String mcategory;                               // 카테고리
	
	// DTO(view에 온 입력값) -> Entity(DB에 저장)
	public static Music toSaveEntity(MusicDTO musicDTO) {
		Music music = Music.builder()
							  .mno(musicDTO.getMno())
							  .mname(musicDTO.getMname())
							  .malbum(musicDTO.getMalbum())
							  .msinger(musicDTO.getMsinger())
							  .mcategory(musicDTO.getMcategory())
							  .build();
		
		return music;
	}
	
}
