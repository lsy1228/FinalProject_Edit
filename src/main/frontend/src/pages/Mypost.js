import React, { useEffect, useState } from "react";
import styled from "styled-components";
import MyPageHeader from "../shopPage/MypageHeader";
import { Axios, AxiosError } from "axios";
import AxiosFinal from "../api/AxiosFinal";
import EditQnaModal from "./EditQnaModal";
import Pagenation from "./Pagenation";
import ReviewPagenation from "./Pagenation";

const Container = styled.div`
    width: 100%;
    height: 100vh;
    display: flex;
    flex-direction: column;
    @media (max-width: 390px) {
        width: 100%;
    }
`

const InnerContainer = styled.div`
    width: 100%;
    height: 100%;
    display: flex;
    flex-direction: column;
`;


const Review = styled.div`
    display: flex;
    flex-direction: column;
    width: 100%;
    height: auto;
    padding-bottom: 20px;
    .review {
        margin: 0 40px;
        margin-top: 20px;
        .reviewBoard {
            height: 20px;
            font-size: 14px;
            font-weight: bold;
        }
    }
    @media (max-width: 390px) {
        height: fit-content;
        padding-bottom: 0;
        .review {
            margin: 0;
        }
    }
`;


const ReviewTable = styled.table`
    width: 100%;
    height: fit-content;
    margin-bottom: 50px;
    tr {
        width: 100%;
        .Num {
            width: 5%;
        }
        .Product {
            width: 30%;
        }
        .Title {
            width: 35%;
        }
        .User, .Date, .Delete {
            width: 10%;
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
    button {
        background-color: white;
        color: black;
        border: none;
        &:hover {
            color: gray;
            cursor: pointer;
        }
    }
    .product {
        display: flex;
        justify-content: space-evenly;
        img {
            width: 50px;
            height: 50px;
            object-fit: contain;
        }
    }
    .reviewContent {
        font-size: 14px;
        text-align: left;
        background-color: whitesmoke;
        .content {
            margin: 0 60px;
        }
        img {
            width: 500px;
            height: auto;
            margin: 10px 30px 0 30px;
            padding: 10px 30px;
        }
    }
    .noReview {
        padding: 50px 0;
    }
    @media (max-width: 390px) {
        height: fit-content;
        tr {
            .Num, .num, .User, .user{
                display: none;
            }
            .Product, .Title, .Date, .Delete {
                width: 25%;
            }
            td {
                padding: 5px;
            }
        }
        .reviewContent {
            img {
                width: 370px;
                height: auto;
            }
        }
        .product {
            display: block;
        }
    }

`;

const QnA = styled.div`
    width: 100%;
    height: auto;
    padding-bottom: 20px;
    display: flex;
    flex-direction: column;
    .qna {
        margin: 0 40px;
        .qnaBoard {
            height: 20px;
            font-size: 14px;
            font-weight: bold;
        }
    }
    @media (max-width: 390px) {
        height: fit-content;
        padding-bottom: 0;
        .qna {
            margin: 0;
        }
    }
`;

const QnATable = styled.table`
    width: 100%;
    height: fit-content;
    margin-bottom: 50px;
    tr {
        width: 100%;
        th {
            padding-bottom: 10px;
        }
        .Num {
            width: 5%;
        }
        .Title {
            width: 55%;
        }
        .User, .Status, .Edit, .Delete {
            width: 10%;
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
    button {
        background-color: white;
        color: black;
        border: none;
        &:hover {
            color: gray;
            cursor: pointer;
        }
    }
    .qnaContent {
        font-size: 14px;
        background-color: whitesmoke;
        .content {
            margin: 0 60px;
            text-align: left;
        }
    }
    .qnaReply {
        background-color: whitesmoke;
        font-weight: bold;
        .reply {
            margin: 10px 30px 0 30px;
            padding: 10px 40px;
            text-align: left;
        }
    }
    .noQna {
        padding: 50px 0;
    }
    @media (max-width: 390px) {
        height: fit-content;
        tr {
            .Num, .num, .User, .user{
                display: none;
            }
            .Title {
                width: 35%;
            }
            .edit {
                white-space: nowrap;
            }
            td {
                padding: 5px;
            }
        }
    }
`;


const Mypost = () => {

    const id = window.localStorage.getItem("userIdSuv");
    const [qnaData, setQnaData] = useState([]);
    const [expanded, setExpanded] = useState([]);
    const [rvExpanded, setRvExpanded] = useState([]);
    const [modalOpen, setModalOpen] = useState(false);
    const [productId, setProductId] = useState("");
    const [qnaId, setQnaId] = useState("");
    const [deleteCount, setDeleteCount] = useState([]);
    const [reviewData, setReviewData] = useState([]);

    // qna pagenation
    const [limit, setLimit] = useState(5);  // 한 페이지에 표시할 아이템 수
    const [page, setPage] = useState(1);    // 페이지 번호
    const offset = (page - 1) * limit;      // 시작 인덱스

    // review pagenation
    const [reviewLimit, setReviewLimit] = useState(5);
    const [reviewPage, setReviewPage] = useState(1);
    const reviewOffset = (reviewPage - 1) * reviewLimit;

    useEffect(()=> {
        const viewMyQna = async() => {
            const rsp = await AxiosFinal.myQna(id);
            console.log(rsp.data);
            setQnaData(rsp.data);
        }
        viewMyQna();

        const viewMyReview = async() => {
            const rsp = await AxiosFinal.myReview(id);
            console.log(rsp.data);
            setReviewData(rsp.data);
        }
        viewMyReview();

    }, [deleteCount, modalOpen]);

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
        if(response.data) {
            alert("문의가 삭제되었습니다");
            setDeleteCount(preCount => preCount + 1);
        }
    }

    const deleteReview = async(reviewId) => {
        const response = await AxiosFinal.deleteMyReview(reviewId);
        if(response.data) {
            alert("리뷰가 삭제되었습니다");
            setDeleteCount(preCount => preCount + 1);
        }
    }

    const handleReview = async(index) => {
        if(rvExpanded.includes(index)) {
            setRvExpanded(rvExpanded.filter((row)=> row !== index));
        } else {
            setRvExpanded([...rvExpanded, index]);
        }
    }

    const sortedQnaData = qnaData.slice().reverse();
    const sortedReviewData = reviewData.slice().reverse();


    return(

        <Container>
                <MyPageHeader />
                <InnerContainer>
                    <Review>
                       <div className="review">
                            <div className="reviewBoard">Review</div>
                            <hr />
                            <ReviewTable>
                                <thead>
                                    <tr>
                                        <th className="Num">NUM</th>
                                        <th className="Product">PRODUCT</th>
                                        <th className="Title">TITLE</th>
                                        <th className="User">USER</th>
                                        <th className="Date">DATE</th>
                                        <th className="Delete">DELETE</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    {sortedReviewData.length > 0 ?
                                    (sortedReviewData.slice(reviewOffset, reviewOffset+reviewLimit).map((e, index) => (
                                    <React.Fragment key={index}>
                                    <tr>
                                        <td className="num">{reviewOffset+index+1}.</td>
                                        <td className="product">
                                            <img src={e.productImgFst} alt="" />
                                            <p>{e.productName}</p>
                                            <p>{e.productSize}</p>
                                        </td>
                                        <td className="title" onClick={()=>handleReview(index)}>{e.reviewTitle}</td>
                                        <td className="user">{e.userName}</td>
                                        <td className="date">{e.reviewDate}</td>
                                        <td className="btn"><button onClick={()=>deleteReview(e.reviewId)}>삭제</button></td>
                                    </tr>
                                    {rvExpanded.includes(index) && (
                                    <tr>
                                        <td colSpan={6} className="reviewContent">
                                            <p className="content">{e.reviewContent}</p>
                                            {e.reviewImg && (
                                                <img src={e.reviewImg} alt="" />
                                            )}
                                        </td>
                                    </tr>
                                    )}
                                    </React.Fragment>
                                    ))
                                    ) : (
                                        <tr>
                                            <td className="noReview" colSpan={6}>리뷰 내역이 없습니다.</td>
                                        </tr>
                                    )
                                    }
                                </tbody>
                            </ReviewTable>
                       </div>
                    </Review>
                    <ReviewPagenation
                        total={reviewData.length}
                        limit={reviewLimit}
                        page={reviewPage}
                        setPage={setReviewPage}
                        />
                    <QnA>
                        <div className="qna">
                            <div className="qnaBoard">QnA</div>
                            <hr />
                            <QnATable>
                                <thead>
                                    <tr>
                                        <th className="Num">NUM</th>
                                        <th className="Title">TITLE</th>
                                        <th className="User">USER</th>
                                        <th className="Status">STATUS</th>
                                        <th className="Edit">EDIT</th>
                                        <th className="Delete">DELETE</th>
                                    </tr>
                                </thead>
                                <tbody>
                                {sortedQnaData.length > 0 ? (
                                sortedQnaData.slice(offset, offset + limit).map((e, index) => (
                                <React.Fragment key={index}>
                                <tr >
                                    <td className="num">{offset + index + 1}.</td>
                                    <td className="title" onClick={()=>handleQna(index)}>{e.qnaTitle}</td>
                                    <td className="user">{e.userName}</td>
                                    <td className="status" style={{fontWeight:"bold"}}>{e.qnaStatus === "HOLD" ? '답변대기' : '답변완료'}</td>
                                    <td className="btn">
                                    {e.qnaStatus === "HOLD" ?
                                        (<button onClick={() => editQna(e.productId, e.qnaId)}>수정</button>
                                        ) : (
                                            '불가'
                                        )}
                                    </td>
                                    <td className="Btn"><button onClick={()=>deleteQna(e.qnaId)}>삭제</button></td>
                                </tr>
                                {expanded.includes(index) && (
                                <tr>
                                    <td colSpan={6} className="qnaContent">
                                        <p className="content" >{e.qnaContent}</p>
                                        {e.reply &&
                                        <div className="qnaReply">
                                            <p className="reply">▶ {e.reply}</p>
                                        </div>
                                        }
                                    </td>
                                </tr>
                                )}
                                </React.Fragment>
                                ))
                                ) : (
                                    <tr>
                                        <td className="noQna" colSpan={6}>문의 내역이 없습니다.</td>
                                    </tr>
                                )}
                            </tbody>
                            {modalOpen && (
                                <EditQnaModal open={modalOpen} close={closeModal} productId={productId} qnaId={qnaId} />
                            )}
                            </QnATable>
                        </div>
                    </QnA>
                    <Pagenation
                        total={qnaData.length} // 전체 아이템 수
                        limit={limit}          // 페이지 당 아이템 수
                        page={page}            // 현재 페이지 번호
                        setPage={setPage}      // 페이지 번호를 변경
                            />
                </InnerContainer>

        </Container>
    )
}


export default Mypost;