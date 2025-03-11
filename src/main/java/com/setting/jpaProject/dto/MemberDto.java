package com.setting.jpaProject.dto;

import com.setting.jpaProject.entity.Member;
import lombok.Builder;

@Builder
public record MemberDto(
        String name,
        String email
){
    public static MemberDto fromEntity(Member member) {
        return MemberDto.builder()
                .name(member.getName())   // Assuming getName() exists in the Member entity
                .email(member.getEmail()) // Assuming getEmail() exists in the Member entity
                .build();
    }
}
