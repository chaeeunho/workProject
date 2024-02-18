package com.work.community.dto;

import java.sql.Timestamp;

import com.work.community.entity.UserDiary;
import com.work.community.entity.Users;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDiaryDTO {

   private Integer dno; //글 번호
   
   @NotEmpty(message = "글 제목은 필수 입력 사항입니다.")
   private String dtitle; //글 제목
   
   @NotEmpty(message = "글 내용은 필수 입력 사항입니다.")
   private String dcontent; //글 내용
   
   private String dfilename;
   
   private String dfilepath;
   
   private Users users;
   
   private Timestamp createdDate;
   private Timestamp updatedDate;

   public static UserDiaryDTO toSaveDTO(UserDiary userDiary) {
      UserDiaryDTO userDiaryDTO = UserDiaryDTO.builder()
             .dno(userDiary.getDno())
             .dtitle(userDiary.getDtitle())
             .dcontent(userDiary.getDcontent())
             .dfilename(userDiary.getDfilename())
             .dfilepath(userDiary.getDfilepath())
             .build();

      return userDiaryDTO;
   }
}