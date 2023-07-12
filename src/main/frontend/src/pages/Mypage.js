import React, {useState, useRef, useContext, useEffect} from "react";
import styled from "styled-components";
import { useNavigate, Link } from "react-router-dom";
import ModifyingInfo from "./ModifyingInfo";
import Secession from "./Secession";
import Mypost from "./Mypost"
import FAQ from "./FAQ"
import Wishlist from "./Wishlist"
import { UserContext } from "../context/UserInfo";
import AxiosFinal from "../api/AxiosFinal";



const Container = styled.div`
    width: 100%;
    height: 100vh;
    display: flex;
`

const TopButton = styled.button`
    border: none;
    background-color: white;
   
    &:hover{
        color: rgba(0,0,0,0.5);
    }
`  

const MainBody = styled.div`
    width: 100%;
    height: 100vh;
    display: flex;
    flex-direction: column;

`
const Head = styled.div`
    width: 100%;
    display: flex;
    
    a{
        text-decoration: none;
        color: black;
    }
    .nav{
        width: 100%;
        padding: 0 20px 0 10px;
        display: flex;
        justify-content: space-between;
    }
   
    .nav1{
        height: 70px;
        font-weight: bolder;
        font-size: 50px;
    }

    .nav2{
        display: flex;
        justify-content: center;
        align-items: center;
        color: rgb(100,100,100);
        font-size: 13px;
    }
`


const InnerContainer = styled.div`
    width: 100vw;
    height: 100%;
    display: flex;
    justify-content: center;
    align-items: center;
`


const Body = styled.div`
    display: flex;
    width: 1010px;
    flex-wrap: wrap;
    
    a{
 
        text-decoration: none;
        color: black;
        /* border: 1px solid black; */
    }
   
    .box{
        cursor: pointer;
        display: flex;
        width: 500px;
        height: 160px;
        border: 1px solid #ccc;
        align-items: center;
        justify-content: center;
        flex-direction: column;

        
        .title{
            margin-bottom: 10px;
            width: 100%;
            display: flex;
            font-size: 15px;
            font-weight: bolder;
            align-items: center;
            justify-content: center;
            /* border: 1px solid black; */
        }

        .tt1{
            color: #656165;
            font-size: 11px;
            margin-left: 20px;
        
        }

        .tt2{
            display: flex;
            align-items: center;
            justify-content: center;
            color: #a19aa2;
            font-size: 10px;
            /* border: 1px solid black; */
        }
    }

    @media only screen and (max-width: 1000px) {
            .box{
                height: 100px;
            }
    }

`

const Footer = styled.div`
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


//카트 영역
const CartToggle=styled.div`
    margin-top: 30px;
    width: 260px;
    display: flex;
    flex-direction: column;
    border: 1px solid #CCC;
    background-color: white;
    position: absolute;
    right: 2.8rem;
    top:3rem;
     z-index: 100;

    a{
    height: 40px;
    display: flex;
    justify-content: center;
    align-items: center;
    font-size: 11px;
    text-decoration: none;
    background-color: black;
    color: white;
    &:hover{
        background-color: #CCC;
        color: black;
    }
  }


  .cartToggleItem{
    width: 100%;
    height: 100px;
    border-bottom: 1px solid #CCC;
    display: flex;
    img{
        height: 100px;
    }
  }
  .itemInfo{
    width: 200px;
    font-size: 11px;
    display: flex;
    justify-content: center;
    flex-direction: column;
    align-items:center
  }
  .itemName{
    height: 20px;
    font-weight: bolder;

  }
  .deleteItem{
    border: none;
    background-color: white;
    cursor: pointer;
    color: #CCC;
    &:hover{
        color: black;
    }
  }
  .count{
    display: flex;
  }
  .plus,.minus{
    height: 13px;
    width: 10px;
    display: flex;
    justify-content: center;
    align-items: center;
    border: none;
    background-color: white;
    cursor: pointer;
    &:hover{
        color: white;
        background-color: black;
    }
  }
  .countbutton{
    display: flex;
    flex-direction: column;
  }
  input{
    width: 20px;
    height: 20px;
  }
  .itemPrice{
  }

`

  const CartList=styled.div`
    border-bottom: 1px solid #ccc;
    width: 100%;
    height: 100px;
    // overflow-y: scroll;
    ::-webkit-scrollbar {
    display: none;
    }
  `

const IsLoginFalse = [
    { name : "login"}
  ]
  const IsLoginTrue = [
    { name : "logout"}, 
    { name : "cart"},    
    { name : "FAQ"}
  ]



const Mypage = () =>{
    const [count, setCount] = useState([]);
    const[cartList, setCartList] = useState([]);

     //카트 토글 여는 컴포넌트
     const [openCart, setOpenCart] = useState(false);

    const nav = useNavigate();
    // const {isLogin, setIsLogin} = useContext(UserContext);

    const isLogin = window.localStorage.getItem("isLoginSuv");
    const id = window.localStorage.getItem("userIdSuv");
    console.log(id);


    const navigate = useNavigate();
    const onChangePage=(e)=>{
       if(e==="logout"){
            window.localStorage.setItem("isLoginSuv", "FALSE");
            window.localStorage.setItem("userIdSuv", "");
            nav("/");
        }
        else if (e==="FAQ") {
            navigate("/FAQ")
        }
        else if (e==="cart") {
            //카트 창 열리기
            setOpenCart(!openCart);
       }
    }
    
    const clickOrder = () => {
        nav("/Order");
    }


       useEffect(() => {
            const getCartList = async()=>{
                if(!id) {
                    return;
                }
                const rsp = await AxiosFinal.cartItemList(id);
                if(rsp.status === 200) {
                    const copyCnt = rsp.data.map(e => e.count);
                    setCartList(rsp.data);
                    console.log(rsp.data);
                    setCount(copyCnt);
                }
            };
            getCartList();
        }, []);

        const updateCount = async (count, cartList, idx) => {
            const response = await AxiosFinal.updateCount( count, cartList, idx);
            const result = response.data;
            console.log(result)
        };
        console.log(cartList)



        // 수량 증가
        const countPlus = (idx) => {
            console.log(idx);
            setCount(prevCount => {
                const newCount = [...prevCount];
                newCount[idx] += 1;
                updateCount(newCount[idx], cartList, idx);
                return newCount;
            });
        };


        // 수량 감소
        const countMinus = (idx) => {
            setCount(prevCount => {
                const newCount = [...prevCount];
                if (newCount[idx] > 1) {
                    newCount[idx] -= 1;
                    updateCount(newCount[idx], cartList, idx);
                }
                return newCount;
            });
        };



        // 카트 아이템 삭제
        const deleteCartItem = async(id, index) => {
            console.log(index);
            console.log("삭제");
            const cartItemId =  cartList[index].cartItemId;
    console.log(" ::"  + cartItemId);
            const rsp = await AxiosFinal.deleteCartItem(id, cartItemId);
            setCartList(rsp.data);
        }

 

    return(
        <Container>
         {openCart &&
                                  <CartToggle >
                                     {cartList && cartList.map((e, index)=>(
                                          <CartList  key={e.cartItemId}>
                                              <div className="cartToggleItem">
                                                  <div className="itemImg">
                                                      <img src={e.productImgFst} />
                                                  </div>
                                                  <div className="itemInfo">
                                                      <div className="itemName">
                                                      {e.productName}
                                                      </div>
                                                      <div  className="count">
                                                          <input type="text" Value={count[index]}/>
                                                          <div className="countbutton">
                                                          <button className="plus" onClick={()=>countPlus(index)}>∧</button>
                                                                  <button className="minus" onClick={()=>countMinus(index)}>∨</button>
                                                          </div>
                                                      </div>
                                                      <div className="itemPrice">
                                                      {(e.setOriginProductPrice * count[index]).toLocaleString()} won
                                                      </div>
                                                  </div>
                                                  <button className="deleteItem"  onClick={() => deleteCartItem(id, index)}>x</button>
                                              </div>
                                           </CartList>
                                     ))}
                                              <Link to="/Cart">장바구니</Link>
                                      </CartToggle>
                                        }
            <MainBody>
            <Head>
                <div className="nav">
                <Link to="/"><div className="nav1" >
                     iMMUTABLE
                    </div></Link>
                    <div className="nav2">
                    {IsLoginFalse.map(s=>( isLogin==="FALSE" &&
                                        <TopButton key={s.name}>
                                            <Link to="/Login">{s.name}</Link>
                                        </TopButton>
                                    ))}
                           {IsLoginTrue.map(s=>( isLogin==="TRUE" &&
                                        <TopButton key={s.name} onClick={()=>onChangePage(s.name)}>
                                            {s.name}
                                        </TopButton>
                                    ))}
                    </div>
                </div>
            </Head>
            <InnerContainer>   
                <Body>
                    <div className="box" onClick={clickOrder}>
                       <div className="title">ORDER <div className="tt1"> 주문내역조회</div></div>
                       <div className="tt2">
                        고객님께서 주문하신 상품의 주문내역을 확인할 수 있습니다. 
                       </div>
                    </div>
                    <div className="box">
                    <Link to="/ModifyingInfo">
                        <div className="title">PROFILE <div className="tt1"> 회원 정보</div></div>
                       <div className="tt2">
                        회원이신 고객님의 개인정보 및 수정하는 공간입니다.
                       </div> 
                       <div className="tt2">
                       개인정보를 최신 정보로 유지하시면 보다 간편히 쇼핑을 즐길실 수 있습니다.
                       </div>
                       </Link>
                    </div>
                    
                    <div className="box">
                        <Link to="/Wishlist">
                            <div className="title"> WISHLSIT <div className="tt1"> 관심 상품</div></div>
                            <div className="tt2">
                            관심상품으로 등록하신 상품의 목록을 보여드립니다.
                            </div>
                       </Link>
                    </div>

                    <div className="box">
                        <Link to="/Mypost">
                        <div className="title">BOARD<div className="tt1"> 게시물 관리</div></div>
                        <div className="tt2">
                            고객님께서 작성하신 게시물을 관리하는 공간입니다. 
                        </div>
                        <div className="tt2">
                            고객님께서 작성하신 글을 한눈에 관리하실 수 있습니다.
                        </div>
                        </Link>
                    </div>
                    <div className="box">
                        <Link to="/FAQ">
                            <div className="title">NOTICE <div className="tt1"> 공지사항</div></div>
                            <div className="tt2">
                                    공지사항 및 자주 묻는 질문을 보실 수 있는 공간입니다.
                            </div>
                            <div className="tt2">
                                    궁금하신 내용을 한눈에 보실 수 있습니다.
                            </div>
                        </Link>
                    </div>
                   
                    
                    <div className="box">
                        <Link to="/Secession">
                            <div className="title">SECESSION<div className="tt1"> 회원탈퇴</div></div>
                            <div className="tt2">
                                더 이상 이용을 원치 않을 경우 회원 탈퇴를 하실 수 있습니다.
                            </div>   
                       </Link>
                    </div>
                
                </Body>
            </InnerContainer>
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
            </MainBody>
        </Container>
    )
}


export default Mypage;