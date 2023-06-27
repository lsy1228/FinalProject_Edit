package com.kh.iMMUTABLE.repository;

import com.kh.iMMUTABLE.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PasswordRepository extends JpaRepository<User, Long>  {
//    @Query(value = "update users  set userPwd = :password")
//    List<User> findByUsers(@Param("password") String password);
}
