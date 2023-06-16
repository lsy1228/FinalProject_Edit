package com.kh.iMMUTABLE.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
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
    @OnDelete(action = OnDeleteAction.CASCADE)
    private int qnaId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private int userId;

    @Column(nullable = false)
    private String qnaTitle;

    @Column(nullable = false)

    private String qnaContent;
    private String qnaPwd;
    private LocalDateTime qnaDate;

    @OneToOne
    @JoinColumn(name = "reply_id")
    private Reply reply;
}
