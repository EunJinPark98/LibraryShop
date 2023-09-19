package com.green.Shop.item.service;

import com.green.Shop.item.vo.ItemVO;

import java.util.List;

public interface ItemService {

    //상품 전시
    public List<ItemVO> displayItemList();

    //상품 상세
    public ItemVO itemDetail(String itemCode);

    //카테고리 조회
    public List<ItemVO> selectCategory();
}
