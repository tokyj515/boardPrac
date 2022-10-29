package com.example.boardprac.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController {

    @GetMapping("/board/write") //boardwrite.html로 이동할 링크 설정
    public String boardWriteForm(){
        return "boardwrite";
    }

}
