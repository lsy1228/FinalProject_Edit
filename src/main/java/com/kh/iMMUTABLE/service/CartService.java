package com.kh.iMMUTABLE.service;


import com.kh.iMMUTABLE.entity.Cart;
import com.kh.iMMUTABLE.entity.CartItem;
import com.kh.iMMUTABLE.entity.Product;
import com.kh.iMMUTABLE.entity.User;
import com.kh.iMMUTABLE.repository.CartItemRepository;
import com.kh.iMMUTABLE.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;

    // User에게 장바구니 생성
    public void createCart(User user){
        Cart cart = Cart.createCart(user);
    }


    public void addCart(User user, Product product, int count){
        Cart cart = cartRepository.findByUserID(user.getUserId());

        // 장바구니가 비어있다면 생성.
        if(cart == null){
            cart = Cart.createCart(user);
            cartRepository.save(cart);
        }

        // cartItem 생성
        CartItem cartItem = cartItemRepository.findByCartIdAndProductId(cart.getCartId(), product.getProductId());

        // cartItem이 비어있다면 새로 생성
        if(cartItem == null){
            cartItem = CartItem.createCartItem(cart, product, count);
            cartItemRepository.save(cartItem);
        } else{
            cartItem.addCount(count);
        }
    }

    // 장바구니 조회

    public List<CartItem> userCartView(Cart cart){
        List<CartItem> cartItems = cartItemRepository.findAll();
        List<CartItem> userItems = new ArrayList<>();

        for(CartItem cartItem : cartItems){
            if(cartItem.getCart().getCartId() == cart.getCartId()){
                userItems.add(cartItem);
            }
        }
        return userItems;
    }


    // 장바구니 Item 삭제
    public void cartItemDelete(int cartItemId){
        cartItemRepository.deleteById(cartItemId);
    }




}
