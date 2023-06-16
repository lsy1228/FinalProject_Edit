package com.kh.iMMUTABLE.repository;

import com.kh.iMMUTABLE.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email); //null포인트를 방지하기 위해서 Optional을 쓴다 Wrapper 클래스의 종류
    boolean existsByEmail(String email);
}
