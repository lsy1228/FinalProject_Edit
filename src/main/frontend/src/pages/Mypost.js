import React, { useEffect, useState } from "react";
import styled from "styled-components";
import MyPageHeader from "../shopPage/MypageHeader";
import { Axios, AxiosError } from "axios";
import AxiosFinal from "../api/AxiosFinal";
import EditQnaModal from "./EditQnaModal";

const Container = styled.div`
    width: 100%;
    height: 100vh;
    display: flex;
`
const Mainbody = styled.div`
    width: 100%;
    height: 100vh;
    display: flex;
    flex-direction: column;

`

const InnerContainer = styled.div`
    width: 100%;
    height: 100%;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
`


const Article = styled.div`

    width: 1200px;
    height: 350px;
    display: flex;
    margin-top: 20px;
    

    .header{
        font-size: 20px;
        font-weight: bold;
        height: 30px;
        border-bottom: 1.5px solid #ccc;
        width: 100%
    }

    .view{
        position: absolute;
        width: 1200px;
    }

    .viewTable{
        border-bottom: 1px solid #ccc;
        font-size: 15px;
        margin-top: 40px;
        width: 100%;
        .num{
            width: 5%;
        }
        .sub{
            width: 60%;
        }
        .writer{
            width: 10%;
        }
        .date{
            width: 10%;
        }
    }
   
    .textTable{
        width: 100%;
        td{
            text-align: center;
            padding: 10px 0;
            font-size: 14px;
        }
        .textNum{
            width: 5%;
        }
        .textSub{
            width: 60%;
            &:hover {
                cursor: pointer;
                color: gray;
            }
        }
        .textWriter{
            width: 10%;
        }
        .textDate{
            width: 10%;
        }
        .Btn {
            button {
                background-color: white;
                color: black;
                border: none;
                &:hover {
                    color: gray;
                    cursor: pointer;
                }
            }
        }

    }  
`


const Mypost = () => {

    const id = window.localStorage.getItem("userIdSuv");
    const [qnaData, setQnaData] = useState([]);
    const [expanded, setExpanded] = useState([]);
    const [modalOpen, setModalOpen] = useState(false);
    const [productId, setProductId] = useState([]);
    const [qnaId, setQnaId] = useState([]);
    
    const [deleteCount, setDeleteCount] = useState([]);

    useEffect(()=> {
        const viewMyQna = async(id) => {
            const rsp = await AxiosFinal.myQna(id);
            setQnaData(rsp.data);
        }
        viewMyQna(id);

    }, [deleteCount]);

    const handleQna = (index) => {
        if(expanded.includes(index)) {
            setExpanded(expanded.filter((row)=> row !== index));
        } else {
            setExpanded([...expanded, index]);
        }
    }

    const editQna = (productId, qnaId) => {
        setModalOpen(true);
        setProductId(productId);
        setQnaId(qnaId);
    }

    const closeModal = () => {
        setModalOpen(false);
    }

    const deleteQna = async(qnaId) => {
        const response = await AxiosFinal.deleteMyQna(qnaId);
        console.log(response.data);
        if(response.data) {
            alert("문의가 삭제되었습니다");
            setDeleteCount(preCount => preCount + 1);
        }
    }


    return(

        <Container>
            <Mainbody>
                <MyPageHeader />
                <InnerContainer>
                    <Article>
                        <div className="header">
                            REVIEW
                        </div>
                        <div className="view">
                            <table className="viewTable">
                                <tr>
                                    <th className="num">NUM</th>
                                    <th className="sub">SUBJECT</th>
                                    <th className="writer">WRITER</th>
                                    <th className="date">DATE</th>
                                </tr>
                            </table>
                            <table className="textTable">
                                <tr>
                                    <td className="textNum">1</td>
                                    <td className="textSub">사이즈도 적당하고 마음에 들어요 :)</td>
                                    <td className="textWriter">김동규</td>
                                    <td className="textDate">2023.06.23</td>
                                </tr>
                            </table>
                        </div>
                    </Article>
                    <Article>
                        <div className="header">
                            Q & A
                        </div>
                        <div className="view">
                            <table className="viewTable">
                                <tr>
                                    <th className="num">NUM</th>
                                    <th className="sub">SUBJECT</th>
                                    <th className="writer">WRITER</th>
                                    <th className="status">STATUS</th>
                                    <th className="edit">EDIT</th>
                                    <th className="delete">DELETE</th>
                                </tr>
                            </table>
                            <table className="textTable">
                                {qnaData.length > 0 ? (qnaData.map((e, index) => (
                                <React.Fragment key={index}>
                                <tr >
                                    <td className="textNum">{index + 1}</td>
                                    <td className="textSub" onClick={()=>handleQna(index)}>{e.qnaTitle}</td>
                                    <td className="textWriter">{e.userName}</td>
                                    <td className="textDate">{e.qnaStatus === "HOLD" ? '대기중' : '답변완료'}</td>
                                    <td className="Btn"><button onClick={()=>editQna(e.productId, e.qnaId)}>수정</button></td>
                                    <td className="Btn"><button onClick={()=>deleteQna(e.qnaId)}>삭제</button></td>
                                </tr>
                                {expanded.includes(index) && (
                                <tr>
                                    <td colSpan={2}>{e.qnaContent}</td>
                                </tr>
                                )}
                                </React.Fragment>
                                ))
                                ) : (
                                    <tr>
                                        <td colSpan={4}>문의 내역이 없습니다.</td>
                                    </tr>
                                )} 
                            </table>
                            {modalOpen && (
                                <EditQnaModal open={modalOpen} close={closeModal} productId={productId} qnaId={qnaId} />
                            )}
                        </div>  
                    </Article>
                </InnerContainer>
            </Mainbody>
        </Container>
    )
}


export default Mypost;