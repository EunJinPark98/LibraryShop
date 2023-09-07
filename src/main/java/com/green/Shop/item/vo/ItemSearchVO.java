package com.green.Shop.item.vo;

import lombok.Data;

@Data
public class ItemSearchVO {
    private String searchCateCode;
    private String searchItemName;
    private int[] searchItemStatus;
    private String searchItemStockMin;  //int는 빈문자를 못받음 오류남
    private String searchItemStockMax;
}
