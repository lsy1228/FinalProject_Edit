package com.kh.iMMUTABLE.service;

import com.kh.iMMUTABLE.constant.OrderStatus;
import com.kh.iMMUTABLE.dto.OrderDto;
import com.kh.iMMUTABLE.entity.Order;
import com.kh.iMMUTABLE.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    public List<OrderDto> getOrderListAll() {
        List<Order> orderList = orderRepository.findAll();
        List<OrderDto> orderDtos = new ArrayList<>();
        for(Order order : orderList){
            OrderDto orderDto = new OrderDto();
            orderDto.setOrderId(order.getOrderId());
            orderDto.setUserId((int) order.getUser().getUserId());
            orderDto.setOrderAddress(order.getOrderAddress());
            orderDto.setOrderDate(order.getOrderDate());
            orderDto.setTotalPrice(order.getTotalPrice());
            orderDto.setOrderStatus(order.getOrderStatus());
            orderDto.setShipCompany(order.getShipCompany());
            orderDto.setShipCode(order.getShipCode());
            orderDtos.add(orderDto);
        }
        return orderDtos;
    }

    public boolean upLoadData(int orderId,String orderStatue,int shipCode, String orderShipCompany) {
        System.out.println("서비스 : " +  orderId);
        System.out.println("서비스 : " +  orderStatue);
        Order order = orderRepository.findByOrderId(orderId);
        order.setOrderStatus(OrderStatus.valueOf(orderStatue));
        order.setShipCode(shipCode);
        order.setShipCompany(orderShipCompany);
        orderRepository.save(order);
        return true;
    }
}
