package com.green.Shop.cart.vo;

import lombok.Data;

@Data
public class CartVO {
    private String cartCode;
    private String itemCode;
    private int cartCnt;
    private String memberId;
    private String putDate;
}
