package com.work.community.dto;

import java.sql.Timestamp;

import com.work.community.entity.Users;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CommentsDTO {

	private Integer cno; // 방명록 번호
    private String ccontent; // 방명록 내용
    private Users users; // 작성자 닉네임
    private Timestamp createdDate;
    private Timestamp updatedDate; 
    
}
