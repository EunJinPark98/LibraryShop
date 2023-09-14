package com.green.Shop.cart.vo;

import com.green.Shop.item.vo.ItemVO;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
public class CartVO {
    private String cartCode;
    private String itemCode;
    private int cartCnt;
    private String memberId;
    private String putDate;
    private ItemVO itemVO;
    private List<String> cartCodeList;
}
