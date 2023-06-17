package com.kh.iMMUTABLE.controller;


import com.kh.iMMUTABLE.dto.UserDTO;
import com.kh.iMMUTABLE.dto.UserRequestDto;
import com.kh.iMMUTABLE.entity.User;
import com.kh.iMMUTABLE.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;


@RestController
@Slf4j
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    UserService userService;
    @PostMapping("/login")
    public ResponseEntity<Boolean>  userList(@RequestBody Map<String, String> loginData) {
        String userEmail = loginData.get("email");
        String userPwd = loginData.get("pwd");
        System.out.println("user Email :  " + userEmail);
        System.out.println("user Password :  " + userPwd);
        boolean result = userService.getUserList(userEmail,userPwd);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


}
