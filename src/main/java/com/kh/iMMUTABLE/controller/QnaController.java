package com.kh.iMMUTABLE.controller;

import com.kh.iMMUTABLE.service.QnaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;

@RestController // JSON 등 객체로 반환해준다
@Slf4j
@RequestMapping("/qna")
@RequiredArgsConstructor
public class QnaController {
    private QnaService qnaService;
    @PostMapping("/uploadQna")
    public ResponseEntity<Boolean> updateQna (@RequestBody Map<String, String> qnaData) {
        String userEmail = qnaData.get("userEmail");
        String qnaTitle = qnaData.get("qnaTitle");
        String qnaContent = qnaData.get("qnaContent");
        LocalDateTime qnaDate = LocalDateTime.now();
        System.out.println(qnaTitle);
        boolean result = qnaService.qnaUpload(userEmail ,qnaTitle, qnaContent, qnaDate);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
