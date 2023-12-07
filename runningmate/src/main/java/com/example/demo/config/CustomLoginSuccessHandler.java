package com.example.demo.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.example.demo.controller.RunningMateController;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class CustomLoginSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
    	// Authentication 객체를 이용해서 사용자가 가진 모든 권한을 체크
        List<String> roleNames = new ArrayList<>();
        authentication.getAuthorities().forEach(authority->{
            roleNames.add(authority.getAuthority());
        });
        //log.warn("ROLE NAMES : "+roleNames);
        if(roleNames.contains("ROLE_ADMIN")) {
            response.sendRedirect("/admin_page/admin_main");
            return;
        }
        else if(roleNames.contains("ROLE_USER")) {
            response.sendRedirect("/index");
            return;
        }
        response.sendRedirect("/");
    }
    
    
   
}