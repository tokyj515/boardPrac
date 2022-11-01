package com.example.boardprac.controller;

import com.example.boardprac.entity.Board;
import com.example.boardprac.repository.BoardRepository;
import com.example.boardprac.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

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
    public String boardWritePro(Board board, Model model, MultipartFile file) throws IOException {
        //boardWriteProcess
        //System.out.println(board.getId()); -> 확인차 사용
        //String title, String content -> 매개변수가 많아지면 다 기억할 수도 없고 귀찮기 때문에 객체로 받아옴

        boardService.boardWrite(board, file);

        //if문을 통해 글 작성이 실패했을 때의 경우도 만들 수 있다
        //model.addAttribute("message", "글 작성이 실패하였습니다.");
        model.addAttribute("message", "글 작성이 완료되었습니다.");
        model.addAttribute("searchUrl", "/board/list");

        return "message";
    }


    /* 그냥 화면에 리스트만 쭉 불러와주는 형태, 최신글이 가장 밑에 있음
    @GetMapping("/board/list")
    public String boardList(Model model){
        model.addAttribute("list", boardService.boardList());
        //boardService.boardList()를 통해 반환되는 리턴값을 "list"란 이름으로 받아서 클라에 넘겨줌
        return "boardlist";
    }*/





    @GetMapping("/board/list")  //http://localhost:8080/board/list?page=2&size=20
    public String boardList(Model model,
                            @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable){
        model.addAttribute("list", boardService.boardList(pageable));
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


    @GetMapping("/board/modify/{id}")
    public String boardModify(@PathVariable("id") int id, Model model){
        model.addAttribute("board", boardService.boardView(id));
        return "boardmodify";
    }


    @PostMapping("/board/update/{id}")
    public String boardUpdate(@PathVariable("id") int id, Board board, Model model, MultipartFile file) throws IOException {
        Board boardTemp = boardService.boardView(id); //기존 글을 불러옴
        boardTemp.setTitle(board.getTitle()); //새로운 제목
        boardTemp.setContent(board.getContent()); //새로운 내용

        boardService.boardWrite(boardTemp, file); //변경 내용 다시 저장

        model.addAttribute("message", "글 수정이 완료되었습니다.");
        model.addAttribute("searchUrl", "/board/list");

        return "message";
    }

}
