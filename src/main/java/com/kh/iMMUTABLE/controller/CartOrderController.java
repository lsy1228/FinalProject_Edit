package com.kh.iMMUTABLE.controller;

import com.kh.iMMUTABLE.entity.Order;
import com.kh.iMMUTABLE.service.CartOrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@Slf4j
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/order")
@RequiredArgsConstructor

public class CartOrderController {
    private final CartOrderService cartOrderService;

    @GetMapping ("/cartOrder")
    public ResponseEntity<Boolean> orderCart (@RequestBody Map<String, String> orderData) {
//        String userEmail = orderData.get("userEmail");
        Long cartId = Long.valueOf(orderData.get("cartId"));
        boolean result = cartOrderService.cartOrder(cartId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
