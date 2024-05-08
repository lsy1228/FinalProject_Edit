package com.kh.iMMUTABLE.kakaoPay.response;
import lombok.Getter;

@Getter
public class Amount {
    // 결제 내역 가져올 때
    private int total;      // 총 결제 금액
    private int tax_free;   // 비과세 금액
    private int tax;        // 부가세 금액
}
