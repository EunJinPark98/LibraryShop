package com.green.Shop.buy.service;

import com.green.Shop.buy.vo.BuyDetailVO;
import com.green.Shop.buy.vo.BuyVO;
import com.green.Shop.cart.vo.CartVO;

import java.util.List;

public interface BuyService {

    //다음 구입 상품 코드 조회
    public String selectNextBuyCode();

    //구매상품 등록
    public void insertBuy(BuyVO buyVO, CartVO cartVO);

    //구매 목록 조회
    public List<BuyVO> selectBuyList(String memberId);

}
