package com.example.nextbase.adapter.in.web;

import com.example.nextbase.application.port.in.BoardUseCase;
import com.example.nextbase.domain.Board;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/boards")
@RequiredArgsConstructor
@CrossOrigin(origins = "*") // 프론트엔드 연동을 위한 CORS 허용
public class BoardController {

    private final BoardUseCase boardUseCase;

    // 게시판 작성
    @PostMapping
    public ResponseEntity<Board> create(@RequestBody BoardDto request) {
        Board board = boardUseCase.createBoard(request.getTitle(), request.getContent(), request.getAuthor());
        return ResponseEntity.ok(board);
    }

    // 게시판 단건 조회
    @GetMapping("/{id}")
    public ResponseEntity<Board> get(@PathVariable Long id) {
        return ResponseEntity.ok(boardUseCase.getBoard(id));
    }

    // 게시판 전체 조회 (요청하신 /api/boards/all 경로)
    @GetMapping("/all")
    public ResponseEntity<List<Board>> getAll() {
        return ResponseEntity.ok(boardUseCase.getAllBoards());
    }

    // 게시판 수정
    @PutMapping("/{id}")
    public ResponseEntity<Board> update(@PathVariable Long id, @RequestBody BoardDto request) {
        Board board = boardUseCase.updateBoard(id, request.getTitle(), request.getContent());
        return ResponseEntity.ok(board);
    }

    // 게시판 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        boardUseCase.deleteBoard(id);
        return ResponseEntity.ok().build();
    }

    @Data
    public static class BoardDto {
        private String title;
        private String content;
        private String author;
    }
}
