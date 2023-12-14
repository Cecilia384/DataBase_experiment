package com.zpy.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("food")
public class Food {
    @TableId
    private Integer id;
    private String foodName;
    private String store;
    private Double price;
    private Integer stock;
    private String descr;
    private String fimage;
    private Integer status;

}
