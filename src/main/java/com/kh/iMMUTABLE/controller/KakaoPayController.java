package com.kh.iMMUTABLE.controller;

import com.kh.iMMUTABLE.kakaoPay.PayInfoDto;
import com.kh.iMMUTABLE.kakaoPay.response.PayApproveResDto;
import com.kh.iMMUTABLE.kakaoPay.response.PayReadyResDto;
import com.kh.iMMUTABLE.service.KakaoPayService;
import com.kh.iMMUTABLE.utils.SecurityUtil;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/payment")
public class KakaoPayController {
    private final KakaoPayService kakaoPayService;

    // 상품과 가격에 대한 정보를 보내서 리다이렉트 url 받음
    @PostMapping("/ready")
    public ResponseEntity<?> getRedirectUrl(@RequestBody PayInfoDto payInfoDto) {
        log.warn("상품 정보 : " + payInfoDto.getUserName());
        return ResponseEntity.status(HttpStatus.OK)
                .body(kakaoPayService.getRedirectUrl(payInfoDto));
    }
    @GetMapping("/success/{id}")
    public ResponseEntity<?> afterGetRedirectUrl(@PathVariable("id") long id, @RequestParam("pg_token") String pgToken) {
        log.warn(String.valueOf(id));
        PayApproveResDto approve = kakaoPayService.getApprove(id, pgToken);
        log.info("✅✅✅"+approve.toString());

        return ResponseEntity.status(HttpStatus.OK).body(approve);
    }

}
