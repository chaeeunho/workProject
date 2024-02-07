package com.work.community.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.work.community.config.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Autowired
	private CustomUserDetailsService customService;
	
	//pom.xml에서 security 추가해도 별도의 로그인 없이 접속 가능
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		http.userDetailsService(customService);
		
		http
			.authorizeHttpRequests(authorize -> authorize
					.requestMatchers("/**", "/css/**", "/js/**", "/images/**", "/user/join").permitAll()
					.requestMatchers("/user/userpage").authenticated()
					.anyRequest().authenticated()
					)
					.formLogin(form -> 
						form.loginPage("/login")
							.defaultSuccessUrl("/main", true) //true가 없으면 999오류 발생
							.permitAll()
					);
		
					//접근 권한 페이지
					http.exceptionHandling().accessDeniedPage("/auth/accessDenied");
					
					http.logout().logoutUrl("/logout")
					.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
					.invalidateHttpSession(true)
					.logoutSuccessUrl("/");
	
		return http.build();
	}
	
	//암호화 설정
	//PasswordEncoder를 상속받은 BCryptPasswordEncoder를 반환
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
}
