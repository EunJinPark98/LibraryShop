package com.green.Shop.cart.service;

import com.green.Shop.buy.vo.BuyDetailVO;
import com.green.Shop.cart.vo.CartVO;

import java.util.List;

public interface CartService {
    //장바구니 상품 등록
    public int insertCart(CartVO cartVO);

    //장바구니 목록 조회
    public List<CartVO> selectCartList(String memeberId);

    //장바구니 선택 항목 삭제
    public void deleteCartItem(CartVO cartVO);

    //장바구니 상품 수량 수정
    public void updateCartItemCnt(CartVO cartVO);

    //구매를 위한 장바구니 목록 조회
    public List<BuyDetailVO> selectCartListForBuy(CartVO cartVO);
}
