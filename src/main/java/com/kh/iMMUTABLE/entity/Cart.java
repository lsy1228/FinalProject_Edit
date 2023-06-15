package com.kh.iMMUTABLE.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "cart")
@Getter @Setter @ToString
public class Cart {
    @Id
    @Column(name = "cart_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private int cartId;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}
