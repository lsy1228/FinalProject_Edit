package com.kh.iMMUTABLE.service;


import com.kh.iMMUTABLE.dto.ProductDto;
import com.kh.iMMUTABLE.entity.Product;
import com.kh.iMMUTABLE.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class ProductService {
    // 의존성을 통해 빈에 등록된 필드는 불변성이 있어야 하므로 final 선언을 해야 함
    private final ProductRepository productRepository;  // 상품 정보를 조회하는 데이터 액세스 객체
    // 상품정보조회
    public List<ProductDto> getProductList(){
        List<Product> products = productRepository.findAll();   // 모든 상품 정보 조회
        List<ProductDto> productDTOS = new ArrayList<>();
        for(Product product : products){
            ProductDto productDTO = new ProductDto();
            productDTO.setProductName(product.getProductName());
            productDTO.setProductPrice(product.getProductPrice());
            productDTO.setProductDesc(product.getProductDesc());
            productDTO.setProductMainImg(product.getProductMainImg());
            productDTO.setProductDetailImg(product.getProductDetailImg());
            productDTOS.add(productDTO);
        }
        return  productDTOS;

    }

}
