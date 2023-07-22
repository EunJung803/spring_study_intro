package hello.hellospring2023.repository;

import hello.hellospring2023.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class MemoryMemberRepositoryTest {

    MemberRepository repository = new MemoryMemberRepository();

    @Test
    public void save() {
        Member member = new Member();       // 테스트용 Member 객체 선언

        member.setName("spring");           // 해당 Member의 이름 설정

        repository.save(member);            // 리포지터리에 저장해보기

        Member result = repository.findById(member.getId()).get();
//        Assertions.assertEquals(result, member);            // 저장이 잘 되었는지 확인해보기 - junit Assertions 사용
        Assertions.assertThat(member).isEqualTo(result);    // 저장이 잘 되었는지 확인해보기 - assertj Assertions 사용
    }
}
