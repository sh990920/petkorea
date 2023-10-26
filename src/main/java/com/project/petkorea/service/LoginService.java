package com.project.petkorea.service;

import com.project.petkorea.entity.Member;
import com.project.petkorea.repository.MemberRepository;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Builder
public class LoginService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepository.findById(username);
        if(member == null) {
            throw new UsernameNotFoundException(username);
        }
        return User.builder()
                .username(member.getId())
                .password(member.getPassword())
                .roles(member.getRollName())
                .build();
    }

    public String signUp(Member member, PasswordEncoder passwordEncoder){
        boolean isEmpty = idCheck(member.getId());
        if(isEmpty){
            member.setPassword(passwordEncoder.encode(member.getPassword()));
            member.setRollName("USER");
            Member signUpMember = memberRepository.save(member);
            if(signUpMember == null){
                return "no";
            }else{
                return "yes";
            }
        }else{
            return "exist";
        }
    }

    public boolean idCheck(String id){
        Member member = memberRepository.findById(id);
        if(member != null){
            return false;
        }
        return true;
    }
}
