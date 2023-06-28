package com.kh.iMMUTABLE.controller;


import com.kh.iMMUTABLE.dto.UserDto;
import com.kh.iMMUTABLE.entity.User;
import com.kh.iMMUTABLE.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@Slf4j
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    @PostMapping("/login")
    public ResponseEntity<Boolean>  userList(@RequestBody Map<String, String> loginData) {
        String userEmail = loginData.get("email"); // userEmail?
        String userPwd = loginData.get("pwd");
        System.out.println("user Email :  " + userEmail);
        System.out.println("user Password :  " + userPwd);
        boolean result = userService.getUserList(userEmail,userPwd);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    @GetMapping("/check")
    public ResponseEntity<Boolean> regMemCheck (@RequestParam String email) {
        System.out.println("이메일 확인인인 : " + email);
        boolean result = userService.userCheck(email);
        System.out.println(result);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<Boolean>  signupList(@RequestBody Map<String, String> loginData) {
        String userName = loginData.get("userName");
        String userEmail = loginData.get("userEmail");
        String userPwd = loginData.get("userPwd");
        String userAddr = loginData.get("userAddr");
        String userPhone = loginData.get("userPhone");
        System.out.println("user Email :  " + userEmail);
        System.out.println("user Password :  " + userPwd);
        boolean result = userService.signUpUser(userName,userEmail,userPwd,userAddr,userPhone);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    // 이메일 찾기
    @GetMapping("/searchEmail")
    public ResponseEntity<Boolean> searchMemEmail (@RequestParam String userEmail) {
        System.out.println(userEmail);
        boolean result = userService.searchUserEmail(userEmail);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    // 비밀번호 재설정
    @PostMapping("/updatePwd")
    public ResponseEntity<Boolean> updatePwd(@RequestBody Map<String, String> loginData) {
        String userEmail = loginData.get("userEmail"); //
        String userPwd = loginData.get("userPwd");
        System.out.println(userEmail);
        System.out.println(userPwd);
        boolean result = userService.updateUserPassword(userEmail, userPwd);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    // 회원정보 가져오기
    @GetMapping("/users")
    public ResponseEntity<UserDto> usersList(@RequestParam String userId) {
        System.out.println(userId);
        UserDto userDtos = userService.getUser(userId);
        return new ResponseEntity<>(userDtos, HttpStatus.OK);
    }



    // 회원 정보 수정
    @PostMapping("/saveInfo")
    public ResponseEntity<Boolean> saveInfo(@RequestBody Map<String, String> userData){
        String userName = userData.get("userName");
        String userAddr = userData.get("userAddr");
        String userPwd = userData.get("userPwd");
        String userEmail = userData.get("userId");
        String userPhone = userData.get("userPhone");
        System.out.println(userName);
        System.out.println(userPwd);
        boolean result = userService.saveUserInfo(userEmail, userName, userPwd, userPhone, userAddr);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    // 회원 탈퇴
    // 탈퇴
    @PostMapping("/sec")
    public ResponseEntity<Boolean> memberSec(@RequestBody Map<String, String> userDate) {
        String userEmail = userDate.get("userId");
        String userPwd = userDate.get("userPwd");
        System.out.println(userPwd);
        boolean result = userService.memberSec(userPwd);
        if (result) {
            return new ResponseEntity<>(true, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }
    }

}
