package com.kh.iMMUTABLE.repository;

import com.kh.iMMUTABLE.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
}
