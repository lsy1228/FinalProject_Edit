package com.kh.iMMUTABLE.repository;

import com.kh.iMMUTABLE.entity.Order;
import com.kh.iMMUTABLE.entity.Qna;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Order findByOrderId(long OrderId);

}
