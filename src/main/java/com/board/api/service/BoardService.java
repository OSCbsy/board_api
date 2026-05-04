package com.board.api.service;
//이 클래스가 속한 패키지. 보통 비즈니스 로직을 담당하는 서비스 폴더

import com.board.api.domain.Board;
//보드 클래스를 가져오는 코드. 게시글 하나를 표현하는 객체가 보드. 내가 만든 json파일

import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;
//json 파일을 읽고 쓰기 위해 jackson 기능을 가져온 거
//Objectmapper는 말하자면 자바 객체와 json을 서로 바꿔주는 도구

import org.springframework.stereotype.Service;
//@service 어노테이션을 쓰기 위해 가져오는 코드 이 어노테이션을 붙이면 스프링이 이 클래스를 서비스 역할의 bean으로 등록해줌

import java.io.File;
//파일을 찾고, 읽고, 저장하는 사용되는 코드를 가져옴

import java.time.LocalDateTime;
//현재 시간을 가져오기 위해 사용하는 코드

import java.util.*;
//자바의 여러 기본 자료구조와 유틸 기능을 가져오는 코드

@Service
//서비스 클래스라는 표시. 이 클래스는 직접 new하지 말고, 너가 객체로 만들어서 관리해달라
public class BoardService {
    //BoardService 클래스를 선언하는 코드

    private final ObjectMapper mapper = new ObjectMapper();
    //json 변환도구를 하나 만듦
    //mapper가 하는 일: board.json 파일 읽기, List<Board>저장

    private final File file = new File("boards.json");
    //이 클래스 안에서만 사용, mapper은 앞으로 board.json파일을 읽고 자바의 리스트<Board>로 변환시킴
    //List<Board> 저장, boards.json 파일로 변환
    //private는 이 클래스 안에서만 사용하겠다는 뜻
    //final은 한 번 만든 mapper를 다른 걸로 바꾸지 않겠다는 뜻
    //boards.json이라는 파일을 가리키는 객체를 만든 것


    private List<Board> boards = new ArrayList<>();
    //게시글을 담아둘 리스트
    //DB를 안 쓰니까 메모리 안에 게시글 목록을 들고 있는 거
    //json 쓰더라도 서버 메모리 안에 직접 저장하는 구조가 필요함

    private Long nextId = 1L;
    //다음 게시글에 붙일 id 값이야.

    public BoardService() {
        loadBoards();
    }
    //생성자 BoardService 객체가 만들어질 때 자동으로 실행됨
    //spring이 @service를 보고 boardservice 객체를 만들면 생성자가 실행되고 그 안에서 loadBoards()가 호출됨

    private void loadBoards()
    //boards.json 파일에서 게시글 목록을 읽어오는 메서드 private이니까 이 클래스 안에서만 쓰는 내부 기능. 서비스가 내부적으로 쓰는 기능
    {
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
    //예외가 발생할 수 있는 코드를 감싸는 부분 (파일이 없거나 제이슨형식이 깨지거나 권한문제 보드클래스랑 제이슨이 안맞거나)

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

    //전체 게시글 목록을 반환하는 메서드야.


    public Board getBoard(Long id) {
        for (Board board : boards) {
            if (board.getId().equals(id)) {
                return board;
            }
        }
        throw new RuntimeException("해당 게시글 없음");
    }

    //특정 id를 가진 게시글 하나를 조회하는 메서드야.

    public Board createBoard(final Board board) {
        String now = LocalDateTime.now().toString();

        board.setId(nextId++);
        board.setCreatedAt(now);
        board.setUpdatedAt(now);

        boards.add(board);
        saveBoards();

        return board;
    }

    //boards 리스트 안에 있는 게시글을 하나씩 꺼내서 검사하는 반복문이야.

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