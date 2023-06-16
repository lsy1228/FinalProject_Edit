package com.kh.iMMUTABLE.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "admin")
@Getter @Setter @ToString
public class Admin {
    @Id
    @Column(name = "admin_id_num")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @OnDelete(action = OnDeleteAction.CASCADE) // 조인된 테이블도 같이 드롭해줌
    private int adminIdNum;

    @Column(nullable = false)
    private String adminId;

    @Column(nullable = false)
    private String adminPw;
}
