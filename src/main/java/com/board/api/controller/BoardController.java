package com.board.api.controller;

import com.board.api.domain.Board;
import com.board.api.service.BoardService;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/boards")
@CrossOrigin(origins = "http://localhost:5173")
public class BoardController {

    private final BoardService boardService;

    public BoardController(BoardService boardService){
        this.boardService = boardService;
    }

    @GetMapping
    public List<Board> getBoards(){
        return boardService.getBoards();
    }

    @GetMapping("/{id}")
    public Board getBoard(@PathVariable Long id){
        return boardService.getBoard(id);
    }

    @PostMapping
    public Board createBoard(@RequestBody Board board){
        return boardService.createBoard(board);
    }

    @PutMapping("/{id}")
    public Board updateBoard(@PathVariable Long id, @RequestBody Board board){
        return boardService.updateBoard(id, board);
    }

    @DeleteMapping("/{id}")
    public String deleteBoard(@PathVariable Long id){
        boardService.deleteBoard(id);
        return "삭제됨: " + id;
    }


}