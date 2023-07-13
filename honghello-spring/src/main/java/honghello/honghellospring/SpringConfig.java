package honghello.honghellospring;

import honghello.honghellospring.repository.MemberRepository;
import honghello.honghellospring.repository.MemoryMemberRepository;
import honghello.honghellospring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class SpringConfig {
    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository(){
        return new MemoryMemberRepository();
    }
}
