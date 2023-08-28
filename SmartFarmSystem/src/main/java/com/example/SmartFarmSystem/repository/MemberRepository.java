package com.example.SmartFarmSystem.repository;

import com.example.SmartFarmSystem.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Integer> {
    Member findByMemberIdAndMemberPw(String mid, String mpw);
}
