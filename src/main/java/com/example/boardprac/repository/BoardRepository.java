package com.example.boardprac.repository;


import com.example.boardprac.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseBody;

@Repository
public interface BoardRepository extends JpaRepository<Board, Integer> {
    //<Board, int> -> 엔티티이름과 기본키의자료형
    //BoardRepository -> BoardDAO
}
