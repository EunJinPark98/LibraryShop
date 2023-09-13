package com.green.Shop.test;

import com.green.Shop.item.vo.ItemVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

//html, js에서 넘어오는 데이터를 받는 방식
@Controller
@RequestMapping("/test1")
public class TestController1 {

    @GetMapping("/test1")
    public String test1(){
        return "test/test1/test1";
    }

    @PostMapping("/test2")
    public String test2(@RequestParam(name = "requestName", required = false) String name
                        , @RequestParam int age
                        , @RequestParam(required = false, defaultValue = "java") String name2
                        , @ModelAttribute ItemVO itemVO){
        System.out.println(name2); //전달된 name2가 없으면 java가 출력
        return "test/test1/test1";
    }
}
