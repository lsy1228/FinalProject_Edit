package com.kh.iMMUTABLE.controller;

import com.kh.iMMUTABLE.kakaoPay.PayInfoDto;
import com.kh.iMMUTABLE.kakaoPay.response.PayApproveResDto;
import com.kh.iMMUTABLE.service.CartOrderService;
import com.kh.iMMUTABLE.service.KakaoPayService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    private final CartOrderService cartOrderService;

    // 상품과 가격에 대한 정보를 보내서 리다이렉트 url 받음
    @PostMapping("/ready")
    public ResponseEntity<?> getRedirectUrl(@RequestBody PayInfoDto payInfoDto) {
        log.warn("상품 정보 : " + payInfoDto.getUserName());
        return ResponseEntity.status(HttpStatus.OK)
                .body(kakaoPayService.getRedirectUrl(payInfoDto));
    }
    @GetMapping("/success/{id}")
    public void afterGetRedirectUrl(HttpServletResponse response, @PathVariable("id") long id, @RequestParam("pg_token") String pgToken) throws IOException {
        log.warn(String.valueOf(id));
        PayApproveResDto approve = kakaoPayService.getApprove(id, pgToken);
        log.info("✅✅✅"+approve.toString());

        if (cartOrderService.cartOrder(approve, id)) {
            response.sendRedirect("http://localhost:3000/Order");
            //response.sendRedirect("http://127.0.0.1:8111/OrderComplete");
            //response.sendRedirect("/Order");
        } else {

        }
    }
}
