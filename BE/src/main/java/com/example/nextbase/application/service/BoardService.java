package com.example.nextbase.application.service;

import com.example.nextbase.application.port.in.BoardUseCase;
import com.example.nextbase.application.port.out.BoardPort;
import com.example.nextbase.domain.Board;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class BoardService implements BoardUseCase {

    private final BoardPort boardPort;

    @Override
    public Board createBoard(String title, String content, String author) {
        Board board = Board.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();
        return boardPort.save(board);
    }

    @Override
    @Transactional(readOnly = true)
    public Board getBoard(Long id) {
        return boardPort.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Board not found with id: " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Board> getAllBoards() {
        return boardPort.findAll();
    }

    @Override
    public Board updateBoard(Long id, String title, String content) {
        Board board = getBoard(id);
        board.update(title, content);
        return boardPort.save(board);
    }

    @Override
    public void deleteBoard(Long id) {
        boardPort.deleteById(id);
    }
}
