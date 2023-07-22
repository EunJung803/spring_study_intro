package hello.hellospring2023.repository;

import hello.hellospring2023.domain.Member;
import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    // test가 실행되고 끝날 때 마다 저장소를 싹 지워주기
    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    public void save() {
        Member member = new Member();       // 테스트용 Member 객체 선언

        member.setName("spring");           // 해당 Member의 이름 설정

        repository.save(member);            // 리포지터리에 저장해보기

        Member result = repository.findById(member.getId()).get();
//        Assertions.assertEquals(result, member);            // 저장이 잘 되었는지 확인해보기 - junit Assertions 사용
        assertThat(member).isEqualTo(result);    // 저장이 잘 되었는지 확인해보기 - assertj Assertions 사용
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();

        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }

}
