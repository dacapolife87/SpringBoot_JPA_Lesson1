package me.hjjang.jpa.service;

import me.hjjang.jpa.domain.Member;
import me.hjjang.jpa.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    EntityManager em;


    @Test
    @Rollback(value = false)
    public void 회원가입() {
        Member member = new Member();
        member.setName("jang");

        Long savedId = memberService.join(member);

        em.flush();
        assertEquals(member, memberRepository.find(savedId));
    }

    @Test
    public void 중복_회원_예외() throws Exception {
        //givin
        Member member1 = new Member();
        member1.setName("jang");
        Member member2 = new Member();
        member2.setName("jang");
        
        //when
        memberService.join(member1);

        assertThrows(IllegalArgumentException.class, () -> {
            memberService.join(member2);
        });
    }
}
