package com.green.Shop.interceptor;

import com.green.Shop.admin.vo.SubMenuVO;
import com.green.Shop.item.service.ItemService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class CateInterceptor implements HandlerInterceptor {
    private final ItemService itemService;

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        modelAndView.addObject("categoryList", itemService.selectCategory());
//컨트롤러의 VO로 넘겨진 데이터 받기
//        Map<String, Object> data = modelAndView.getModel();
//        SubMenuVO subMenuVO = (SubMenuVO) data.get("subMenuVO");
//        subMenuVO.setSubMenuName("안녕");
//        modelAndView.addObject("subM", subMenuVO.getSubMenuCode());

    }
}
