package com.kh.iMMUTABLE.controller;

import com.kh.iMMUTABLE.dto.LikeDto;
import com.kh.iMMUTABLE.entity.Like;
import com.kh.iMMUTABLE.service.LikeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/like")
@RequiredArgsConstructor
public class LikeController {
    private final LikeService likeService;

    @PostMapping("/likeInsert")
    public ResponseEntity<Boolean> likeProduct (@RequestBody Map<String, String> likeData) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        long id = Long.parseLong(authentication.getName());
        long productId = Long.parseLong(likeData.get("productId"));
        boolean result = likeService.addLike(id, productId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/likeList")
    public ResponseEntity<List<LikeDto>> likeList () {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        long id = Long.parseLong(authentication.getName());
        List<LikeDto> result = likeService.likeList(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/likeDelete")
    public ResponseEntity<Boolean> dislikeProduct (@RequestBody Map<String, String> dislikeData) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        long id = Long.parseLong(authentication.getName());
        long productId = Long.parseLong(dislikeData.get("productId"));
        boolean result = likeService.likeDelete(id, productId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/Heart")
    public ResponseEntity<Boolean> viewHeart (@RequestBody Map<String, String> heartData) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(authentication);
        if(authentication == null || authentication.getName() == null) {
            return new ResponseEntity<>(false, HttpStatus.OK);
        }
        long id = Long.parseLong(authentication.getName());
        long productId = Long.parseLong(heartData.get("productId"));
        boolean result = likeService.likeView(id, productId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
