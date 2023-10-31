package com.green.Shop.member.service;

import com.green.Shop.member.vo.MemberVO;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final SqlSessionTemplate sqlSession;

    @Override
    public int join(MemberVO memberVO) {
        return sqlSession.insert("memberMapper.join", memberVO);
    }

    @Override
    public MemberVO selectLoginInfo(String memberId) {
        return sqlSession.selectOne("memberMapper.selectLoginInfo", memberId);
    }

    @Override
    public boolean idCheck(String memberId) {
         String idCheck = sqlSession.selectOne("memberMapper.idCheck", memberId);
         return idCheck == null ? true : false;
    }
}
