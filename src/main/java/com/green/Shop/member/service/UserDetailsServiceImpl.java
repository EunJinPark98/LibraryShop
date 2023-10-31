package com.green.Shop.member.service;

import com.green.Shop.member.vo.MemberVO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final MemberService memberService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MemberVO memberVO = memberService.selectLoginInfo(username);

        return User.withUsername(memberVO.getMemberId())
                .password(memberVO.getMemberPw()) //비밀번호 앞에 "{noop}" 넣어주면 암호화안된 비밀번호 가능
                .roles(memberVO.getMemberRoll()).build();
    }
}
