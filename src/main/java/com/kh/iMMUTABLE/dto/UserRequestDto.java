package com.kh.iMMUTABLE.dto;

import com.kh.iMMUTABLE.constant.Authority;
import com.kh.iMMUTABLE.entity.User;
import lombok.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRequestDto {
    private String userEmail;
    private String password;

//    public User toUser(PasswordEncoder passwordEncoder) {
//        return User.builder()
//                .email(userEmail)
//                .password(passwordEncoder.encode(password))
//                .authority(Authority.ROLE_USER)
//                .build();
//    }

}
