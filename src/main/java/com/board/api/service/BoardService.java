package com.board.api.service;

import com.board.api.domain.Board;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class BoardService {

    private final List<Board> boards = new ArrayList<>();
    private Long nextId = 1L;

    public List<Board> getBoards(){
        return boards;
    }

    public Board createBoards(String title){
        Board board = new Board(nextId++, title);
        boards.add(board);
        return board;
    }
}