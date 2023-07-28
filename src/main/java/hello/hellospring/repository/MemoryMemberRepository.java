package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class MemoryMemberRepository implements MemberRepository {

    private long sequence;
    private static Map<Long, Member> store = new HashMap<>();

    @Override
    public Member save(Member member) {
        System.out.println("save sequence: " + sequence);
        member.setId(++sequence);
        store.put(sequence, member);
        System.out.println("save sequence: " + sequence);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values()
                        .stream()
                        .filter(member -> member.getName().equals(name))
                        .findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>((Collection) store);
    }

    public void clear() {
        store.clear();
    }
}
