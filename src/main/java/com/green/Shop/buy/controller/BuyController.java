package com.green.Shop.buy.controller;

import com.green.Shop.buy.service.BuyService;
import com.green.Shop.buy.vo.BuyDetailVO;
import com.green.Shop.cart.service.CartService;
import com.green.Shop.cart.vo.CartVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/buy")
@RequiredArgsConstructor
public class BuyController {
    private final BuyService buyService;
    private final CartService cartService;

    //선택한 상품 구매하기
    @GetMapping("/buyItem")
    public String buyItem(CartVO cartVO){
        List<BuyDetailVO> buyDetailList = cartService.selectCartListForBuy(cartVO);
        System.out.println(buyDetailList);
        return "redirect:/cart/list";
    }

}
