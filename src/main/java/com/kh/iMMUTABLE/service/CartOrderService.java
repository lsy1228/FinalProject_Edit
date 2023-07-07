package com.kh.iMMUTABLE.service;

import com.kh.iMMUTABLE.constant.OrderStatus;
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

//    public CartDto getCartList(Long cartId) {
//        System.out.println(cartId);
//        Optional<Cart> optionalCart = cartRepository.findById(cartId);
//        CartDto cartDto = new CartDto();
//
//        if (optionalCart.isPresent()) {
//            Cart cart = optionalCart.get();
//            List<CartItem> cartItems = cart.getCartItemList();
//            List<CartItemDto> cartItemDtos = new ArrayList<>();
//
//            for (CartItem cartItem : cartItems) {
//                CartItemDto cartItemDto = new CartItemDto();
//                cartItemDto.setCartItemId(cartItem.getCartItemId());
//                cartItemDto.setProductPrice(cartItem.getCartPrice());
//                cartItemDtos.add(cartItemDto);
//            }
//            cartDto.setCartItemId(cartItemDtos);
//        }
//        return cartDto;
//    }

    public List<OrderDto> cartOrder(Long cartId) {
//        System.out.println(userId);
        System.out.println(cartId);
//        Users user = userRepository.findByUserEmail(userId);
//        System.out.println(user.getUserEmail());
        Optional<Cart> cart = cartRepository.findById(cartId); // 카트아이디로 해당되는 카트 찾음
        List<OrderDto> orderDtoList = new ArrayList<>();    // 반환될 OrderDto

        if (cart.isPresent()) {  // 카트가 존재하면
            for (CartItem cartItem : cart.get().getCartItemList()) { // 해당 카트에 있는 카트 아이템 가져옴
                Order order = new Order();  // Order 엔티티에 정보 저장하기 위해 생성
                order.setUser(cartItem.getCart().getUser());
                order.setOrderDate(LocalDate.from(LocalDateTime.now()));
                order.setTotalPrice(cartItem.getCartPrice());
                order.setProduct(cartItem.getProduct());
                order.setOrderAddress(cartItem.getCart().getUser().getUserAddr());
                order.setOrderStatus(OrderStatus.CHECK);
                order.setSizeStatus(cartItem.getProduct().getSizeStatus());
                orderRepository.save(order);

                OrderDto orderDto = new OrderDto();
                orderDto.setUserId(order.getUser().getUserId());
                orderDto.setOrderDate(order.getOrderDate());
                orderDto.setProductId(order.getProduct().getProductId());
                orderDto.setProductName(order.getProductName());
                orderDto.setProductImgFst(order.getProduct().getProductImgFst());
                orderDtoList.add(orderDto);
            }
            // 카트 삭제
//            cartRepository.deleteById(cartId);
        }
        return orderDtoList;

    }
}
