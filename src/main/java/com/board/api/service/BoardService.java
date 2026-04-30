package com.board.api.service;

import com.board.api.domain.Board;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class BoardService {

    private final ObjectMapper mapper = new ObjectMapper();
    private final File file = new File("boards.json");

    private List<Board> boards = new ArrayList<>();
    private Long nextId = 1L;

    public BoardService() {
        loadBoards();
    }

    private void loadBoards() {
        try {
            System.out.println("현재 실행 경로: " + new File(".").getAbsolutePath());
            System.out.println("boards.json 경로: " + file.getAbsolutePath());
            System.out.println("boards.json 존재 여부: " + file.exists());

            if (file.exists()) {
                boards = mapper.readValue(file, new TypeReference<List<Board>>() {});

                nextId = boards.stream()
                        .map(Board::getId)
                        .filter(Objects::nonNull)
                        .mapToLong(Long::longValue)
                        .max()
                        .orElse(0L) + 1;
            }
        } catch (Exception e) {
            throw new RuntimeException("boards.json 읽기 실패", e);
        }
    }

    private void saveBoards() {
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(file, boards);
        } catch (Exception e) {
            throw new RuntimeException("boards.json 저장 실패", e);
        }
    }

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
        saveBoards();

        return board;
    }

    public Board updateBoard(Long id, Board newBoard) {
        for (Board board : boards) {
            if (board.getId().equals(id)) {
                board.setTitle(newBoard.getTitle());
                board.setContent(newBoard.getContent());
                board.setAuthor(newBoard.getAuthor());
                board.setUpdatedAt(LocalDateTime.now().toString());

                saveBoards();

                return board;
            }
        }

        throw new RuntimeException("해당 게시글 없음");
    }

    public void deleteBoard(Long id) {
        boards.removeIf(board -> board.getId().equals(id));
        saveBoards();
    }
}