import React, { useContext, useState, useEffect } from "react";
import styled from "styled-components";
import Header from "../shopPage/Header";
import Modal from "./Modal";
import {FaRegHeart, FaHeart} from "react-icons/fa";
import { UserContext } from "../context/UserInfo";
import { useNavigate } from "react-router-dom";
import AxiosFinal from "../api/AxiosFinal";
import Pagenation from "./Pagenation";


const Container = styled.div`
    margin: 0;
    padding: 0;
    width: 100%;
    height: 100vh;
    display: flex;
    flex-direction: column;
`;

const InnerContainer = styled.div`
    width: 100%;
    height: 100vh;
    margin-top: 50px;
    
    .product {
        margin: 0 40px;
        display: flex;
        .productImg {
            display: flex;
            flex-direction: column;
            width: 70%;         
            justify-content: center;
            align-items: center;
            img {
                display: flex;
                margin-bottom: 10px;
                width: 500px;
                height: 100%;
            }
           
        }
        .wholeDesc {
            width: 30%;
            display: flex;
            .descWrapper {
                position: sticky;
                top: 200px;
                width: 100%;
                height: 250px;
                display: flex;
                flex-direction: column;

                .productName {
                    font-size: 20px;
                    margin-bottom: 5px;
                    font-weight: bold;
                }
                .productPrice {
                    font-size: 12px;
                    margin-bottom: 5px;
                }
                .colorSize {
                    display: flex;
                    .productColor {
                        width: 155px;
                        height: 30px;
                        border: 1px solid black;
                        padding-left: 10px;
                        margin-right: 10px;
                    }
                    .productSize {
                        select {
                            width: 150px;
                            height: 32px;
                            border-radius: 0px;
                        }
                    }

                }
                .addBtn {
                    display: flex;
                    .heart {
                        background-color: white;
                        border: 1px solid black;
                        margin: 20px 0;
                        margin-right: 10px;
                        width: 50px;
                        height: 50px;
                        font-size: 20px;
                    }
                    .faHeart {
                        color: red;
                    }
                    .cart {  
                        width: 268px;
                        height: 50px;
                        margin: 20px 0;
                        border: 1px solid black;
                        background-color: black;
                        color: white;
                        &:hover {
                        background-color: white;
                        color: black;
                        }
                    }
                }
                .productDesc {
                    width: 350px;
                    font-size: 12px;
                }
                .detailWrapper {
                    p {
                        font-size: 12px; 
                        &:hover {
                            cursor: pointer;
                            color: gray;
                        }
                    }
                    .detail {
                        font-size: 12px;
                        ul {
                            width: 350px;
                            padding-left: 0;
                            margin-left: 15px;
                        }
                    }
                }
            }
        }
    }
`;


const Review = styled.div`
    width: 100%;
    height: 300px;
    margin-bottom: 30px;
    .review {
        margin: 0 40px;
        height: 100%;
        margin-top: 20px;
        .reviewBoard {
            height: 20px;
            padding-right: 20px;
            font-size: 14px;
            font-weight: bold;
        }
    }
`;

const ReviewTable = styled.table`
    width: 100%;
    tr {
        width: 100%;
        th {
            padding-bottom: 10px;
        }
        .Num {
            width: 10%;
        }
        .Title {
            width: 50%;
        }
        .User, .Date {
            width: 20%;
        }
        td {
            text-align: center;
        }
        
    }
`;

const QnA = styled.div`
    width: 100%;
    padding-bottom: 20px;
    .qna {
        margin: 0 40px;
        .qnaBoard {
            height: 20px;
            padding-right: 20px;
            font-size: 14px;
            font-weight: bold;
        }
        .qnaWrapper {
            display: flex;
            justify-content: space-between;
            .qnaWrite {
                font-size: 14px;
                &:hover {
                    cursor: pointer;
                    text-decoration: underline;
                }
            }
        }
    }
`;

const QnATable = styled.table`
    width: 100%;
    tr {
        width: 100%;
        th {
            padding-bottom: 10px;
        }
        .Num, .Status {
            width: 10%;
        }
        .Title {
            width: 40%;
        }
        .User, .Date {
            width: 15%;
        }
        td {
            text-align: center;
            padding: 10px 0;
            font-size: 14px;
        }
        .title {
            &:hover {
                cursor: pointer;
                color: gray;
            }
        }   
    }
    .qnaContent {
        height: 40px;
        font-size: 14px;
        .content {
            margin: 0 80px;
        }
    }
`;





const ProductInfo = () => {
    const [cartItems, setCartItems] = useState([]);
    const nav = useNavigate();

    const [click, setClick] = useState(false);
    const [modalOpen, setModalOpen] = useState(false);
    const [likeClick, setlikeClick] = useState(false);
    const [productId, setProductId] = useState();   // 사이즈에 따른 상품 아이디
    const [product, setProduct] = useState([]);
    const [qnaData, setQnaData] = useState([]); 
    const [expanded, setExpanded] = useState([]); 

    // pagenation
    const [limit, setLimit] = useState(5);  // 한 페이지에 표시할 아이템 수
    const [page, setPage] = useState(1);    // 페이지 번호
    const offset = (page - 1) * limit;      // 시작 인덱스

    console.log(product);
    const id = window.localStorage.getItem("userIdSuv");
    const isLogin = window.localStorage.getItem("isLoginSuv");
    const heartProductId = window.localStorage.getItem("heartProductId");

    const handleSelect = (e) => {
        const productId = e.target.value;
        // console.log(productId);
        setProductId(productId);    
    };

    const detailClick = () => {
        setClick(!click);
    }

    const clickLike = async(id, heartProductId) => {
        if(isLogin === "FALSE") {
            nav("/Login");
        } else {
        await AxiosFinal.likeProduct(id, heartProductId);
        setlikeClick(true); 
        }
    }

    const clickLikeDelete = async(id, heartProductId) => {
        await AxiosFinal.deleteLikeProduct(id, heartProductId);
        setlikeClick(false);
    }



    const writeQna = () => {
        if (isLogin === "TRUE") {
            setModalOpen(true);
        } else {
            nav("/Login");
        }
    }
   

    useEffect(()=> {
        const storedData = window.localStorage.getItem("productData");
         if (storedData) {
            setProduct(JSON.parse(storedData));
        }
        const heartView = async(id, heartProductId) => {
            const rsp = await AxiosFinal.viewHeart(id, heartProductId);
            if(rsp.data) {
                setlikeClick(true);
            } else { 
                setlikeClick(false);
            }
        } 

        const qnaView = async(heartProductId) => {
            const rsp = await AxiosFinal.viewQna(heartProductId);
            console.log(rsp.data);
            setQnaData(rsp.data);
        }
        

        if (heartProductId) {
            heartView(id, heartProductId);
            qnaView(heartProductId);
          }
    }, [modalOpen]);
    

    const handleQna = (index) => {
        if(expanded.includes(index)) {
            setExpanded(expanded.filter((row)=> row !== index));
        } else {
            setExpanded([...expanded, index]);
        }
    }

    const closeModal = () => {
        setModalOpen(false);
    }

    const clickCart = async(id, productId) => {
        console.log("동규 >> " + productId); //요거는 email인뎁쇼,,,
        console.log("동규 email>> " + id); //요거는 email인뎁쇼,,,

        const params = await AxiosFinal.addCartItem(id, productId); 

    }





    return (
        <Container>
                <Header />
            <InnerContainer>
                {product.length > 0 && (<div className="product">           
                    <div className="productImg">
                            <img src={product[0].productImgFst} alt="" />
                            <img src={product[0].productImgSnd} alt="" />
                            <img src={product[0].productImgDetail} alt="" />
                    </div>
                    <div className="wholeDesc">
                        <div className="descWrapper">
                            <div className="productName">{product[0].productName}</div>
                            <div className="productPrice">{product[0].productPrice}</div>
                            <div className="colorSize">
                                <div className="productColor">{product[0].productColor}</div>
                                <div className="productSize">
                                    <select onChange={handleSelect} defaultValue="default">
                                        <option value="default">SIZE</option>
                                        {product.map((data)=> (
                                            <option key={data.productId} value={data.productId}>
                                                {data.productSize}
                                            </option>
                                        ))}
                                    </select>
                                </div>
                            </div>
                            <div className="addBtn">
                               {likeClick? <button className="heart" onClick={()=>clickLikeDelete(id, heartProductId)}><FaHeart className="faHeart"/></button> : <button className="heart" onClick={()=>clickLike(id, heartProductId)}><FaRegHeart/></button>}
                                <button className="cart" onClick={()=>clickCart(id, productId)}>ADD TO CART</button>
                            </div>
                            <div className="productDesc">product desc</div>
                            <div className="detailWrapper">
                                <p onClick={detailClick}>DETAILS  {click? "–" : "+"}</p>
                                {click && (<div className="detail">
                                    <ul>
                                        <li>{product[0].productContent}</li>
                                    </ul></div>)}
                            </div>
                        </div>
                    </div>
                </div>)}
                <Review>
                    <div className="review"> 
                        <div className="reviewBoard">Review</div>
                        <hr />
                        <ReviewTable>
                            <tbody>
                                <tr>
                                    <th className="Num">NUM</th>
                                    <th className="Title">TITLE</th>
                                    <th className="User">USER</th>
                                    <th className="Date">DATE</th>
                                </tr>
                            </tbody>
                            <tbody>                             {/*DB 값 가져오기*/}
                                <tr>
                                    <td className="number">1.</td>
                                    <td className="title">리뷰 작성</td>
                                    <td className="user">이***</td>
                                    <td className="date">2023-06-10</td>
                                </tr>
                            </tbody>
                        </ReviewTable>
                    </div>
                </Review>
                <QnA>
                    <div className="qna">
                        <div className="qnaWrapper">
                            <div className="qnaBoard">Q&A</div>
                            <div className="qnaWrite" onClick={writeQna}>문의 작성</div>
                        </div>
                        <Modal open={modalOpen} close={closeModal} header="문의 작성"/>
                        <hr />
                        <QnATable>
                            <tbody>
                                <tr>
                                    <th className="Num">NUM</th>
                                    <th className="Status">STATUS</th>
                                    <th className="Title">TITLE</th>
                                    <th className="User">USER</th>
                                    <th className="Date">DATE</th>
                                </tr>
                                {qnaData.length > 0 ? (
                                qnaData.slice(offset, offset + limit).map((e, index) => (
                                <React.Fragment key={index}>
                                    <tr onClick={() => handleQna(index)}>
                                    <td className="number">{offset + index + 1}.</td>
                                    <td className="status">{e.qnaStatus === "HOLD" ? '답변대기' : '답변완료'}</td>
                                    <td className="title">{e.qnaTitle}</td>
                                    <td className="user">{e.userName.substring(0,1)}**</td>
                                    <td className="date">{e.qnaDate}</td>
                                    </tr>
                                    {expanded.includes(index) && (
                                    <td colSpan={4} className="qnaContent">
                                        <p className="content">{e.qnaContent}</p>
                                    </td>
                                    )}
                                </React.Fragment>
                                ))
                                ) : (
                                    <tr>
                                    <td colSpan={4}>문의가 없습니다.</td>
                                    </tr>
                                )}
                            </tbody>
                        </QnATable>
                    </div>
                    <Pagenation
                                total={qnaData.length} // 전체 아이템 수
                                limit={limit}          // 페이지 당 아이템 수
                                page={page}            // 현재 페이지 번호
                                setPage={setPage}      // 페이지 번호를 변경
                            />
                </QnA>
                
            </InnerContainer>
                
        </Container>
    )

}

export default ProductInfo;