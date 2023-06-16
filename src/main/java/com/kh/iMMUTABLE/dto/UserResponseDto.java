package com.kh.iMMUTABLE.dto;

import com.kh.iMMUTABLE.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponseDto {
    private String email;
    private String name;

    public static UserResponseDto of(User user) {
        return UserResponseDto.builder()
                .email(user.getUserEmail())
                .name(user.getUserName())
                .build();
    }
}
