package com.guohenry.myproject1springboot.service;


import com.guohenry.myproject1springboot.dto.CreateOrderRequest;
import com.guohenry.myproject1springboot.dto.OrderQueryParams;
import com.guohenry.myproject1springboot.model.Order;

import java.util.List;

public interface OrderService {

    Integer countOrder(OrderQueryParams orderQueryParams);

    List<Order> getOrders(OrderQueryParams orderQueryParams);

    Order getOrderById(Integer orderId);

    Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest);

}