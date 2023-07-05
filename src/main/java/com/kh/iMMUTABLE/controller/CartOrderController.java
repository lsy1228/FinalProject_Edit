package com.kh.iMMUTABLE.controller;

import com.kh.iMMUTABLE.dto.OrderDto;
import com.kh.iMMUTABLE.service.CartOrderService;
import com.kh.iMMUTABLE.service.CartService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/cartOrder")
@RequiredArgsConstructor

public class CartOrderController {
    private final CartOrderService cartOrderService;

    @PostMapping("/cartOrder")
    public ResponseEntity<Boolean> orderCart (@RequestBody Map<String, String> orderData) {
        List<OrderDto>
    }
}
