package com.kh.iMMUTABLE.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProductDTO {
    private int productId;          // 상품코드
    private String productName;     // 상품이름
    private int productPrice;       // 상품가격
    private String productDesc;     // 상품설명
    private String productImg;      // 상품이미지
    private String productDetailImg; // 상품상세이미지

}
