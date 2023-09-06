package com.green.Shop.admin;

import com.green.Shop.item.vo.CateVO;
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
}
