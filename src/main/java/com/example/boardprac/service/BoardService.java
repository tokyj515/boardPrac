package com.example.boardprac.service;

import com.example.boardprac.entity.Board;
import com.example.boardprac.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@RequiredArgsConstructor
@Service
@Transactional
public class BoardService {

    //dependency injection
    private final BoardRepository boardRepository; //board테이블에 접근할 수 있는 리포지토리객체 생성


    public void write(Board board){
        boardRepository.save(board);
    }


    public List<Board> boardList(){
        return boardRepository.findAll();
    }

}
