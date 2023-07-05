package com.kh.iMMUTABLE.repository;

import com.kh.iMMUTABLE.dto.UserDto;
import com.kh.iMMUTABLE.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByUserEmailAndUserPwd(String userEmail,String userPassword);

    User findByUserEmail(String userEmail);


    User findByUserPwd(String userPwd);

    User findByUserId(long userId);

    boolean existsByUserEmail(String userEmail); // entity 이름과 같아야함, exists에 s 붙여주기
}
