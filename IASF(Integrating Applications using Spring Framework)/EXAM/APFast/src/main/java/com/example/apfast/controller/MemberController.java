package com.example.apfast.controller;

import com.example.apfast.entity.Member;
import com.example.apfast.service.MemberService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class MemberController {

    @Autowired
    private MemberService memberService;

    @GetMapping("/")
    public String index(Model model) {
        return "index";
    }

    @PostMapping("/login")
    public String login(Model model, @RequestParam String userId, @RequestParam String password, HttpSession session) {
        Optional<Member> member = memberService.findByUserId(userId);
        if (member.isPresent() && member.get().getPassword().equals(password)) {
            session.setAttribute("member", member.get());
            return "redirect:/vehicles";
        }else {
            model.addAttribute("error", "Invalid username or password");
            return "index";
        }
    }



    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

}