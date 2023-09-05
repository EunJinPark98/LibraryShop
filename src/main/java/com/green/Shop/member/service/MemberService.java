package com.green.Shop.member.service;

import com.green.Shop.member.vo.MemberVO;

public interface MemberService {

    //회원가입
    public int join(MemberVO memberVO);

    //로그인
    public MemberVO login(MemberVO memberVO);
}
