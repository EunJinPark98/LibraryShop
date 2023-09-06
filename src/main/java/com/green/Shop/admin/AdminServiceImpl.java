package com.green.Shop.admin;

import com.green.Shop.item.vo.CateVO;
import com.green.Shop.item.vo.ItemVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("adminService")
public class AdminServiceImpl implements AdminService {
    @Autowired
    private SqlSessionTemplate sqlSession;

    @Override
    public List<CateVO> selectCateList() {
        return sqlSession.selectList("adminMapper.selectCateList");
    }

    @Override
    public void insertItem(ItemVO itemVO) {
        sqlSession.insert("adminMapper.insertItem", itemVO);
    }

    @Override
    public List<ItemVO> selectItemList() {
        return sqlSession.selectList("adminMapper.selectItemList");
    }

}
