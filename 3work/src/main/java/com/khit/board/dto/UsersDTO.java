package com.khit.board.dto;

import java.sql.Timestamp;


import com.khit.board.entity.Role;
import com.khit.board.entity.Users;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UsersDTO {
	
	private Integer uno;
	
	//아이디는 4자~20자로 입력
	@Size(min=4, max=20)
	@NotEmpty(message = "사용자 ID는 필수 항목입니다.")
	private String uid;
	
	@NotEmpty(message = "비밀번호는 필수 항목입니다.")
	private String upassword;
	
	@NotEmpty(message = "이름은 필수 항목입니다.")
	private String uname;
	
	@Size(min=4 ,max=10)
	@NotEmpty(message = "별명은 필수 항목입니다.")
	private String unickname;
	
	@NotEmpty(message = "주소는 필수 항목입니다.")
	private String uaddress;
	
	@NotEmpty(message = "핸드폰입력은 필수 항목입니다.")
	private String uphone;
	
	@NotEmpty(message = "주소입력은 필수 항목입니다.")
	private String ubirth;
	
	
	private Role role;
	
	private Timestamp createdDate;
	
	private Timestamp updatedDate;
	
	public static UsersDTO toSaveDTO(Users users) {
		UsersDTO usersDTO = UsersDTO.builder()
				.uno(users.getUno())
				.uid(users.getUid())
				.upassword(users.getPassword())
				.unickname(users.getUnickname())
				.uaddress(users.getUaddress())
				.uphone(users.getUphone())
				.ubirth(users.getUbirth())
				.createdDate(users.getCreatedDate())
				.updatedDate(users.getUpdatedDate())
				.build();
		return usersDTO;
	}
}
