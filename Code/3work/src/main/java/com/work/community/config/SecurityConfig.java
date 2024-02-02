package com.work.community.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
   
   //@Bean : 프로젝트에서 관리가 안되는 클래스를 빈으로 사용할때 필요
   //pom.xml에서 security 추가해도 별도의 로그인 없이 접속 가능
   @Bean
   SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
      
      http
         .authorizeHttpRequests(authorize -> authorize
               .requestMatchers("/**", "/css/**", "/js/**").permitAll()
               );
               
      return http.build();
   }
   
}