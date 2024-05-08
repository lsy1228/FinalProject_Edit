package com.kh.iMMUTABLE.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kh.iMMUTABLE.constant.OrderStatus;
import com.kh.iMMUTABLE.constant.SizeStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "orders")
@Getter @Setter @ToString
public class Order {
    @Id
    @Column(name = "order_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private long orderId;                           // 주문번호
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private Users user;                             // 사용자
    @Column(nullable = false)
    private String orderAddress;                    // 주문주소
    private LocalDate orderDate;                    // 주문날짜
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;                        // 상품
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id")
    private Cart cart;                              // 카트
//    private String productName;                     // 상품   이름
//    private String productColor;                    // 상품   색상
//    @Enumerated(EnumType.STRING)
//    private SizeStatus sizeStatus;                  // 상품 사이즈
    @Column(nullable = false)
    private int totalPrice;                         // 총 가격
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus=OrderStatus.CHECK;
    private String shipCompany;                     // 배송회사
    private long shipCode;                          // 배송코드
    private int count;                              // 수량
//    private int productPrice;                       // 상품가격
    private String orderName;                       // 주문자 이름
    private String orderPhone;                      // 주문자 번호
    private String orderEmail;                      // 주문자 이메일
    private boolean reviewed = false;               // 리뷰작성 여부
}
