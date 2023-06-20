package com.kh.iMMUTABLE.controller;

import com.kh.iMMUTABLE.dto.ProductDto;
import com.kh.iMMUTABLE.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController // JSON 등 객체로 반환해준다
@Slf4j
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;


    @PostMapping("/upload")
    public ResponseEntity<Boolean> uploadItem (@RequestBody Map<String, String> loginData){
        String productName = loginData.get("productName");
        String productPrice = loginData.get("productPrice");
        String productColor = loginData.get("productColor");
        String productSize = loginData.get("productSize");
        String productCategory = loginData.get("productCategory");
        String productMainImg = loginData.get("productMainImg");
        String productDetail = loginData.get("productDetail");
        System.out.println(productDetail);
        boolean result = productService.itemUpLoad(productName,productPrice,productColor,productSize,productCategory,productMainImg,productDetail);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


}
