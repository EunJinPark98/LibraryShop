package com.green.Shop.item.vo;

import lombok.Data;

import java.util.List;

@Data
public class ItemVO extends PageVO{
    private String itemCode;
    private String itemName;
    private int itemPrice;
    private String itemIntro;
    private int itemStock;
    private String cateCode;
    private int itemStatus;
    private List<ImgVO> imgList;

}
