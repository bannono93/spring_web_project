package com.zerock.ex2.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="tbl_memo")
@ToString
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Memo {

    //GenerationType.AUTO = JPA 구현체가 생성하는 방식
    //GenerationType.IDENTITY = 데이터베이스가 키생성 결정 (MySql,MariaDB의 경우 auto Increment 방식)
    //GenerationType.SEQUENCE = 데이터베이스의 sequence를 이용해서 키를 생성 (@SequenceGenerator())
    //GenerationType.TABLE = 키 생성 전용 테이블을 생성해서 키 생성 (@TableGenerator())

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mno;

    @Column(length = 200, nullable = false)
    private String memoText;


}
