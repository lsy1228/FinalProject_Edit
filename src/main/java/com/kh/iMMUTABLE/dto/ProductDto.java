package com.kh.iMMUTABLE.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class ProductDto {
    private int productId;              // 상품코드 (PK)
    private String productName;         // 상품명
    private int productPrice;           // 상품가격
    private String projectStock;        // 상품재고
    private String productMainImg;      // 상품메인이미지
    private String productDetail;    // 상품상세이미지
    private LocalDateTime regTime;      // 상품등록시간
    private LocalDateTime UpdateTime;   // 상품수정시간
    private String categoryName;        // 상품카테고리
    private int size_name;                // 사이즈
}
