import React, { useEffect, useState } from "react";
import styled from "styled-components";
import {Link , useNavigate} from 'react-router-dom';
import MyPageHeader from "../shopPage/MypageHeader";
import AxiosFinal from "../api/AxiosFinal";

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
                    font-size: 14px;
                    justify-content: center;
                    align-items: center;
                }
                .size {
                    display: flex;
                    font-size: 14px;
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

    const onClick = (productId) => {
        navigate(`/Review/${productId}`);
    }

    const id = window.localStorage.getItem("userIdSuv");
    const [orderList, setOrderList] = useState([]);

    useEffect(()=> {
        const orderView = async() => {
            const rsp = await AxiosFinal.orderHistory(id);
            console.log(rsp.data); setOrderList(rsp.data);
        }
        orderView();
    }, []);


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
                                {orderList.map((e) => (
                                <tr key={e.orderId}>
                                    <td className="tdInfo">
                                        <div className="product"><img src={e.productImgFst} alt="" /></div>
                                        <div className="name">{e.productName}</div>
                                        <div className="size"><div>{e.productSize}</div></div>
                                    </td>
                                    <td className="tdDate">{e.orderDate}</td>
                                    <td className="tdNum">{e.orderId}</td>
                                    <td className="tdPrice">{e.productPrice}원</td>
                                    <td className="tdStatus"><button onClick={()=>onClick(e.productId)}>리뷰작성</button></td>
                                </tr>
                                ))}
                            </tbody>

                        </table>
                    </div>

                </OrderTable>

            </InnerContainer>
        </Container>
    );
}

export default Order;