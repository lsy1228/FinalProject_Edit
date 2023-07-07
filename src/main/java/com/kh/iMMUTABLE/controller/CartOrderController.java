package com.kh.iMMUTABLE.controller;

import com.kh.iMMUTABLE.dto.CartItemDto;
import com.kh.iMMUTABLE.dto.OrderDto;
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

    @GetMapping ("/cartOrder")
    public ResponseEntity<List<OrderDto>> orderCart (@RequestParam String Id) {
//        String userEmail = orderData.get("userEmail");
        Long cartId = Long.parseLong(Id);
        List<OrderDto> result = cartOrderService.cartOrder(cartId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
