package com.kh.iMMUTABLE.service;

import com.kh.iMMUTABLE.constant.QnaStatus;
import com.kh.iMMUTABLE.dto.UserDto;
import com.kh.iMMUTABLE.entity.Qna;
import com.kh.iMMUTABLE.entity.User;
import com.kh.iMMUTABLE.repository.QnaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    //save를 통한 update문 기능 findByQnaId를 통해서 select 한 후 set을 통해 update를 자동으로 변환 시켜준다.
    public boolean upLoadReply(long qnaId,String qnaStatue,String qnaReplay) {
        System.out.println("서비스 : " +  qnaReplay);
        Qna qna = qnaRepository.findByQnaId(qnaId);
        qna.setQnaStatus(QnaStatus.valueOf(qnaStatue));
        qna.setReply(qnaReplay);
        qnaRepository.save(qna);
        return true;
    }
}
