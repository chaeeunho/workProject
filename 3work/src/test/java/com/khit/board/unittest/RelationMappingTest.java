package com.khit.board.unittest;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.khit.board.entity.Users;
import com.khit.board.repository.UsersRepository;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@SpringBootTest
public class RelationMappingTest {
	
	@Autowired
	private UsersRepository memberRepository;
	
	/*@Test
	public void testInsertData() {

		
		//관리자
		Users users1 = new Users();
		users1.setUid("eunh1o");
		users1.setPassword("1234");
		users1.setUname("은호1");
		users1.setUnickname("으1노");
		users1.setUaddress("강서1구");
		users1.setUphone("010939391");
		users1.setUbirth("2012/12/21");
		users1.setRole("ADMIN");
		
		memberRepository.save(users1);
		log.info("" + users1);
		
	}*/
}
