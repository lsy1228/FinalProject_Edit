package com.kh.iMMUTABLE.controller;


import com.kh.iMMUTABLE.dto.UserDTO;
import com.kh.iMMUTABLE.dto.UserRequestDto;
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
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    UserService userService;
    @PostMapping("/login")
    public ResponseEntity<List<UserDTO>>  userList(@RequestBody Map<String, String> loginData){
        String userid = loginData.get("email");
        String userPwd = loginData.get("pwd");
        System.out.println(userid);
        System.out.println(userPwd);
        List<UserDTO> list = userService.getUserList();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }


}
