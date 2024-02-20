package com.work.community.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Table(name = "profile")
@ToString
@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Profile {

	@Id  // 필수 입력 - PK
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer pno; //프로필 번호
	
	@Column(nullable = true)
	private String pintroduce; //자기소개
	
	@Column(nullable = true)
	private String plike; //좋아하는 것
	
	private Integer phits; //방문수 
	
	private String pfilename; //프로필 이미지, 음악 파일명
	
	private String pfilepath; //프로필 이미지, 음악 파일경로
	
	@OneToOne
    @JoinColumn(name = "uno")
    private Users user;
	
	
}
