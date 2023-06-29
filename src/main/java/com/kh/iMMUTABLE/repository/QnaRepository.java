package com.kh.iMMUTABLE.repository;

import com.kh.iMMUTABLE.entity.Qna;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface QnaRepository extends JpaRepository<Qna, Long> {
    Qna findByQnaId(long qnaId);

}


