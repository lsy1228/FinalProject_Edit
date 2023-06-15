package com.kh.iMMUTABLE.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "qna")
@Getter @Setter @ToString
public class Qna {
    @Id
    @Column(name = "qna_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int qnaId;

    @Column(nullable = false)
    private String qnaTitle;
    @Column(nullable = false)
    private String qnaContent;
    private String qnaPwd;
    private LocalDateTime qnaDate;
}
