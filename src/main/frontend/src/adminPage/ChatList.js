import React, { useState, useContext } from "react";
import styled from "styled-components";
import { UserContext } from "../context/UserInfo";
import AdminChatSocket from "../chatPage/AdminChatSocket";

//전체 컨테이너 CSS
const Container=styled.div`
    width: 100%;
    height: calc(100vh - 180px);
    display: flex;   
`
//채팅리스트가 보여지는 CSS
const ChatListView=styled.div`
    width: 50%;
    flex-direction: column;
    overflow-y: scroll;
    ::-webkit-scrollbar {
    display: none;
    }
`
//채팅 목록의 데이터가 들어가는CSS
const ChatListData=styled.div`
    height: 100px;
    border-bottom: 1px solid #CCC;
    border-left: 1px solid #CCC;
    display: flex;
    justify-content: center;
    align-items: center;
    &:hover{
        background-color: #CCC;
    }
`
//채팅UI
const ChattingData=styled.div`
    height:100%;
    border-left:1px solid #CCC;
    border-bottom: 1px solid #CCC;
    border-right: 1px solid #CCC;
`
const ChatList=()=>{
    const context = useContext(UserContext);
    const {chatList} = context;
    console.log(chatList);

    //방ID를 담을 useState
    const [onRoomId,setOnRoomId]=useState();
    const onFindRoomId=(roomId)=>{
        console.log(roomId);
        setOnRoomId(roomId);
    }

    return(
        <Container> 
          <ChatListView>
            {chatList && chatList.map((l,index)=>
                  <ChatListData key={l.roomName} onClick={()=>onFindRoomId(l.roomName)}>
                      {l.userId} / {l.last}
                  </ChatListData>)}
          </ChatListView>

          <ChatListView>
               <ChattingData>
                    <AdminChatSocket setRoomId={onRoomId}/>
               </ChattingData>
          </ChatListView>
        
        </Container>
    );
};

export default ChatList;
