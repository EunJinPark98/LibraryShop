package com.green.Shop.cart.service;

import com.green.Shop.cart.vo.CartVO;

public interface CartService {
    //장바구니 상품 등록
    public void insertCart(CartVO cartVO);
}
