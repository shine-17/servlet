package hello.servlet.domain.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class MemberRepositoryTest {

    MemberRepository memberRepository = MemberRepository.getInstance();

    @AfterEach
    void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    void save() {
        //given : 주어졌을 때
        Member member = new Member("hello", 20);

        //when : 실행했을 때
        Member savedMember = memberRepository.save(member);

        //then : 원하는 결과
        Member findMember = memberRepository.findById(savedMember.getId());
        assertThat(findMember).isEqualTo(savedMember);
    }

    @Test
    void findAll() {
        //given
        Member member1 = new Member("member1", 20);
        Member member2 = new Member("member2", 30);

        //when
        memberRepository.save(member1);
        memberRepository.save(member2);

        //then
        List<Member> result = memberRepository.findAll();
        assertThat(result).hasSize(2);
        assertThat(result).contains(member1, member2);
    }
}
