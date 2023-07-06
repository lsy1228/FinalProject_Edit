import React, { useState } from "react";
import styled from "styled-components";
import {Link , useNavigate} from 'react-router-dom';
import MyPageHeader from "../shopPage/MypageHeader";

const Container = styled.div`
    width: 100%;
    height: 100vh;
    
`;


const InnerContainer = styled.div`
    width: 100%;
    margin-top: 50px;
    .header {
        margin: 0 40px;
        font-size: 25px;
        font-weight: bold;
        margin-bottom: 20px;
    }
`;

const OrderTable = styled.div`
    width: 100%;
    height: 500px;
    .wrapper {
        margin: 0 40px;
        .orderTable {
            width: 100%;
            th {
                padding-bottom: 10px;
            }
            td {
                text-align: center;
            }
            .Info {
                width: 40%;
            }
            .Date, .Num, .Price, .Review{
                width: 15%;
            }
            .tdInfo {
                display: flex;
                justify-content: space-evenly;
                img {
                    width: 100px;
                    height: 100px;
                    object-fit: contain;
                }
                .name {
                    display: flex;
                    font-weight: bold;
                    justify-content: center;
                    align-items: center;
                }
            }
            button {
                width: 100px;
                height: 30px;
                background-color: black;
                color: white;
                outline: none;
                cursor: pointer;
            }
        }
    }
`;



const Order = () => {
    const navigate = useNavigate();

    const onClick = () => {
        navigate("/Review");
    }

    return (
        <Container>
            <MyPageHeader />
            <InnerContainer>
                <div className="header">주문내역 조회
                <hr />
                </div>
                <OrderTable>
                    <div className="wrapper">
                        <table className="orderTable">
                            <tr>
                                <th className="Info">상품정보</th> 
                                <th className="Date">주문일자</th>
                                <th className="Num">주문번호</th>
                                <th className="Price">주문금액</th>
                                <th className="Review">리뷰작성</th>
                            </tr>
                            <tbody> {/* DB에서 값 가져오기 */}
                                <tr>
                                    <td className="tdInfo">
                                        <div className="product"><img src="product.jpg" alt="" /></div>
                                        <div className="name"><div>Viscose Tricot Crewneck</div></div>
                                    </td>
                                    <td className="tdDate">2023.06.10</td>
                                    <td className="tdNum">123456789</td>
                                    <td className="tdPrice">₩‌1,054,800</td>
                                    <td className="tdStatus"><button onClick={onClick}>리뷰작성</button></td>
                                </tr>
                            </tbody>

                        </table>
                    </div>

                </OrderTable>

            </InnerContainer>
        </Container>
    );
}

export default Order;