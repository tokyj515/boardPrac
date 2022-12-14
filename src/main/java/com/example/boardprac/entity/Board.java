package com.example.boardprac.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

//boardDto.? -> 이건 내가 구현해보기

//@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Board { //우리가 만든 테이블 이름과 같게 해주는 게 좋음(연결할 테이블)

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column
    private String filename;

    @Column
    private String filepath;


    public Board(String title, String content){
        this.title = title;
        this.content = content;
    }


}
