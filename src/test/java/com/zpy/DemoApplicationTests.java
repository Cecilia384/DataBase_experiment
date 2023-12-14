package com.zpy;

import com.github.pagehelper.PageInfo;
import com.zpy.mapper.OrderMapper;
import com.zpy.pojo.Order;
import com.zpy.service.OrderService;
import com.zpy.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class DemoApplicationTests {
    @Autowired
    private ProductService productService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderMapper orderMapper;


    @Test
    void test1() {
        Order invoice = orderService.getInvoiceById(1);
        System.out.println(invoice.toString());

    }

}
