package com.kh.iMMUTABLE.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "chatlist")
@Getter
@Setter
@ToString
public class ChatList {
    @Id
    @Column(name = "chat_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long chatRoomId;
    @Lob
    @Column(name = "room_id")
    private String roomId;
    @Column(name = "user_id")
    private String userId;
}
