package com.example.nextbase.application.port.out;

import com.example.nextbase.domain.Board;
import java.util.List;
import java.util.Optional;

public interface BoardPort {
    Board save(Board board);
    Optional<Board> findById(Long id);
    List<Board> findAll();
    void deleteById(Long id);
}
