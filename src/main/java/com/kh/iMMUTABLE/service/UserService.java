package com.kh.iMMUTABLE.service;

import com.kh.iMMUTABLE.constant.Authority;
import com.kh.iMMUTABLE.entity.User;
import com.kh.iMMUTABLE.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public boolean getUserList(String userEmail, String userPassword){
        List<User> userList = userRepository.findByUserEmailAndUserPwd(userEmail,userPassword);
        System.out.println("service : " + userEmail);
        System.out.println("service : " + userPassword);
        System.out.println(userList);
        //역할 여부 추출
        for(User user : userList){
            String userAuth = String.valueOf(user.getAuthority());
            System.out.println(userAuth);
        }
        if(!userList.isEmpty()) return true;
        else return false;
    }

    public boolean signUpUser(String userName, String userEmail, String userPwd, String userAddr,String userPhone){
        User user = new User();
        user.setUserName(userName);
        user.setUserEmail(userEmail);
        user.setUserPwd(userPwd);
        user.setUserAddr(userAddr);
        user.setUserPhone(userPhone);
        user.setUserDate(LocalDateTime.now());
        user.setAuthority(Authority.valueOf("ROLE_USER"));
        User signUpUser = userRepository.save(user);
        return true;
    }

//    public boolean userEmailCheck(String userEmail, String userName) {
//
//        User user = userRepository.findByUserEmailAndUserPwd(userEmail);
//        if(user!=null && user.getUserName().equals(userName)) {
//            return true;
//        }
//        else {
//            return false;
//        }
//    }
}
