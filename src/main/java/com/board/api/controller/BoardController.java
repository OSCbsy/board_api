package com.board.api.controller;

import com.board.api.service.BoardService;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/boards")
public class BoardController {
    
    private final BoardService boardService;
    
    public BoardController(BoardService boardService){
        this.boardService=boardService;
    }

    @GetMapping("/boards")
    public List<String> getBoards(){
        return boardService.getBoards();
    }
}