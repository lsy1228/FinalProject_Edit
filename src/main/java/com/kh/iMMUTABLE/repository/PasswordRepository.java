package com.kh.iMMUTABLE.repository;

import com.kh.iMMUTABLE.entity.Like;
import com.kh.iMMUTABLE.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PasswordRepository extends JpaRepository<User, Long>  {
    @Query(value = "update users  set userPwd = :password")
    List<User> findByUsers(@Param("password") String password);
}
