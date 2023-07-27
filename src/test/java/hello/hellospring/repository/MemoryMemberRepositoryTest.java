package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    void afterEach() {
        repository.clear();
    }

    @Test
    void save() {
        // given
        Member member = new Member();
        member.setName("m1");
        Member member1 = repository.save(member);
        boolean equals = member1.equals(member);
        System.out.println("equals: " + equals);
    }

    @Test
    void findById() {
        Member member = new Member();
        member.setName("m1");
        repository.save(member);
        Member member1 = repository.findById(member.getId()).get();
        boolean equals = member1.equals(member);
        System.out.println("equals: " + equals);
    }
}
