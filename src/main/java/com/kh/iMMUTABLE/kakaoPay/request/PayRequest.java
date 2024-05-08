package com.kh.iMMUTABLE.kakaoPay.request;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.util.LinkedMultiValueMap;

@Getter
@AllArgsConstructor
public class PayRequest {
    private String url;     // 요청을 보낼 카카오 api url
    private LinkedMultiValueMap<String, String> map;    // 해당 요청에 담을 request
}
