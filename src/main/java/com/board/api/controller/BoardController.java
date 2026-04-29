package com.board.api.controller;

import com.board.api.domain.Board;
import com.board.api.service.BoardService;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping
public class BoardController {

    private final BoardService boardService;

    public BoardController(BoardService boardService){
        this.boardService = boardService;
    }

    @GetMapping("/boards")
    public List<Board> getBoards(){
        return boardService.getBoards();
    }

    @PostMapping("/boards")
    public Board createBoard(@RequestBody Map<String, String> body){
        String title = body.get("title");
        return boardService.createBoards(title);
    }
}