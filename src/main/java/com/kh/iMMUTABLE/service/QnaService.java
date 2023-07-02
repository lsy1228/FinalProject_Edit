package com.kh.iMMUTABLE.service;

import com.kh.iMMUTABLE.constant.QnaStatus;
import com.kh.iMMUTABLE.dto.QnaDto;
import com.kh.iMMUTABLE.dto.UserDto;
import com.kh.iMMUTABLE.entity.Product;
import com.kh.iMMUTABLE.entity.Qna;
import com.kh.iMMUTABLE.repository.ProductRepository;
import com.kh.iMMUTABLE.repository.QnaRepository;
import com.kh.iMMUTABLE.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.kh.iMMUTABLE.entity.User;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class QnaService {
    private final QnaRepository qnaRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public List<Qna> getQnaListAll() {
        List<Qna> qnaList = qnaRepository.findAll();
        return qnaList;
    }

    // Q&A 댓글
    //save를 통한 update문 기능 findByQnaId를 통해서 select 한 후 set을 통해 update를 자동으로 변환 시켜준다.
    public boolean upLoadReply(long qnaId,String qnaStatue,String qnaReplay) {
        System.out.println("서비스 : " +  qnaReplay);
        Qna qna = qnaRepository.findByQnaId(qnaId);
        qna.setQnaStatus(QnaStatus.valueOf(qnaStatue));
        qna.setReply(qnaReplay);
        qnaRepository.save(qna);
        return true;
    }

    // QnA 업로드
    public boolean qnaUpload(String userEmail , String productId, String qnaTitle, String qnaContent, LocalDateTime qnaDate){
        System.out.println(userEmail + productId + qnaTitle + qnaContent + qnaDate);
        Qna qna = new Qna();
        User user = userRepository.findByUserEmail(userEmail);
        Product product = productRepository.findByProductId(Long.parseLong(productId));
        qna.setUser(user);
        qna.setProduct(product);
        qna.setQnaTitle(qnaTitle);
        qna.setQnaContent(qnaContent);
        qna.setQnaDate(qnaDate);
        Qna uploadQna = qnaRepository.save(qna);
        return true;
    }

    public List<Qna> getStatusQnaList(String qnaStatus){
        List<Qna> qnaList = qnaRepository.findByQnaStatus(QnaStatus.valueOf(qnaStatus));
        return qnaList;
    }

    public List<QnaDto> getQna(String productId) {
        Product product = productRepository.findByProductId(Long.parseLong(productId));
        List<Qna> qnas = product.getQnaList();

        List<QnaDto> qnaDtos = new ArrayList<>();
        for(Qna qna : qnas) {
            QnaDto qnaDto = new QnaDto();
            qnaDto.setQnaId(qna.getQnaId());
            qnaDto.setQnaTitle(qna.getQnaTitle());
            qnaDto.setQnaContent(qna.getQnaContent());
            qnaDto.setQnaDate(qna.getQnaDate());
            qnaDto.setQnaStatus(QnaStatus.HOLD);

            User user = userRepository.findByUserId(qna.getUser().getUserId());
            qnaDto.setUserName(user.getUserName());
            qnaDto.setUserId(user.getUserId());
            qnaDtos.add(qnaDto);
        }
        return qnaDtos;
    }
}
