package com.kh.iMMUTABLE.repository;

import com.kh.iMMUTABLE.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByUserEmailAndUserPwd(String userEmail,String userPassword);

    List<User> deteleById(int userId);

}
