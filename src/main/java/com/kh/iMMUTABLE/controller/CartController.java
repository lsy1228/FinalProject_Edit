package com.kh.iMMUTABLE.controller;


import com.kh.iMMUTABLE.entity.Product;
import com.kh.iMMUTABLE.entity.User;
import com.kh.iMMUTABLE.service.CartService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@Slf4j
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {

}
