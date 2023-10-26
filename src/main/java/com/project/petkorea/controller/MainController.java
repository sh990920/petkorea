package com.project.petkorea.controller;

import com.project.petkorea.entity.Member;
import com.project.petkorea.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class MainController {

    @Autowired
    MemberService memberService;

    @GetMapping("/")
    public String mainPage(Principal principal, Model model){
        if(principal == null){
            return "redirect:/loginPage/";
        }
        Member member = memberService.findMember(principal.getName());
        model.addAttribute("member", member);
        return "Main";
    }


}
