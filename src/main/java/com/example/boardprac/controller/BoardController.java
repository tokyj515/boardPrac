package com.example.boardprac.controller;

import com.example.boardprac.entity.Board;
import com.example.boardprac.repository.BoardRepository;
import com.example.boardprac.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class BoardController {


    private final BoardRepository boardRepository;
    private final BoardService boardService;


    @GetMapping("/board/write") //boardwrite.html로 이동할 링크 설정
    public String boardWriteForm(){
        return "boardwrite";
    }



    @PostMapping("/board/writepro") //form 태그의 url과 일치해야 함
    public String boardWritePro(Board board){
        //boardWriteProcess
        //System.out.println(board.getId()); -> 확인차 사용
        //String title, String content -> 매개변수가 많아지면 다 기억할 수도 없고 귀찮기 때문에 객체로 받아옴

        boardService.write(board);

        return "";
    }



    @GetMapping("/board/list")
    public String boardList(Model model){
        model.addAttribute("list", boardService.boardList());
        //boardService.boardList()를 통해 반환되는 리턴값을 "list"란 이름으로 받아서 클라에 넘겨줌



        return "boardlist";
    }

    @GetMapping("/board/view")  //?id=1
    public String boardView(Model model, int id){
        model.addAttribute("board", boardService.boardView(id));
        return "boardview";
    }


    @GetMapping("/board/delete")
    public String boardDelete(int id){
        boardService.boardDelete(id);
        return "redirect:/board/list";  //게시글을 삭제하면 보통 리스트로 돌아감
    }



}
