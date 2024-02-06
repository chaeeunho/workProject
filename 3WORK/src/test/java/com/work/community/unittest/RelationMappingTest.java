package com.work.community.unittest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.work.community.entity.Role;
import com.work.community.entity.Users;
import com.work.community.repository.UsersRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class RelationMappingTest {

	@Autowired
	private UsersRepository memberRepository;
	
	//@Test
	/* public void testInsertData() {

		//관리자
		Users users1 = new Users();
		users1.setUid("user1");
		users1.setUpassword("1234");
		users1.setUname("김뽀로");
		users1.setUnickname("뽀ㅓ로로");
		users1.setUaddress("은평구");
		users1.setUphone("01012341234");
		users1.setUbirth("2012/12/21");
		users1.setRole(Role.MEMBER);
		
		memberRepository.save(users1);
		log.info("" + users1);
		
	} */
}
