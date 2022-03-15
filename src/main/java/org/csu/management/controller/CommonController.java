package org.csu.management.controller;

import org.csu.management.domain.Item;
import org.csu.management.domain.Order;
import org.csu.management.service.CommodityService;
import org.csu.management.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @Description 从index跳转到主页面
 * @Date 2022/3/13 4:52 下午
 * @Author RessMatthew
 * @Version 1.0
 **/

@Controller
@RequestMapping("/common")
@SessionAttributes(value={"allNotShippedOrderList","NoQTYItemList"})
public class CommonController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private CommodityService commodityService;

    @GetMapping("/main")
    public String ViewMain(Model model){
        List<Order> allNotShippedOrderList = orderService.findAllNotShippedOrder();
        model.addAttribute("allNotShippedOrderList",allNotShippedOrderList);

        List<Item> allItemList = commodityService.getAllItemList();
        List<Item> NoQTYItemList =new ArrayList<>();
        Iterator<Item> allItemListIterator=allItemList.iterator();

        /**
         * @Description //TODO 迭代判断qty小于100提示
         * @Date 2:35 下午 2022/3/15
         **/
        while (allItemListIterator.hasNext()){
            Item item = allItemListIterator.next();
            if(item.getQuantity()<=100){
                NoQTYItemList.add(item);
            }
        }
        model.addAttribute("NoQTYItemList",NoQTYItemList);
        return "/common/Main";
    }
}
