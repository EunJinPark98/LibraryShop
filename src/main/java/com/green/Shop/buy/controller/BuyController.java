package com.green.Shop.buy.controller;

import com.green.Shop.buy.service.BuyService;
import com.green.Shop.buy.vo.BuyDetailVO;
import com.green.Shop.buy.vo.BuyVO;
import com.green.Shop.cart.service.CartService;
import com.green.Shop.cart.vo.CartVO;
import com.green.Shop.member.vo.MemberVO;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
    public String buyItem(CartVO cartVO, HttpSession session){
        List<BuyDetailVO> buyDetailList = cartService.selectCartListForBuy(cartVO);
        int buyTotalPrice = 0;
        for(BuyDetailVO buyDetail : buyDetailList){
            buyTotalPrice += buyDetail.getBuyPrice();
        }
        String buyCode = buyService.selectNextBuyCode();
        MemberVO loginInfo = (MemberVO) session.getAttribute("loginInfo");

        BuyVO buyVO = new BuyVO();
        buyVO.setBuyCode(buyCode);
        buyVO.setMemberId(loginInfo.getMemberId());
        buyVO.setBuyTotalPrice(Integer.toString(buyTotalPrice));
        buyVO.setBuyDetailList(buyDetailList);
        buyService.insertBuy(buyVO, cartVO);

        return "redirect:/buy/list";
    }

    //구매 정보 목록
    @GetMapping("/list")
    public String buyList(Model model, HttpSession session){
        MemberVO loginInfo = (MemberVO) session.getAttribute("loginInfo");
        List<BuyVO> buyList = buyService.selectBuyList(loginInfo.getMemberId());
        System.out.println(buyList);
        model.addAttribute("buyList", buyList);

        return "/content/buy/buy_list";
    }

    //바로구매 하러가기
    @PostMapping("/buyForm")
    public String buyForm(BuyDetailVO buyDetailVO, BuyVO buyVO, HttpSession session){
        String buyCode = buyService.selectNextBuyCode();
        buyDetailVO.setBuyCode(buyCode);

        buyVO.setMemberId(((MemberVO)session.getAttribute("loginInfo")).getMemberId());
        buyVO.setBuyCode(buyCode);

        buyVO.setBuyTotalPrice(Integer.toString(buyDetailVO.getBuyPrice()));

        buyService.regBuy(buyVO, buyDetailVO);

        return "/content/buy/buy_form";
    }

}
