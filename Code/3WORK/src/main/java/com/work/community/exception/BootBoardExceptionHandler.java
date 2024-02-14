package com.work.community.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice //예외처리 역할을 하는 클래스
@RestController //문자를 반환하는 클래스
public class BootBoardExceptionHandler {
	
	@ExceptionHandler(value = Exception.class)
	public String globalExceptionHanbdler(Exception e) {
		return "<h2>" + e.getMessage() +  "</h2>";
	}
}
