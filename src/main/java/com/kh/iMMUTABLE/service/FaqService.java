package com.kh.iMMUTABLE.service;

import com.kh.iMMUTABLE.entity.Faq;
import com.kh.iMMUTABLE.repository.FaqRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class FaqService {
    private final FaqRepository faqRepository;

    public boolean faqUpload(String faqTitle, String faqContent){
        Faq faq = new Faq();
        faq.setFaqTitle(faqTitle);
        faq.setFaqContent(faqContent);
        Faq uploadFaq = faqRepository.save(faq);
        return true;
    }
}
