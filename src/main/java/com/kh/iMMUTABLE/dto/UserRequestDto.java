package com.kh.iMMUTABLE.dto;

import com.kh.iMMUTABLE.constant.Authority;
import com.kh.iMMUTABLE.entity.Users;
import lombok.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRequestDto {
    private String userEmail;
    private String userPwd;
    private String userName;
    private String userAddr;
    private String userPhone;

    public Users toUser(PasswordEncoder passwordEncoder) {
        System.out.println("리퀘스트 : " + userEmail);
        System.out.println("리퀘스트 : " + userPwd);
        return Users.builder()
                .userEmail(userEmail)
                .userPwd(passwordEncoder.encode(userPwd))
                .userAuth(Authority.ROLE_ADMIN)
                .build();
    }

  public Users toMember(PasswordEncoder passwordEncoder) {
        return Users.builder()
                .userEmail(userEmail)
                .userPwd(passwordEncoder.encode(userPwd))
                .userName(userName)
                .userDate(LocalDateTime.now())
                .userPhone(userPhone)
                .userAddr(userAddr)
                .userAuth(Authority.ROLE_USER)
                .build();
  }



    public UsernamePasswordAuthenticationToken toAuthentication() {
        System.out.println("RequestDto 접속 완료");
        return new UsernamePasswordAuthenticationToken(userEmail, userPwd);
    }

}
