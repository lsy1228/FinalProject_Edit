package com.kh.iMMUTABLE.kakaoPay;
import lombok.Getter;

@Getter
public class PayInfoDto {
    // 리액트에서 받아오는 가격과 상품이름
    private long cartId;
    private String userName;
    private String userEmail;
    private String userPhone;
    private String userAddr;
}
