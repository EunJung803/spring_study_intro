package hello.hellospring2023.repository;

import hello.hellospring2023.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;
    /**
     * 동시성 문제가 고려되어 있지 않음, 실무에서는 ConcurrentHashMap, AtomicLong 사용 고려
     */


    @Override
    public Member save(Member member) {
        member.setId(++sequence);               // id를 세팅하고
        store.put(member.getId(), member);      // store 해시맵에 저장
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));      // null이어도 Optional로 감싸서 반환 -> 클라이언트에서 처리 가능
    }

    @Override
    public Optional<Member> findByName(String name) {
        // 맵에서 루프를 돌면서 찾으면 반환, 없으면 null 반환
        return store.values().stream()
                .filter(member -> member.getName().equals(name))    // 같은 name인지 확인
                .findAny();     // 하나라도 찾는다
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());     // store의 value는 Member
    }


    public void clearStore() {
        store.clear();
    }
}
