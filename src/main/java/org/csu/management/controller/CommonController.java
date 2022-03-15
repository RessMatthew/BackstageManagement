package org.csu.management.controller;

import org.csu.management.domain.Order;
import org.csu.management.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;

/**
 * @Description 从index跳转到主页面
 * @Date 2022/3/13 4:52 下午
 * @Author RessMatthew
 * @Version 1.0
 **/

@Controller
@RequestMapping("/common")
@SessionAttributes("allNotShippedOrderList")
public class CommonController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/main")
    public String ViewMain(Model model){
        List<Order> allNotShippedOrderList = orderService.findAllNotShippedOrder();
        model.addAttribute("allNotShippedOrderList",allNotShippedOrderList);
        return "/common/Main";
    }
}
