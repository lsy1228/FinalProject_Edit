import React, { useEffect, useState, useRef } from "react";
import styled from "styled-components";
const Container=styled.div`
    width:300px;
    height:90%;
    display:flex;
    justify-content: center;
    align-items: center;
    font-size:12px;

}
`
const ChatEmpty = () => {

    return (
        <Container>
            contact us!
        </Container>
      );
    };

    export default ChatEmpty;