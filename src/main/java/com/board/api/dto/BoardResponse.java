package com.board.api.dto;

import com.board.api.domain.Board;

public record BoardResponse(Long id, String title, String content, String author) {

    public static BoardResponse from(Board board) {
        return new BoardResponse(
                board.getId(),
                board.getTitle(),
                board.getContent(),
                board.getAuthor()
        );
    }
}
