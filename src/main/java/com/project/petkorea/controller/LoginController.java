package com.project.petkorea.controller;

import com.project.petkorea.entity.Member;
import com.project.petkorea.service.LoginService;
import com.project.petkorea.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {
    @Autowired
    LoginService loginService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    MemberService memberService;

    @GetMapping("/loginPage/")
    public String loginPage(@RequestParam(value = "error", required = false) String error,
                            @RequestParam(value = "errorMessage", required = false) String errorMessage, Model model){
        model.addAttribute("error", error);
        model.addAttribute("errorMessage", errorMessage);
        return "Login/Login";
    }

    @PostMapping("/loginPage/login/")
    public void login(String id){
        loginService.loadUserByUsername(id);
    }

    @GetMapping("/loginPage/login/loginSuccess/")
    public String loginSuccess(){
        return "redirect:/";
    }

    @GetMapping("/logout/")
    public void logout(){

    }

    @PostMapping("/loginPage/signUp/")
    @ResponseBody
    public String signUp(Member member){
        String res = "no";
        res = loginService.signUp(member, passwordEncoder);
        return res;
    }
}
