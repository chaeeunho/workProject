package com.work.community.entity;

import com.work.community.dto.InfoDTO;

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
public class Info {
   
   @Id                                                     // PK (Primary Key)
   @GeneratedValue(strategy = GenerationType.IDENTITY)     // 자동순번
   private Integer inno;                                    // 기사 번호
   
   @Column(nullable = false)                               // not null
   private String intitle;                                  // 기사 제목
   
   @Column(length = 4000, nullable = false)                // 최대 글자 4000자 & not null
   private String incontent;                                // 기사 내용
   
   @Column(nullable = false)                               // not null
   private String indate;                                   // 기사 작성일
   
   private String infilename;                               // 기사 사진명
   
   private String infilepath;                               // 기사 사진 경로

   // dto -> entity 변환
   public static Info toSaveEntity(InfoDTO infoDTO) {
      Info info = Info.builder()
                  .inno(infoDTO.getInno())
                  .intitle(infoDTO.getIntitle())
                  .incontent(infoDTO.getIncontent())
                  .indate(infoDTO.getIndate())
                  .infilename(infoDTO.getInfilename())
                  .infilepath(infoDTO.getInfilepath())
                  .build();
      return info;
   }
      
}