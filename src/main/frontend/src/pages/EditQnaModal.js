import React, { useContext, useEffect, useState } from "react";
import styled from "styled-components";
import AxiosFinal from "../api/AxiosFinal";
import { UserContext } from "../context/UserInfo";
import {useNavigate} from 'react-router-dom';

const Container = styled.div`
    .modal {
        display: none;  // 숨겨진 상태로 시작
        position: fixed;
        width: 400px;
        height: 600px;
        top: 10%;  // 화면 전체를 덮도록 위치
        bottom: 0;
        left: 35%;
        z-index: 99; // 다른 모달 보다 위에 위치하도록 함
        background-color: white;
        border-radius: 10px;
 }

    .openModal {
        display: flex; // 모달이 보이도록 함
        align-items: center;
        /* 팝업이 열릴때 스르륵 열리는 효과 */
        animation: modal-bg-show 0.8s;
    }
    button {
        width: 100px;
        height: 30px;
        outline: none;
        cursor: pointer;
        margin-right: 20px;
        border: 0;
    }

    .form {
        border: 1px solid black;
        border-radius: 10px;
        width: 100%;
        height: 100%;
        //팝업이 열릴때 스르륵 열리는 효과
        animation: modal-show 0.3s;
        overflow: hidden;
        header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            width: 100%;
            height: 30px;
            background-color: #E6E6E6;
            .header-title {
                margin-left: 5px;
                font-weight: bold;
            }
            .close {
                margin-right: 5px;
                cursor: pointer;
            }
        }
        .main {
            margin: 10px 10px;
            .product {
                margin: 40px 0;
                display: flex;
                justify-content: space-evenly;
                img {
                    width: 100px;
                    height: 100px;
                    object-fit: cover;
                }
                .productInfo {
                    display: flex;
                    flex-direction: column;
                    justify-content: center;
                    .productName {
                        font-weight: bold;
                        margin-bottom: 5px;
                    }
                }
            }
            .mainTitle {
                width: 100%;
                height: 30px;
                display: flex;
                justify-content: space-evenly;
                margin-bottom: 10px;
                .title {
                    width: 30px;
                    font-size: 14px;
                }
                input {
                    width: 250px;
                    outline: none;
                    border: 1px solid gray;
                }
            }
            .mainCon {
                width: 100%;
                height: 230px;
                display: flex;
                justify-content: space-evenly;
                margin-bottom: 20px;
                .content {
                    width: 30px;
                    font-size: 14px;
                }
                textarea {
                    width: 250px;
                    outline: none;
                    border: 1px solid gray;
                    resize: none;
                }
                textarea::placeholder {
                font-family: Arial, sans-serif;
                font-size: 14px;
                color: #999;
                }
            }
            .Btn {
                display: flex;
                justify-content: center;
                .write {
                    background-color: black;
                    color: white;
                    
                }
            }
        }
    }


    @keyframes modal-show {
        from {
            opacity: 0;
            margin-top: -50px;
        }
        to {
            opacity: 1;
            margin-top: 0;
        }
    }
    @keyframes modal-bg-show {
        from {
            opacity: 0;
        }
        to {
            opacity: 1;
        }
    }

`;

const EditQnaModal = (props) => {
    const {open, close, productId, qnaId} = props;
    const [inputTitle, setInputTitle] = useState('');
    const [inputContent, setInputContent] = useState('');
    const [product, setProduct] = useState([]);

    const nav = useNavigate();

    useEffect(()=> {
        const editProductInfo = async(productId) => {
            const response = await AxiosFinal.myQnaProductInfo(productId);
            setProduct(response.data);
        }
        editProductInfo(productId);

        const getMyQna = async(qnaId) => {
            const response = await AxiosFinal.editViewMyQna(qnaId);
            setInputTitle(response.data.qnaTitle);
            setInputContent(response.data.qnaContent);
        }
        getMyQna(qnaId);


    }, [productId, qnaId]);

    const editTitle = (e) => {
        setInputTitle(e.target.value);
    }

    const editContent = (e) => {
        setInputContent(e.target.value);
    }

    const editMyQna = async(qnaId, title, content) => {
        const response = await AxiosFinal.editMyQna(qnaId, title, content);
        if(response.data) {
            alert("문의 수정이 완료되었습니다");
            close();
        } else {
            alert("문의 수정이 실패하였습니다");
            close();
        }
    }

    return (
        <Container>
            <div className={open ? 'openModal modal' : 'modal'}>
            {open &&
               <div className="form">
                    <header>
                        <div className="header-title">iMMUTABLE</div>
                        <div className="close" onClick={close}>&times;</div>
                    </header>
                    <div className="main">
                        <h2>문의수정</h2>
                        <div className="product">
                            <img src={product.productImgFst}/>
                            <div className="productInfo">
                                <div className="productName">{product.productName}</div>
                                <div className="productPrice">{product.productPrice}</div>
                            </div>
                        </div>
                        <div className="mainTitle">
                            <div className="title">제목</div> <input type="text" defaultValue={inputTitle} onChange={editTitle} /> 
                        </div>
                        <div className="mainCon">
                            <div className="content">내용</div> <textarea defaultValue={inputContent} onChange={editContent} />
                        </div>
                        <div className="Btn">
                            <button className="cancle" onClick={close}>취소</button>
                            <button type="submit" className="write" onClick={()=>editMyQna(qnaId, inputTitle, inputContent)}>수정하기</button>
                        </div>
                    </div>
                </div>
            }   
            </div>
        </Container>
    );
};
export default EditQnaModal;