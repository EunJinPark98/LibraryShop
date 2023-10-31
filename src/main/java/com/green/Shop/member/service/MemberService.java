package com.green.Shop.member.service;

import com.green.Shop.member.vo.MemberVO;

public interface MemberService {

    //회원가입
    public int join(MemberVO memberVO);

    //시큐리티 로그인 정보
    public MemberVO selectLoginInfo(String memberId);

    //아이디 중복 확인
    public boolean idCheck(String memberId);
}
