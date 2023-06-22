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
import java.util.ArrayList;
import java.util.List;

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
    private String productName;     // 상품명

    @Column(nullable = false)
    private int productPrice;       // 상품 가격

    @Column(nullable = false)
    private String productColor;      // 상품 컬러

    @Column(nullable = false)
    private String productSize;             // 사이즈

    @Column(nullable = false)
    private String productCategory;      // 상품 카테고리

    @Lob
    private String productMainImg;      // 상품 이미지


    @Lob
    @Column(nullable = false)
    private String productDetail;    // 상품 상세

    private int productStock;       // 상품 재고

    @Enumerated(EnumType.STRING)
    private ProductSellStatus productSellStatus;    // 상품 판매 상태
    private LocalDateTime regTime;          // 등록시간
    private LocalDateTime updateTime;       // 수정시간



    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Qna> qnaList = new ArrayList<>();
}
