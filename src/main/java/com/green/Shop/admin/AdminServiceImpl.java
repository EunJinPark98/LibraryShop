package com.green.Shop.admin;

import com.green.Shop.item.vo.CateVO;
import com.green.Shop.item.vo.ItemSearchVO;
import com.green.Shop.item.vo.ItemVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

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
    public List<ItemVO> selectItemList(ItemSearchVO itemSearchVO) {
        return sqlSession.selectList("adminMapper.selectItemList", itemSearchVO);
    }

    @Override
    public void updateStock(ItemVO itemVO) {
        sqlSession.update("adminMapper.updateStock", itemVO);
    }

    @Override
    public void updateStatus(ItemVO itemVO) {
        sqlSession.update("adminMapper.updateStatus", itemVO);
    }

    @Override
    public void insertImgs(ItemVO itemVO) {
        sqlSession.insert("adminMapper.insertImgs", itemVO);
    }

    @Override
    public String selectNextItemCode() {
        return sqlSession.selectOne("adminMapper.selectNextItemCode");
    }

    @Override
    public String selectNextImgCode() {
        return sqlSession.selectOne("adminMapper.selectNextImgCode");
    }

}
