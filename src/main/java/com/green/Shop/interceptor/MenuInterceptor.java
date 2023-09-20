package com.green.Shop.interceptor;

import com.green.Shop.admin.service.MenuService;
import com.green.Shop.admin.vo.SubMenuVO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class MenuInterceptor implements HandlerInterceptor {
    private final MenuService menuService;

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        modelAndView.addObject("menuList", menuService.selectMenuList());

        Map<String, Object> data = modelAndView.getModel();

//        SubMenuVO subMenuVO = (SubMenuVO) data.get("subMenuVO");

        if(data.get("subMenuVO") != null){
            SubMenuVO subMenuVO = (SubMenuVO) data.get("subMenuVO");
            subMenuVO.setSubMenuCode("1234111");
            modelAndView.addObject("subMenuVO", subMenuVO.getSubMenuCode());
        }else{
            SubMenuVO subMenuVO = new SubMenuVO();
            subMenuVO.setSubMenuCode("1234111");
            modelAndView.addObject("subMenuVO", subMenuVO.getSubMenuCode());
        }




    }
}
