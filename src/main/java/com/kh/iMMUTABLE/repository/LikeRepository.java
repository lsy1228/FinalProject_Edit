package com.kh.iMMUTABLE.repository;

import com.kh.iMMUTABLE.entity.Like;
import com.kh.iMMUTABLE.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {
}
