package com.kh.iMMUTABLE.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class ChatDto {

    //채팅 친 유저
    private String user;
    //채팅 메시지
    private String chatMessage;
    //채팅 시간
    private Date date;
    //채팅 읽음 표시
    private String chatStatus;



}
