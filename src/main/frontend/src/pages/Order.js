import React, { useContext, useEffect, useState } from "react";
import styled from "styled-components";
import {Link , useNavigate} from 'react-router-dom';
import MyPageHeader from "../shopPage/MypageHeader";
import AxiosFinal from "../api/AxiosFinal";
import { UserContext } from "../context/UserInfo";
import Pagenation from "./Pagenation";

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
    height: 550px;
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
            .reviewWrite {
                width: 100px;
                height: 30px;
                background-color: black;
                color: white;
                cursor: pointer;
            }
            .reviewBtn {
                width: 100px;
                height: 30px;
                background-color: white;
                color: black;
            }
            .noOrder {
                height: 100px;
                cursor: default;
            }
        }
    }
`;



const Order = () => {
    const navigate = useNavigate();

    const {orderId, setOrderId} = useContext(UserContext);

    const onClick = (productId, orderId) => {
        setOrderId(orderId);
        navigate(`/Review/${productId}`);
    }

    const id = window.localStorage.getItem("userIdSuv");
    const [orderList, setOrderList] = useState([]);

    // pagenation
    const [limit, setLimit] = useState(5);
    const [page, setPage] = useState(1);
    const offset = (page - 1) * limit;

    useEffect(()=> {
        const orderView = async() => {
            const rsp = await AxiosFinal.orderHistory(id);
            console.log(rsp.data); setOrderList(rsp.data);
        }
        orderView();
    }, []);

    const sortedOrderList = orderList.slice().reverse();


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
                            <tbody>
                                {sortedOrderList.length > 0 ? (sortedOrderList.slice(offset, offset+limit).map((e) => (
                                <tr key={e.orderId}>
                                    <td className="tdInfo">
                                        <div className="product"><img src={e.productImgFst} alt="" /></div>
                                        <div className="name">{e.productName}</div>
                                        <div className="size"><div>{e.productSize}</div></div>
                                    </td>
                                    <td className="tdDate">{e.orderDate}</td>
                                    <td className="tdNum">{e.orderId}</td>
                                    <td className="tdPrice">{e.productPrice}원</td>
                                    {e.reviewed ? (
                                        <td className="tdStatus"><button className="reviewBtn">작성완료</button></td>
                                    ) : (
                                    <td className="tdStatus"><button className="reviewWrite" onClick={()=>onClick(e.productId, e.orderId)}>리뷰작성</button></td>
                                    )}
                                </tr>
                                ))) : (
                                    <tr>
                                        <td colSpan={5} className="noOrder"> 주문 내역이 없습니다. </td>
                                    </tr>
                                )}
                            </tbody>
                        </table>
                    </div>
                </OrderTable>
                <Pagenation
                    total={orderList.length}
                    limit={limit}
                    page={page}
                    setPage={setPage}
                />
            </InnerContainer>
        </Container>
    );
}

export default Order;