package com.example.nextbase.adapter.out.persistence;

import com.example.nextbase.application.port.out.BoardPort;
import com.example.nextbase.domain.Board;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class BoardPersistenceAdapter implements BoardPort {

    private final BoardRepository boardRepository;

    @Override
    public Board save(Board board) {
        BoardJpaEntity entity = BoardJpaEntity.builder()
                .id(board.getId())
                .title(board.getTitle())
                .content(board.getContent())
                .author(board.getAuthor())
                .build();
        BoardJpaEntity savedEntity = boardRepository.save(entity);
        return mapToDomain(savedEntity);
    }

    @Override
    public Optional<Board> findById(Long id) {
        return boardRepository.findById(id).map(this::mapToDomain);
    }

    @Override
    public List<Board> findAll() {
        return boardRepository.findAll().stream()
                .map(this::mapToDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        boardRepository.deleteById(id);
    }

    private Board mapToDomain(BoardJpaEntity entity) {
        return Board.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .content(entity.getContent())
                .author(entity.getAuthor())
                .build();
    }
}
