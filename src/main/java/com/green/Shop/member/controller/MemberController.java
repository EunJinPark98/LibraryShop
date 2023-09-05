package com.green.Shop.member.controller;

import com.green.Shop.member.service.MemberService;
import com.green.Shop.member.vo.MemberVO;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.reflect.Member;

@Controller
@RequestMapping("/member")
public class MemberController {
    @Resource(name = "memberService")
    private MemberService memberService;

    @GetMapping("/joinForm")
    private String joinForm(){
        return "/fragment/main_layout";
    }

    @PostMapping("/join")
    private String join(MemberVO memberVO){
        memberService.join(memberVO);
        return "redirect:/";
    }

    @GetMapping("/loginForm")
    private String loginForm(){
        return "/content/member/login";
    }

    @ResponseBody
    @PostMapping("/login")
    private String login(MemberVO memberVO, HttpSession session){
        MemberVO loginInfo = memberService.login(memberVO);
        if(loginInfo != null){
            session.setAttribute("loginInfo", loginInfo);
        }
        return "session.loginInfo";
    }






}
