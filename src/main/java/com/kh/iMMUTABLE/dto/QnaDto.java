package com.kh.iMMUTABLE.dto;

import com.kh.iMMUTABLE.constant.QnaStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class QnaDto {
    private Long qnaId;
    private String qnaTitle;
    private String qnaContent;
    private String qnaPwd;
    private String reply;
    private LocalDateTime qnaDate;
    private long userId;
    private long productId;

    @Enumerated(EnumType.STRING)
    private QnaStatus qnaStatus;
}
