package com.kh.iMMUTABLE.kakaoPay.response;
import lombok.Getter;

@Getter
public class PayReadyResDto {
    // 카카오페이 api에 request로 보내면 얻는 response dto
    private String tid; // 결제 고유 번호
    private String next_redirect_pc_url; // 웹일 경우 받는 결제페이지
    private String created_at;
}
