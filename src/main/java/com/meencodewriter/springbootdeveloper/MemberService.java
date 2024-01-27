package com.meencodewriter.springbootdeveloper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    public void test() {
        // 1. create
        memberRepository.save(new Member(1L, "A"));

        // 2. Read
        Optional<Member> member = memberRepository.findById(1L); // Read One
        List<Member> memberList = memberRepository.findAll(); // Read All

        // 3. Remove
        memberRepository.deleteById(1L);
    }
}
