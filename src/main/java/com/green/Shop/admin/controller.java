package com.green.Shop.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class controller {

    @GetMapping("/regItem")
    public String regItem(){
        return "";
    }
}
