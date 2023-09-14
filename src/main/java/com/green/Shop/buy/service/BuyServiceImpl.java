package com.green.Shop.buy.service;

import com.green.Shop.buy.vo.BuyDetailVO;
import com.green.Shop.buy.vo.BuyVO;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BuyServiceImpl implements BuyService{
    private final SqlSessionTemplate sqlSession;

    @Override
    public String selectNextBuyCode() {
        return sqlSession.selectOne("buyMapper.selectNextBuyCode");
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertBuy(BuyVO buyVO) {
        sqlSession.insert("buyMapper.insertBuy", buyVO);
        sqlSession.insert("buyMapper.insertBuyDetail", buyVO);
    }

}
