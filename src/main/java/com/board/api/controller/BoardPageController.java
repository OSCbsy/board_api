package com.board.api.controller;

import com.board.api.domain.Board;
import com.board.api.service.BoardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class BoardPageController {

    private final BoardService boardService;

    public BoardPageController(BoardService boardService) {
        this.boardService = boardService;
    }

    // 게시글 목록 화면
    @GetMapping("/boards-page")
    public String boardsPage(Model model) {
        model.addAttribute("boards", boardService.getBoards());
        return "boards";
    }

    // 게시글 상세 화면
    @GetMapping("/boards-page/{id}")
    public String boardDetail(@PathVariable Long id, Model model) {
        model.addAttribute("board", boardService.getBoard(id));
        return "board-detail";
    }

    // 게시글 작성 화면
    @GetMapping("/boards-write")
    public String writeForm() {
        return "board-write";
    }

    // 게시글 작성 처리
    @PostMapping("/boards-page")
    public String createBoard(Board board) {
        boardService.createBoard(board);
        return "redirect:/boards-page";
    }

    // 게시글 수정 화면
    @GetMapping("/boards-edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("board", boardService.getBoard(id));
        return "board-edit";
    }

    // 게시글 수정 처리
    @PostMapping("/boards-edit/{id}")
    public String updateBoard(@PathVariable Long id, Board board) {
        boardService.updateBoard(id, board);
        return "redirect:/boards-page/" + id;
    }

    // 게시글 삭제 처리
    @PostMapping("/boards-delete/{id}")
    public String deleteBoard(@PathVariable Long id) {
        boardService.deleteBoard(id);
        return "redirect:/boards-page";
    }
}