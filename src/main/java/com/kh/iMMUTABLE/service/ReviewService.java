package com.kh.iMMUTABLE.service;

import com.kh.iMMUTABLE.dto.ProductDto;
import com.kh.iMMUTABLE.entity.Order;
import com.kh.iMMUTABLE.entity.Product;
import com.kh.iMMUTABLE.entity.Review;
import com.kh.iMMUTABLE.entity.Users;
import com.kh.iMMUTABLE.repository.OrderRepository;
import com.kh.iMMUTABLE.repository.ProductRepository;
import com.kh.iMMUTABLE.repository.ReviewRepository;
import com.kh.iMMUTABLE.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;

    public ProductDto reviewProduct(long productId) {
        Product product = productRepository.findByProductId(productId);
        ProductDto productDto = new ProductDto();
        productDto.setProductName(product.getProductName());
        productDto.setProductImgFst(product.getProductImgFst());
        return productDto;
    }

    public boolean writeReview(int rate, long productId, String title, String content, String userEmail, LocalDate reviewDate, long orderId) {
        Users users = userRepository.findByUserEmail(userEmail);
        Product product = productRepository.findByProductId(productId);
        Order order = orderRepository.findByOrderId(orderId);

        Review review = new Review();
        review.setReview_rate(rate);
        review.setReview_title(title);
        review.setReview_content(content);
        review.setUser(users);
        review.setProduct(product);
        review.setReview_date(reviewDate);
        review.setOrder(order);
        order.setReviewed(true);
        reviewRepository.save(review);
        return true;
    }
}
