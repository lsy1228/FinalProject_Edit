package com.kh.iMMUTABLE.entity;

import com.kh.iMMUTABLE.constant.ChatStatus;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "chatting")
@Getter
@Setter
@ToString
public class Chatting {

    //채팅 메시지 id
    @Id
    @Column(name = "chatting_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int chattringId;

    //채팅창id 외래키
    @Column(nullable = false)
    private int chatId;

    //고객아이디
    @Column(nullable = false)
    private int customerId;

    //관리자 아이디
    @Column(nullable = false)
    private int adminId;
    //채팅 메시지
    @Lob //문자열보다 긴 문자열을 사용
    @Column(nullable = false)
    private String message;

    //채팅시간
    @Column(nullable = false)
    private LocalDateTime chattingTime;

    //읽음 여부
    @Enumerated(EnumType.STRING)
    private ChatStatus chatStatus;



}
