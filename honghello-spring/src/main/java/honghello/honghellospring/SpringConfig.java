package honghello.honghellospring;

import honghello.honghellospring.repository.JpaMemberRepository;
import honghello.honghellospring.repository.MemberRepository;
import honghello.honghellospring.repository.MemoryMemberRepository;
import honghello.honghellospring.service.MemberService;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class SpringConfig {

    private final MemberRepository memberRepository;

    @Autowired
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository);
    }

//    @Bean
//    public MemberRepository memberRepository(){
//        return new MemoryMemberRepository();
//        return new JpaMemberRepository(em);
//    }
}
