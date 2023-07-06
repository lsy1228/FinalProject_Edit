import React, {useState, useContext} from "react";
import styled from "styled-components";
import DropdownMenu from "./DropdownMenu";
import { useNavigate, Link } from "react-router-dom";
import DropFiter from "./DropFiter";
import test from "../img/test.webp"
import { UserContext } from "../context/UserInfo"; 

const Container = styled.div`
    width: 100%;
    display: flex;    
`

const Mainbody=styled.div`
    width: 100%;
    margin: 0px 40px 0px 40px;
    `

const TopButton = styled.button`
    border: none;
    background-color: white;
    &:hover{
        color: rgba(0,0,0,0.5);
    }
`    

const Head = styled.div`
    width: 100%;
    height: 70px;
    display: flex;
    flex-direction: column;

    a{
        text-decoration: none;
        color: black;
    }



    .nav{
        width: 100%;
        display: flex;
        justify-content: space-between;
        flex-direction: row;
        
    }

    .nav1{
        align-items: center;
        justify-content: center;
        display: flex;
        font-size: 13px;    
        cursor: pointer;
        margin-top: auto;
        &:hover{
            color: rgba(0,0,0,0.5);
        }
        
    }

    .nav2{
        
        margin-left: 20%;
        width: 300px;
        display: flex;
        justify-content: center;
        align-items: center;
        font-weight: bolder;
        font-size: 50px;
    }

    .nav3{

        display: flex;
        width: 300px;
        justify-content: flex-end;
        font-size: 13px;
        margin-top: auto;
        div{
            margin-left: 20px;
        }
    }

`


//카트 영역
const CartToggle=styled.div`
    margin-top: 30px;
    width: 260px;
    height: 400px;
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
    width: 100%;
    height: 360px;
    overflow-y: scroll;
    ::-webkit-scrollbar {
    display: none;
    }
  `




const MenuList = [
    {name : "iMMUTABLE"},
]

const IsLoginFalse = [
    { name : "login"}
  ]
  const IsLoginTrue = [
    { name : "logout"},
    { name : "mypage"},    
    { name : "cart"},    
    { name : "FAQ"}
  ]

  


const Header = ({ onClick }) => {
    const [selectedMenu, setSelectedMenu] = useState(null)
 
    const [isMenuClicked, setIsMenuClicked] = useState(false);
    const [isFilterOpen, setIsFilterOpen] = useState(false);
    // const {isLogin, setIsLogin} = useContext(UserContext);
     //카트 토글 여는 컴포넌트
     const [openCart, setOpenCart] = useState(false);

    const isLogin = window.localStorage.getItem("isLoginSuv");

    const navigate = useNavigate();
    const onChangePage=(e)=>{
        console.log(e);
        if(e==="cart"){
            //카트 창 열리기
            setOpenCart(!openCart);
        }
        else if (e==="FAQ") {
            navigate("/FAQ")
        }
        else if(e==="logout"){
            window.localStorage.setItem("isLoginSuv", "FALSE");
            window.localStorage.setItem("userIdSuv", "");
            window.location.reload();
        }
        else if(e==="SHOP"){
            navigate("/Shop");
            console.log(e);
        }
        else if(e==="mypage"){
            navigate("/Mypage")
        }
    }

    
    const handleMenuClick = (menuName) => {
       
        if (selectedMenu === menuName) {
          setSelectedMenu(null) // 이미 선택된 메뉴를 다시 클릭하면 닫힙니다.
          setIsMenuClicked(true)
        } else {
          setSelectedMenu(menuName);
          setIsMenuClicked(false)
        }
      };

      

      const handleFilter = () => {
        setIsFilterOpen(!isFilterOpen);
      };

      //수량 임의 설정
    const[number,setNumber]=useState(1);
    const countPlus=()=>{
        setNumber(number+1);
    }
    //상품 수량 줄이는 버튼
    const countMinus=()=>{
        setNumber(number-1);
        if(number <= 1){
            setNumber(1);
        }
    }

    

    return(
      <Container> 
            {openCart && <CartToggle>
                        <CartList>
                            <div className="cartToggleItem">
                                <div className="itemImg">
                                    <img src={test}/>
                                </div>
                                <div className="itemInfo">
                                    <div className="itemName">
                                    Sweat Shirt
                                    </div>
                                    <div className="count">
                                        <input type="text" value={number}/>
                                        <div className="countbutton">
                                        <button className="plus" onClick={countPlus}>∧</button>
                                        <button className="minus" onClick={countMinus}>∨</button>
                                        </div>
                                    </div>    
                                    <div className="itemPrice">
                                        1,043,000
                                    </div>
                                </div>
                                <button className="deleteItem">x</button>
                            </div>
                         </CartList>
                            <Link to="/Cart">장바구니</Link>
                    </CartToggle>}
        <Mainbody>
       
            <Head>
                <div className="nav">
                    <div className="nav1"   onClick={onClick}>
                        {MenuList.map(v=>(
                            <div key={v.name}
                            onClick={() => handleMenuClick(v.name)} 
                            className={selectedMenu === v.name ? "active" : ""}>        
                            {v.name} 
                          </div>
                        ))}
                    </div>
                    <a href="/"><div className="nav2" >
                     iMMUTABLE
                    </div></a>
                    <div className="nav3">
                    {isLogin==="FALSE" && IsLoginFalse.map(s=> (
                                        <TopButton key={s.name}>
                                            <Link to="/Login">{s.name}</Link>
                                        </TopButton>
                                    ))}
                          {isLogin==="TRUE" && IsLoginTrue.map(s=> ( 
                                        <TopButton key={s.name} onClick={()=>onChangePage(s.name)}>
                                            {s.name}
                                        </TopButton>
                                    ))}
                            
                    </div>
                </div>
                {selectedMenu === "iMMUTABLE" && <DropdownMenu  />} 
            </Head>
        
            </Mainbody>
       
       </Container>  
       
     )
 };
 
 
 export default Header;        