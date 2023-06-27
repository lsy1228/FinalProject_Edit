package com.kh.iMMUTABLE.controller;

import com.kh.iMMUTABLE.dto.LikeDto;
import com.kh.iMMUTABLE.entity.Like;
import com.kh.iMMUTABLE.service.LikeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/like")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class LikeController {
    private final LikeService likeService;

    @PostMapping("/likeInsert")
    public ResponseEntity<Boolean> likeProduct (@RequestBody Map<String, Object> likeData) {
        String id = (String)likeData.get("id");
        int productId = (int)likeData.get("productId");
        boolean result = likeService.likeInsert(id, productId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/likeList")
    public ResponseEntity<List<LikeDto>> likeList (@RequestParam String id) {
        String userEmail = id;
        System.out.println(userEmail);
        List<LikeDto> result = likeService.likeList(userEmail);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


}
