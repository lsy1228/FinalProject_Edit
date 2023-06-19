package com.kh.iMMUTABLE.controller;

import com.kh.iMMUTABLE.dto.UserDto;
import com.kh.iMMUTABLE.entity.User;
import com.kh.iMMUTABLE.service.AdminService;
import com.kh.iMMUTABLE.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @GetMapping("/check")
    public ResponseEntity<List<UserDto>> idCheck(@RequestParam String id){
        List<UserDto> list = adminService.getUserListAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
