package com.green.Shop.interceptor;

import com.green.Shop.member.vo.MemberVO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

//관리자 권한 체크
@Component
public class CheckAdminInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        MemberVO loginInfo = (MemberVO)session.getAttribute("loginInfo");


        if(loginInfo == null || !loginInfo.getMemberRoll().equals("ADMIN")){
            response.sendRedirect("/item/main");
            return false; //false면 검문소에 걸림 다른 페이지로 이동
        }

        return true; //true면 검문소 통과
    }
}
