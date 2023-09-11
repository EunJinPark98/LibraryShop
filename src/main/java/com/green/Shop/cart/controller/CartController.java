package com.green.Shop.cart.controller;

import com.green.Shop.cart.service.CartService;
import com.green.Shop.cart.vo.CartVO;
import com.green.Shop.member.vo.MemberVO;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/cart")
public class CartController {
    @Resource(name = "cartService")
    private CartService cartService;

    //장바구니 아이템 추가
    @PostMapping("/insertCart")
    public String insertCart(CartVO cartVO, HttpSession session){
        MemberVO loginInfo = (MemberVO) session.getAttribute("loginInfo");
        cartVO.setMemberId(loginInfo.getMemberId());
        cartService.insertCart(cartVO);
        return "/content/cart/insert_cart_result";
    }


}
