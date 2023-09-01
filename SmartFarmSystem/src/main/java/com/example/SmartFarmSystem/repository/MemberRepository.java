package com.example.SmartFarmSystem.repository;

import com.example.SmartFarmSystem.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Integer> {
    Member findByMemberIdAndMemberPw(String mid, String mpw);
    Optional<Member> findByMemberId(String mid);
}
