package com.green.Shop.admin;

import com.green.Shop.item.vo.CateVO;

import java.util.List;

public interface AdminService {

    //상품 등록시 카테고리 목록 조회
    public List<CateVO> selectCateList();

}
