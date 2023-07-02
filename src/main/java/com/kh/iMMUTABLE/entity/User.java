package com.kh.iMMUTABLE.entity;

import com.kh.iMMUTABLE.constant.Authority;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity // JPA에 Entity 클래스임을 알려줌, DB테이블로 만들어져야 할 클래스
@Table(name = "users")
@Getter @Setter @ToString
//기본 생성자 질문드리기
@NoArgsConstructor
public class User {
    @Id // 해당 필드가 primary key임을 지정
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long userId;
    // 회원번호 생성 조건
    @Column(nullable = false)
    private String userEmail; // 회원이메일

    @Column(nullable = false)
    private String userPwd; // 비밀번호
    @Column(nullable = false)
    private String userName; // 이름
    @Column(nullable = false)
    private String userAddr; // 주소
    @Column(nullable = false)
    private String userPhone; // 폰 번호
    private LocalDateTime userDate; // 가입일
    private String userImg; // 회원 이미지
    @Enumerated(EnumType.STRING)
    private Authority authority;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<Qna> qnas;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<Order> orders;
    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<Cart> carts;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<Like> likes;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<ChatList> chatLists;

    @Builder//빌더 패턴!!! 시큐리티쪽은 빌더 패턴을 많이 쓴다. 매개변수가 많을 때 순서 안지켜도 됨
    public User(long user, String email, String password, String name, Authority authority) {
        this.userId = user;
        this.userEmail = email;
        this.userPwd = password;
        this.userName = name;
        this.authority = authority;

    }


}