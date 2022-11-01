package com.example.boardprac.service;

import com.example.boardprac.entity.Board;
import com.example.boardprac.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;


@RequiredArgsConstructor
@Service
@Transactional
public class BoardService {

    //dependency injection
    private final BoardRepository boardRepository; //board테이블에 접근할 수 있는 리포지토리객체 생성


    //글 작성 처리, 글만 작성할 때
    /*
    public void boardWrite(Board board){
        boardRepository.save(board);
    }*/

    //글 작성 처리, 파일도 함께 불러오기
    public void boardWrite(Board board, MultipartFile file) throws IOException {

        String projectPath = System.getProperty("user.dir")+ "\\src\\main\\resources\\static\\files";
        UUID uuid = UUID.randomUUID(); //랜덤으로 식별자(이름) 생성
        String fileName = uuid + "_" + file.getOriginalFilename();

        File saveFile = new File(projectPath, fileName); //경로와 매개변수로 들어온 파일의 이름을 지정
        file.transferTo(saveFile);

        board.setFilename(fileName);
        board.setFilepath("/files/" + fileName);

        boardRepository.save(board);
    }


    /*
    //게시글 리스트 처리, 그냥 불러오기만 하는 것
    public List<Board> boardList(){
        return boardRepository.findAll();
    }*/



    //페이지 처리한 게시글 리스트 처리
    public Page<Board> boardList(Pageable pageable){
        return boardRepository.findAll(pageable);
    }



    //찾는 키워드가 있는 글을 찾아서 페이징 처리 후 보여주기
    public Page<Board> boardSearchList(String searchKeyword, Pageable pageable){
        return boardRepository.findByTitleContaining(searchKeyword, pageable);
    }



    //특정 게시글 불러오기 처리
    public Board boardView(int id){
        return boardRepository.findById(id).get();
    }


    //특정 게시글 삭제
    public void boardDelete(int id){
        boardRepository.deleteById(id);
    }






}
