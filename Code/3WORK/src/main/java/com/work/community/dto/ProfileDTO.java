package com.work.community.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ProfileDTO {
	
	private Integer pno; //회원 번호
	
	private String uintroduce; //자기소개
	
	private String ulike; //좋아하는 것
	
	private Integer phits; //방문수 
	
	private String ifilename; //프로필 이미지, 음악 파일명
	
	private String ifilepath; //프로필 이미지, 음악 파일경로
}
