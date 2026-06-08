package com.example.nextbase.application.service;

import com.example.nextbase.application.port.in.MemberUseCase;
import com.example.nextbase.application.port.out.MemberPort;
import com.example.nextbase.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService implements MemberUseCase {

    private final MemberPort memberPort;

    @Override
    public Member createMember(String name, String email) {
        Member member = Member.builder()
                .name(name)
                .email(email)
                .build();
        return memberPort.save(member);
    }

    @Override
    @Transactional(readOnly = true)
    public Member getMember(Long id) {
        return memberPort.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Member not found with id: " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Member> getAllMembers() {
        return memberPort.findAll();
    }

    @Override
    public Member updateMember(Long id, String name, String email) {
        Member member = getMember(id);
        member.update(name, email);
        return memberPort.save(member);
    }

    @Override
    public void deleteMember(Long id) {
        memberPort.deleteById(id);
    }
}
