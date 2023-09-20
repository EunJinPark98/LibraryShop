package com.green.Shop.interceptor;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class InterceptorConfig implements WebMvcConfigurer {
    private final CheckAdminInterceptor adminInterceptor;
    private final MenuInterceptor menuInterceptor;
    private final CateInterceptor cateInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //관리자 권한 확인
        registry.addInterceptor(adminInterceptor)
                .addPathPatterns("/admin/**")
                .excludePathPatterns("/admin/**Fetch");

        //관리자 메뉴 조회
        registry.addInterceptor(menuInterceptor)
                .addPathPatterns("/admin/**")
                .excludePathPatterns("/admin/**Fetch");

        //카테고리 조회
        registry.addInterceptor(cateInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/**/**Fetch");

    }
}
