package com.work.community.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class AuthenticationController {

	@GetMapping("/authenticated")
    public String authenticateUser() {
        // 현재 사용자의 인증 객체를 가져옴
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        
        // 사용자가 인증되어 있지 않다면, 인증을 설정
        if (!authentication.isAuthenticated()) {
            // 여기서 사용자를 인증
            // 실제로는 사용자를 인증하는 프로세스를 구현해야 하나
            // 해당 컨트롤러에서는 단순히 인증을 true로 설정
            authentication.setAuthenticated(true);
        }
        
        // 인증된 사용자라면 원하는 페이지로 이동
        return "redirect:/user/userpage";
    }
}
