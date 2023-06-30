package com.kh.iMMUTABLE.controller;

import com.kh.iMMUTABLE.dto.QnaDto;
import com.kh.iMMUTABLE.entity.Faq;
import com.kh.iMMUTABLE.entity.Qna;
import com.kh.iMMUTABLE.service.QnaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController // JSON 등 객체로 반환해준다
@Slf4j
@RequestMapping("/qna")
@RequiredArgsConstructor
public class QnaController {
    private final QnaService qnaService;

    // QnA 작성
    @PostMapping("/uploadQna")
    public ResponseEntity<Boolean> updateQna (@RequestBody Map<String, String> qnaData) {
        String userEmail = qnaData.get("userEmail");
        String productId = qnaData.get("productId");
        String qnaTitle = qnaData.get("qnaTitle");
        String qnaContent = qnaData.get("qnaContent");
        LocalDateTime qnaDate = LocalDateTime.now();
        boolean result = qnaService.qnaUpload(userEmail ,productId, qnaTitle, qnaContent, qnaDate);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/memQnaLoad")
    public ResponseEntity<List<Qna>> listQna() {
        List<Qna> result = qnaService.getQnaList();
        System.out.println(result);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
