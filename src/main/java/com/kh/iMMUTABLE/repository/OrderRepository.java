package com.kh.iMMUTABLE.repository;

import com.kh.iMMUTABLE.constant.OrderStatus;
import com.kh.iMMUTABLE.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Order findByOrderId(long OrderId);

    List<Order> findByOrderStatus(OrderStatus orderStatus);

    List<Order> findByOrderDate(LocalDate orderDate);

    List<Order> findByCartCartId(long cartId);
}
