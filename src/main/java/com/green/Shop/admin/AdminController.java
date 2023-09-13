package com.green.Shop.admin;

import com.green.Shop.item.vo.ImgVO;
import com.green.Shop.item.vo.ItemSearchVO;
import com.green.Shop.item.vo.ItemVO;
import com.green.Shop.util.ConstantVariable;
import com.green.Shop.util.UploadUtil;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
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
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminService;

    //상품 등록 페이지로 이동
    @GetMapping("/regItemForm")
    public String regItemForm(Model model){
        model.addAttribute("cateList", adminService.selectCateList());
        return "content/admin/reg_item";
    }

    //상품 등록
    @PostMapping("/regItem")
    public String regItem(ItemVO itemVO, MultipartFile mainImg, MultipartFile[] subImg){
        //첨부파일 기능 단일
        ImgVO vo = UploadUtil.uploadFile(mainImg);
        //첨부파일 기능 다중
        List<ImgVO> imgList = UploadUtil.multiFileUpload(subImg);
        imgList.add(vo);

        //다음에 들어갈 코드들
        String itemCode = adminService.selectNextItemCode();
        for(ImgVO imgVO : imgList){
            imgVO.setItemCode(itemCode);
        }

        itemVO.setImgList(imgList);

        itemVO.setItemCode(itemCode);
        adminService.insertItem(itemVO);

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
