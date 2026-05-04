package com.board.api.domain;

import lombok.Data;

@Data
public class Board {

    private Long id;
    private String title;
    private String content;
    private String author;
    private String createdAt;
    private String updatedAt;

}