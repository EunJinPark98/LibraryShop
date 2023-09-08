package com.green.Shop.admin;

import com.green.Shop.item.vo.ImgVO;
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
import java.util.ArrayList;
import java.util.List;
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
        //첨부될 파일 명 설정
        String uuid = UUID.randomUUID().toString(); //랜덤
        String extension = originFileName.substring(originFileName.lastIndexOf(".")); //확장자
        String attachedFileName = uuid + extension;
        try {
            File file = new File(ConstantVariable.UPLOAD_PATH + attachedFileName);
            mainImg.transferTo(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //다음에 들어갈 코드들 (001 002 ... 증가 코드이기 때문에 따로 쿼리 만듦)
        String imgCode = adminService.selectNextImgCode();
        String itemCode = adminService.selectNextItemCode();

        //상품 이미지 등록
        List<ImgVO> imgList = new ArrayList<>();
        ImgVO imgVO = new ImgVO();
        imgVO.setOriginFileName(originFileName);
        imgVO.setAttachedFileName(attachedFileName);
        imgVO.setIsMain("Y");
        imgVO.setImgCode(imgCode);
        imgVO.setItemCode(itemCode);

        //itemVO.setItemCode(itemCode);
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
