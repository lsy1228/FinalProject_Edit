package com.kh.iMMUTABLE.controller;

import com.kh.iMMUTABLE.dto.UserDto;
import com.kh.iMMUTABLE.entity.Qna;
import com.kh.iMMUTABLE.entity.User;
import com.kh.iMMUTABLE.service.AdminService;
import com.kh.iMMUTABLE.service.QnaService;
import com.kh.iMMUTABLE.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;
    private final UserService userService;
    private final QnaService qnaService;

    @GetMapping("/check")
    public ResponseEntity<List<UserDto>> idCheck(){
        List<UserDto> list = adminService.getUserListAll();
        System.out.println("adminController :" + list);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping("/deleteUser")
    public ResponseEntity<Boolean>  signupList(@RequestBody Map<String, String> loginData) {
        String userId = loginData.get("userId");
        System.out.println(userId);
        boolean result = userService.userDelete(userId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/qnaLoad")
    public ResponseEntity<List<Qna>> qnaLoad(){
        List<Qna> list = qnaService.getQnaListAll();
        System.out.println("adminController :" + list);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

}
