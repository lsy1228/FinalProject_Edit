package com.kh.iMMUTABLE.repository;

import com.kh.iMMUTABLE.entity.User;

import java.util.Optional;

public interface UserRepository {
    Optional<User> findByEmail(String email); //null포인트를 방지하기 위해서 Optional을 쓴다 Wrapper 클래스의 종류

}
