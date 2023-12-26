package com.zpy.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
//import com.sun.org.apache.xpath.internal.operations.Or;
import com.zpy.pojo.Customer;
import com.zpy.pojo.Food;
import com.zpy.pojo.Order;
import com.zpy.service.CustomerService;
import com.zpy.service.FoodService;
import com.zpy.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.awt.print.Book;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("normalUser")
@Slf4j
public class NormalUserController {

    @Autowired
    private FoodService foodService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private CustomerService customerService;
    @RequestMapping("listFood")
    public String list(@RequestParam(required = false,defaultValue = "1",value = "pageNum") Integer pageNum,
                       @RequestParam(required = false,defaultValue = "12",value = "pageSize") Integer pageSize, Model model, Food food) {
        if (pageNum <= 0 || pageNum.equals("") || pageNum == null) {
            pageNum = 1;
        }
        if (pageSize <= 0 || pageSize.equals("") || pageSize == null) {
            pageSize = 12;
        }
        PageHelper.startPage(pageNum, pageSize);
        QueryWrapper<Food> qw=new QueryWrapper<>();
        if (food.getFoodName()!=null){
            qw.like("food_name",food.getFoodName());
        }

        //qw.eq("status",1);
        List<Food> list = foodService.list(qw);
        PageInfo<Food> objectPageInfo = new PageInfo<>(list);
        model.addAttribute("pageInfo", objectPageInfo);
        return "customer-food-list";
    }

    @RequestMapping("myOrders")
    public String myOrders(@RequestParam(required = false,defaultValue = "1",value = "pageNum") Integer pageNum,
                           @RequestParam(required = false,defaultValue = "12",value = "pageSize") Integer pageSize, Model model, Food food, HttpSession session){


        if (pageNum <= 0 || pageNum.equals("") || pageNum == null) {
            pageNum = 1;
        }
        if (pageSize <= 0 || pageSize.equals("") || pageSize == null) {
            pageSize = 12;
        }
        Integer userId =(Integer) session.getAttribute("userId");
        PageHelper.startPage(pageNum, pageSize);
        String foodName = food.getFoodName();
        PageInfo<Order>pageInfo=orderService.listMyOrders(userId,foodName);
        model.addAttribute("pageInfo",pageInfo);
        return "myOrders-list";
    }

    @RequestMapping("delMyOrder/{id}")
    public String delMyOrder(@PathVariable Integer id){
        boolean b = orderService.removeById(id);
        return "redirect:/normalUser/myOrders";
    }

    @RequestMapping("preUpdateMyOrder/{id}")
    public String preUpdateMyOrder(@PathVariable Integer id,Model model){
        Order order = orderService.getById(id);
        Integer cid = order.getCid();
        Integer fid = order.getFid();
        Customer customer = customerService.getById(cid);
        Food food = foodService.getById(fid);
        model.addAttribute("food",food);
        model.addAttribute("customer",customer);
        model.addAttribute("order",order);
        return "myOrders-update";
    }

    @RequestMapping("updateMyOrder")
    public String updateMyOrder(Integer id,Integer count){
        Order order = orderService.getById(id);
        Integer oldCount = order.getCount();
        Integer fid = order.getFid();
        Food food = foodService.getById(fid);
        order.setCount(count);
        order.setTotal(count* food.getPrice());
        orderService.updateById(order);
        //修改库存
        if (oldCount>count){
            //库存加
            food.setStock(food.getStock()+(oldCount-count));
            foodService.updateById(food);
        }else {
            //库存减
            food.setStock(food.getStock()-(count-oldCount));
            foodService.updateById(food);
        }
        return "redirect:/normalUser/myOrders";
    }

    //批量删除
    @PostMapping("batchDeleteMyOrder")
    @ResponseBody
    public String batchDeleteReaderBook(String idList){
        String[]split= StrUtil.split(idList,",");
        List<Integer> list=new ArrayList<>();

        for (String s : split) {
            if (!s.isEmpty()){
                int i = Integer.parseInt(s);
                list.add(i);
            }
        }
        boolean b = orderService.removeByIds(list);
        if (b){
            return "OK";
        }else {
            return "error";
        }
    }

    @RequestMapping("cart")
    public String cart(Model model,HttpSession session){
        Integer userId =(Integer) session.getAttribute("userId");
        PageInfo<Order>pageInfo=orderService.myCart(userId);
        double a=0;
        List<Order> list = pageInfo.getList();
        for (Order order : list) {
            a+=order.getTotal();
        }
        model.addAttribute("totalMoney",a);
        model.addAttribute("pageInfo",pageInfo);
        return "cart";
    }

    @RequestMapping("updateOrder")
    @ResponseBody
    public String updateOrder(Integer orderId,Integer count){
        Order order = orderService.getById(orderId);
        Integer oldCount = order.getCount();
        Integer fid = order.getFid();
        Food food = foodService.getById(fid);
        Double price = food.getPrice();
        order.setId(orderId);
        order.setCount(count);
        order.setTotal(count*price);
        boolean b = orderService.updateById(order);
        //修改库存
        log.info(oldCount+"");
        log.info(count+"");
        if (oldCount>count){
            food.setStock(food.getStock()+(oldCount-count));
            foodService.updateById(food);
        }else {
            food.setStock(food.getStock()-(count-oldCount));
            foodService.updateById(food);
        }

        if (b){
            return "OK";
        }else {
            return "error";
        }


    }

    @RequestMapping("payOrder")
    public String payOrder(HttpSession session){
        Integer userId = (Integer) session.getAttribute("userId");

        int i=orderService.payOrder(userId);
        return "redirect:/normalUser/myOrders";
    }

    @RequestMapping("collection")
    public String collection(@RequestParam(required = false,defaultValue = "1",value = "pageNum") Integer pageNum,
                             @RequestParam(required = false,defaultValue = "12",value = "pageSize") Integer pageSize, Model model,HttpSession session){
        if (pageNum <= 0 || pageNum.equals("") || pageNum == null) {
            pageNum = 1;
        }
        if (pageSize <= 0 || pageSize.equals("") || pageSize == null) {
            pageSize = 12;
        }
        PageHelper.startPage(pageNum, pageSize);
        Integer userId =(Integer) session.getAttribute("userId");
        PageInfo<Order>pageInfo=orderService.myCollection(userId);
        model.addAttribute("pageInfo",pageInfo);
        return "myCollection";
    }

    @RequestMapping("collectionToCart/{orderId}")
    public String collectionToCart(@PathVariable Integer orderId){
        Order order = orderService.getById(orderId);
        order.setIsorder(0);
        orderService.updateById(order);
        return "redirect:/normalUser/cart";
    }



    @RequestMapping("toFoodSingle/{foodId}")
    public String toFoodSingle(@PathVariable  Integer foodId,Model model){
        Food food = foodService.getById(foodId);
        model.addAttribute("food",food);
        QueryWrapper<Food>qw=new QueryWrapper<>();
        qw.eq("store",food.getStore());
        List<Food> list = foodService.list(qw);
        model.addAttribute("foodList",list);
        return "food-single";
    }

    @RequestMapping("foodToCart/{foodId}")
    public String foodToCart(@PathVariable Integer foodId,HttpSession session){

        Integer userId =(Integer) session.getAttribute("userId");
        QueryWrapper<Order>qw=new QueryWrapper<>();
        qw.eq("cid",userId);
        qw.eq("fid",foodId);
        qw.eq("isorder",0);
        Order one = orderService.getOne(qw);
        if (one!=null){
            session.setAttribute("msg","此菜品已加入小餐车");
            return "redirect:/normalUser/listFood";
        }
        Food food = foodService.getById(foodId);
        Order order = new Order();
        order.setFid(foodId);
        order.setCid(userId);
        order.setCount(1);
        order.setTotal(food.getPrice());
        orderService.save(order);
        session.removeAttribute("msg");
        return "redirect:/normalUser/cart";
    }

    @RequestMapping("delCart/{orderId}")
    public String delCart(@PathVariable Integer orderId,HttpSession session){

        boolean remove = orderService.removeById(orderId);
        return "redirect:/normalUser/cart";
    }

    @RequestMapping("foodToCollection/{foodId}")
    public String foodToCollection(@PathVariable Integer foodId,HttpSession session){
        Integer userId =(Integer) session.getAttribute("userId");
        QueryWrapper<Order>qw=new QueryWrapper<>();
        qw.eq("cid",userId);
        qw.eq("fid",foodId);
        qw.eq("isorder",2);
        Order order = orderService.getOne(qw);
        if (order!=null){
            session.setAttribute("msg","此菜品已收藏");
            return "redirect:/normalUser/listFood";
        }
        Food food = foodService.getById(foodId);
        Order order1 = new Order();
        order1.setFid(foodId);
        order1.setCid(userId);
        order1.setCount(1);
        order1.setTotal(food.getPrice());
        order1.setIsorder(2);
        orderService.save(order1);
        session.removeAttribute("msg");
        return "redirect:/normalUser/collection";
    }

    //取消收藏
    @RequestMapping("delCollection/{orderId}")
    public String delCollection(@PathVariable Integer orderId){
        boolean b = orderService.removeById(orderId);
        return "redirect:/normalUser/collection";
    }

    @RequestMapping("confirmOrder/{orderId}")
    public String confirmOrder(@PathVariable Integer orderId){
        Order order = orderService.getById(orderId);
        order.setStatus(2);
        orderService.updateById(order);
        return "redirect:/normalUser/myOrders";
    }

    @RequestMapping("invoice/{orderId}")
    public String invoice(@PathVariable Integer orderId,Model model){
        Order order = orderService.getInvoiceById(orderId);
        model.addAttribute("order",order);
        return "invoice";

    }


}
