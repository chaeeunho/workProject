package com.work.community.entity;

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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "comments")
@Entity
public class Comments extends BaseEntity{
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Integer cno; //방명록 번호
   
   @Column(length = 200, nullable = false)
   private String ccontent; //방명록 내용
   
   //회원 한명이 여러 방명록 작성
   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "uno") // 외래키 설정 및 nullable 설정
   private Users users; // 외래키 설정

}