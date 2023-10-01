package com.green.Shop.item.service;

import com.green.Shop.item.vo.ItemVO;

import java.util.List;

public interface ItemService {

    //상품 전시
    public List<ItemVO> displayItemList(ItemVO itemVO);

    //총 상품 수
    public int totalDataCnt();

    //상품 상세
    public ItemVO itemDetail(String itemCode);

    //카테고리 조회
    public List<ItemVO> selectCategory();
}
