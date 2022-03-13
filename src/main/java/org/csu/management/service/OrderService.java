package org.csu.management.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.csu.management.domain.LineItem;
import org.csu.management.domain.Order;
import org.csu.management.entity.OrderStatus;
import org.csu.management.mapper.LineItemMapper;
import org.csu.management.mapper.OrderMapper;
import org.csu.management.mapper.OrderStatusMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

/**
 * @Description
 * @Date 2022/3/13 7:39 下午
 * @Author RessMatthew
 * @Version 1.0
 **/

@Service
public class OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderStatusMapper orderStatusMapper;

    @Autowired
    private LineItemMapper lineItemMapper;



    public List<Order> findAllOrder(){

        return orderMapper.selectList(null);
    }

    /**
     * @Description 订单status为P表示未发货，O表示已发货,P表示删除
     **/
    public List<Order> findAllNotShippedOrder(){
        List<Order> allOrder = findAllOrder();
        Iterator<Order> allOrderIterator = allOrder.iterator();

        int i=0;
        while(allOrderIterator.hasNext()&&i<allOrder.size()){
            Order order = allOrder.get(i);
            String status =  findStatusByOrderId(order.getOrderId());
            order.setStatus(status);
            if(!status.equals("P")){
                allOrder.remove(i);
                i--;
            }
            i++;
        }
        return allOrder;
    }
    public List<Order> findAllShippedOrder(){
        List<Order> allOrder = findAllOrder();
        Iterator<Order> allOrderIterator = allOrder.iterator();

        int i=0;
        while(allOrderIterator.hasNext()&&i<allOrder.size()){
            Order order = allOrder.get(i);
            String status =  findStatusByOrderId(order.getOrderId());
            order.setStatus(status);
            if(!status.equals("O")){
                allOrder.remove(i);
                i--;
            }
            i++;
        }
        return allOrder;
    }
    public List<Order> findAllDeletedOrder(){
        List<Order> allOrder = findAllOrder();
        Iterator<Order> allOrderIterator = allOrder.iterator();

        int i=0;
        while(allOrderIterator.hasNext()&&i<allOrder.size()){
            Order order = allOrder.get(i);
            String status =  findStatusByOrderId(order.getOrderId());
            order.setStatus(status);
            if(!status.equals("D")){
                allOrder.remove(i);
                i--;
            }
            i++;
        }
        return allOrder;
    }

    /**
     * @Description 由orderId得到status
     **/
    public String findStatusByOrderId(int orderId){
        QueryWrapper<OrderStatus> wrapper = new QueryWrapper<>();
        wrapper.eq("orderid",orderId);
        OrderStatus orderStatus = orderStatusMapper.selectOne(wrapper);
        return orderStatus.getStatus();
    }

    public List<LineItem> findLienItemListByOrderId(int orderId){
        QueryWrapper<LineItem> wrapper = new QueryWrapper<>();
        wrapper.eq("orderid",orderId);
        List<LineItem> lineItems = lineItemMapper.selectList(wrapper);
        return lineItems;
    }

    /**
     * @Description //TODO 发货
     * @Date 11:25 下午 2022/3/13
     **/
    public void updateStatusByOrderId(int orderId){
        QueryWrapper<OrderStatus> wrapper = new QueryWrapper<>();
        wrapper.eq("orderid",orderId);
        OrderStatus orderStatus = orderStatusMapper.selectOne(wrapper);
        orderStatus.setStatus("O");

        orderStatusMapper.updateById(orderStatus);

    }
    /**
     * @Description //TODO 删除订单
     * @Date 11:25 下午 2022/3/13
     **/
    public void updateStatusForDeletedByOrderId(int orderId){
        QueryWrapper<OrderStatus> wrapper = new QueryWrapper<>();
        wrapper.eq("orderid",orderId);
        OrderStatus orderStatus = orderStatusMapper.selectOne(wrapper);
        orderStatus.setStatus("D");

        orderStatusMapper.updateById(orderStatus);

    }

    public void updateOrderByOrder(Order order){
        QueryWrapper<Order> wrapper = new QueryWrapper<>();
        wrapper.eq("orderid",order.getOrderId());
        Order newOrder = orderMapper.selectOne(wrapper);

        newOrder.setUsername(order.getUsername());
        newOrder.setOrderDate(order.getOrderDate());
        newOrder.setShipAddress1(order.getShipAddress1());
        newOrder.setShipAddress2(order.getShipAddress2());
        newOrder.setShipCity(order.getShipCity());
        newOrder.setShipState(order.getShipState());
        newOrder.setShipZip(order.getShipZip());
        newOrder.setShipCountry(order.getShipCountry());
        newOrder.setBillAddress1(order.getBillAddress1());
        newOrder.setBillAddress2(order.getBillAddress2());
        newOrder.setBillCity(order.getBillCity());
        newOrder.setBillState(order.getBillState());
        newOrder.setBillZip(order.getBillZip());
        newOrder.setBillCountry(order.getBillCountry());
        newOrder.setCourier(order.getCourier());
        newOrder.setTotalPrice(order.getTotalPrice());
        newOrder.setBillToFirstName(order.getBillToFirstName());
        newOrder.setBillToLastName(order.getBillToLastName());
        newOrder.setShipToFirstName(order.getShipToFirstName());
        newOrder.setShipToLastName(order.getShipToLastName());
        newOrder.setCreditCard(order.getCreditCard());
        newOrder.setExpiryDate(order.getExpiryDate());
        newOrder.setCardType(order.getCardType());
        newOrder.setLocale(order.getLocale());


        orderMapper.updateById(newOrder);




    }
}
