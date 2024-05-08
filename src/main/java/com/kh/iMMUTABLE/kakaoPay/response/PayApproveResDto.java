package com.kh.iMMUTABLE.kakaoPay.response;

import lombok.Getter;

@Getter
public class PayApproveResDto {
    // 결제 승인 요청할 때 담아서 보냄
    private Amount amount; // 결제 금액 정보
    private String item_name;   // 상품명
    private String created_at;  // 결제 요청 시간
    private String approved_at; // 결제 승인 시간
}
