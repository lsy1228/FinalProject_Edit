package com.kh.iMMUTABLE.entity;

import com.kh.iMMUTABLE.constant.ProductSellStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "product")
@Getter @Setter @ToString
public class Product {
    @Id
    @Column(name = "product_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private int productId;

    @Column(nullable = false)
    private String productName;     // 상품코드

    @Column(nullable = false)
    private int productPrice;       // 상품 가격

    @Lob
    @Column(nullable = false)
    private String productDesc;     // 상품설명

    @Column(nullable = false)
    private int projectStock;       // 상품 재고

    @Lob
    @Column(nullable = false)
    private String productImg;      // 상품 이미지

    @Lob
    @Column(nullable = false)
    private String productDetailImg;    // 상품 상세 이미지

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;      // 상품 카테고리

    @Enumerated(EnumType.STRING)
    private ProductSellStatus productSellStatus;    // 상품 판매 상태
    private LocalDateTime regTime;          // 등록시간
    private LocalDateTime updateTime;       // 수정시간

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "size_id")
    private Size size;             // 사이즈

}
