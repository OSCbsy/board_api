package com.board.api.service;

import com.board.api.domain.Board;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class BoardService {

    private final List<Board> boards = new ArrayList<>();
    private Long nextId = 1L;

    public List<Board> getBoards() {
        return boards;
    }

    public Board getBoard(Long id) {
        for (Board board : boards) {
            if (board.getId().equals(id)) {
                return board;
            }
        }
        throw new RuntimeException("해당 게시글 없음");
    }

    public Board createBoard(Board board) {
        String now = LocalDateTime.now().toString();

        board.setId(nextId++);
        board.setCreatedAt(now);
        board.setUpdatedAt(now);

        boards.add(board);
        return board;
    }

    public Board updateBoard(Long id, Board newBoard) {
        for (Board board : boards) {
            if (board.getId().equals(id)) {
                board.setTitle(newBoard.getTitle());
                board.setContent(newBoard.getContent());
                board.setAuthor(newBoard.getAuthor());
                board.setUpdatedAt(LocalDateTime.now().toString());

                return board;
            }
        }

        throw new RuntimeException("해당 게시글 없음");
    }

    public void deleteBoard(Long id) {
        boards.removeIf(board -> board.getId().equals(id));
    }
}