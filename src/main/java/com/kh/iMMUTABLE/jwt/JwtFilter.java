package com.kh.iMMUTABLE.jwt;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String BEARER_PREFIX = "Bearer ";
    private final TokenProvider tokenProvider;

    // OncePerRequestFilter 인터페이스를 구현하기 때문에 요청 받을 때 단 한번만 실행됨

    // Request header에서 토큰 정보 꺼내오기
    private String resolveToken(HttpServletRequest request) {
        log.info("첫번째: "+request.getRequestURI());
        log.info("첫번째: "+request.getHeader(AUTHORIZATION_HEADER));
        String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(BEARER_PREFIX)) {
            return bearerToken.substring(7);
        }
        return null;
    }

    // 실제 필터링 로직을 수행하는 곳
    // 가입/로그인/재발급을 제외한 모든 request 요청은 이 필터를 거치기 때문에 토큰 정보가 없거나 유효하지 않으면 정상적으로 수행 X
    // JWT 토큰의 인증 정보를 현재 쓰레드의 SecurityContext에 저장하는 역할 수행
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // Request Header에서 토큰 꺼냄
        String jwt = resolveToken(request);
        log.warn("✨✨✨"+jwt);

        // validToken으로 토큰 유효성 검사
        // 정상 토큰이면 해당 토큰으로 Authentication을 가져와서 SecurityContext에 저장
        if (StringUtils.hasText(jwt) && tokenProvider.validateToken(jwt)) {
            Authentication authentication = tokenProvider.getAuthentication(jwt);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }

}
