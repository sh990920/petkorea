package com.project.petkorea.service;

import com.project.petkorea.entity.Member;
import com.project.petkorea.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
    @Autowired
    MemberRepository memberRepository;

    public Member findMember(String id){
        Member member = memberRepository.findById(id);
        return member;
    }
}
