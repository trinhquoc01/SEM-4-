package com.example.apfast.service;

import com.example.apfast.entity.Member;
import com.example.apfast.repository.MemberRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MemberService {
    @Autowired
    private MemberRepository memberRepository;


    public Optional<Member> findByUserId(String id) {
        return memberRepository.findByUserId(id);
    }

    public boolean checkSession(HttpSession session) {
        if (session.getAttribute("member") == null) {
            return false;
        }
        return true;
    }


}