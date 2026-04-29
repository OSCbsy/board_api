package com.board.api.service;

import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class BoardService {
    
    public List<String> getBoards(){
        return List.of("게시글1", "게시글2", "게시글3");
    }

    public String createBoards(String title){
        return "생성됨:" + title;
    }
}
