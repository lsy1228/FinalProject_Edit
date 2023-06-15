package com.kh.iMMUTABLE.repository;

import com.kh.iMMUTABLE.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
