package com.kh.iMMUTABLE.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "likes")
@Getter @Setter @ToString
public class Like {
    @Id
    @Column(name = "like_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int like_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}
