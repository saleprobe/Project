package com.example.SmartFarmSystem.service;

import com.example.SmartFarmSystem.domain.Member;
import com.example.SmartFarmSystem.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MemberService {

    private MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public void registerMember(Member member) {
        validateDuplicateMember(member);  //중복 회원 검증
        memberRepository.save(member);
    }

    public boolean loginMember(String memberId, String memberPw) {
        Member member = memberRepository.findByMemberIdAndMemberPw(memberId, memberPw);
        return member != null; // 멤버가 존재하면 true, 존재하지 않으면 false를 반환
    }

    private void validateDuplicateMember(Member member) {
        //같은 이름이 있는 중복 회원X
        Optional<Member> result = memberRepository.findByMemberId(member.getmid());
        result.ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
    }
}
