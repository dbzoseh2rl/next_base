package com.example.nextbase.application.port.out;

import com.example.nextbase.domain.Member;
import java.util.List;
import java.util.Optional;

public interface MemberPort {
    Member save(Member member);
    Optional<Member> findById(Long id);
    List<Member> findAll();
    void deleteById(Long id);
}
