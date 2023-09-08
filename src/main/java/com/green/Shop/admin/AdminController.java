package com.green.Shop.admin;

import com.green.Shop.item.vo.ItemSearchVO;
import com.green.Shop.item.vo.ItemVO;
import com.green.Shop.util.ConstantVariable;
import jakarta.annotation.Resource;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

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
    public String regItem(MultipartFile mainImg){
        //첨부파일
        String originFileName = mainImg.getOriginalFilename();
        //첨부파일 저장 위치
        //첨부될 파일 명 설정 (랜덤명 + 확장자)
        String uuid = UUID.randomUUID().toString();
        String extension = originFileName.substring(originFileName.lastIndexOf("."));
        String attachedFileName = uuid + extension;

        try {
            //파일 첨부
            File file = new File(ConstantVariable.UPLOAD_PATH + attachedFileName);
            mainImg.transferTo(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //adminService.insertItem(itemVO);
        return "redirect:/admin/regItemForm";
    }

    //상품관리 페이지로 이동
    @RequestMapping("/itemManage")
    public String itemManageForm(ItemSearchVO itemSearchVO, Model model){
        model.addAttribute("itemList", adminService.selectItemList(itemSearchVO));
        model.addAttribute("cateList", adminService.selectCateList());
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
