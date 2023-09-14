package com.green.Shop.buy.service;

import com.green.Shop.buy.vo.BuyVO;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BuyServiceImpl implements BuyService{
    private final SqlSessionTemplate sqlSession;

//    @Override
//    public String selectNextBuyCode() {
//        return sqlSession.selectOne("buyMapper.selectNextBuyCode");
//    }
//
//    @Override
//    public void insertBuy(BuyVO buyVO) {
//        sqlSession.insert("buyMapper.insertBuy", buyVO);
//    }
}
