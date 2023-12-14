package com.zpy.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("product")
public class Product {
    @TableId
    private Integer id;
    private String productName;
    private Double price;
    private Integer stock;
    private String type;
    private String description;
    private String pimage;
    private Integer status;
}
