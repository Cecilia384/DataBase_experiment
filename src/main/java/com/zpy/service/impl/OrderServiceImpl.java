package com.zpy.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageInfo;
import com.zpy.mapper.OrderMapper;
import com.zpy.pojo.Food;
import com.zpy.pojo.Order;
import com.zpy.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order>implements OrderService {
    @Autowired
    private OrderMapper orderMapper;
//    @Override
//    public PageInfo<Order> listOrder(Integer id) {
//        List<Order>list=orderMapper.listOrder(id);
//        return new PageInfo<>(list);
//    }

    @Override
    public Order getOrderById(Integer id) {

        return orderMapper.getOrderById(id) ;
    }

    @Override
    public PageInfo<Order> listOrder(Integer id) {
        List<Order>list=orderMapper.listOrder(id);
        return new PageInfo<>(list);
    }

    @Override
    public PageInfo<Order> listMyOrders(Integer userId,String foodName) {
        List<Order>list=orderMapper.listMyOrders(userId,foodName);
        return new PageInfo<>(list);
    }

    @Override
    public PageInfo<Order> myCart(Integer userId) {
        List<Order>list=orderMapper.myCart(userId);
        return new PageInfo<>(list);
    }

    @Override
    public int payOrder(Integer userId) {
        return orderMapper.payOrder(userId);
    }

    @Override
    public PageInfo<Order> myCollection(Integer userId) {
        List<Order>myCollection=orderMapper.myCollection(userId);
        return new PageInfo<>(myCollection);
    }

    @Override
    public Order getInvoiceById(Integer orderId) {
        return orderMapper.invoice(orderId);
    }
}
