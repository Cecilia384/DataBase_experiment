package com.zpy.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Data
@TableName("customer")
public class Customer {
    @TableId
    private Integer id;
    private String customerName;
    private String email;
    private String phone;
    private String address;
    private String cimage;
    private String password;
}
