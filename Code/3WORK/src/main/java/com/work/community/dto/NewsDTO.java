package com.work.community.dto;

import com.work.community.entity.News;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor  // 모든 필드를 매개변수로 갖는 생성자
@Data
public class NewsDTO {
	
	private Integer nno;
	
	@NotEmpty(message = "기사명은 필수 입력 사항입니다.")
	private String ntitle;
	
	@NotEmpty(message = "기사 내용은 필수 입력 사항입니다.")
	private String ncontent;
	
	@NotEmpty(message = "작성일은 필수 입력 사항입니다.")
	private String ndate;
	
	private String nfilename;

	private String nfilepath;

	// Entity(model<db>에 저장됨) -> DTO(view로 보기)로 변환
	// 목록보기, 상세보기
	public static NewsDTO toSaveDTO(News news) {
		NewsDTO newsDTO = NewsDTO.builder()
								 .nno(news.getNno())
								 .ntitle(news.getNtitle())
								 .ncontent(news.getNcontent())
								 .ndate(news.getNdate())
								 .nfilename(news.getNfilename())
								 .nfilepath(news.getNfilepath())
								 .build();
		
		return newsDTO;
	}

}
