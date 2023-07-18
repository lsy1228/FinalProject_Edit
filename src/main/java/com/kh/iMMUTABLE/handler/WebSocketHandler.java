package com.kh.iMMUTABLE.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kh.iMMUTABLE.dto.ChatMessageDto;
import com.kh.iMMUTABLE.entity.ChatRoom;
import com.kh.iMMUTABLE.service.ChatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@RequiredArgsConstructor
@Slf4j
@Component
public class WebSocketHandler extends TextWebSocketHandler {
    private final ObjectMapper objectMapper;
    private final ChatService chatService;
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload();
        log.warn("{}", payload);
        ChatMessageDto chatMessage = objectMapper.readValue(payload, ChatMessageDto.class);
//        System.out.println("핸들러 DTO메시지 : "+ chatMessage.getMessage());
//        System.out.println("핸들러 DTO 룸아이디 : "+ chatMessage.getRoomId());
        //마지막 메시지를 DB에 넣음
        chatService.saveLastMessage(chatMessage.getRoomId(),chatMessage.getMessage());
        ChatRoom chatRoom = chatService.findRoomById(chatMessage.getRoomId());
        System.out.println("핸들러 챗 룸 : " + chatRoom);
        chatRoom.handlerActions(session, chatMessage, chatService);
    }
}
