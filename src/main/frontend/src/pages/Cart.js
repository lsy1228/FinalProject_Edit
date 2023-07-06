import React, { useEffect, useState } from "react";
import styled from "styled-components";
import { Link, useNavigate } from "react-router-dom";
import PopupPostCode from "../api/PopupPostCode";
import AxiosFinal from "../api/AxiosFinal";
import { isCompositeComponent } from "react-dom/test-utils";

const Container=styled.div`
    width: 100%;
    height: 100vh;
    display: flex;
    justify-content: center;
    align-items: center;
    flex-direction: column;
    .contTop{
        width: 80%;
        display: flex;
        flex-direction: row;
    }
    .chkBtn{
        width: 100%;
        height: 30px;
        display: flex;
        justify-content: flex-start;
    }
    .chkAll,.chkDel{
        margin-right: 5px;
        height: 25px;
        border-radius: 2px;
        border: 1px solid #CCC;
        font-size: 11px;
        color: rgb(50,50,50);
        background-color: white;
        &:hover{
            background-color: black;
            color: white;
        }
    }
    a{
        display: flex;
        justify-content: flex-end;
        align-items: center;
        text-decoration: none;
        color:rgb(50,50,50);
        font-size: 12px;
    }
`
const MainBody=styled.div`
    border-top: 1px solid #ccc;
    display: flex;
    justify-content: center;
    align-items: center;
    flex-direction: column;
    width: 80%;
    height: 90%;
    .paymentBtn{
        margin: 10px 0 0 0;
        width: 100%;
        height: 70px;
        font-size: 12px;
        color: rgb(50,50,50);
        border: 1px solid #CCC;
        background-color: white;
        &:hover{
            background-color: black;
            color: white;
        }
    }
`
const Products = styled.div`
    width: 100%;
    height: 100%;
    overflow-y:scroll;
    ::-webkit-scrollbar {
    display: none;
    }

`
const Products_in=styled.div`
  
    width: 100%;    
    height: 110px;
    display: flex;
    justify-content: space-between;
    align-items: center;
    flex-direction: row;
    &:hover{
        background-color: rgba(0,0,0,0.1);
    }
    .checkBox{
        width: 20px;
        height: 100%;
        display: flex;
        justify-content: center;
        align-items: center;
        margin-left: 10px;
    }
    .product_image{
        display: flex;
        justify-content: center;
        align-items: center;
        width: 100px;
        height: 100%;
    }
    .itemName{
        display: flex;
        justify-content: flex-start;
        align-items: center;
        width: 40%;
        height: 100%;
        padding: 0 0 0 5px;
        font-size: 12px;
    }
    .count{
        display: flex;
        justify-content: center;
        align-items: center;
        width: 50px;
    }
    input{
        width: 20px;
        height: 20px;
    }
    .countbutton{
        display: flex;
        flex-direction: column;
    }
    .plus,.minus{
        width: 15px;
        height: 15px;
        display: flex;
        align-items: center;
        justify-content: center;
        border: none;
        background-color: white;
        &:hover{
            background-color: black;
            color: white;
        }
    }
    .price{
        width: 120px;
        font-size: 12px;
    }
    img{
        width: 80px;
    }
`
const Total=styled.div`
    display: flex;
    justify-content: space-evenly;
    align-items: center;
    width: 100%;
    border-top: 1px solid #ccc;
    border-bottom: 1px solid #ccc;
    height: 80px;
    font-size: 12px;
`

const OrderInfo=styled.div`
    display: flex;
    flex-direction: row;    
    align-items: center;
    width: 100%;
    height: 300px;
    border-bottom: 1px solid #ccc;
    color:rgba(80,80,80);
    font-size: 12px;
    .shippingInfo{
        padding: 0 0 0 10px;
        width: 40%;
        height: 100%;
        display: flex;
        justify-content: center;
        align-items: flex-start;
        flex-direction: column;
        .name{
            margin: 20px 0 0 0;
        }
        .addr{
            margin: 10px 0 10px 0;
        }
    }
    .addrChange{
        font-size: 12px;
        color: rgb(50,50,50);
        background-color: white;
        border: 1px solid #CCC;
        width: 120px;
        height: 40px;
        border-radius: 4px;
        &:hover{
            background-color: black;
            color: white;
        }
    }
`


const Cart=()=>{

    const navigate = useNavigate();

    const[cartList, setCartList] = useState([]); 

    // 가격 임의 설정
    const price = 1000;
    // 토탈 가격 임의 설정
    const[totalPrice,setTotalPrice]=useState(1);


    // const countUpChange= async(cartItemId,count)=> {
    //     //CartItem 내의 수량이랑 값만 업데이트 되면 됨
    //     console.log("==> cartItemId : " + cartItemId);
    //     console.log("==> count : " + count);

     
    //         //cartItemID 를 넘겨줌
    //         const rsp = await AxiosFinal.updateCartItem();

    // }

    // const countDownChange= async(cartItemId)=> {
    //     //CartItem 내의 수량이랑 값만 업데이트 되면 됨
    //     console.log("==> cartItemId : " + cartItemId);
    //     //setNumber(number-1);
    //     // if(number <= 1){
    //     //     setNumber(1);
    //     // }
    //     //setTotalPrice(price*number);
    //     const getCartList = async()=>{
    //         //cartItemID 를 넘겨줌
    //         const rsp = await AxiosFinal.updateCartItem();
    //      };
    // }


    // 주문창으로 이동
    const onClickCartOrder = async() => {
        const getList = await AxiosFinal.getCartList()
        navigate("/CartOrder");
    }
    

    const [count,setCount] = useState([]);
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

    const id = window.localStorage.getItem("userIdSuv");
    // console.log("=Cart== > id " + id)
    useEffect(() => {
        const getCartList = async()=>{
            if(!id) {
                return;
            }
            const rsp = await AxiosFinal.cartItemList(id);
            if(rsp.status === 200) {
                const copyCnt = rsp.data.map(e => e.count);
                setCartList(rsp.data);
                // setCount(copyCnt);
            } 
        };
        getCartList();
    }, []);
 
       
        
        // 수량을 증가
        // const countPlus = (idx) => { 
        //     setCount(prevCount => {
        //       const newCount = [...prevCount];
        //       newCount[idx] += 1;
 
        //       return newCount;
        //     });
        //   };


        // 수량을 감소
        // const countMinus = (idx) => {
        //     setCount(prevCount => {
        //       const newCount = [...prevCount];
        //       if (newCount[idx] > 1) {
        //         newCount[idx] -= 1;
       
        //       }
        //       return newCount;
        //     });
        //   };
          
          

    return(
        <Container>
            <div className="contTop">
                <div className="chkBtn">
                    <button className="chkAll">전체 선택</button>       
                    <button className="chkDel">선택 삭제</button>   
                </div>
                <Link to="/">home</Link>   
            </div>               
            <MainBody>
                <Products>
                {cartList && cartList.map((e, index)=>(
                <Products_in key={e.cartItemId}>
                        <div className="checkBox">
                            <input type="checkbox"/></div>
                        <div className="product_image">
                            <img src ={e.productImgFst} /></div>                        
                        <div className="itemName">{e.productName}</div>
                            {/* <div className="count">
                                            <input type="text" Value={count[index]}/>
                                            <div className="countbutton">
                                                <button className="plus" onClick={()=>countPlus(index)}>∧</button>
                                                <button className="minus" onClick={()=>countMinus(index)}>∨</button>
                                            </div>
                                        </div>    
                        <div className="price">{(count[index] * e.productPrice).toLocaleString()} won</div> */}
                    </Products_in>  
                            ))}
                </Products>     
                <OrderInfo>                    
                    <div className="shippingInfo">
                            ACCOUNT DETAIL
                            <div className="name">leetaetae</div>
                            <div className="addr">46, Samosil-gil,Nam-myeon, Gimcheon-si, <br/> Gyeongsangbuk-do, Republic of Korea</div>
                            <div className="phone">+821010004000</div>
                    </div>
                    <button className="addrChange" onClick={openPostCode}>배송지 변경</button>
                    {isPopupOpen && (                    
                        <PopupPostCode onClose={closePostCode} />
                 )} 
                </OrderInfo>
                <button className="paymentBtn" onClick={onClickCartOrder}>{totalPrice-6000}won payment</button>
            </MainBody>        
        </Container>
    );
};

export default Cart;