package com.kh.iMMUTABLE.service;

import com.kh.iMMUTABLE.constant.OrderStatus;
import com.kh.iMMUTABLE.dto.CartItemDto;
import com.kh.iMMUTABLE.dto.OrderDto;
import com.kh.iMMUTABLE.dto.UserDto;
import com.kh.iMMUTABLE.entity.*;
import com.kh.iMMUTABLE.kakaoPay.PayInfoDto;
import com.kh.iMMUTABLE.kakaoPay.response.PayApproveResDto;
import com.kh.iMMUTABLE.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class CartOrderService {
    private final UserRepository userRepository;
    private final CartRepository cartRepository;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final CartItemRepository cartItemRepository;

    // 카트 제품 불러와서 확인
    public List<CartItemDto> cartOrderList(long cartId) {
        List<CartItem> cartItemList = cartItemRepository.findByCartCartId(cartId);
        List<CartItemDto> cartItemDtoList = new ArrayList<>();
        Optional<Cart> cart = cartRepository.findById(cartId);
        for(CartItem cartItem : cartItemList) {
            CartItemDto cartItemDto = new CartItemDto();
            cartItemDto.setProductName(cartItem.getProduct().getProductName());
            cartItemDto.setCount(cartItem.getCount());
            cartItemDto.setProductPrice(cartItem.getCartPrice());
            cartItemDto.setProductImgFst(cartItem.getProduct().getProductImgFst());
            cartItemDto.setSizeStatus(cartItem.getProduct().getSizeStatus());
            cartItemDtoList.add(cartItemDto);
        }
        return cartItemDtoList;
    }


    public boolean cartOrder(PayApproveResDto payApproveResDto, long id) {

        Cart cart = cartRepository.findByUserUserId(id);
        Users users = userRepository.findByUserId(id);

            for (CartItem cartItem : cart.getCartItemList()) { // 해당 카트에 있는 카트 아이템 가져옴
                Order order = new Order();  // Order 엔티티에 정보 저장하기 위해 생성
                order.setUser(cartItem.getCart().getUser());    // 주문 user
                order.setOrderDate(LocalDate.from(LocalDateTime.now()));    // 주문 날짜
//                order.setOrderDate(LocalDate.from(payApproveResDto.getApproved_at()));
                order.setCart(cart);  // 주문 장바구니
                order.setProductName(cartItem.getProduct().getProductName());
                order.setProductColor(cartItem.getProduct().getProductColor()); // 주문 제품 컬러
                order.setProductPrice(cartItem.getCartPrice()); // 각 제품별 가격
                order.setTotalPrice(payApproveResDto.getAmount().getTotal()); // 장바구니 총 가격
                order.setProduct(cartItem.getProduct());        // 주문 제품
                order.setOrderStatus(OrderStatus.CHECK);        // 주문 상태
                order.setSizeStatus(cartItem.getProduct().getSizeStatus()); // 주문 사이즈
                order.setCount(cartItem.getCount());

                order.setOrderAddress(users.getUserAddr());    // 주문하는 사람 주소
                order.setOrderName(users.getUserName());       // 주문하는 사람 이름
                order.setOrderPhone(users.getUserPhone());     // 주문하는 사람 전화번호
                order.setOrderEmail(users.getUserEmail());     // 주문하는 사람 이메일
                orderRepository.save(order);
            }
            cart.getCartItemList().clear();
            cartRepository.save(cart);
        return true;
    }


    public List<OrderDto> orderList (long cartId) {
        List<Order> orderList = orderRepository.findByCartCartId(cartId);
        List<OrderDto> result = new ArrayList<>();

        for(Order order : orderList) {
            OrderDto orderDto = new OrderDto();
            orderDto.setOrderId(order.getOrderId());
            orderDto.setUserId(order.getUser().getUserId());
            orderDto.setOrderAddress(order.getOrderAddress());
            orderDto.setOrderDate(order.getOrderDate());
            orderDto.setOrderStatus(order.getOrderStatus());
            orderDto.setProductImgFst(order.getProduct().getProductImgFst());
            orderDto.setProductPrice(order.getTotalPrice());
            orderDto.setTotalPrice(order.getCart().getTotalPrice());
            orderDto.setUserName(order.getUser().getUserName());
            orderDto.setUserEmail(order.getUser().getUserEmail());
            orderDto.setUserPhone(order.getUser().getUserPhone());
            result.add(orderDto);
        }
        return result;
    }

    // totalprice 가져오기
    public int getTotalPrice (long cartId) {
        Optional<Cart> cart = cartRepository.findById(cartId);
        int totalPrice = cart.get().getTotalPrice();
        return totalPrice;
    }

    public UserDto orderGetUser(long cartId) {
        Optional<Cart> cartOptional = cartRepository.findById(cartId);
        if(cartOptional.isPresent()) {
            Cart cart = cartOptional.get();
            Users users = cart.getUser();

            UserDto userDto = new UserDto();
            userDto.setUserId(users.getUserId());
            userDto.setUserName(users.getUserName());
            userDto.setUserEmail(users.getUserEmail());
            userDto.setUserPhone(users.getUserPhone());
            userDto.setUserAddr(users.getUserAddr());
            return userDto;
        } else {
            throw new NoSuchElementException("cart not found");
        }
    }

    // 주문내역
    public List<OrderDto> orderHistory() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userId = authentication.getName();
        List<Order> orderList = orderRepository.findByUserUserId(Long.parseLong(userId));
        List<OrderDto> orderDtos = new ArrayList<>();

        for(Order order : orderList) {
            OrderDto orderDto = new OrderDto();
            orderDto.setProductName(order.getProduct().getProductName());           // 주문 제품 이름
            orderDto.setProductImgFst(order.getProduct().getProductImgFst());       // 주문 제품 이미지
            orderDto.setProductSize(order.getProduct().getSizeStatus().toString()); // 주문 제품 사이즈
            orderDto.setOrderId(order.getOrderId());                            // 주문 번호
            orderDto.setProductId(order.getProduct().getProductId());           // 주문 제품 번호
            orderDto.setProductPrice(order.getProduct().getProductPrice());     // 주문 제품 가격
            orderDto.setTotalPrice(order.getTotalPrice());
            orderDto.setCount(order.getCount());
            orderDto.setOrderDate(order.getOrderDate());                        // 주문 날짜
            orderDto.setReviewed(order.isReviewed());                           // 리뷰 작성 여부
            orderDto.setOrderStatus(order.getOrderStatus());                    // 주문 상태
            orderDto.setShipCode(order.getShipCode());                          // 배송 번호
            orderDto.setShipCompany(order.getShipCompany());                    // 배송 회사
            orderDtos.add(orderDto);
        }
        return orderDtos;
    }
}
