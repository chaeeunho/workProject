package com.work.community.config;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

import com.work.community.entity.Users;

public class SecurityUser extends User{
	private static final long serialVersionUID = 1L;
	
	private Users users;
	public SecurityUser(Users users) {
		//암호화 안된 상태는 "{noop}" + member.getPassword()을 사용함
		super(users.getUid(),users.getUpassword(), 
				AuthorityUtils.createAuthorityList(users.getRole().toString()));
		this.users = users;
	}
	
	public Users getMember() {
		return users;
	}
}