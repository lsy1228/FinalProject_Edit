package com.kh.iMMUTABLE.dto;

import com.kh.iMMUTABLE.constant.ProductSellStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class ProductDto {
    private int productId;              // 상품코드 (PK)
    private String productName;         // 상품명
    private int productPrice;           // 상품가격
    private String productColor;
    private String productSize;
    private String productCategory;
    private int prodoctStock;        // 상품재고
    private String productMainImg;      // 상품메인이미지
    private String productDetail;       // 상품상세
//    private LocalDateTime regTime;      // 상품등록시간
//    private LocalDateTime UpdateTime;   // 상품수정시간
//    private String categoryName;        // 상품카테고리
    private String size_name;                // 사이즈
    @Enumerated(EnumType.STRING)
    private ProductSellStatus productSellStatus;
}
