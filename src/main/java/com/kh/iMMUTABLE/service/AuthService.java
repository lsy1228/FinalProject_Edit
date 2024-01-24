package com.kh.iMMUTABLE.service;

import com.kh.iMMUTABLE.dto.TokenDto;
import com.kh.iMMUTABLE.dto.TokenRequestDto;
import com.kh.iMMUTABLE.dto.UserRequestDto;
import com.kh.iMMUTABLE.dto.UserResponseDto;
import com.kh.iMMUTABLE.entity.RefreshToken;
import com.kh.iMMUTABLE.entity.Users;
import com.kh.iMMUTABLE.jwt.TokenProvider;
import com.kh.iMMUTABLE.repository.RefreshTokenRepository;
import com.kh.iMMUTABLE.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthService {

    private final AuthenticationManagerBuilder managerBuilder;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;

    // 관리자
    public UserResponseDto signup(UserRequestDto requestDto) {
        if (userRepository.existsByUserEmail(requestDto.getUserEmail())) {
            throw new RuntimeException("이미 가입되어 있는 유저입니다");
        }
        System.out.println("서비스 사인업 requestDto: " + requestDto);
        Users user = requestDto.toUser(passwordEncoder);
        return UserResponseDto.of(userRepository.save(user));
    }

    // 관리자
    //로그인시 TokenDto 를 반환한다.
    public TokenDto login(UserRequestDto requestDto) {
        System.out.println("토큰디티오 접속 완료");
        UsernamePasswordAuthenticationToken authenticationToken = requestDto.toAuthentication();
        System.out.println("authenticationToken : " + authenticationToken);
        Authentication authentication = managerBuilder.getObject().authenticate(authenticationToken);
        System.out.println("authentication" + authentication);
        return tokenProvider.generateTokenDto(authentication);
    }

    // 사용자
    public UserResponseDto userSignUp(UserRequestDto requestDto) {
        if(userRepository.existsByUserEmail(requestDto.getUserEmail())) {
            throw new RuntimeException("이미 가입되어 있는 유저입니다");
        }
        Users users = requestDto.toMember(passwordEncoder);
        return UserResponseDto.of(userRepository.save(users));
    }

    // 사용자 로그인 토큰
    public TokenDto userLogin(UserRequestDto requestDto) {
        UsernamePasswordAuthenticationToken authenticationToken = requestDto.toAuthentication();
        Authentication authentication = managerBuilder.getObject().authenticate(authenticationToken);

        TokenDto tokenDto = tokenProvider.generateTokenDto(authentication);

        // Refresh Token 저장
        RefreshToken refreshToken = RefreshToken.builder()
                .key(authentication.getName())
                .value(tokenDto.getRefreshToken())
                .build();

        refreshTokenRepository.save(refreshToken);

        return tokenDto;
    }

    // 토큰 재발급
    public TokenDto reissue(TokenRequestDto tokenRequestDto) {
        // refresh 토큰 검증, 만료 여부를 먼저 검사
        if(!tokenProvider.validateToken(tokenRequestDto.getRefreshToken())) {
            throw new RuntimeException("Refresh Token이 유효하지 않습니다");
        }
        // 토큰을 기반으로 정보 가져옴
        Authentication authentication = tokenProvider.getAuthentication(tokenRequestDto.getRefreshToken());
        // userId를 기반으로 refresh 토큰 가져옴
        RefreshToken refreshToken = refreshTokenRepository.findByKey(authentication.getName())
                .orElseThrow(() -> new RuntimeException("로그아웃 된 사용자 입니다"));

        // DB에 있는 토큰과 클라이언트가 전달한 토큰이 같은지 확인
        if(!refreshToken.getValue().equals(tokenRequestDto.getRefreshToken())) {
            throw new RuntimeException("유저 정보가 일치하지 않습니다");
        }

        // 일치하면 로그인 했을 때와 동일하게 새로운 토큰 생성
        TokenDto tokenDto = tokenProvider.generateTokenDto(authentication);

        // refresh 토큰 값도 갱신
        RefreshToken newRefreshToken = refreshToken.updateValue(tokenDto.getRefreshToken());
        refreshTokenRepository.save(newRefreshToken);
        return tokenDto;
    }
}
