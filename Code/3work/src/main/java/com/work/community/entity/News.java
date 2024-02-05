package com.work.community.entity;

import com.work.community.dto.NewsDTO;

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
public class News {
	
	@Id                                                     // PK (Primary Key)
	@GeneratedValue(strategy = GenerationType.IDENTITY)     // 자동순번
	private Integer nno;                                    // 기사 번호
	
	@Column(nullable = false)                               // not null
	private String ntitle;                                  // 기사 제목
	
	@Column(length = 4000, nullable = false)                // 최대 글자 4000자 & not null
	private String ncontent;                                // 기사 내용
	
	@Column(nullable = false)                               // not null
	private String ndate;                                   // 기사 작성일
	
	@Column(nullable = false)                               // not null
	private String nfilename;                               // 기사 사진명
	
	@Column(nullable = false)                               // not null
	private String nfilepath;                               // 기사 사진 경로

	// dto -> entity 변환
	public static News toSaveEntity(NewsDTO newsDTO) {
		News news = News.builder()
						.nno(newsDTO.getNno())
						.ntitle(newsDTO.getNtitle())
						.ncontent(newsDTO.getNcontent())
						.ndate(newsDTO.getNdate())
						.nfilename(newsDTO.getNfilename())
						.nfilepath(newsDTO.getNfilepath())
						.build();
		return news;
	}
		
}
