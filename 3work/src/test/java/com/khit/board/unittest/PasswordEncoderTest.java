package com.khit.board.unittest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.khit.board.entity.Role;
import com.khit.board.entity.Users;
import com.khit.board.repository.UsersRepository;

@SpringBootTest
public class PasswordEncoderTest {
	
	@Autowired
	private UsersRepository usersRepository;
	@Autowired
	private PasswordEncoder pwEncoder;
	
	
//	
//	@Test
//	public void testInsertData() {
//		//일반 회원 - 저장
//		Users users = new Users();
//		
//		users.setPassword(pwEncoder.encode("khit123"));
//		users.setRole(Role.ADMIN);
//		users.setUaddress("강서구");
//		users.setUid("khit");
//		users.setUname("박이레");
//		users.setUnickname("으노");
//
//		users.setUphone("09190");
//		
//		
//		usersRepository.save(users);
//	}
//	
}
