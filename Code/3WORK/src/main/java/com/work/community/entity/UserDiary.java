package com.work.community.entity;

import com.work.community.dto.UserDiaryDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Table(name = "userdiary")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class UserDiary extends BaseEntity{
   
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Integer dno; //글 번호
   
   @Column(nullable = false)
   private String dtitle; //글 제목
   
   @Column(nullable = false) 
   private String dcontent; //글 내용
   
   private String dfilename;
   
   private String dfilepath;
   
   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn //memberId의 name값과 일치해야함
   private Users users;

   public static UserDiary saveToEntity(UserDiaryDTO userDiaryDTO) {
       UserDiary userDiary = UserDiary.builder()
               .dtitle(userDiaryDTO.getDtitle())
               .dcontent(userDiaryDTO.getDcontent())
               .dfilename(userDiaryDTO.getDfilename())
               .dfilepath(userDiaryDTO.getDfilepath())
               .users(userDiaryDTO.getUsers())
               .build();

       return userDiary;
   }
   
}