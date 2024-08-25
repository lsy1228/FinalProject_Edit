package com.kh.iMMUTABLE.service;

import com.kh.iMMUTABLE.entity.Users;
import com.kh.iMMUTABLE.kakaoPay.PayInfoDto;
import com.kh.iMMUTABLE.kakaoPay.request.MakePayRequest;
import com.kh.iMMUTABLE.kakaoPay.request.PayRequest;
import com.kh.iMMUTABLE.kakaoPay.response.PayApproveResDto;
import com.kh.iMMUTABLE.kakaoPay.response.PayReadyResDto;
import com.kh.iMMUTABLE.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class KakaoPayService {
    private final MakePayRequest makePayRequest;
    private final UserRepository userRepository;
    private PayReadyResDto payReadyResDto;
    private final CartOrderService cartOrderService;

    @Value("${adminKey}")
    private String adminKey;

    // adminKey를 헤더에 담아 post 요청
    // 결제를 위한 정보를 카카오 서버에 전달하고 결제 고유 번호 받아옴
    public PayReadyResDto getRedirectUrl(PayInfoDto payInfoDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        long id = Long.parseLong(authentication.getName());
        log.info(String.valueOf(id));
        Users users = userRepository.findByUserId(id);

        // 헤더
        HttpHeaders headers = new HttpHeaders();
        String auth = "KakaoAK " + adminKey;
        headers.set("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
        headers.set("Authorization", auth);


        PayRequest payRequest = makePayRequest.getReadyRequest(id, payInfoDto);

        // Header와 Body를 합쳐서 RestTemplate로 보내기 위한 작업
        HttpEntity<LinkedMultiValueMap<String, String>> urlRequest = new HttpEntity<>(payRequest.getMap(), headers);

        // RestTemplate로 Response 받아와서 dto로 변환 후 반환
        // 응답받은 json 객체를 java 객체로 변환 (postForObject)
        RestTemplate rt = new RestTemplate();
        PayReadyResDto payReadyResDto = rt.postForObject(payRequest.getUrl(),urlRequest,PayReadyResDto.class);
        users.setTid(payReadyResDto.getTid());

        return payReadyResDto;
    }

    public PayApproveResDto getApprove(long id, String pgToken) {
        Users users = userRepository.findByUserId(id);
        String tid = users.getTid();

        HttpHeaders headers = new HttpHeaders();
        String auth = "KakaoAK " + adminKey;
        // 헤더
        headers.set("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
        headers.set("Authorization", auth);

        // 바디
        PayRequest payRequest = makePayRequest.getApproveRequest(tid, id ,pgToken);

        HttpEntity<LinkedMultiValueMap<String, String>> requestEntity = new HttpEntity<>(payRequest.getMap(), headers);

        RestTemplate rt = new RestTemplate();
        PayApproveResDto payApproveResDto = rt.postForObject(payRequest.getUrl(),requestEntity, PayApproveResDto.class);
        log.warn("🙏🙏🙏"+payApproveResDto.getAmount().getTotal());
        return payApproveResDto;
    }

}
