package com.kh.iMMUTABLE.repository;

import com.kh.iMMUTABLE.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByUserEmailAndUserPwd(String userEmail,String userPassword);

    Optional<User> findByEmail(String email);
    Boolean existsByEmail(String email);
}
