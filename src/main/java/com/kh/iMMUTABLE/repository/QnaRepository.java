package com.kh.iMMUTABLE.repository;

import com.kh.iMMUTABLE.entity.Qna;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface QnaRepository extends JpaRepository<Qna, Long> {

    @Modifying
    @Query("UPDATE Question q set q.showCount = q.showCount + 1 where q.questionId = :questionId")
    void updateQnaReply(@Param("questionId") Long questionId);
}
