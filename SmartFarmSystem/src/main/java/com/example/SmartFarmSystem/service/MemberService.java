package com.example.SmartFarmSystem.service;

import com.example.SmartFarmSystem.domain.Member;
import com.example.SmartFarmSystem.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    private MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public void registerMember(Member member) {
        memberRepository.save(member);
    }

    public boolean loginMember(String memberId, String memberPw) {
        Member member = memberRepository.findByMemberIdAndMemberPw(memberId, memberPw);
        return member != null; // 멤버가 존재하면 true, 존재하지 않으면 false를 반환
    }
}
