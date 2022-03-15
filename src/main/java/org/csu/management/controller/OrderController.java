package org.csu.management.controller;

import org.csu.management.domain.Order;
import org.csu.management.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;

/**
 * @Description
 * @Date 2022/3/14 10:11 上午
 * @Author RessMatthew
 * @Version 1.0
 **/

@Controller
@RequestMapping("/order")
@SessionAttributes(value={"order","allNotShippedOrderList"})
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/viewOrders")
    public String viewOrder(Model model){
        List<Order> allNotShippedOrderList = orderService.findAllNotShippedOrder();
        List<Order> allShippedOrderList = orderService.findAllShippedOrder();
        List<Order> allDeletedOrderList = orderService.findAllDeletedOrder();


        model.addAttribute("allNotShippedOrderList",allNotShippedOrderList);
        model.addAttribute("allShippedOrderList",allShippedOrderList);
        model.addAttribute("allDeletedOrderList",allDeletedOrderList);
        return "/order/ViewOrders";
    }

    @GetMapping("/finalOrder")
    public String viewFinalOrder(int orderId,Model model){
        Order order = orderService.findOneOrderByOrderId(orderId);
        model.addAttribute("order",order);
        return "/order/FinalOrder";
    }

    @GetMapping("/shipOrder")
    public String shipOrder(int orderId){
        orderService.updateStatusByOrderId(orderId);
        return "redirect:/order/viewOrders";
    }

    @GetMapping("/deleteOrder")
    public String deleteOrder(int orderId){
        orderService.updateStatusForDeletedByOrderId(orderId);
        return "redirect:/order/viewOrders";
    }

    @PostMapping("/updateOrder")
    public String updateOrder(Order newOrder){
        orderService.updateOrderByOrder(newOrder);
        System.out.println("更新成功");
        return "redirect:/order/viewOrders";
    }
}
