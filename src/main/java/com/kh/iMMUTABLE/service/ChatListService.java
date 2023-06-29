package com.kh.iMMUTABLE.service;

import com.kh.iMMUTABLE.entity.ChatList;
import com.kh.iMMUTABLE.repository.ChatListRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class ChatListService {

    private final ChatListRepository chatListRepository;

    public List<ChatList> getOrderListAll() {
        List<ChatList> orderList = chatListRepository.findAll();
    return orderList;
    }

}
