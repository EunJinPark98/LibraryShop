package com.green.Shop.item.service;

import com.green.Shop.item.vo.ItemVO;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {
    private final SqlSessionTemplate sqlSession;

    @Override
    public List<ItemVO> displayItemList(ItemVO itemVO) {
        return sqlSession.selectList("itemMapper.displayItemList", itemVO);
    }

    @Override
    public int totalDataCnt() {
        return sqlSession.selectOne("itemMapper.totalDataCnt");
    }

    @Override
    public ItemVO itemDetail(String itemCode) {
        return sqlSession.selectOne("itemMapper.itemDetail", itemCode);
    }

    @Override
    public List<ItemVO> selectCategory() {
        return sqlSession.selectList("itemMapper.selectCategory");
    }
}
