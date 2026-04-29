package com.board.api.domain;

public class Board {

    private Long id;
    private String title;

    public Board(Long id, String title) {
        this.id = id;
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }
}