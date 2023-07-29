package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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
        assertEquals(member1, member);
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
        assertEquals(member1, member);
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
        assertEquals(member2, member);
    }

    @Test
    void findAll() {
        // given
        final Member member1 = new Member();
        member1.setName("m1");
        repository.save(member1);

        final Member member2 = new Member();
        member2.setName("m2");
        repository.save(member2);

        // when
        final List<Member> members = repository.findAll();

        // then
        assertEquals(members.size(), 2);
    }
}
