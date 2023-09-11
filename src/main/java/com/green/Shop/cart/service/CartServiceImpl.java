package com.green.Shop.cart.service;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("cartService")
public class CartServiceImpl implements CartService{
    @Autowired
    private SqlSessionTemplate sqlSession;
}
