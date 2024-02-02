package com.khit.board.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration    //환경 설정에 사용하는 어노테이션
@EnableWebSecurity
public class SecurityConfig {
	
	//@Bean는 프로젝트에서 관리가 안되는 클래스를 빈으로 사용할때 필요함
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		//인증 설정 -> 권한 설정
		// 로그인이 필요없음: /", "/css/**", "/images/**", "/auth/main", "/member/**"
		// 로그인이 필요: 그외의 경로
		http
		  .authorizeHttpRequests(authorize -> authorize
				  .requestMatchers("/**").permitAll());
		return http.build();
	}
	  @Bean
	    public PasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }
}
