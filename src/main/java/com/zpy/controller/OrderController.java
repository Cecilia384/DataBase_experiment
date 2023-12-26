package com.zpy.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zpy.pojo.*;
import com.zpy.service.CustomerService;
import com.zpy.service.FoodService;
import com.zpy.service.OrderService;
import com.zpy.service.ProductService;
import com.zpy.utils.MailUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("order")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private ProductService productService;
    @Autowired
    private FoodService foodService;

    //查询所有订单
    @RequestMapping("/listOrder")
    public String list(@RequestParam(required = false,value = "pageNum",defaultValue = "1")Integer pageNum,
                       @RequestParam(required = false,value = "pageSize",defaultValue = "10")Integer pageSize, Model model,
                       Food food){
        if (pageNum<=0||pageNum.equals("")||pageNum==null){
            pageNum=1;
        }
        if (pageSize<=0||pageSize.equals("")||pageSize==null){
            pageSize=10;
        }

        Integer id = food.getId();
        PageHelper.startPage(pageNum,pageSize);
        PageInfo<Order> list = orderService.listOrder(id);
        model.addAttribute("pageInfo",list);
        return "order-list";
    }

    //跳转到添加订单页面
    @RequestMapping("preSaveOrder")
    public String preBorrow(Model model) {
        List<Food> foodList = foodService.list(null);
        List<Customer> customerList = customerService.list(null);
        model.addAttribute("customerList",customerList);
        model.addAttribute("foodList",foodList);
        return "order-save";
    }

    //添加订单
    @RequestMapping("saveOrder")
    public String borrow(Integer cid,Integer fid,Integer count,Model model){
        Order order = new Order();
        order.setCid(cid);
        order.setFid(fid);
        Food byId = foodService.getById(fid);
        if (count>byId.getStock()){
            model.addAttribute("msg","此商品库存不足，无法购买");
            return "order-save";
        }
        Double total=byId.getPrice()*count;
        order.setTotal(total);
        order.setCount(count);
        order.setIsorder(1);
        boolean save = orderService.save(order);
        //库存减去订单数量
        byId.setStock(byId.getStock()-count);
        boolean b = foodService.updateById(byId);
        return "redirect:/order/listOrder";
    }

    //跳转到修改订单的页面
    @RequestMapping("preUpdateOrder/{id}")
    public String preUpdateOrder(@PathVariable Integer id,Model model){
        Order order = orderService.getOrderById(id);
        List<Customer> customerList = customerService.list(null);
        List<Food> foodList = foodService.list(null);
        model.addAttribute("order",order);
        model.addAttribute("customerList",customerList);
        model.addAttribute("foodList",foodList);
        return "order-update";
    }

    //修改订单
    @RequestMapping("updateOrder")
    public String update(Integer cid, Integer fid, Integer count){
        QueryWrapper<Order>qw=new QueryWrapper<>();
        qw.eq("cid",cid);
        qw.eq("fid",fid);
        Order order = orderService.getOne(qw);
        Integer oldCount = order.getCount();
        order.setTotal(foodService.getById(fid).getPrice()*count);
        order.setCount(count);
        orderService.updateById(order);
        //修改库存
        if (oldCount>=count){
            Food food = foodService.getById(fid);
            food.setStock(food.getStock()+(oldCount-count));
            foodService.updateById(food);
        }else {
            Food food = foodService.getById(fid);
            food.setStock(food.getStock()-(count-oldCount));
            foodService.updateById(food);
        }
        return "redirect:/order/listOrder";
    }

    //删除订单
    @RequestMapping("delOrder/{id}")
    public String delReaderBook(@PathVariable("id") Integer id){
        boolean b = orderService.removeById(id);
        return "redirect:/order/listOrder";
    }

    //批量删除
    @PostMapping("batchDeleteOrder")
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

    @RequestMapping("sendMessage/{email}/{foodName}/{orderId}")
    public String sendMessage(@PathVariable String email,@PathVariable String foodName,@PathVariable Integer orderId){
        MailUtils.sendMail(email,"您订购的"+foodName+"正在准备中，请稍等","餐馆邮件");
        Order byId = orderService.getById(orderId);
        byId.setStatus(1);
        orderService.updateById(byId);
        return "redirect:/order/listOrder";
    }
}
