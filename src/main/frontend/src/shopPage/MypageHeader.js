import React from "react";
import styled from "styled-components";
import { useNavigate, Link } from "react-router-dom";


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

const IsLoginFalse = [
    { name : "login"}
  ]
  const IsLoginTrue = [
    { name : "logout"},
    { name : "mypage"},    
    { name : "cart"},    
    { name : "FAQ"}
  ]

const MyPageHeader = () => {

    const isLogin = window.localStorage.getItem("isLoginSuv");

    const navigate = useNavigate();
    const onChangePage=(e)=>{
        console.log(e);
        if(e==="cart"){
            navigate("/Cart");
        }
        else if (e==="FAQ") {
            navigate("/FAQ")
        }
        else if(e==="logout"){
            window.localStorage.setItem("isLoginSuv", "FALSE");
            window.localStorage.setItem("userIdSuv", "");
            navigate("/");
        }
        else if(e==="SHOP"){
            navigate("/Shop");
            console.log(e);
        }
        else if(e==="mypage"){
            navigate("/Mypage")
        }
    }

    return(
      <Container> 
        <Mainbody>
            <Head>
                <div className="nav">
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
            </Head>
        
            </Mainbody>
       
       </Container>  
       
     )
 };
 
 
 export default MyPageHeader;        