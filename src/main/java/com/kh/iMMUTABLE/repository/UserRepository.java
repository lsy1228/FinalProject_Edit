package com.kh.iMMUTABLE.repository;

import com.kh.iMMUTABLE.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByUserEmailAndUserPwd(String useremail,String userpwd);
}
