package com.work.community.dto;

import com.work.community.entity.Info;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor  // 모든 필드를 매개변수로 갖는 생성자
@Data
public class InfoDTO {
   
   private Integer inno;
   
   @NotEmpty(message = "글제목은 필수 입력 사항입니다.")
   private String intitle;
   
   @NotEmpty(message = "글 내용은 필수 입력 사항입니다.")
   private String incontent;
   
   @NotEmpty(message = "작성일은 필수 입력 사항입니다.")
   private String indate;
   
   private String infilename;

   private String infilepath;

   // Entity(model<db>에 저장됨) -> DTO(view로 보기)로 변환
   // 목록보기, 상세보기
   public static InfoDTO toSaveDTO(Info info) {
      InfoDTO infoDTO = InfoDTO.builder()
                         .inno(info.getInno())
                         .intitle(info.getIntitle())
                         .incontent(info.getIncontent())
                         .indate(info.getIndate())
                         .infilename(info.getInfilename())
                         .infilepath(info.getInfilepath())
                         .build();
      
      return infoDTO;
   }

}