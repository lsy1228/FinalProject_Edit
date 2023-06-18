package com.kh.iMMUTABLE.service;

import com.kh.iMMUTABLE.entity.User;
import com.kh.iMMUTABLE.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public boolean getUserList(String useremail, String userpwd){
        List<User> userList = userRepository.findByUserEmailAndUserPwd(useremail,userpwd);
        System.out.println("service : " + useremail);
        System.out.println("service : " + userpwd);
        System.out.println(userList);
        if(!userList.isEmpty()) return true;
        else return false;
    }
}
