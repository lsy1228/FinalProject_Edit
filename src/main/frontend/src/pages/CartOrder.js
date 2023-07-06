import React, { useContext, useState } from "react";
import styled from "styled-components";
import { UserContext } from "../context/UserInfo";
import PopupPostCode from "../api/PopupPostCode";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import PayLogo from "../img/kakaoPayLogo.png"


const Container = styled.div`
    width: 100%;
    height: 100vh;
    display: flex;
    justify-content: center;
    align-items: center;
    flex-direction: column;

    .header {
        margin-bottom: 20px;
        font-weight: bold;
        font-size: 50px;
    }
    hr {
        width: 95%;
    }
    .billingInput {
        width: 395px;
        height: 40px;
        margin-top: 20px;
        font-size: 10px;
        border: 1px solid #ccc;
            &::placeholder {
                padding: 5px;
                font-size: 10px;
            };
    }
    .addrBtn {
        text-align: right;
        width: 50px;   
        font-size: 10px;
        background-color: white;
        border: none;
        &:hover{
            color: #CCC;
        }
        margin-bottom : 10px
    }
    .hint {
        width: 50%;
        display: flex;
        margin: 5px 0px 0px 8px;
        justify-content:right;
        align-items:center;
        font-size: 12px;
        color: #999;
    }
    .item {
        font-size: 13px;
        font-weight: bold;
    }
    .payBtn {
        margin-top: 10px;
        width: 400px;   
        height: 40px;
        background-color: white;
        border: 1px solid black;
        &:hover{
            color: #CCC;
            background-color: black;
        }
        margin-bottom : 10px
    }
    a {
        text-decoration: none;
        font-size: 13px;
        color: black;
        justify-content: center;
        text-align: center;
        line-height: 40px;
    }
`;

const Footer = styled.div`
    margin-top: 20px;
    display: flex;
    align-items: center;
    justify-content: center;
    
    
    .fotbox{
        height: 100px;
    }

    .tt1{
        display: flex;
        align-items: center;
        justify-content: center;
        color: #8b9192;
        font-size: 14px;
        font-weight: 600;
    }


    .tt2{
        display: flex;
        align-items: center;
        justify-content: center;
        color: #c1c2c8;
        font-size: 12px;
    }

`

const CartOrder = () => {

    const navigate = useNavigate();

    const [inputName, setInputName] = useState("");
    const [inputEmail, setInputEmail] = useState("");
    const [inputPhone, setInputPhone] = useState("");

    const [emailMessage, setEmailMessage] = useState("")
    const [phoneMessage, setPhoneMessage] = useState("");

    const [isName, setIsName] = useState(false);
    const [isEmail, setIsEmail] = useState(false);
    const [isPhone, setIsPhone] = useState(false);

    //저장된 주소값을 설정하여 주소는 받아온다.
    const context = useContext(UserContext);
    const {addr, setAddr} = context;

     //주소찾기 영역
    const [isPopupOpen, setIsPopupOpen] = useState(false);

    // 팝업창 열기
    const openPostCode = () => {
        setIsPopupOpen(true);
    }
     
	// 팝업창 닫기
    const closePostCode = (e) => {
        setIsPopupOpen(false);
    }

    const onClickHeader = () => {
        navigate("/");
    }

    // 이름 정규식
    const onChangeName = (e) => {
        const inputNameRegex = /^[가-힣]{2,5}$/ // 이름은 한글로만 2자리 이상 5자리 미만
        const nameCurrent = e.target.value;
        setInputName(nameCurrent);
        if (!inputNameRegex.test(nameCurrent)) { // 이름은 잘못 입력 되었을 때
            setIsName(false);
        } else {
            setIsName(true);
        };
    };

    //메일 정규식
    const onChangeMail = (e) => {
        const inputEmailRegex = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/
        const emailCurrent = e.target.value;
        setInputEmail(emailCurrent);
        if (inputEmailRegex.test(emailCurrent)) { // 이메일 입력이 잘 못 되었을 때
            setEmailMessage('올바른 이메일 형식입니다.')
            setIsEmail(true);
        } else {
            setEmailMessage('이메일 형식이 올바르지 않습니다.')
            setIsEmail(false)
        } 
    }

    //전화번호 정규식
    const onChangePhone = (e) => {
        const inputPhoneRegex = /^\d{3}\d{4}\d{4}$/;
        const phoneCurrent = e.target.value;
        setInputPhone(phoneCurrent);
        if (!inputPhoneRegex.test(e.target.value)) { // 전화번호 입력이 잘 못 되었을 때
            setPhoneMessage('전화번호 형식이 올바르지 않습니다.');
            setIsPhone(false);
        } else {
            setPhoneMessage('올바른 전화번호 형식입니다.');
            setIsPhone(true);
        } 
    };

    //컨텍스트에 카카오결제 페이지를 저장한다
    const {setPayUrl,payUrl,totalPrice,setTotalPrice} = context; 

    //카카오 결제로 들어가는 axios
    const handlePayment1m = async () => {
        console.log(payUrl);
        // setTotalPrice(10000);
          try {
            const response = await axios.post(
              'https://kapi.kakao.com/v1/payment/ready',    
              {
                cid: 'TC0ONETIME', // 가맹점 CID
                partner_order_id: 'partner_order_id', // 가맹점 주문번호
                partner_user_id: 'partner_user_id', // 가맹점 회원 ID
                item_name: 'iMMUTABLE 결제창',
                quantity: 30,
                total_amount: 1000, // 결제 금액
                tax_free_amount: 0,
                approval_url: 'http://localhost:3000/OrderComplete', // 결제 성공 시 리다이렉트할 URL
                cancel_url: 'http://localhost:3000/CartOrder', // 결제 취소 시 리다이렉트할 URL
                fail_url: 'http://localhost:3000/CartOrder', // 결제 실패 시 리다이렉트할 URL
              },   
              {
                headers: {
                  Authorization: `KakaoAK 3923bafc46ca0d258af73bbf339e36d4`, // admin key
                  "Content-type": `application/x-www-form-urlencoded;charset=utf-8`
                },
              }
            );
            console.log(response.data); // 결제 요청 결과 확인
            console.log(response.data.next_redirect_pc_url);
            console.log(response.data.tid);
            window.localStorage.setItem("tid", response.data.tid);
            setPayUrl(response.data.next_redirect_pc_url);    
          } catch (error) {
            console.error("에러입니다1.");
            console.error(error);
          }
      };

    return(
        <Container>
            <div className="header" onClick={onClickHeader}>iMMUTABLE</div>
            <p className="item">ORDER SUMMARY</p>
            <hr />
            <div className="item">BILLING ADDRESS</div>
            <hr />
            <input type="name" className="billingInput" placeholder="NAME" onChange={onChangeName}/>
            <input type="email" className="billingInput" placeholder="EMAIL" onChange={onChangeMail}/>
            <div className="hint">
                        {inputEmail.length > 0 && (
                        <span className={`message ${isEmail ? 'success' : 'error'}`}>{emailMessage}</span>)}
                    </div>
            <input type="address" className="billingInput" value={addr} placeholder="ADDRESS"/>
            <button className="addrBtn" onClick={openPostCode}>FIND</button>
            <div id='popupDom'>
                            {isPopupOpen && (                    
                                    <PopupPostCode onClose={closePostCode} />
                            )} 
                        </div>
            <input type="phone" className="billingInput" placeholder="PHONE" onChange={onChangePhone}/>
            <div className="hint">
                            {inputPhone.length > 0 && (
                            <span className={`message ${isPhone ? 'success' : 'error'}`}>{phoneMessage}</span>)}
                    </div>

            <div className="item">PAYMENT</div>
            <hr />
            <a href={payUrl} target="blank"className="payBtn" onClick={handlePayment1m}>PAY AND PLACE ORDER</a>
            <Footer>
                <div className="fotbox">
              <div className="tt1">
              iMMUTABLE & Q / A
              </div>
              <div className="tt2">
              MON - FRI : AM 10:00 ~ PM 05:00 LUNCH TIME : PM 12:00 ~ PM 01:00 SAT.SUN.HOLIDAY CLOSED
                </div>
                <div className="tt2">
                카카오뱅크 : 3333-333-3333 예금주 : iMMUTABLE
                </div>
                </div>
            </Footer>
        </Container>
    );
};

export default CartOrder;