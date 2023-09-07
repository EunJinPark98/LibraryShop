package com.green.Shop.item.controller;

import com.green.Shop.item.service.ItemService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/item")
public class ItemController {
    @Resource(name="itemService")
    private ItemService itemService;

    @GetMapping("/main")
    public String shopMain(Model model){
        model.addAttribute("displayItemList", itemService.displayItemList());
        return "content/item/main";
    }

}
