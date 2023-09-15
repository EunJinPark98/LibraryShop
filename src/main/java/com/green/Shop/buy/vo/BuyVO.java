package com.green.Shop.buy.vo;

import com.green.Shop.cart.vo.CartVO;
import lombok.Data;

import java.util.List;

@Data
public class BuyVO {
    private String buyCode;
    private String memberId;
    private String buyTotalPrice;
    private String buyDate;
    private List<BuyDetailVO> buyDetailList;
}
