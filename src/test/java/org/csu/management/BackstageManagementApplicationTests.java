package org.csu.management;

import org.csu.management.domain.LineItem;
import org.csu.management.domain.Order;
import org.csu.management.service.OrderService;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@SpringBootTest
@MapperScan("org.csu.management.mapper")
class BackstageManagementApplicationTests {


    @Test
    void contextLoads() {
    }

    @Autowired
    private OrderService orderService;

    @Test
    public void testFindAllOrder(){
        List<Order> orderList= new ArrayList<Order>();
        orderList=orderService.findAllOrder();

        Iterator<Order> it = orderList.iterator();

        // 输出集合中的第一个元素
        System.out.println(it.next());

    }

    @Test
    public void testFindAllNotShippedOrder(){
        List<Order> orderList= new ArrayList<Order>();
        orderList=orderService.findAllNotShippedOrder();

        Iterator<Order> it = orderList.iterator();

        // 输出集合中的第一个元素
        while(it.hasNext()) {
            System.out.println(it.next());
        }
    }

    @Test
    public void testFindAllShippedOrder(){
        List<Order> orderList= new ArrayList<Order>();
        orderList=orderService.findAllShippedOrder();

        Iterator<Order> it = orderList.iterator();

        // 输出集合中的第一个元素
        while(it.hasNext()) {
            System.out.println(it.next());
        }
    }

    @Test
    public void testFindLienItemListByOrderIdr(){
        List<Order> orderList= new ArrayList<Order>();
        orderList=orderService.findAllShippedOrder();
        List<LineItem> lienItemListByOrderId = orderService.findLienItemListByOrderId(orderList.get(0).getOrderId());

        Iterator<LineItem> it = lienItemListByOrderId.iterator();

        // 输出集合中的第一个元素
        while(it.hasNext()) {
            System.out.println(it.next().getItemId());
        }
    }

    @Test
    public void testUpdateStatusByOrderId(){
        orderService.updateStatusByOrderId(1001);
    }

    @Test
    public void testUpdateOrderByOrder(){
        List<Order> orderList= new ArrayList<Order>();
        orderList=orderService.findAllShippedOrder();

        Order newOrder = orderList.get(0);
        newOrder.setShipAddress1("test");

        orderService.updateOrderByOrder(newOrder);

    }



}
