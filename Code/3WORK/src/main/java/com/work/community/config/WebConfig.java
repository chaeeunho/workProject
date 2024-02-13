package com.work.community.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer{
	String resourcePath = "/upload/**";
	String savePath = "file:/C:/3workfiles/";
	
	//메서드 재정의
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		
		//파일의 경로 설정
		registry.addResourceHandler(resourcePath) //view에 접근할 경로
				.addResourceLocations(savePath);	//실제 저장파일 위치
	}
}
