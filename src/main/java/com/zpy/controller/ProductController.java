package com.zpy.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zpy.pojo.Product;
import com.zpy.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("product")
public class ProductController {
    @Autowired
    private ProductService productService;
    @Value("${location}")
    private String location;

    //查询所有商品
    @RequestMapping("listProduct")
    public String listProduct(@RequestParam(required = false,defaultValue = "1",value = "pageNum") Integer pageNum,
                              @RequestParam(required = false,defaultValue = "10",value = "pageSize") Integer pageSize, Model model, Product product){
        if (pageNum <= 0 || pageNum.equals("") || pageNum == null) {
            pageNum = 1;
        }
        if (pageSize <= 0 || pageSize.equals("") || pageSize == null) {
            pageSize = 10;
        }
        PageHelper.startPage(pageNum, pageSize);
        QueryWrapper<Product> qw=new QueryWrapper<>();
        if (product.getProductName()!=null){
            qw.like("product_name",product.getProductName());
        }
        if (product.getType()!=null){
            qw.eq("type",product.getType());
        }
        QueryWrapper<Product>qw1=new QueryWrapper<>();
        qw1.groupBy("type");
        List<Product> typeList = productService.list(qw1);
        model.addAttribute("typeList",typeList);
        List<Product> list = productService.list(qw);
        PageInfo<Product>pageInfo=new PageInfo<>(list);
        model.addAttribute("pageInfo",pageInfo);
        return "product-list";
    }

    //跳转到添加商品的页面
    @RequestMapping("preSaveProduct")
    public String preSaveProduct(){
        return "product-save";
    }

    //添加商品
    @RequestMapping("saveProduct")
    public String saveBook(Product product, MultipartFile file){
        transFile(product,file);
        boolean save = productService.save(product);
        return "redirect:/product/listProduct";
    }

    //上传图片
    private void transFile(Product product, MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        int index = originalFilename.lastIndexOf(".");
        String suffix = originalFilename.substring(index);
        String prefix =System.nanoTime()+"";
        String path=prefix+suffix;
        File file1 = new File(location);
        if (!file1.exists()){
            file1.mkdirs();
        }
        File file2 = new File(file1,path);
        try {
            file.transferTo(file2);
        } catch (IOException e) {
            e.printStackTrace();
        }
        product.setPimage(path);
    }


    //根据id获取商品
    @RequestMapping("preUpdateProduct/{id}")
    public String preUpdateBook(@PathVariable("id")Integer id,Model model){
        Product byId = productService.getById(id);
        model.addAttribute("product",byId);
        return "product-update";
    }

    //修改商品
    @RequestMapping("updateProduct")
    public String updateBook(Product product){
        boolean save = productService.updateById(product);
        return "redirect:/product/listProduct";

    }

    //删除商品
    @RequestMapping("delProduct/{id}")
    public String delBook(@PathVariable("id") Integer id){
        boolean b = productService.removeById(id);
        return "redirect:/product/listProduct";
    }

    //批量删除
    @PostMapping("batchDeleteProduct")
    @ResponseBody
    public String batchDeleteBook(String idList){
        String[]split= StrUtil.split(idList,",");
        List<Integer>list=new ArrayList<>();

        for (String s : split) {
            if (!s.isEmpty()){
                int i = Integer.parseInt(s);
                list.add(i);
            }
        }
        boolean b = productService.removeByIds(list);
        if (b){
            return "OK";
        }else {
            return "error";
        }
    }

    @RequestMapping("shangjia/{id}")
    public String shangjia(@PathVariable Integer id){
        Product byId = productService.getById(id);
        byId.setStatus(1);
        boolean b = productService.updateById(byId);
        return "redirect:/product/listProduct";
    }

    @RequestMapping("xiajia/{id}")
    public String xiajia(@PathVariable Integer id){
        Product byId = productService.getById(id);
        byId.setStatus(2);
        boolean b = productService.updateById(byId);
        return "redirect:/product/listProduct";
    }
}
