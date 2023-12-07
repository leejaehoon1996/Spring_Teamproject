package com.example.demo.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Member;
import com.example.demo.repository.MemberRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PrincipalDetailsService implements UserDetailsService {
    @Autowired 
    private MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("========================username:"+username+"========================");
        Member byUsername = memberRepository.findByUserId(username);
        if(byUsername != null){
            return new PrincipalDetails(byUsername);
        }
        throw new UsernameNotFoundException("User not found with username: " + username); // 사용자를 찾지 못했다면 이 예외를 발생시킵니다.
    }
}