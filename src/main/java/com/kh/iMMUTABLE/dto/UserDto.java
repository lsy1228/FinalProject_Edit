package com.kh.iMMUTABLE.dto;

import com.kh.iMMUTABLE.constant.Authority;
import com.kh.iMMUTABLE.entity.Users;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class UserDto {
    private long userId;
    private String userEmail;
    private String userPwd;
    private String userName;
    private String userAddr;
    private String userPhone;
    private LocalDateTime userDate;
    private String userImg;



    @Enumerated(EnumType.STRING)
    private Authority authority;

}
