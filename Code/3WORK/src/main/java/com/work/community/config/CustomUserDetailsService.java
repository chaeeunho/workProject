package com.work.community.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.work.community.entity.Users;
import com.work.community.repository.UsersRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService{
	
	@Autowired
	private UsersRepository usersRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Users> findUser = usersRepository.findByUid(username);
		if(findUser.isEmpty()) {
			throw new UsernameNotFoundException(username + "사용자 없음");
		}else {
			Users users = findUser.get();
			return new SecurityUser(users);
		}
	}
}
