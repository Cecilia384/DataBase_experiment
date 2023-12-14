package com.zpy.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageInfo;
import com.zpy.mapper.ProductMapper;
import com.zpy.pojo.CountNumber;
import com.zpy.pojo.Product;
import com.zpy.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper , Product> implements ProductService {
    @Autowired
    private ProductMapper productMapper;


    @Override
    public List<CountNumber> queryNum() {
        return productMapper.queryNum();
    }

    @Override
    public List<CountNumber> queryTotal() {
        return productMapper.queryTotal();
    }
}
