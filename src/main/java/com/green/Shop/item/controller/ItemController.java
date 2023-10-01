package com.green.Shop.item.controller;

import com.green.Shop.item.service.ItemService;
import com.green.Shop.item.vo.ItemVO;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/item")
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;


    //메인 페이지
    @GetMapping("/main")
    public String shopMain(Model model, ItemVO itemVO){
        //페이지
        itemVO.setTotalDataCnt(itemService.totalDataCnt());
        itemVO.setPageInfo();

        //아이템 목록
        model.addAttribute("itemList", itemService.displayItemList(itemVO));

        return "/content/item/main";
    }

    //아이템 상세 페이지
    @GetMapping("/detail")
    public String itemDetail(String itemCode, Model model){
        model.addAttribute("item", itemService.itemDetail(itemCode));
        return "/content/item/item_detail";
    }

}
