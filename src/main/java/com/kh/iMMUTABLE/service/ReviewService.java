package com.kh.iMMUTABLE.service;

import com.kh.iMMUTABLE.dto.ProductDto;
import com.kh.iMMUTABLE.dto.ReviewDto;
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
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.*;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;

    // 리뷰 관련 제품 정보 가져오기
    public ProductDto reviewProduct(long productId) {
        Product product = productRepository.findByProductId(productId);
        ProductDto productDto = new ProductDto();
        productDto.setProductName(product.getProductName());
        productDto.setProductImgFst(product.getProductImgFst());
        return productDto;
    }

    // 리뷰 작성하기
    public boolean writeReview(int rate, long productId, String title, String content, String userEmail, LocalDate reviewDate, long orderId, String reviewImg) {
        Users users = userRepository.findByUserEmail(userEmail);
        Product product = productRepository.findByProductId(productId);
        Order order = orderRepository.findByOrderId(orderId);

        // 사용자, 제품, 주문이 없는 경우 처리
        if (users == null || product == null || order == null) {
            return false;
        }

        Review review = new Review();
        review.setReview_rate(rate);            // 리뷰 별점
        review.setReview_title(title);          // 리뷰 제목
        review.setReview_content(content);      // 리뷰 내용
        review.setUser(users);                  // 작성자
        review.setProduct(product);             // 리뷰 제품
        review.setReview_date(reviewDate);      // 리뷰 작성 날짜
        review.setOrder(order);                 // 주문번호
        if (reviewImg != null) {                // 리뷰 이미지가 있을 경우
            review.setReview_img(reviewImg);    // 사용자가 넣은 이미지로 리뷰 이미지 설정
        }
        order.setReviewed(true);
        reviewRepository.save(review);
        return true;
    }

    // 제품 별 리뷰 불러오기
    public List<ReviewDto> viewReview(String productName) {
        List<Product> productList = productRepository.findByProductName(productName);
        List<ReviewDto> reviewDtoList = new ArrayList<>();

        for(Product product : productList) {
            long productId = product.getProductId();
            List<Review> reviews = reviewRepository.findByProductProductId(productId);
            for(Review review : reviews) {
                ReviewDto reviewDto = new ReviewDto();
                reviewDto.setOrderId(review.getOrder().getOrderId());
                reviewDto.setReviewTitle(review.getReview_title());
                reviewDto.setReviewContent(review.getReview_content());
                reviewDto.setReviewRate(review.getReview_rate());
                reviewDto.setUserName(review.getUser().getUserName());
                reviewDto.setReviewDate(review.getReview_date());
                if (review.getReview_img() != null) {
                    reviewDto.setReviewImg(review.getReview_img());
                }
                reviewDtoList.add(reviewDto);
            }
        }
        return reviewDtoList;
    }

    // 내가 쓴 리뷰 불러오기
    public List<ReviewDto> myReview(String userId) {
        long id = Long.parseLong(userId);
        Users users = userRepository.findByUserId(id);
        // 사용자가 없는 경우 빈 리스트 반환 또는 예외 처리
        if (users == null) {
            return Collections.emptyList();
        }
//        long userId = users.getUserId();
        List<Review> reviewList = reviewRepository.findByUserUserId(id);
        List<ReviewDto> reviewDtoList = new ArrayList<>();

        for(Review review : reviewList) {
            ReviewDto reviewDto = new ReviewDto();
            reviewDto.setReviewId(review.getReview_id());                               // 리뷰 번호
            reviewDto.setReviewTitle(review.getReview_title());                         // 리뷰 제목
            reviewDto.setReviewContent(review.getReview_content());                     // 리뷰 내용
            reviewDto.setReviewRate(review.getReview_rate());                           // 리뷰 별점
            reviewDto.setUserName(review.getUser().getUserName());                      // 리뷰 작성자 이름
            reviewDto.setReviewDate(review.getReview_date());                           // 리뷰 작성 날짜
            reviewDto.setProductName(review.getProduct().getProductName());             // 리뷰 작성한 제품 이름
            reviewDto.setProductImgFst(review.getProduct().getProductImgFst());         // 리뷰 작성한 제품관련 이미지
            reviewDto.setProductSize(review.getProduct().getSizeStatus().toString());   // 리뷰 작성한 제품의 사이즈
            if (review.getReview_img() != null) {                                       // 작성된 리뷰에 이미지가 포함되어 있을 때
                reviewDto.setReviewImg(review.getReview_img());                         // 사용자가 넣은 이미지로 설정
            }
            reviewDtoList.add(reviewDto);
        }
        return reviewDtoList;
    }

    // 내가 쓴 리뷰 삭제하기
    public boolean deleteReview (String reviewId) {
        long id = Long.parseLong(reviewId);
        reviewRepository.deleteById(id);
        return true;
    }

    // 리뷰 수정 가져오기
//    public ReviewDto editReviewInfo (long reviewId) {
//        Optional<Review> review = reviewRepository.findById(reviewId);
//        ReviewDto reviewDto = new ReviewDto();
//        if(review.get().getReview_img() != null) {
//            reviewDto.setReviewImg(review.get().getReview_img());
//        }
//        reviewDto.setReviewRate(review.get().getReview_rate());
//        reviewDto.setReviewTitle(review.get().getReview_title());
//        reviewDto.setReviewContent(review.get().getReview_content());
//        return reviewDto;
//    }

    // 리뷰 수정 가져오기
    public ReviewDto editReviewInfo(long reviewId) {
        Optional<Review> reviewOptional = reviewRepository.findById(reviewId);

        if (reviewOptional.isPresent()) {
            Review review = reviewOptional.get();
            ReviewDto reviewDto = new ReviewDto();
            // review 엔터티의 정보를 ReviewDto에 복사
            reviewDto.setReviewTitle(review.getReview_title());
            reviewDto.setReviewContent(review.getReview_content());
            reviewDto.setReviewRate(review.getReview_rate());
            if (review.getReview_img() != null) {
                reviewDto.setReviewImg(review.getReview_img());
            }
            return reviewDto;
        }
        return null; // 혹은 예외를 throw하여 상황에 맞게 처리
    }


    // 리뷰 수정하기
//    public boolean editMyReview (String reviewId, String title, String content, String userRate, String imgUrl) {
//        long id = Long.parseLong(reviewId);
//        int rate = Integer.parseInt(userRate);
//        Optional<Review> reviewOptional = reviewRepository.findById(id);        // 리뷰 번호로 해당되는 리뷰 가져오기
//        if(reviewOptional.isPresent()) {                                        // 리뷰 존재
//            Review review = reviewOptional.get();
//            review.setReview_title(title);
//            review.setReview_content(content);
//            review.setReview_date(LocalDate.now());
//            review.setReview_rate(rate);
//            if(imgUrl == null) {
//                review.setReview_img(null);
//            } else {
//                review.setReview_img(imgUrl);
//            }
//            reviewRepository.save(review);
//            return true;
//        }
//        return false;
//    }

    // 리뷰 수정하기
    public boolean editMyReview(String reviewId, String title, String content, String userRate, String imgUrl) {
        try {
            long id = Long.parseLong(reviewId);
            int rate = Integer.parseInt(userRate);
            Optional<Review> reviewOptional = reviewRepository.findById(id);
            if (reviewOptional.isPresent()) {
                Review review = reviewOptional.get();
                // 새로운 값이 제공되지 않았을 때 불필요한 업데이트를 피하기 위해 null 또는 빈 값에 대한 확인을 추가
                if (title != null && !title.isEmpty()) {
                    review.setReview_title(title);
                }
                if (content != null && !content.isEmpty()) {
                    review.setReview_content(content);
                }
                review.setReview_date(LocalDate.now());
                review.setReview_rate(rate);
                reviewRepository.save(review);
                return true;
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return false;
    }

}