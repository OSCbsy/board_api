package com.board.api.controller;

import com.board.api.dto.BoardRequest;
import com.board.api.dto.BoardResponse;
import com.board.api.service.BoardService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/boards")
@CrossOrigin(origins = "http://localhost:5173")
public class BoardController {

    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping
    public List<BoardResponse> getBoards() {
        return boardService.getBoards();
    }

    @GetMapping("/{id}")
    public BoardResponse getBoard(@PathVariable Long id) {
        return boardService.getBoard(id);
    }

    @PostMapping
    public BoardResponse createBoard(@RequestBody BoardRequest request) {
        return boardService.createBoard(request);
    }

    @PutMapping("/{id}")
    public BoardResponse updateBoard(@PathVariable Long id, @RequestBody BoardRequest request) {
        return boardService.updateBoard(id, request);
    }

    @DeleteMapping("/{id}")
    public String deleteBoard(@PathVariable Long id) {
        boardService.deleteBoard(id);
        return "삭제됨: " + id;
    }
}
