package com.kh.iMMUTABLE.repository;

import com.kh.iMMUTABLE.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem,Integer> {
    CartItem findByCartIdAndProductId(int cartId, long productId);
}
