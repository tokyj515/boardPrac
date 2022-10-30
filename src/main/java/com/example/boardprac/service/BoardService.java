package com.example.boardprac.service;

import com.example.boardprac.entity.Board;
import com.example.boardprac.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@RequiredArgsConstructor
@Service
@Transactional
public class BoardService {

    //dependency injection
    private final BoardRepository boardRepository; //board테이블에 접근할 수 있는 리포지토리객체 생성


    //글 작성 처리
    public void write(Board board){
        boardRepository.save(board);
    }


    //게시글 리스트 처리
    public List<Board> boardList(){
        return boardRepository.findAll();
    }


    //특정 게시글 불러오기 처리
    public Board boardView(int id){
        return boardRepository.findById(id).get();
    }



}
