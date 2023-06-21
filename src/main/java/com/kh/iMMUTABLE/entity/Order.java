package com.kh.iMMUTABLE.entity;

import com.kh.iMMUTABLE.constant.OrderStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter @Setter @ToString
public class Order {
    @Id
    @Column(name = "order_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private int orderId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    private LocalDateTime orderDate;
    @Column(nullable = false)
    private int totalPrice;
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    private String shipCompany;
    private int shipCode;



    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    // mappedBy = "order"에서 order는 orderItem에 있는 변수order를 의미
    private List<OrderItem> orderItemList = new ArrayList<>();
}
