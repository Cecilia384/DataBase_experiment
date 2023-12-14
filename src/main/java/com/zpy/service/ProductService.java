package com.zpy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.zpy.pojo.CountNumber;
import com.zpy.pojo.Product;

import java.util.List;

public interface ProductService extends IService<Product> {


    List<CountNumber> queryNum();

    List<CountNumber> queryTotal();
}
