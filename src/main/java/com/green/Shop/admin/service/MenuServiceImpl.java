package com.green.Shop.admin.service;

import com.green.Shop.admin.vo.MenuVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {
    @Autowired
    private SqlSessionTemplate sqlSession;

    @Override
    public List<MenuVO> selectMenuList() {
        return sqlSession.selectList("menuMapper.selectMenuList");
    }
}
