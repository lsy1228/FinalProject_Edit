package com.kh.iMMUTABLE.controller;

import com.kh.iMMUTABLE.dto.CartItemDto;
import com.kh.iMMUTABLE.dto.OrderDto;
import com.kh.iMMUTABLE.entity.Cart;
import com.kh.iMMUTABLE.entity.Order;
import com.kh.iMMUTABLE.service.CartOrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@Slf4j
@RequestMapping("/order")
@RequiredArgsConstructor

public class CartOrderController {
    private final CartOrderService cartOrderService;

    @PostMapping  ("/cartOrder")
    public ResponseEntity<Boolean> orderCart (@RequestBody Map<String, String> saveOrder) {
//        String userEmail = orderData.get("userEmail");
        long cartId = Long.parseLong(saveOrder.get("cartId"));
        boolean result = cartOrderService.cartOrder(cartId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping ("/orderList")
    public ResponseEntity<List<OrderDto>> orderList (@RequestParam String cartId) {
        List<OrderDto> result = cartOrderService.orderList(Long.parseLong(cartId));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    // total price 들고오기
    @GetMapping("/totalPrice")
    public ResponseEntity<Integer> cartOrderTotalPrice (@RequestParam String cartId) {
        int result = cartOrderService.getTotalPrice(Long.parseLong(cartId));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
