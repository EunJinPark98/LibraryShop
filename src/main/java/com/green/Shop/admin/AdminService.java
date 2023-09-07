package com.green.Shop.admin;

import com.green.Shop.item.vo.CateVO;
import com.green.Shop.item.vo.ItemVO;

import java.util.List;

public interface AdminService {

    //상품 등록시 카테고리 목록 조회
    public List<CateVO> selectCateList();

    //상품 등록
    public void insertItem(ItemVO itemVO);

    //전체 상품 조회
    public List<ItemVO> selectItemList();

    //재고 수정
    public void updateStock(ItemVO itemVO);

    //상태 수정
    public void updateStatus(ItemVO itemVO);

}
