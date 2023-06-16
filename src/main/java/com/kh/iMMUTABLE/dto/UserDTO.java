package com.kh.iMMUTABLE.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class UserDTO {
    private int userId;
    private String userEmail;
    private String userPwd;
    private String userName;
    private String userAddr;
    private String userPhone;
    private LocalDateTime userDate;
    private String userImg;

}
