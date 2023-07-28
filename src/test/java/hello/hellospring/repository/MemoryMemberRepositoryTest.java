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

        // when
        Member member1 = repository.save(member);

        // then
        boolean equals = member1.equals(member);
        System.out.println("equals: " + equals);
    }

    @Test
    void findById() {
        // given
        Member member = new Member();
        member.setName("m1");
        repository.save(member);

        // when
        Member member1 = repository.findById(member.getId()).get();

        //then
        boolean equals = member1.equals(member);
        System.out.println("equals: " + equals);
    }

    @Test
    void findByName() {
        // given
        final Member member = new Member();
        member.setName("m2");
        repository.save(member);
        System.out.println(member);

        // when
        Member member2 = repository.findByName("m2").get();

        //then
        boolean equals = member2.equals(member);
        System.out.println("equals: " + equals);
    }

}
