package com.kh.iMMUTABLE.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "admin")
@Getter @Setter @ToString
public class Admin {
    @Id
    @Column(name = "admin_id_num")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int adminIdNum;

    @Column(nullable = false)
    private String adminId;

    @Column(nullable = false)
    private String adminPw;
}
