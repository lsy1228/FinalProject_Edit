package com.kh.iMMUTABLE.controller;

import com.kh.iMMUTABLE.service.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/review")
@RequiredArgsConstructor

public class ReviewController {
    private final ReviewService reviewService;

}
