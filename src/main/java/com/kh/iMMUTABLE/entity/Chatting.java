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
    @OnDelete(action = OnDeleteAction.CASCADE)
    private long chattingId;

    //채팅창id 외래키 가져오기
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chat_id")
    private ChatList chatList;

    //고객아이디
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

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
