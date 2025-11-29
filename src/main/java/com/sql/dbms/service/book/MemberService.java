package com.sql.dbms.service.book;

import org.springframework.stereotype.Service;

import com.sql.dbms.repositry.book.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public boolean isValidMember(Integer id){
        return memberRepository.existsById(id);
    }
}
