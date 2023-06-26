package com.kh.iMMUTABLE.controller;

import com.kh.iMMUTABLE.service.FaqService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.Map;

@RestController // JSON 등 객체로 반환해준다
@Slf4j
@RequestMapping("/faq")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class FaqController {
    private final FaqService faqService;

    // FAQ upload
    @PostMapping("/uploadFaq")
    public ResponseEntity<Boolean> uploadFaq (@RequestBody Map<String, String> faqData) {
        String faqTitle = faqData.get("faqTitle");
        String faqContent = faqData.get("faqContent");
        System.out.println(faqTitle);
        boolean result = faqService.faqUpload(faqTitle, faqContent);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
