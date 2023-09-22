package com.green.Shop.buy.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
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
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

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
        buyVO.setBuyTotalPrice(buyTotalPrice);
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

        buyVO.setBuyTotalPrice(buyDetailVO.getBuyPrice());

        buyService.regBuy(buyVO, buyDetailVO);

        return "/content/buy/buy_form";
    }

    //장바구니 선택구매
    @ResponseBody
    @PostMapping("/insertBuyFetch")
    public void insertBuyFetch(@RequestBody Map<String, Object> data, HttpSession session){
        System.out.println(data);
        BuyVO buyVO = new BuyVO();
        String buyCode = buyService.selectNextBuyCode();
        buyVO.setBuyCode(buyCode);
        MemberVO loginInfo = (MemberVO) session.getAttribute("loginInfo");
        buyVO.setMemberId(loginInfo.getMemberId());
        int buyTotalPrice = Integer.parseInt(data.get("buyTotalPrice").toString());
        buyVO.setBuyTotalPrice(buyTotalPrice);

        //buyVO의 buyDetailList채우기
        ObjectMapper mapper = new ObjectMapper();
        BuyDetailVO[] detailArr = mapper.convertValue(data.get("detailList"), BuyDetailVO[].class);
        List<BuyDetailVO> buyDetailList = Arrays.asList(detailArr);
        for(BuyDetailVO vo : buyDetailList){
            vo.setBuyCode(buyCode);
            vo.setBuyDetailCode(buyCode);
        }
        buyVO.setBuyDetailList(buyDetailList);

        CartVO cartVO = new CartVO();
        String[] cartCodeArr = mapper.convertValue(data.get("cartCodeList"), String[].class);
        List<String> cartCodeList = Arrays.asList(cartCodeArr);
        cartVO.setCartCodeList(cartCodeList);

        buyService.insertBuy(buyVO, cartVO);
    }

}
