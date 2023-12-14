package com.zpy.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zpy.pojo.Food;
import com.zpy.pojo.Store;
import com.zpy.service.FoodService;
import com.zpy.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.awt.print.Book;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("store")
public class StoreController {
    @Autowired
    private FoodService foodService;

    @Autowired
    private StoreService storeService;

    @RequestMapping("listStore")
    public String list(@RequestParam(required = false,defaultValue = "1",value = "pageNum") Integer pageNum,
                       @RequestParam(required = false,defaultValue = "10",value = "pageSize") Integer pageSize, Model model, Store store) {
        if (pageNum <= 0 || pageNum.equals("") || pageNum == null) {
            pageNum = 1;
        }
        if (pageSize <= 0 || pageSize.equals("") || pageSize == null) {
            pageSize = 10;
        }
        PageHelper.startPage(pageNum, pageSize);
        QueryWrapper<Store> qw=new QueryWrapper<>();
        if (store.getStoreName()!=null){
            qw.like("store_name",store.getStoreName());
        }

        List<Store> list = storeService.list(qw);
        PageInfo<Store> objectPageInfo = new PageInfo<>(list);
        model.addAttribute("pageInfo", objectPageInfo);
        return "store-list";
    }

    @RequestMapping("preSaveStore")
    public String preSaveStore(){
        return "store-save";
    }

    @RequestMapping("saveStore")
    public String saveStre(Store store){
        boolean save = storeService.save(store);
        return "redirect:/store/listStore";
    }

    @RequestMapping("preUpdateStore/{id}")
    public String preUpdateStore(@PathVariable Integer id,Model model){
        Store byId = storeService.getById(id);
        model.addAttribute("store",byId);
        return "store-update";
    }

    @RequestMapping("updateStore")
    public String updateStore(Store store){
        boolean b = storeService.updateById(store);
        return "redirect:/store/listStore";
    }

    @RequestMapping("delStore/{id}")
    public String delStore(@PathVariable Integer id){
        boolean b = storeService.removeById(id);
        return "redirect:/store/listStore";
    }

    //批量删除
    @PostMapping("batchDeleteStore")
    @ResponseBody
    public String batchDeleteType(String idList){
        String[]split= StrUtil.split(idList,",");
        List<Integer>list=new ArrayList<>();

        for (String s : split) {
            if (!s.isEmpty()){
                int i = Integer.parseInt(s);
                list.add(i);
            }
        }
        boolean b = storeService.removeByIds(list);
        if (b){
            return "OK";
        }else {
            return "error";
        }
    }

    @RequestMapping("storeList/{storeName}")
    public String storeList(@PathVariable String storeName,
                            @RequestParam(required = false,defaultValue = "1",value = "pageNum") Integer pageNum,
                            @RequestParam(required = false,defaultValue = "10",value = "pageSize") Integer pageSize, HttpSession session, Model model){
        if (pageNum <= 0 || pageNum.equals("") || pageNum == null) {
            pageNum = 1;
        }
        if (pageSize <= 0 || pageSize.equals("") || pageSize == null) {
            pageSize = 10;
        }
        PageHelper.startPage(pageNum, pageSize);
        QueryWrapper<Food>qw=new QueryWrapper<>();
        qw.like("store",storeName);
        List<Food> list = foodService.list(qw);
        PageInfo<Food>pageInfo=new PageInfo<>(list);
        session.setAttribute("storeName",storeName);
        model.addAttribute("pageInfo", pageInfo);
        return "store-food-list";
    }


}
