package com.green.Shop.cart.controller;

import com.green.Shop.cart.service.CartService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/cart")
public class CartController {
    @Resource(name = "cartService")
    private CartService cartService;

    @ResponseBody
    @GetMapping("putItem")
    public void loginCheck(HttpSession session){
        if(session.getAttribute("loginInfo")==null){

        }else{

        }
    }


}
