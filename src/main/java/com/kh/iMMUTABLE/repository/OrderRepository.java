package com.kh.iMMUTABLE.repository;

import com.kh.iMMUTABLE.constant.OrderStatus;
import com.kh.iMMUTABLE.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Order findByOrderId(int OrderId);

    List<Order> findByOrderStatus(OrderStatus OrderStatus);

}
