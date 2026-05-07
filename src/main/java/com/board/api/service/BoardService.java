package com.board.api.service;

import com.board.api.domain.Board;
import com.board.api.dto.BoardRequest;
import com.board.api.dto.BoardResponse;
import com.board.api.repository.BoardRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BoardService {

    private final BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    @Transactional(readOnly = true)
    public List<BoardResponse> getBoards() {
        return boardRepository.findAll().stream()
                .map(BoardResponse::from)
                .toList();
    }

    @Transactional(readOnly = true)
    public BoardResponse getBoard(Long id) {
        Board board = findBoardOrThrow(id);
        return BoardResponse.from(board);
    }

    @Transactional
    public BoardResponse createBoard(BoardRequest request) {
        Board saved = boardRepository.save(request.toEntity());
        return BoardResponse.from(saved);
    }

    @Transactional
    public BoardResponse updateBoard(Long id, BoardRequest request) {
        Board board = findBoardOrThrow(id);
        board.update(request.title(), request.content(), request.author());
        return BoardResponse.from(board);
    }

    @Transactional
    public void deleteBoard(Long id) {
        boardRepository.deleteById(id);
    }

    private Board findBoardOrThrow(Long id) {
        return boardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("게시글이 없습니다. id=" + id));
    }
}
