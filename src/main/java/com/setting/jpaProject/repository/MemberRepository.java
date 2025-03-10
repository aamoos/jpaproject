package com.setting.jpaProject.repository;

import java.util.Optional;

import com.setting.jpaProject.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByEmail(String email);

    Optional<Member> findByMemberKey(String memberKey);
}
