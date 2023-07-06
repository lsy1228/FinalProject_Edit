import React from "react";
import styled from "styled-components";
const Container = styled.div`
    width: 100%;
    height: 100vh;
    display: flex;
    justify-content: center;
    align-items: center;
    flex-direction: column;
    
    a {
        width: 300px;
        height: 40px;
        margin-top: 20px;
        text-decoration: none;
        border: 1px solid black;
        color: black;
        justify-content: center;
        text-align: center;
        line-height: 40px;
        &:hover{
            color: #CCC;
            background-color: black;
        }
    }
    .header {
        margin-bottom: 20px;
        font-weight: bold;
        font-size: 50px;
    }
    .item {
        font-size: 20px;
    }
`;

const OrderComplete = () => {

    return(
        <Container>
            <div className="header">iMMUTABLE</div>
            <div className="item">주문이 완료되었습니다.</div>
            <a href="/">메인으로</a>
        </Container>
    );
};

export default OrderComplete;