package com.green.Shop.admin;

import com.green.Shop.item.vo.ItemVO;
import jakarta.annotation.Resource;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Resource(name = "adminService")
    private AdminService adminService;

    //상품 등록 페이지로 이동
    @GetMapping("/regItemForm")
    public String regItemForm(Model model){
        model.addAttribute("cateList", adminService.selectCateList());
        return "content/admin/reg_item";
    }

    //상품 등록
    @PostMapping("/regItem")
    public String regItem(ItemVO itemVO){
        adminService.insertItem(itemVO);
        return "redirect:/admin/regItemForm";
    }

    //상품관리 페이지로 이동
    @GetMapping("/itemManage")
    public String itemManageForm(Model model){
        model.addAttribute("itemList", adminService.selectItemList());
        return "/content/admin/item_manage";
    }

    //재고 수정
    @PostMapping("/updateStock")
    public String updateStock(ItemVO itemVO){
        adminService.updateStock(itemVO);
        return "redirect:/admin/itemManage";
    }

    //상품 상태 수정
    @ResponseBody
    @PostMapping("/updateStatus")
    public void updateStatus(ItemVO itemVO){
        adminService.updateStatus(itemVO);
    }



}
