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
    private int user_id;
    // 회원번호 생성 조건
    private String user_email; // 회원이메일
    private String user_pwd; // 비밀번호
    private String user_name; // 이름
    private String user_addr; // 주소
    private String user_phone; // 폰 번호
    private LocalDateTime user_date; // 가입일
    private String user_img; // 회원 이미지
}
