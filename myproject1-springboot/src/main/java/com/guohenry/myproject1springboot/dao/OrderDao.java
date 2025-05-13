package com.guohenry.myproject1springboot.dao;

import com.guohenry.myproject1springboot.dto.OrderQueryParams;
import com.guohenry.myproject1springboot.model.Order;
import com.guohenry.myproject1springboot.model.OrderItem;

import java.util.List;

public interface OrderDao {

    Integer countOrder(OrderQueryParams orderQueryParams);

    List<Order> getOrders(OrderQueryParams orderQueryParams);

    List<OrderItem> getOrderItemByOrderId(Integer orderId);

    Order getOrderById(Integer orderId);

    Integer createOrder(Integer userId,Integer totalAmount);

    void createOrderItem(Integer orderId, List<OrderItem> orderItemList);


}