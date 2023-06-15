package com.kh.iMMUTABLE.repository;

import com.kh.iMMUTABLE.entity.Chatting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRepository extends JpaRepository<Chatting, Long> {

}
