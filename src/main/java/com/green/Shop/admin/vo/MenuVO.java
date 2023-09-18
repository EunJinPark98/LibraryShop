package com.green.Shop.admin.vo;

import lombok.Data;

import java.util.List;

@Data
public class MenuVO {
    private String menuCode;
    private String menuName;
    private String menuUrl;
    private List<SubMenuVO> subMenuList;
}
