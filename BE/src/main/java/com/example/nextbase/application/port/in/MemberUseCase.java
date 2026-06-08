package com.example.nextbase.application.port.in;

import com.example.nextbase.domain.Member;
import java.util.List;

public interface MemberUseCase {
    Member createMember(String name, String email);
    Member getMember(Long id);
    List<Member> getAllMembers();
    Member updateMember(Long id, String name, String email);
    void deleteMember(Long id);
}
