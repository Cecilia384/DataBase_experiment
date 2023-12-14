package com.zpy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.zpy.pojo.Food;
import com.zpy.pojo.Order;

public interface OrderService extends IService<Order> {
//    PageInfo<Order> listOrder(Integer id);

    //根据订单id获取此订单(包含product和customer的信息)
    Order getOrderById(Integer id);

    PageInfo<Order> listOrder(Integer id);

    PageInfo<Order> listMyOrders(Integer userId,String foodName);


    PageInfo<Order> myCart(Integer userId);

    int payOrder(Integer userId);

    PageInfo<Order> myCollection(Integer userId);

    Order getInvoiceById(Integer orderId);
}
