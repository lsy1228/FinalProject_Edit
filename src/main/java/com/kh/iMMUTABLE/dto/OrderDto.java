package com.kh.iMMUTABLE.dto;

import com.kh.iMMUTABLE.constant.OrderStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDate;

@Getter
@Setter
@ToString
public class OrderDto {
    private long orderId;// 주문Id (PK)
    private long userId;// 유저ID
    private String orderAddress;
    private LocalDate orderDate;
    private long productId;
    private String productName;
    private String productColor;
    private String productImgFst;
    private String productSize;
    private int totalPrice;
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
    private String shipCompany;
    private int shipCode;
}
