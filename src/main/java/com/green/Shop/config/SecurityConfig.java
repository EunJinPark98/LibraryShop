package com.green.Shop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.csrf(AbstractHttpConfigurer::disable) //csrf공격에 대한 보안 해제
                .authorizeHttpRequests(authorize ->
                        //authorize.anyRequest().permitAll() 			//모든 요청 허용
                        authorize.requestMatchers("/"
                                        , "/item/main"
                                        , "/member/joinForm"
                                        , "/member/join"
                                        , "/member/loginForm"
                                        , "/member/idCheckFetch"
                                ).permitAll()  //해당 경로는 허가됨
                                .requestMatchers("/admin/**").hasRole("ADMIN") // 권한이 "ADMIN"만 허가됨 (2개부턴 hasAnyRole)
                                .anyRequest().authenticated() 			//나머지는 인증 필요
                )
                .formLogin(login ->
                        login.loginPage("/member/loginForm")           //로그인 하는 페이지
                                .loginProcessingUrl("/login")   //로그인 처리 UserDetailsService구현메소드 실행
                                .usernameParameter("memberId")  //아이디 입력 input name값
                                .passwordParameter("memberPw")  //비밀번호 입력 input name값
                                .defaultSuccessUrl("/item/main", true)  //로그인 성공 시 이동 경로(true는 경로로, false는 이전페이지로 이동)
                                .failureUrl("/member/loginForm")       //로그인 실패 시 이동 경로
                ).logout(logout ->
                        logout.logoutUrl("/logout") //로그아웃
                                .invalidateHttpSession(true) //로그아웃 시 세션 초기화 true false
                                .logoutSuccessUrl("/") //로그아웃하고 이동경로
                )
                .exceptionHandling(excep ->
                        excep.accessDeniedPage("/member/denyPage") //접근 거부됐을 때 가는 페이지 컨트롤러 경로
                );
        return http.build();
    }

    @Bean
    public WebSecurityCustomizer configure(){
        return (web) -> web.ignoring()
                .requestMatchers("/js/**", "/css/**", "/images/**", "/favicon.ico");
    }


}
