package com.kh.iMMUTABLE.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "size")
@Getter @Setter @ToString
public class Size {
    @Id
    @Column(name = "size_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int size_id; // 사이즈 아이디
    private String size_name; // 사이즈명
    private int size_measures; // 사이즈값
}
