package com.example.nextbase.application.port.in;

import com.example.nextbase.domain.Board;
import java.util.List;

public interface BoardUseCase {
    Board createBoard(String title, String content, String author);
    Board getBoard(Long id);
    List<Board> getAllBoards();
    Board updateBoard(Long id, String title, String content);
    void deleteBoard(Long id);
}
