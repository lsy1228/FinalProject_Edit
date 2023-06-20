package com.kh.iMMUTABLE.repository;

import com.kh.iMMUTABLE.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByProductId(String ProductId);
}
