package com.kh.iMMUTABLE.security;

import com.kh.iMMUTABLE.jwt.JwtFilter;
import com.kh.iMMUTABLE.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
public class JwtSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
    // SecurityConfigureAdapter 클래스를 상속, Spring Security의 구성을 조정하는데 사용된느 어댑터 클래스
    private final TokenProvider tokenProvider;

    // HttpSecurity 객체를 매개변수로 받아서 Spring Security 구성을 조정
    @Override
    public void configure(HttpSecurity http) {
        JwtFilter customFilter = new JwtFilter(tokenProvider);
        // JwtFilter를 Spring Security 필터 체인에 추가
        http.addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter.class);
    }
}

