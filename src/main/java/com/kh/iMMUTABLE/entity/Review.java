package com.kh.iMMUTABLE.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "review")
@Getter @Setter @ToString
public class Review {
    @Id
    @Column(name = "review_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int review_id;
    @Column(nullable = false)
    private String review_title;
    @Column(nullable = false)
    private String review_content;
    @Column(nullable = false)
    private int review_rate;
    private LocalDateTime review_date;

    @ManyToOne(fetch = FetchType.LAZY) // 많은 리뷰가 하나의 상품에 달리기 때문
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}
