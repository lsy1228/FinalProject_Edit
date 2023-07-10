import React, { useEffect, useState, useRef } from "react";
import styled from "styled-components";
const Container=styled.div`
.msg_input {
  width: 200px; /* 원하는 너비 설정 */
  height: 20px; /* 높이값 초기화 */
  font-size:11px;
  line-height : normal; /* line-height 초기화 */
  padding: .8em .5em; /* 원하는 여백 설정, 상하단 여백으로 높이를 조절 */
  border: 1px solid #999;
  outline-style: none; /* 포커스시 발생하는 효과 제거를 원한다면 */
  -webkit-appearance: none; /* 브라우저별 기본 스타일링 제거 */
  -moz-appearance: none; appearance: none;

}
.msg_send {
  margin: 10px;
  width: 100px; /* 원하는 너비 설정 */
  height: 20px;
  font-size:11px;
  color: white;
  background-color: black;
  border: orange;
  &:hover{
    color:#CCC;
  }
}
.msg_close {
  margin: 40px;
  width: 140px; /* 원하는 너비 설정 */
  height: 20px;
  color: white;
  background-color: black;
  font-size: 13px;
  font-weight: 400;
  border: orange;
}
`


const ChatSocket = () => {
    const [socketConnected, setSocketConnected] = useState(false);
    const [inputMsg, setInputMsg] = useState("");
    const [rcvMsg, setRcvMsg] = useState("");
    const webSocketUrl = `ws://localhost:8112/ws/chat`;
    const roomId = window.localStorage.getItem("chatRoomId");
    const sender = "곰돌이사육사";
    let ws = useRef(null);
    const [items, setItems] = useState([]);

    const onChangMsg = (e) => {
        setInputMsg(e.target.value)
    }

    const onEnterKey = (e) => {
        if(e.key === 'Enter') onClickMsgSend(e);
    }

    const onClickMsgSend = (e) => {
        e.preventDefault();
        ws.current.send(
            JSON.stringify({
            "type":"TALK",
            "roomId": roomId,
            "sender": sender,
            "message":inputMsg}));
            setInputMsg("");
    }
    const onClickMsgClose = () => {
        ws.current.send(
            JSON.stringify({
            "type":"CLOSE",
            "roomId": roomId,
            "sender":sender,
            "message":"종료 합니다."}));
        ws.current.close();
    }

    useEffect(() => {
        console.log("방번호 : " + roomId);
        if (!ws.current) {
            ws.current = new WebSocket(webSocketUrl);
            ws.current.onopen = () => {
                console.log("connected to " + webSocketUrl);
            setSocketConnected(true);
            };
        }
        if (socketConnected) {
            ws.current.send(
                JSON.stringify({
                "type":"ENTER",
                "roomId": roomId,
                "sender": sender,
                "message":"처음으로 접속 합니다."}));
        }
        ws.current.onmessage = (evt) => {
            const data = JSON.parse(evt.data);
            console.log(data.message);
            setRcvMsg(data.message);
            setItems((prevItems) => [...prevItems, data]);
      };
    }, [socketConnected]);

    return (
        <Container>
            <div>socket connected : {`${socketConnected}`}</div>
            <button className="msg_close" onClick={onClickMsgClose}>&times;</button>
            <div>
                {items.map((item) => {
                return <div>{`${item.sender} > ${item.message}`}</div>;
                })}
            </div>
            <input className="msg_input" placeholder="문자 전송" value ={inputMsg} onChange={onChangMsg} onKeyUp={onEnterKey}/>
            <button className="msg_send" onClick={onClickMsgSend}>전송</button>
        </Container>
      );
    };

    export default ChatSocket;