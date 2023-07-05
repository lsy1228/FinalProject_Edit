package com.kh.iMMUTABLE.service;

import com.kh.iMMUTABLE.constant.OrderStatus;
import com.kh.iMMUTABLE.dto.OrderDto;
import com.kh.iMMUTABLE.entity.*;
import com.kh.iMMUTABLE.repository.CartItemRepository;
import com.kh.iMMUTABLE.repository.CartRepository;
import com.kh.iMMUTABLE.repository.OrderRepository;
import com.kh.iMMUTABLE.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class CartOrderService {
    private final UserRepository userRepository;
    private final CartRepository cartRepository;
    private final OrderRepository orderRepository;

    public boolean cartOrder(String userId, Long cartId) {
        System.out.println(userId);
        System.out.println(cartId);
        Users user = userRepository.findByUserEmail(userId);
        System.out.println(user.getUserEmail());
        Optional<Cart> cartOpt = cartRepository.findById(cartId);

        if(cartOpt.isPresent()) {
            Cart cart = cartOpt.get();

            for (CartItem cartItem : cart.getCartItemList()) {
                Order order = new Order();
                order.setUser(cartItem.getCart().getUser());
                order.setOrderDate(LocalDate.from(LocalDateTime.now()));
                order.setTotalPrice(cartItem.getCartPrice());
                order.setProduct(cartItem.getProduct());
                order.setOrderAddress(user.getUserAddr());
                order.setOrderStatus(OrderStatus.CHECK);
                order.setSizeStatus(cartItem.getProduct().getSizeStatus());
                Order saveOrder = orderRepository.save(order);
            }
        }
        // 카트 삭제
        cartRepository.deleteById(cartId);
        return true;
    }
}
