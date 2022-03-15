package org.csu.management;

import org.csu.management.domain.*;
import org.csu.management.service.CommodityService;
import org.csu.management.service.OrderService;

import org.csu.management.service.UserService;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@SpringBootTest
@MapperScan("org.csu.management.mapper")
class BackstageManagementApplicationTests {


    @Autowired
    private OrderService orderService;

    @Autowired
    private CommodityService commodityService;

    @Autowired
    private UserService userService;


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

    @Test
    public void testGetAllItemList(){
        List<Item> allItemList = commodityService.getAllItemList();
        Iterator<Item> it = allItemList.iterator();

        while(it.hasNext()) {
            System.out.println(it.next());
        }
    }

    @Test
    public void testAllCategoryList(){
        List<Category> allCategoryList = commodityService.getAllCategoryList();
        Iterator<Category> it = allCategoryList.iterator();

        while(it.hasNext()) {
            System.out.println(it.next());
        }
    }

    @Test
    public void testGetAllProductList(){
        List<Product> allProductList = commodityService.getAllProductList();
        Iterator<Product> it = allProductList.iterator();

        while(it.hasNext()) {
            System.out.println(it.next());
        }
    }

    @Test
    public void tetssearchUserList(){
        List<User> userList = userService.searchUserList();

        Iterator<User> it = userList.iterator();

        while(it.hasNext()) {
            System.out.println(it.next());
        }
    }

    @Test
    public void testgetItemByItemId(){
        Item item = commodityService.getItemByItemId("EST-18");
        System.out.println(item);

    }

    @Test
    public void testUpdateItemByItem(){
        Item item = commodityService.getItemByItemId("EST-18");
        item.setStatus("O");
        commodityService.updateItem(item);
        System.out.println("更新成功");
    }

    @Test
    public void testgetProductByProductId(){
        Product product = commodityService.getProductByProductId("AV-CB-01");
        System.out.println(product);
    }

}
