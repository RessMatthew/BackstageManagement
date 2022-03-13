package org.csu.management.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Description 从index跳转到主页面
 * @Date 2022/3/13 4:52 下午
 * @Author RessMatthew
 * @Version 1.0
 **/

@Controller
@RequestMapping("/common")
public class CommonController {

    @GetMapping("/main")
    public String ViewMain(){
        return "/common/Main";
    }
}
