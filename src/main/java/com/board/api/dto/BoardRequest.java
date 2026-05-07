package com.board.api.dto;

import com.board.api.domain.Board;

public record BoardRequest(String title, String content, String author) {

    public Board toEntity() {
        return new Board(title, content, author);
    }
}
