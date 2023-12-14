package com.zpy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zpy.pojo.Order;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderMapper extends BaseMapper<Order> {
    List<Order> listOrder(Integer id);

    Order getOrderById(Integer id);

    List<Order> listMyOrders(Integer userId,String foodName);

    List<Order> myCart(Integer userId);

    int payOrder(Integer userId);

    List<Order> myCollection(Integer userId);

    Order invoice(Integer orderId);
}
