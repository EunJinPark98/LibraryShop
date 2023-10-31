package com.green.Shop.member.controller;

import com.green.Shop.member.service.MemberService;
import com.green.Shop.member.vo.MemberVO;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.reflect.Member;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/joinForm")
    public String joinForm(){
        return "/fragment/main_layout";
    }

    @PostMapping("/join")
    public String join(MemberVO memberVO){
        String encodedPw = passwordEncoder.encode(memberVO.getMemberPw());
        memberVO.setMemberPw(encodedPw);
        memberService.join(memberVO);
        return "redirect:/";
    }

    @GetMapping("/loginForm")
    public String loginForm(MemberVO memberVO){
        return "/content/member/login";
    }


    @ResponseBody
    @PostMapping("/idCheckFetch")
    public boolean idCheck(String memberId){
        return memberService.idCheck(memberId);
    }

    @GetMapping("/info")
    public String memberInfo(){
        return "/content/member/member_info";
    }

    @GetMapping("/denyPage")
    public String denyPage(){
        return "/content/member/deny";
    }






}
