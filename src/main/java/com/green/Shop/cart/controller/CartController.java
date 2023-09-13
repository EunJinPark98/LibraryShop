package com.green.Shop.cart.controller;

import com.green.Shop.cart.service.CartService;
import com.green.Shop.cart.vo.CartVO;
import com.green.Shop.member.vo.MemberVO;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    //장바구니 아이템 추가
    @ResponseBody
    @PostMapping("/insertCart")
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
    public String deleteCartItem(String[] cartCodes){
        for(String cartCode : cartCodes) {
            cartService.deleteCartItem(cartCode);
        }
        return "redirect:/cart/list";
    }

    //장바구니 상품 수량 변경
    @PostMapping("/updateCartItemCnt")
    @ResponseBody
    public void updateCartItemCnt(CartVO cartVO){
        cartService.updateCartItemCnt(cartVO);
    }


}
