package com.kh.iMMUTABLE.kakaoPay.request;

import com.kh.iMMUTABLE.entity.Cart;
import com.kh.iMMUTABLE.entity.CartItem;
import com.kh.iMMUTABLE.entity.Product;
import com.kh.iMMUTABLE.kakaoPay.PayInfoDto;
import com.kh.iMMUTABLE.repository.CartItemRepository;
import com.kh.iMMUTABLE.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class MakePayRequest {
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    public PayRequest getReadyRequest(long id, PayInfoDto payInfoDto) {
        LinkedMultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        // 상품 이름과 총합 가격 필요
        // payInfoDto에 cartId에 대한 정보가 있으므로 이를 기반으로 관련된 정보를 구함

        // cart 총 가격
        Cart cart = cartRepository.findByCartId(payInfoDto.getCartId());
        int totalPrice = cart.getTotalPrice();

        // 상품 이름
        List<CartItem> cartItemList = cartItemRepository.findByCartCartId(payInfoDto.getCartId());
        List<String> productNameList = new ArrayList<>();

        for(CartItem cartItem : cartItemList) {
            Product product = cartItem.getProduct();
            String proName = product.getProductName();
            productNameList.add(proName);
        }

        String productName = productNameList.size()>1 ? productNameList.get(0) + " 외 " + (productNameList.size()-1) + "개" : productNameList.get(0);

        String orderId = "point" + id;
        map.add("cid", "TC0ONETIME");
        map.add("partner_order_id", orderId);       // 가맹점 주문번호
        map.add("partner_user_id", "iMMUTABLE");    // 가맹점 회원 ID
        // 상품 이름
        map.add("item_name", productName);
        // 수량
        map.add("quantity", String.valueOf(cartItemList.size()));
        // 가격
        map.add("total_amount", String.valueOf(totalPrice));
        // 비과세 금액
        map.add("tax_free_amount", "0");

        // 결제 성공, 취소, 실패시 리다이렉트할 url
        map.add("approval_url", "http://localhost:8111/payment/success"+"/"+id);
        map.add("cancel_url", "http://localhost:8111/payment/cancle");
        map.add("fail_url", "http://localhost:8111/CartOrder");

        return new PayRequest("https://kapi.kakao.com/v1/payment/ready", map);
    }

    public PayRequest getApproveRequest(String tid, long userId, String pgToken) {

        LinkedMultiValueMap<String, String> map = new LinkedMultiValueMap<>();

        String orderId = "point" + userId;
        map.add("cid", "TC0ONETIME");
        map.add("tid", tid);
        map.add("partner_order_id", orderId);
        map.add("partner_user_id", "iMMUTABLE");
        // 결제 성공하면 성공시 리다이렉트 되는 url에 토큰값이 붙어서 리다이렉트 됨
        // 여기서 해당 내용을 뽑아서 사용
        map.add("pg_token", pgToken);

        return new PayRequest("https://kapi.kakao.com/v1/payment/approve", map);

    }
}
