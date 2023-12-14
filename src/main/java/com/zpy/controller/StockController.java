package com.zpy.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zpy.pojo.Food;
import com.zpy.pojo.Product;
import com.zpy.service.FoodService;
import com.zpy.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Book;
import java.util.List;

@Controller
@RequestMapping("stock")
public class StockController {
    @Autowired
    private FoodService foodService;

    //查询所有商品
    @RequestMapping("listStock")
    public String list(@RequestParam(required = false,defaultValue = "1",value = "pageNum") Integer pageNum,
                       @RequestParam(required = false,defaultValue = "10",value = "pageSize") Integer pageSize, Model model, Food food) {
        if (pageNum <= 0 || pageNum.equals("") || pageNum == null) {
            pageNum = 1;
        }
        if (pageSize <= 0 || pageSize.equals("") || pageSize == null) {
            pageSize = 10;
        }
        PageHelper.startPage(pageNum, pageSize);
        //查询所有已上架的商品
//        List<Product> list = productService.listAll(product.getProductName());
        QueryWrapper<Food>qw=new QueryWrapper<>();
        if (food.getStock()!=null){
            qw.like("food_name",food.getFoodName());
        }
        qw.eq("status",1);
        List<Food> list = foodService.list(qw);
        PageInfo<Food> objectPageInfo = new PageInfo<>(list);
        model.addAttribute("pageInfo", objectPageInfo);
        return "stock-list";
    }



    //添加库存
    @RequestMapping("addStock/{id}")
    public String addStock(@PathVariable  Integer id){
        Food byId = foodService.getById(id);
        byId.setStock(byId.getStock()+100);
        foodService.updateById(byId);
        return "redirect:/stock/listStock";
    }
}
