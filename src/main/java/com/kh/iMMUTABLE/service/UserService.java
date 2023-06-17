package com.kh.iMMUTABLE.service;

import com.kh.iMMUTABLE.dto.UserDTO;
import com.kh.iMMUTABLE.entity.User;
import com.kh.iMMUTABLE.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<UserDTO> getUserList(){
        List<User> users = userRepository.findByUserEmail("dlxo");
        List<UserDTO> userDTOS = new ArrayList<>();
        return userDTOS;


    }

}
