package com.green.Shop.cart.service;

import com.green.Shop.cart.vo.CartVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("cartService")
public class CartServiceImpl implements CartService{
    @Autowired
    private SqlSessionTemplate sqlSession;

    @Override
    public int insertCart(CartVO cartVO) {
        return sqlSession.insert("cartMapper.insertCart", cartVO);
    }

    @Override
    public List<CartVO> selectCartList(String memeberId) {
        return sqlSession.selectList("cartMapper.selectCartList", memeberId);
    }

    @Override
    public void deleteCartItem(String cartCode) {
        sqlSession.delete("cartMapper.deleteCartItem", cartCode);
    }
}
