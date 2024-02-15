package com.work.community.dto;

import java.sql.Timestamp;

import com.work.community.entity.Role;
import com.work.community.entity.Users;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UsersDTO {
	
	private Integer uno;
   
    //아이디는 4자~20자로 입력
    @NotEmpty(message = "이메일(아이디)은 필수 항목입니다.")
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "")
    private String uid;

    @NotEmpty
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*[!@#$%^&*(),.?\":{}|<>])(?=.*[0-9]).{8,10}$", message = "")
    private String upassword;

    @NotEmpty(message = "이름은 필수 항목입니다.")
    @Pattern(regexp = "^[가-힣]*$", message = "")
    private String uname;

    @NotEmpty(message = "별명은 필수 항목입니다.")
    @Size(min=2, max=6)
    private String unickname;

    @NotEmpty(message = "주소는 필수 항목입니다.")
    private String uaddress;

    @NotEmpty(message = "핸드폰입력은 필수 항목입니다.")
    @Pattern(regexp = "^[0-9]{3}-[0-9]{4}-[0-9]{4}$", message = "")
    private String uphone;

    @NotEmpty(message = "생년월일은 필수 항목입니다.")
    private String ubirth;
   
    private Role role;
   
    private Timestamp createdDate;
   
    private Timestamp updatedDate;
    
    public static UsersDTO toSaveDTO(Users users) {
    	UsersDTO usersDTO = UsersDTO.builder()
						            .uno(users.getUno())
						            .uid(users.getUid())
						            .upassword(users.getUpassword())
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


