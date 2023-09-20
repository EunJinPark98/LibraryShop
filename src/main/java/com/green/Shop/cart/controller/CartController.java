package com.green.Shop.cart.controller;

import com.green.Shop.cart.service.CartService;
import com.green.Shop.cart.vo.CartVO;
import com.green.Shop.member.vo.MemberVO;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    //장바구니 아이템 추가
    @ResponseBody
    @PostMapping("/insertCartFetch")
    public int insertCart(CartVO cartVO, HttpSession session){
        MemberVO loginInfo = (MemberVO) session.getAttribute("loginInfo");
        cartVO.setMemberId(loginInfo.getMemberId());
        return cartService.insertCart(cartVO);
    }

    //장바구니 목록
    @GetMapping("/list")
    public String cartList(HttpSession session, Model model){
        MemberVO loginInfo = (MemberVO) session.getAttribute("loginInfo");
        model.addAttribute("cartList", cartService.selectCartList(loginInfo.getMemberId()));
        return "/content/cart/cart_list";
    }

    //장바구니 선택 품목 삭제
    @GetMapping("/deleteCartItem")
    public String deleteCartItem(@RequestParam(name = "cartCodeList") CartVO cartVO){
        cartService.deleteCartItem(cartVO);
        return "redirect:/cart/list";
    }

    //장바구니 상품 수량 변경
    @PostMapping("/updateCartItemCntFetch")
    @ResponseBody
    public void updateCartItemCnt(CartVO cartVO){
        cartService.updateCartItemCnt(cartVO);
    }


}
