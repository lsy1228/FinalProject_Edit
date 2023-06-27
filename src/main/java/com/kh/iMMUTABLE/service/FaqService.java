package com.kh.iMMUTABLE.service;

import com.kh.iMMUTABLE.entity.Faq;
import com.kh.iMMUTABLE.repository.FaqRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class FaqService {
    private final FaqRepository faqRepository;

    // faq 업로드
    public boolean faqUpload(String faqTitle, String faqContent, LocalDateTime faqDate){
        Faq faq = new Faq();
        faq.setFaqTitle(faqTitle);
        faq.setFaqContent(faqContent);
        faq.setFaqDate(faqDate);
        Faq uploadFaq = faqRepository.save(faq);
        return true;
    }

//     faq 목록 가져오기
    public List<Faq> faqList() {
        List<Faq> faqList = faqRepository.findAll();
        for(Faq faq : faqList) {
            faq.getFaqTitle();
            faq.getFaqContent();

        }
        return faqList;
    }
}
