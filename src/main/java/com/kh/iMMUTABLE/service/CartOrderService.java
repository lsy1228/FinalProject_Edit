package com.kh.iMMUTABLE.service;

import com.kh.iMMUTABLE.dto.CartDto;
import com.kh.iMMUTABLE.dto.CartItemDto;
import com.kh.iMMUTABLE.dto.OrderDto;
import com.kh.iMMUTABLE.entity.*;
import com.kh.iMMUTABLE.repository.CartItemRepository;
import com.kh.iMMUTABLE.repository.CartRepository;
import com.kh.iMMUTABLE.repository.OrderRepository;
import com.kh.iMMUTABLE.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class CartOrderService {
    private UserRepository userRepository;
    private CartRepository cartRepository;
    private OrderRepository orderRepository;
    private CartItemRepository cartItemRepository;

    public List<OrderDto> cartOrder(String userId, Long cartId) {
        User user = userRepository.findByUserEmail(userId);
        Optional<Cart> cartOpt = cartRepository.findById(String.valueOf(cartId));
        List<OrderDto> orderDtos = new ArrayList<>();

        if(cartOpt.isPresent()) {
            Cart cart = cartOpt.get();
            List<OrderDto> orderDtoList = new ArrayList<>();
            for(CartItem cartItem : cart.getCartItemList()) {
                Order order = new Order();
                order.setOrderId(order.getOrderId());
                order.setUser(cart.getUser());
                order.
            }
        }


        for (CartItem cartItem : cartItemList) {
            Product product = cartItem.getProduct();

            // 주문 생성 및 저장
            Order order = new Order();
            order.setOrderId(order.getOrderId());
            order.setUser(user);
            order.setOrderAddress(order.getOrderAddress());
            order.setOrderStatus(order.getOrderStatus());
            order.setOrderDate(order.getOrderDate());
            order.setTotalPrice(order.getTotalPrice());
            order.setProduct(order.getProduct());
            order.setSizeStatus(order.getSizeStatus());
            Order saveOrder = orderRepository.save(order);

            OrderDto orderDto = new OrderDto();
            orderDto.setOrderId(orderDto.getOrderId());
            orderDto.setUserId(orderDto.getUserId());
            orderDto.setOrderAddress(order.getOrderAddress());
            orderDto.setOrderStatus(orderDto.getOrderStatus());
            orderDto.setOrderDate(orderDto.getOrderDate());
            orderDto.setTotalPrice(orderDto.getTotalPrice());
            orderDto.setTotalPrice(orderDto.getTotalPrice());
            orderDto.setProductId(orderDto.getProductId());
            orderDto.setProductSize(orderDto.getProductSize());
            orderDtos.add(orderDto);

            // cartItem delete
            cartItemRepository.delete(cartItem);
        }
        // 카트 삭제
        cartRepository.deleteCartByCartId(cartId);
        return orderDtos;
    }
}
