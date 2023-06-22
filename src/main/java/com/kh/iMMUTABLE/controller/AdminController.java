package com.kh.iMMUTABLE.controller;

import com.kh.iMMUTABLE.dto.UserDto;
import com.kh.iMMUTABLE.entity.Order;
import com.kh.iMMUTABLE.entity.Qna;
import com.kh.iMMUTABLE.entity.User;
import com.kh.iMMUTABLE.service.AdminService;
import com.kh.iMMUTABLE.service.OrderService;
import com.kh.iMMUTABLE.service.QnaService;
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
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;
    private final UserService userService;
    private final QnaService qnaService;
    private final OrderService orderService;
    
    //admin page 유저리스트 가져오기
    @GetMapping("/check")
    public ResponseEntity<List<UserDto>> idCheck(){
        List<UserDto> list = adminService.getUserListAll();
        System.out.println("adminController :" + list);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    //admin page에서 유저 삭제
    @PostMapping("/deleteUser")
    public ResponseEntity<Boolean>  signupList(@RequestBody Map<String, String> loginData) {
        String userId = loginData.get("userId");
        System.out.println(userId);
        boolean result = userService.userDelete(userId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    //qna가져오기
    @GetMapping("/qnaLoad")
    public ResponseEntity<List<Qna>> qnaLoad(){
        List<Qna> list = qnaService.getQnaListAll();
        System.out.println("adminController :" + list);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    //qna 업로드
    @PostMapping("/qnaUpload")
    public ResponseEntity<Boolean> qnaupload(@RequestBody Map<String, String> qnaData) {
        int qnaId = Integer.parseInt(qnaData.get("qnaId"));
        String qnaStatue= qnaData.get("qnaStatue");
        String qnaReplay = qnaData.get("qnaReplay");
        System.out.println(qnaId);
        System.out.println("컨트롤러 : " + qnaReplay);
        boolean result = qnaService.upLoadReply(qnaId,qnaStatue,qnaReplay);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    //order 전체 가져오기
    @GetMapping("/orderLoad")
    public ResponseEntity<List<Order>> orderLoad(){
        List<Order> list = orderService.getOrderListAll();
        System.out.println("adminController :" + list);
        System.out.println("adminController :" + list.get(0).getUser().getUserId());
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    //order 수정
    @PostMapping("/orderUpLoad")
    public ResponseEntity<Boolean> orderUpload(@RequestBody Map<String, String> qnaData) {
        int orderId = Integer.parseInt(qnaData.get("orderId"));
        String orderStatue= qnaData.get("orderStatue");
        int shipCode = Integer.parseInt(qnaData.get("orderShipCode"));
        String shipCompany = qnaData.get("orderShipCompany");
        System.out.println(orderId);
        System.out.println("컨트롤러 : " + shipCode);
        System.out.println("컨트롤러 : " + shipCode);
        boolean result = orderService.upLoadData(orderId,orderStatue,shipCode,shipCompany);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
