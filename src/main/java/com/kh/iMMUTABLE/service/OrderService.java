package com.kh.iMMUTABLE.service;

import com.kh.iMMUTABLE.entity.Order;
import com.kh.iMMUTABLE.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    public List<Order> getOrderListAll() {
        List<Order> orderList = orderRepository.findAll();
        return orderList;
    }
}
