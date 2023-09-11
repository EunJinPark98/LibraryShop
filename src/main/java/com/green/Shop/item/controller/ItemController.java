package com.green.Shop.item.controller;

import com.green.Shop.item.service.ItemService;
import com.green.Shop.item.vo.ItemVO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/item")
public class ItemController {
    @Resource(name="itemService")
    private ItemService itemService;

    //메인 페이지
    @GetMapping("/main")
    public String shopMain(Model model){
        List<ItemVO> itemList = itemService.displayItemList();
        model.addAttribute("itemList", itemList);
        System.out.println(itemList);
        return "content/item/main";
    }

    //아이템 상세 페이지
    @GetMapping("/detail")
    public String itemDetail(String itemCode, Model model){
        model.addAttribute("item", itemService.itemDetail(itemCode));
        return "content/item/item_detail";
    }

}
