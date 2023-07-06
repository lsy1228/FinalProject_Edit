import React from "react";
import styled from "styled-components";
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
    .chatHead{
        width: 100%;
        height: 100px;
        border-bottom:1px solid #CCC;
        background-color: aliceblue;
    }
    .chatMiddle{
        width: 100%;
        height: 547px;
        border-bottom:1px solid #CCC;
    }
    .chatFooter{
        width: 100%;
        height: 70px;
        border-bottom:1px solid #CCC;
        background-color: aliceblue;
    }
`
const ChatList=()=>{
    return(
        <Container> 
          <ChatListView>
            <ChatListData>
                대화
            </ChatListData>
            <ChatListData>
                대화
            </ChatListData>
            <ChatListData>
                대화
            </ChatListData>
          </ChatListView>
          <ChatListView>
            <ChattingData>  
                <div className="chatHead">

                </div>
                <div className="chatMiddle">

                </div>
                <div className="chatFooter">

                </div>   
            </ChattingData>
          </ChatListView>
        
        </Container>
    );
};

export default ChatList;
