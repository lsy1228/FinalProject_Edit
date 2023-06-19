package com.kh.iMMUTABLE.controller;

import com.kh.iMMUTABLE.Service.ProductService;
import com.kh.iMMUTABLE.dto.ProductDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController // JSON 등 객체로 반환해준다
public class ProductController {
    ProductService productService;
    public ProductController (ProductService itemService){
        this.productService = itemService;
    }
    // 제품 조회
    @GetMapping("/product")
    public ResponseEntity<List<ProductDto>> itemList(@RequestParam String name){
        List<ProductDto> list = productService.getProductList();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
