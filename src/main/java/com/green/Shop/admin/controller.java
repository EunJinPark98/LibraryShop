package com.green.Shop.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class controller {

    //상품 등록 페이지로 이동
    @GetMapping("/regItemForm")
    public String regItemForm(){
        return "content/admin/reg_item";
    }
}
