package com.example.nextbase.adapter.out.persistence;

import com.example.nextbase.application.port.out.MemberPort;
import com.example.nextbase.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class MemberPersistenceAdapter implements MemberPort {

    private final MemberRepository memberRepository;

    @Override
    public Member save(Member member) {
        MemberJpaEntity entity = MemberJpaEntity.builder()
                .id(member.getId())
                .name(member.getName())
                .email(member.getEmail())
                .build();
        MemberJpaEntity savedEntity = memberRepository.save(entity);
        return mapToDomain(savedEntity);
    }

    @Override
    public Optional<Member> findById(Long id) {
        return memberRepository.findById(id).map(this::mapToDomain);
    }

    @Override
    public List<Member> findAll() {
        return memberRepository.findAll().stream()
                .map(this::mapToDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        memberRepository.deleteById(id);
    }

    private Member mapToDomain(MemberJpaEntity entity) {
        return Member.builder()
                .id(entity.getId())
                .name(entity.getName())
                .email(entity.getEmail())
                .build();
    }
}
