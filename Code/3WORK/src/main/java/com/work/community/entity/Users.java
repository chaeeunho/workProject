package com.work.community.entity;

import java.util.List;

import com.work.community.dto.UsersDTO;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.OrderBy;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor //모든 필드를 매개변수를 가진 생성자
@NoArgsConstructor  //기본 생성자
@Builder
@ToString
@Setter
@Getter
@Table(name = "users")
@Entity
public class Users extends BaseEntity {
   
   @Id  // 필수 입력 - PK
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Integer uno;  // 회원번호
   
   @Column(nullable = false,unique = true)
   private String uid;  // 아이디
   
   @Column(nullable = false)
   private String upassword;  // 비밀번호
   
   @Column(nullable = false, length = 10)
   private String uname;  // 이름 
   
   @Column(nullable = false, unique = true,length = 30)
   private String unickname; // 닉네임
   
   @Column(nullable = false)
   private String ugender; //성별
   
   @Column(nullable = false)
   private String uaddress; // 주소
   
   @Column(nullable = false)
   private String detailuaddress; //상세주소
   
   @Column(nullable = false, length = 30)
   private String uphone; // 핸드폰 번호
   
   @Column(nullable = false)
   private String ubirth; // 생년월일
   
   @Column(nullable = true)
   private String uintroduce; //자기소개
   
   @Column(nullable = true)
   private String ulike; //좋아하는 것
   
   @Column
   private String ufilename;
   
   @Column
   private String ufilepath;
   
   @Column
   private String bgmname;
   
   @Column
   private String bgmpath;
   
   @Column(columnDefinition = "integer default 0")
   private Integer hits;
   
   // @Column
   // private String role;   // 권한
   @Enumerated(EnumType.STRING) private Role role;
   
   @OneToMany(mappedBy = "users", cascade = CascadeType.ALL)
   @OrderBy("cno desc")
   private List<Comments> commentsList;
   
   @OneToMany(mappedBy = "users", cascade = CascadeType.ALL)
   private List<UserDiary> userDiaryList;
   
   // 회원 1명당 1개의 장바구니를 가짐 - 일대일 양방향 관계
   @OneToOne(fetch = FetchType.EAGER, mappedBy = "users", cascade = CascadeType.ALL)
   @JoinColumn(name = "cartno")
   private Cart cart;
       
   // dto -> entity
   public static Users toSaveEntity(UsersDTO usersDTO) {
      Users users = Users.builder()
                       .uid(usersDTO.getUid())
                       .upassword(usersDTO.getUpassword())
                       .uname(usersDTO.getUname())
                       .unickname(usersDTO.getUnickname())
                       .ugender(usersDTO.getUgender())
                       .uaddress(usersDTO.getUaddress())
                       .detailuaddress(usersDTO.getDetailuaddress())
                       .uphone(usersDTO.getUphone())
                       .ubirth(usersDTO.getUbirth())
                       .uintroduce(usersDTO.getUintroduce())
                       .ulike(usersDTO.getUlike())
                       .ufilename(usersDTO.getUfilename())
                       .ufilepath(usersDTO.getUfilepath())
                       .hits(usersDTO.getHits())
                       .bgmname(usersDTO.getBgmname())
                       .bgmpath(usersDTO.getBgmpath())
                       .role(usersDTO.getRole())
                       .build();
      return users;      
   }
   //회원 정보 수정을 위해 형변환
   public static Users toSaveUpdate(UsersDTO usersDTO) {
      Users users = Users.builder()
            .uno(usersDTO.getUno())
            .uid(usersDTO.getUid())
            .upassword(usersDTO.getUpassword())
            .uname(usersDTO.getUname())
            .unickname(usersDTO.getUnickname())
            .ugender(usersDTO.getUgender())
            .uaddress(usersDTO.getUaddress())
            .detailuaddress(usersDTO.getDetailuaddress())
            .uphone(usersDTO.getUphone())
            .ubirth(usersDTO.getUbirth())
            .uintroduce(usersDTO.getUintroduce())
            .ulike(usersDTO.getUlike())
            .ufilename(usersDTO.getUfilename())
            .ufilepath(usersDTO.getUfilepath())
            .hits(usersDTO.getHits())
            .bgmname(usersDTO.getBgmname())
            .bgmpath(usersDTO.getBgmpath())
            .role(usersDTO.getRole())
            .build();
      
      return users;
   }
   
}