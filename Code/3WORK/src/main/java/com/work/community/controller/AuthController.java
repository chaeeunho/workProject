package com.work.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {

   @GetMapping("/auth/accessDenied")
    public String accessDeniedPage() {
        return "accessDenied"; 
    }
}