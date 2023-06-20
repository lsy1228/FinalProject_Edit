package com.kh.iMMUTABLE.service;

import com.kh.iMMUTABLE.dto.UserDto;
import com.kh.iMMUTABLE.entity.Qna;
import com.kh.iMMUTABLE.entity.User;
import com.kh.iMMUTABLE.repository.QnaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class QnaService {
    private final QnaRepository qnaRepository;

    public List<Qna> getQnaListAll() {
        List<Qna> qnaList = qnaRepository.findAll();
        return qnaList;
    }
    public boolean upLoadReply(int qnaId,String answerStatue,String qnareplay){
    return true;
    }
}
