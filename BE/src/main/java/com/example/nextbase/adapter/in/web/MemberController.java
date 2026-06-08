package com.example.nextbase.adapter.in.web;

import com.example.nextbase.application.port.in.MemberUseCase;
import com.example.nextbase.domain.Member;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
@CrossOrigin(origins = "*") // CORS 문제 해결을 위해 추가
public class MemberController {

    private final MemberUseCase memberUseCase;

    @PostMapping
    public ResponseEntity<Member> create(@RequestBody MemberDto request) {
        Member member = memberUseCase.createMember(request.getName(), request.getEmail());
        return ResponseEntity.ok(member);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Member> get(@PathVariable Long id) {
        return ResponseEntity.ok(memberUseCase.getMember(id));
    }

    @GetMapping
    public ResponseEntity<List<Member>> getAll() {
        return ResponseEntity.ok(memberUseCase.getAllMembers());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Member> update(@PathVariable Long id, @RequestBody MemberDto request) {
        Member member = memberUseCase.updateMember(id, request.getName(), request.getEmail());
        return ResponseEntity.ok(member);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        memberUseCase.deleteMember(id);
        return ResponseEntity.ok().build();
    }

    @Data
    public static class MemberDto {
        private String name;
        private String email;
    }
}
