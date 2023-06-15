package com.kh.iMMUTABLE.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity // JPA에 Entity 클래스임을 알려줌, DB테이블로 만들어져야 할 클래스
@Table(name = "users")
@Getter @Setter @ToString
public class User {
    @Id // 해당 필드가 primary key임을 지정
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private int userId;
    // 회원번호 생성 조건
    private String userEmail; // 회원이메일
    private String userPwd; // 비밀번호
    private String userName; // 이름
    private String userAddr; // 주소
    private String userPhone; // 폰 번호
    private LocalDateTime userDate; // 가입일
    private String userImg; // 회원 이미지
}
