package com.kh.iMMUTABLE.service;

import com.kh.iMMUTABLE.dto.CartItemDto;
import com.kh.iMMUTABLE.entity.Cart;
import com.kh.iMMUTABLE.entity.CartItem;
import com.kh.iMMUTABLE.entity.Product;
import com.kh.iMMUTABLE.entity.Users;
import com.kh.iMMUTABLE.repository.CartItemRepository;
import com.kh.iMMUTABLE.repository.CartRepository;
import com.kh.iMMUTABLE.repository.ProductRepository;
import com.kh.iMMUTABLE.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class CartService {
    private final CartItemRepository cartItemRepository;
    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final ProductService productService;

    // 상품 추가
    public boolean addCartItem(String email, long productId) {
        System.out.println(" email : " + email);
        System.out.println("productId : " + productId);

        Users users = userRepository.findByUserEmail(email);    // 해당하는 유저 찾기
        long id = users.getUserId();    // 회원 번호 찾기

        Product product = productRepository.findByProductId(productId); // 해당하는 제품 찾기
        Cart cart = cartRepository.findByUserUserId(id); // 해당 회원 카트 있는지 찾기

        if(cart==null) {    // 카트가 없다면
            cart = new Cart();  // 새로운 카트 생성
            cart.setUser(users);    // 카트에 회원 정보 저장
            cart = cartRepository.save(cart);   // 카트 저장
        }

        // 카트아이템에 선택한 제품이 존재하는지 확인
        CartItem existCartItem = cartItemRepository.findByCartCartIdAndProductProductId(cart.getCartId(), product.getProductId());
        if(existCartItem!=null) { // 이미 존재하는 경우
            existCartItem.setCount(existCartItem.getCount()+1); // 수량을 증가시킴
            existCartItem.setCartPrice(product.getProductPrice() * existCartItem.getCount()); // 수량에 따른 제품가격
            cartItemRepository.save(existCartItem);
        } else {    // 존재하지 않는 경우
            CartItem cartItem = new CartItem(); // 카트아이템 생성
            cartItem.setCart(cart); // 카트아이템 카트 번호에 해당하는 카트 정보 저장
            cartItem.setProduct(product);   // 카트 아이템에 제품 저장
            cartItem.setCount(1);       // 카트 아이템 수량은 기본 1개
            cartItem.setCartPrice(product.getProductPrice());   // 카트 아이템의 가격은 해당 제품의 가격
            cartItem = cartItemRepository.save(cartItem);   // 카트 아이템 저장
        }

        return true;
    }


    // 상품 List 조회
    public List<CartItemDto> getCartItemList(String email) {

        // user_email로 user_id get
        Users user = userRepository.findByUserEmail(email);
        Long user_id = user.getUserId();
        // Cart 테이블에서 user_id로 cart_id get
        Cart cart = cartRepository.findByUserUserId(user_id);
        int totalPrice = 0;
        if(cart==null) {
            return new ArrayList<>();
        }

        // CartItem 테이블에서 cart_id로 cartItem 내 list
        List<CartItem> cartList = cartItemRepository.findByCartCartId(cart.getCartId());

        List<CartItemDto> cartItemDtoList = new ArrayList<>(); // 반환할 리스트

        for (CartItem cartItem : cartList) {
            CartItemDto cartItemDto = new CartItemDto();
            totalPrice += cartItem.getCartPrice();
            cartItemDto.setCartItemId(cartItem.getCartItemId());
            cartItemDto.setCount(cartItem.getCount());
            cartItemDto.setProductPrice(cartItem.getCartPrice());
            cartItemDto.setCartId(cartItem.getCart().getCartId());


            //상품정보에서 상품사진이랑 상품명 get
            Product product = productRepository.findByProductId(cartItem.getProduct().getProductId());
            cartItemDto.setProductName(product.getProductName());
            cartItemDto.setProductImgFst(product.getProductImgFst());
            cartItemDto.setSizeStatus(product.getSizeStatus());
            cartItemDto.setSetOriginProductPrice(product.getProductPrice());
            cartItemDtoList.add(cartItemDto);

        }
        cart.setTotalPrice(totalPrice);
        return cartItemDtoList;
    }



    public CartItem updateCount(int count, long cartItemId){
        CartItem cartItem = cartItemRepository.findByCartItemId(cartItemId);
        cartItem.setCount(count);

        Product tempProduct  = productRepository.findByProductId(cartItem.getProduct().getProductId());
        cartItem.setCartPrice(count * tempProduct.getProductPrice());

        CartItem cartItemDto = cartItemRepository.save(cartItem);

        return cartItemDto;
    }
}