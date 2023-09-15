package com.green.Shop.member.controller;

import com.green.Shop.member.service.MemberService;
import com.green.Shop.member.vo.MemberVO;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
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

    @GetMapping("/joinForm")
    public String joinForm(){
        return "/fragment/main_layout";
    }

    @PostMapping("/join")
    public String join(MemberVO memberVO){
        memberService.join(memberVO);
        return "redirect:/";
    }

    @GetMapping("/loginForm")
    public String loginForm(MemberVO memberVO){
        return "/content/member/login";
    }

    @PostMapping("/login")
    public String login(MemberVO memberVO, HttpSession session){
        MemberVO loginInfo = memberService.login(memberVO);
        if(loginInfo != null){
            session.setAttribute("loginInfo", loginInfo);
        }
        return "content/member/login_result";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("loginInfo");
        return "redirect:/";
    }

    @ResponseBody
    @PostMapping("/idCheck")
    public boolean idCheck(String memberId){
        return memberService.idCheck(memberId);
    }

    @GetMapping("/info")
    public String memberInfo(){
        return "content/member/member_info";
    }






}
