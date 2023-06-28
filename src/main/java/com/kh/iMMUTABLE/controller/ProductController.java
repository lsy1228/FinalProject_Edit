package com.kh.iMMUTABLE.controller;

import com.kh.iMMUTABLE.dto.ProductDto;
import com.kh.iMMUTABLE.entity.Product;
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
@CrossOrigin(origins = "http://localhost:3000")
public class ProductController {
    private final ProductService productService;

    // 제품 전체 조회 어드민페이지에서도 쓰여요!
    @GetMapping("/items")
    public ResponseEntity<List<ProductDto>> itemsList() {
        List<ProductDto> productDtos = productService.getProduct();
        return new ResponseEntity<>(productDtos, HttpStatus.OK);
    }

    @PostMapping("/upload")
    public ResponseEntity<Boolean> uploadItem (@RequestBody Map<String, String> loginData){
        String productName = loginData.get("productName");
        String productPrice = loginData.get("productPrice");
        String productColor = loginData.get("productColor");
        String productSize = loginData.get("productSize");
        String productCategory = loginData.get("productCategory");
        String productImgFst = loginData.get("productImgFst");
        String productImgSnd = loginData.get("productImgSnd");
        String productImgDetail = loginData.get("productImgDetail");
        System.out.println("컨트롤러 : " + productImgDetail);
        boolean result = productService.itemUpLoad(productName,productPrice,productColor,productSize,productCategory,productImgFst,productImgSnd,productImgDetail);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/sellitems")
    public ResponseEntity<List<ProductDto>> sellitems() {
        List<ProductDto> sellProductDtos = productService.getSellProduct();
        return new ResponseEntity<>(sellProductDtos, HttpStatus.OK);
    }

    @GetMapping("/changImgFst")
    public ResponseEntity<Boolean> imgFstList(@RequestBody Map<String, String> imgData){
        long productId = Long.parseLong(imgData.get("productId"));
        String productImgFst = imgData.get("productImgFst");
        boolean result = productService.getProductImgFst(productId,productImgFst);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    @GetMapping("/changImgSnd")
    public ResponseEntity<Boolean> imgSndList(@RequestBody Map<String, String> imgData){
        long productId = Long.parseLong(imgData.get("productId"));
        String productImgSnd = imgData.get("productImgSnd");
        boolean result = productService.getProductImgSnd(productId,productImgSnd);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
