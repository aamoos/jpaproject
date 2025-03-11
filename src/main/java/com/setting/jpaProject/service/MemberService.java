package com.setting.jpaProject.service;

import com.setting.jpaProject.dto.MemberDto;
import com.setting.jpaProject.entity.Member;
import com.setting.jpaProject.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional(readOnly = true)
    public List<MemberDto> findAll() {
        List<Member> findMembers = memberRepository.findAll();
        return findMembers.stream()
                .map(MemberDto::fromEntity)
                .collect(Collectors.toList());
    }
}
