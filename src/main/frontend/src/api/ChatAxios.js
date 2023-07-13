import axios from "axios";
const Final_proj = "http://localhost:8111";

const ChatAxios = {
// 채팅방 개설 API

    chatRoomOpen: async function(name) {
        const chatObject = {
            "name" : name
        }
        return await axios.post(Final_proj + "/chat/room", chatObject);
    },
    saveChatData: async function(roomId,userId) {
        const saveChatData= {
            "roomId" : roomId,
            "userId" : userId,
        }
        return await axios.post(Final_proj + "/chat/saveChatData", saveChatData);
    }
};
export default ChatAxios;
