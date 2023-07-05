package com.kh.iMMUTABLE.controller;



import com.kh.iMMUTABLE.dto.CartDto;
import com.kh.iMMUTABLE.dto.CartItemDto;
import com.kh.iMMUTABLE.entity.CartItem;
import com.kh.iMMUTABLE.repository.UserRepository;
import com.kh.iMMUTABLE.service.CartService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/cart")
@RequiredArgsConstructor

public class CartController {
    private final CartService cartService;

   // 장바구니 추가
    @PostMapping("/addCartItem")
    public ResponseEntity<CartItem> addCartItem(@RequestBody Map<String, String> cartData) {

        try {
            String tempEmail = cartData.get("id"); //id로 찍히지만 실제로 넘어오는건 Email
            String tempProductId = cartData.get("productId").toString();

            int productId = Integer.parseInt(tempProductId);

            CartItem cartItem = cartService.addCartItem(tempEmail, productId);

            return new ResponseEntity<>(cartItem, HttpStatus.CREATED);
        } catch (UsernameNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
    // 장바구니 리스트 불러오기
    @GetMapping("/cartItemList")
    public ResponseEntity<List<CartItemDto>> getCartItemList(@RequestParam String id) {
            List<CartItemDto> result = cartService.getCartItemList(id);
            return new ResponseEntity<List<CartItemDto>>(result, HttpStatus.OK);

    }

//    @PostMapping("/updateCount")
//    public ResponseEntity<Boolean> updateCount(@RequestBody Map<String, String> cartData){
//        int idx = Integer.parseInt(cartData.get("count"));
//        int updatedValue = Integer.parseInt(cartData.get("count"));
//        boolean result = cartService.updateCount(idx, updatedValue);
//        return new ResponseEntity<>(result, HttpStatus.OK);
//    }

}


