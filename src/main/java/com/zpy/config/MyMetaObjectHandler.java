package com.zpy.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

@Slf4j
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        //这是老方法 官网有新方法但是不能用 不知道为啥
        this.setFieldValByName("orderTime",new Date(),metaObject);
        /*自动填充不仅仅可以填充时间 其他自定义字段都可以*/
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("returnTime",new Date(),metaObject);
    }
}
